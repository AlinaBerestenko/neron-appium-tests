package com.neron.sendbx.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource( "classpath:theMind.properties" )
public class TheMindConfig {

    @Value( "${mind.testingDeviceName}" )
    private String testingDeviceName;

    @Value( "${mind.testingVersion}" )
    private String testingVersion;

    @Value( "${mind.testingMaxInstances}" )
    private String testingMaxInstances;

    @Value( "${mind.testingPlatform}" )
    private String testingPlatform;

    @Value( "${mind.pathToQAAndroidBuild}" )
    private String pathToQAAndroidBuild;

    @Value( "${mind.pathToSTAGEAndroidBuild}" )
    private String pathToSTAGEAndroidBuild;

    @Value( "${mind.testUserEmail}" )
    private String testUserEmail;

    @Value( "${mind.testUserPassword}" )
    private String testUserPassword;

    @Value( "${mind.invalidEmailExample}" )
    private String invalidEmailExample;

    @Value( "${mind.port}" )
    private String port;

    public String getTestingDeviceName(){
        return testingDeviceName;
    }
    public String getTestingVersion(){
        return testingVersion;
    }
    public String getTestingMaxInstances() {
        return testingMaxInstances;
    }
    public String getTestingPlatform() {
        return testingPlatform;
    }
    public String getPathToQAAndroidBuild() {
        return pathToQAAndroidBuild;
    }
    public String getPathToSTAGEAndroidBuild() {
        return pathToSTAGEAndroidBuild;
    }
    public String getTestUserEmail() {
        return testUserEmail;
    }
    public String getTestUserPassword() {
        return testUserPassword;
    }
    public String getInvalidEmailExample() {
        return invalidEmailExample;
    }
    public String getPort() {
        return port;
    }
}
