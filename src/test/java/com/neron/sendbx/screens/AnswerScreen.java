package com.neron.sendbx.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AnswerScreen extends AbstractScreen{

    public AnswerScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.TextView[2]")
    private MobileElement answerButton;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.TextView[1]")
    private MobileElement questionText;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.ImageView[1]")
    private MobileElement flagButton;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.ImageView[2]")
    private MobileElement shareButton;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout/android.widget.ImageView")
    private MobileElement reactionButton;

    @AndroidFindBy(xpath = "///android.view.ViewGroup/android.widget.LinearLayout")
    private MobileElement tutorialTooltip;

    public MobileElement getAnswerButton() {
        return answerButton;
    }

    public MobileElement getQuestionText() {
        return questionText;
    }

    public MobileElement getFlagButton() {
        return flagButton;
    }

    public MobileElement getShareButton() {
        return shareButton;
    }

    public MobileElement getReactionButton() {
        return reactionButton;
    }

    public MobileElement getTutorialTooltip() {
        return tutorialTooltip;
    }

}
