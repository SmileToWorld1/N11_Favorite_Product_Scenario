package com.mikrogrup.pages;

import com.mikrogrup.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class N11_GirişYapPage {

    public N11_GirişYapPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//div[@class=\"facebook_large medium facebookBtn  btnLogin\"]")
    public WebElement loginWithFacebookButton;


}
