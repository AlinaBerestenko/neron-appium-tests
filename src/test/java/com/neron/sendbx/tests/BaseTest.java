package com.neron.sendbx.tests;


import com.neron.sendbx.util.TheMind;
import com.neron.sendbx.util.TheMindConfig;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.neron.sendbx.util.Application.readTranslationFile;
import static io.appium.java_client.android.AndroidKeyCode.*;
public class BaseTest extends TheMindConfig {

    String path = "src/test/resources/translation/phrases.csv";
    Map<String, String> translation = null;

    protected static AndroidDriver driver;

    protected TheMind theMind;

    //init Android device
    String pathToStageApp = "/Users/akorchkov/IdeaProjects/neron-appium-tests/app-internal-release.apk";

    @BeforeClass(alwaysRun = true)
    @Parameters({"androidVersion", "deviceName", "port", "deviceUDID"})
    public void SetUpAndroid(@Optional("6.0.1") String androidVersion, @Optional("Samsung") String deviceName, @Optional("4723") String port, @Optional("420008cbde87358d") String deviceUDID) throws MalformedURLException {
        final String URL_STRING = "http://127.0.0.1:"+4723+"/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, androidVersion);
        capabilities.setCapability(MobileCapabilityType.PLATFORM, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 30000);
        capabilities.setCapability(MobileCapabilityType.APP, pathToStageApp);

        driver = new AndroidDriver(new URL(URL_STRING), capabilities);
        theMind = new TheMind(driver);
    }

    //parsing data from file
    @BeforeClass(alwaysRun = true)
    public Map<String, String> getTranslationMap() {
        {
            try {
                translation = readTranslationFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return translation;

    }

    public void presKeyCode(int code) {
        driver.pressKeyCode(code);
    }

    public void hideKeyboard() {
        driver.hideKeyboard();
    }

    public String getActivity() {
        String activityName = driver.currentActivity();
        return activityName;
    }

    public void setNewActivity(String packageName, String activityName) {
        driver.startActivity(new Activity(packageName, activityName));
    }

    public void closeApp() {
        try {
            driver.closeApp();
        } catch (NoSuchSessionException noSuchSessionException) {
            System.out.println("A session is either terminated or not started");
        }
    }

    public void resetApp() {
        try {
            driver.resetApp();
        } catch (NoSuchSessionException noSuchSessionException) {
            System.out.println("A session is either terminated or not started");
        }

    }

    public void pressBack() {
        driver.pressKeyCode(AndroidKeyCode.BACK);
    }

    public void login() throws InterruptedException {
       theMind.authScreen().clickSignInButton();
        theMind.signInScreen().fillEmail(getTestUserEmail());
        theMind.signInScreen().fillPassword(getTestUserPassword());
        hideKeyboard();
        theMind.signInScreen().submitButtonClick();
    }


    //Create keyKode Map;
    Map<Integer, Integer> keyMap;

    public Map<Integer, Integer> getKeyMap() {
        keyMap = new HashMap<>();

        keyMap.put(0, KEYCODE_0);
        keyMap.put(1, KEYCODE_1);
        keyMap.put(2, KEYCODE_2);
        keyMap.put(3, KEYCODE_3);
        keyMap.put(4, KEYCODE_4);
        keyMap.put(5, KEYCODE_5);
        keyMap.put(6, KEYCODE_6);
        keyMap.put(7, KEYCODE_7);
        keyMap.put(8, KEYCODE_8);
        keyMap.put(9, KEYCODE_9);
        return keyMap;
    }


    //press the KeyCode on verification page
    public void pressCode(String code) {
        getKeyMap();
        //get code and parsing it from string to charArray
        char[] chars = code.toCharArray();

        //get char from Array and convert it to int
        int a = Character.getNumericValue(chars[0]);
        int b = Character.getNumericValue(chars[1]);
        int c = Character.getNumericValue(chars[2]);
        int d = Character.getNumericValue(chars[3]);
        int e = Character.getNumericValue(chars[4]);
        int f = Character.getNumericValue(chars[5]);

        //enter the verify code
        presKeyCode(keyMap.get(a));
        presKeyCode(keyMap.get(b));
        presKeyCode(keyMap.get(c));
        presKeyCode(keyMap.get(d));
        presKeyCode(keyMap.get(e));
        presKeyCode(keyMap.get(f));

    }


//Logout from the Feed screen
    public void logout() {
        try {
            if (getActivity().equals("io.themind.neuron.ui.feed.FeedActivity")) {
                theMind.theMindScreen().profileInfoButtonClick();
                theMind.theMindScreen().logout();
                }
                else {
                System.out.println("It is not an activity that I need");
            }
        } catch (Exception exption) {
            System.out.println(getActivity()+ exption );
        }
    }





}
