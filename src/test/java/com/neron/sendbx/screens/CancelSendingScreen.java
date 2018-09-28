package com.neron.sendbx.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CancelSendingScreen extends AbstractScreen{

    public CancelSendingScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "io.themind.neuron.internal:id/launchpadView")
    private MobileElement cancelationButton;

    @AndroidFindBy(id = "io.themind.neuron.internal:id/previewTex")
    private MobileElement questionText;

    @AndroidFindBy(id = "io.themind.neuron.internal:id/launchpadText")
    private MobileElement successfulSendText;



    public FeedScreen clickCancelButton(){
        cancelationButton.click();
        return new FeedScreen(driver);
    }

    public MobileElement getCancelationButton() {
        return cancelationButton;
    }

    public MobileElement getSuccessfulSendText() {
        return successfulSendText;
    }

    public MobileElement getQuestionText() {
        return questionText;
    }

    public boolean isQuestionTextIsDisplayed(){
        return questionText.isDisplayed();
    }

    public String getTextOfQuestionText(){
        return questionText.getText();
    }

    public String getTextOfSuccessfulSendText(){
        return successfulSendText.getText();
    }

    public FeedScreen goToFeedScreen() {
        return new FeedScreen(driver);
    }
}
