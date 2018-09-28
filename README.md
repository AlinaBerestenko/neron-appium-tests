# README #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###
This repository contains UI tests for Android for theMind project

### How do I get set up? ###

Basically you need to have appium installed along with appium-doctor. 
I won't go into a walkthrough of setting these up as there are plenty resources out there to guide you in setting up Appium to test apps on both platforms. 
See below for a some helpful links to help you get appium and appium-doctor installed.

http://appium.io/
http://appium.io/slate/en/tutorial/android.html
http://appium.io/slate/en/tutorial/ios.html
https://github.com/appium/appium-doctor
http://appium.io/slate/en/master/?java#
Those links should be enough to give you all the information you need about appium, 
appium-doctor and getting set up. 
Once you have everything set up remember to use appium doctor to confirm. 
If you have problem with instalation Appium  from console, go to the appium site and install appium desctop

At this point everything for Appium should be installed and in that process you should've installed Apache Maven. If not take a second to install Maven.

Here's a helpful link for doing that:

https://maven.apache.org/install.html


### Howe to run ###

run selenium grid in role hub
run appium node with configure file; rout to file ./src/test/resources/json
example commands for run hub and nods - .src/test/resources/json/start-commands.txt

Run tests
mvn clean test
or if you whant run profile suit  - mvn clean test -P {profile name}

You can generate a report using one of the following command:
mvn allure:serve
Report will be generated into temp folder. Web server with results will start.
mvn allure:report

