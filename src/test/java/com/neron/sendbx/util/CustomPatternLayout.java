package com.neron.sendbx.util;

import org.apache.log4j.EnhancedPatternLayout;
import org.apache.log4j.Level;
import org.apache.log4j.MDC;
import org.apache.log4j.spi.LoggingEvent;

import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomPatternLayout extends EnhancedPatternLayout {
    private static final String CSI = "\u001b[";
    private static final String SPLIT_ITEMS = ",";
    private static final String SPLIT_VALUES = ":";
    private static final Pattern METAS = Pattern.compile(
            "%color(?:-([a-z]+|[0-9]{1,3};[0-9]{1,3};[0-9]{1,3}))?\\{(.*?)}"
    );
    private final ThreadLocal<String> threadId = ThreadLocal.withInitial( () -> {
        String hash = Long.toString( Thread.currentThread().hashCode() );
        MDC.put( "threadHash", hash );
        return hash;
    } );
    private final transient ConcurrentMap<String, String> colors =
            CustomPatternLayout.colorMap();
    private final transient ConcurrentMap<String, String> levels =
            CustomPatternLayout.levelMap();
    private transient String base;

    private static ConcurrentMap<String, String> colorMap() {
        final ConcurrentMap<String, String> map =
                new ConcurrentHashMap<>();
        map.put( "black", "30" );
        map.put( "blue", "34" );
        map.put( "cyan", "36" );
        map.put( "green", "32" );
        map.put( "magenta", "35" );
        map.put( "red", "31" );
        map.put( "yellow", "33" );
        map.put( "white", "37" );
        return map;
    }

    private static ConcurrentMap<String, String> levelMap() {
        final ConcurrentMap<String, String> map =
                new ConcurrentHashMap<>();
        map.put( Level.TRACE.toString(), "2;33" );
        map.put( Level.DEBUG.toString(), "2;37" );
        map.put( Level.INFO.toString(), "0;37" );
        map.put( Level.WARN.toString(), "0;33" );
        map.put( Level.ERROR.toString(), "0;31" );
        map.put( Level.FATAL.toString(), "0;35" );
        return map;
    }

    @Override
    public void setConversionPattern( final String pattern ) {
        this.base = pattern;
        final Matcher matcher = CustomPatternLayout.METAS.matcher( pattern );
        final StringBuffer buf = new StringBuffer( 0 );
        while ( matcher.find() ) {
            matcher.appendReplacement( buf, "" );
            buf.append( CustomPatternLayout.CSI )
                    .append( this.ansi( matcher.group( 1 ) ) )
                    .append( 'm' )
                    .append( matcher.group( 2 ) )
                    .append( CustomPatternLayout.CSI )
                    .append( 'm' );
        }
        matcher.appendTail( buf );
        super.setConversionPattern( buf.toString() );
    }

    public void setColors( final String cols ) {
        for ( final String item : cols.split( CustomPatternLayout.SPLIT_ITEMS ) ) {
            final String[] values = item.split( CustomPatternLayout.SPLIT_VALUES );
            this.colors.put( values[0], values[1] );
        }
        if ( this.base != null ) {
            this.setConversionPattern( this.base );
        }
    }

    public void setLevels( final String lev ) {
        for ( final String item : lev.split( CustomPatternLayout.SPLIT_ITEMS ) ) {
            final String[] values = item.split( CustomPatternLayout.SPLIT_VALUES );
            final String level = values[0].toUpperCase( Locale.ENGLISH );
            if ( Level.toLevel( level, null ) == null ) {
                throw new IllegalArgumentException(
                        String.format( Locale.ENGLISH, "Unknown level '%s'", level )
                );
            }
            this.levels.put( level, values[1] );
        }
    }

    @Override
    public String format( final LoggingEvent event ) {
        this.threadId.get();
        return super.format( event ).replace(
                String.format( "%s?m", CustomPatternLayout.CSI ),
                String.format(
                        "%s%sm",
                        CustomPatternLayout.CSI,
                        this.levels.get( event.getLevel().toString() )
                )
        );
    }

    private String ansi( final String meta ) {
        final String ansi;
        if ( meta == null ) {
            ansi = "?";
        } else if ( meta.matches( "[a-z]+" ) ) {
            ansi = this.colors.get( meta );
            if ( ansi == null ) {
                throw new IllegalArgumentException(
                        String.format( "unknown color '%s'", meta )
                );
            }
        } else {
            ansi = meta;
        }
        return ansi;
    }
}

