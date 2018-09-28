package com.neron.sendbx.util;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {



    @DataProvider(name = "emailFieldCheckedData")
    public Iterator<Object[]> emailFieldCheckedData() throws IOException {
        return loadFieldCheckedData("src/test/resources/test_data/invalid_email_test_data.csv");
    }

    @DataProvider(name = "passwordFieldCheckedData")
    public Iterator<Object[]> passwordFieldCheckedData() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader( new FileReader(new File("src/test/resources/test_data/invalid_password_test_data.csv")));
        String line = reader.readLine();
        while (line != null){
            list.add(new Object[]{line});
            line = reader.readLine();
        }
        return list.iterator();
    }

    private Iterator<Object[]> loadFieldCheckedData(String filePath) throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader( new FileReader(new File(filePath)));
        String line = reader.readLine();
        while (line != null){
            String[] split =  line.split(";");
            list.add(new Object[]{split[0], split[1]});
            line = reader.readLine();
        }
        return list.iterator();
    }
}
