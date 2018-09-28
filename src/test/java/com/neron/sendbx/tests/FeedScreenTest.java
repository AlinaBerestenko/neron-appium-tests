package com.neron.sendbx.tests;

import com.neron.sendbx.screens.FeedScreen;
import com.neron.sendbx.util.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.assertEquals;

public class FeedScreenTest extends BaseTest{

    Application app = new Application();
    private int x;
    private int y;
    boolean status = true;

    Logger logger = LoggerFactory.getLogger(FeedScreen.class);

    @BeforeMethod(alwaysRun = true)
    public void logBeforeMethod(Method m, Object[] p){
        logger.info("test start " + m.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void logAfterMethod(Method m, Object[] p){
        logger.info("test finished " + m.getName());
    }


    @BeforeMethod(groups = "test")
    public void loginToFeed(){
        try{
        if (getActivity().equals("io.themind.neuron.ui.feed.FeedActivity")){
            System.out.println("logged");
        }
        else login();}
        catch (Exception elementException){
            System.out.println(getActivity());
        }
    }


    @Test(groups = "test")
    public void moveToTheMindButton()  {
        if(status == true){
        theMind.feedScreen().closeTutorialIfItPresent();}
       int[] coordinates = theMind.feedScreen().coordinatesToMoveToTheMindButton();
       x = coordinates[0];
       y = coordinates[1];
       //moveButton(theMind.feedScreen().getAskButton(), x, y);
       assertEquals(getActivity(), "io.themind.neuron.ui.mind.MindActivity");
    }

    @Test(groups = "test")
    public void moveToTheAskButton() throws InterruptedException {
        if(status == true){
           status = theMind.feedScreen().closeTutorialIfItPresent();
        }
        int[] coordinates = theMind.feedScreen().coordinatesToMoveToAskButton();
        x = coordinates[0];
        y = coordinates[1];
        //moveButton(theMind.feedScreen().getAskButton(), x, y);
        assertEquals(getActivity(), "io.themind.neuron.ui.ask.AskActivity");
        Thread.sleep(500);
        pressBack();
    }

    @AfterMethod(groups ="test", alwaysRun = true)
    public void backToFeedScreen(){
        pressBack();
    }

    @AfterClass(alwaysRun = true)
    public void logoutFromTheFeed(){
        System.out.println("logout");
        if (status == true){status = theMind.feedScreen().closeTutorialIfItPresent();}
        logout();
    }


}
