package com.mikrogrup.pages;

import com.mikrogrup.utilities.BrowserUtils;
import com.mikrogrup.utilities.ConfigurationReader;
import com.mikrogrup.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class N11_FacebookPage {

    public N11_FacebookPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "email")
    private WebElement inputEmail;

    @FindBy(id = "pass")
    private WebElement inputPassword;

    @FindBy(xpath = "//input[@value=\"Log In\"]")
    private WebElement loginButton;

    public void loginWithFacebook(){
        BrowserUtils.waitNumberOfWindowsToBe(2);
        inputEmail.sendKeys(ConfigurationReader.getProperty("username"));
        inputPassword.sendKeys(ConfigurationReader.getProperty("password"));
        BrowserUtils.waitForClickAbility(loginButton,10).click();
    }

}
