package com.neron.sendbx.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;

public class FeedScreen extends AbstractScreen {

    public FeedScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout[3]")
    private MobileElement askButton;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout/android.widget.FrameLayout[2]")
    private MobileElement bubblesSpace;

    public MobileElement getBubblesSpace() {
        return bubblesSpace;
    }

    public MobileElement getAskButton() {
        return askButton;
    }

    public void clickAskButton() {
        askButton.click();
    }

    public boolean askButtonDisplayed() {
        return askButton.isDisplayed();
    }

    public int[] getBubblesSpaceCoordinates() {
        int[] coordinates = new int[2];
        coordinates[0] = bubblesSpace.getCoordinates().onPage().getX();
        coordinates[1] = bubblesSpace.getCoordinates().onPage().getY();
        return coordinates;
    }

    public int[] getButtonCoordinates() {
        int[] coordinates = new int[2];
        coordinates[0] = askButton.getCoordinates().onPage().getX();
        coordinates[1] = askButton.getCoordinates().onPage().getY();
        return coordinates;
    }

    public int[]  coordinatesToMoveToTheMindButton() {
        int[] coordinates = getButtonCoordinates();
        int x = coordinates[0] - coordinates[0] + 50;
        coordinates[0]  = x;
        return coordinates;
    }

    public int[] coordinatesToMoveToAskButton(){
        int[] coordinates = getButtonCoordinates();
        int x = coordinates[0] + coordinates[0];
        coordinates[0]  = x;
        return coordinates;

    }

    public boolean closeTutorialIfItPresent(){
            try{
                driver.findElement(By.xpath("//android.widget.FrameLayout[3]/android.widget.LinearLayout"))
                        .click();
                }
                catch (org.openqa.selenium.NoSuchElementException ex){
                    System.out.println(ex);
                }
        boolean status = false;
        return status;
    }

}
