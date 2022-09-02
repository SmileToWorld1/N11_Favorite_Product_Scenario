package com.mikrogrup.pages;

import com.mikrogrup.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class N11_IstekListemPage {

    public N11_IstekListemPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//a[@href=\"https://www.n11.com/hesabim/favorilerim\"]")
    public WebElement favorilerim;

    @FindBy(xpath = "//span[@class=\"deleteProFromFavorites\"]")
    public WebElement ürünüSilButton;

    @FindBy(xpath = "//span[.='Ürününüz listeden silindi.']")
    public WebElement ürünSilindiUyarıYazısı;

    @FindBy(xpath = "//span[.='Tamam']")
    public WebElement ürünSilindiUyarıButton;

    @FindBy(xpath = "//div[@id=\"watchList\"]")
    public WebElement watchList;


}
