package com.mikrogrup.step_definitions;

/*
In this class we will be able to pass pre- & post- conditions to
each scenario and each step
 */

import com.mikrogrup.utilities.Driver;
import io.cucumber.java.*;
import org.junit.Ignore;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @AfterStep
    public void takeScreenShotAfterStep(Scenario scenario){
        byte [] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot,"image/png",scenario.getName());
    }

    @After
    public void teardownScenario(){
        Driver.closeDriver();
    }


}
