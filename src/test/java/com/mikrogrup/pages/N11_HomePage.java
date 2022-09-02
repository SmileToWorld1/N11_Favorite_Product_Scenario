package com.mikrogrup.pages;

import com.mikrogrup.utilities.BrowserUtils;
import com.mikrogrup.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class N11_HomePage {

    public N11_HomePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(partialLinkText = "Giriş Yap")
    public WebElement girişYapButton;

    @FindBy(xpath = "//span[.='Tümünü Reddet']")
    public WebElement tümünüReddetCookie;

    @FindBy(partialLinkText = "TT")
    public WebElement hesabımButton;

    @FindBy(partialLinkText = "Çıkış Yap")
    public WebElement cıkışYapButton;

    @FindBy(id = "searchData")
    public WebElement aramaÇubuğu;

    @FindBy(xpath = "//li[@class=\"column \"]//h3")
    public List<WebElement> arananÜrünler;

    @FindBy(partialLinkText = "Favorilerim / Listelerim")
    public WebElement favorilerim_Listelerim_Button;

    public void aramaSonuçlarındanSeçiliSayfayaGit(int sayfaNumarası){
        WebElement aramaSonuçlarıSayfası = Driver.getDriver().findElement(By.linkText("" + sayfaNumarası));
        BrowserUtils.hover(aramaSonuçlarıSayfası);
        BrowserUtils.highlight(aramaSonuçlarıSayfası);
        aramaSonuçlarıSayfası.click();
    }

    public void seçiliÜrünüFavorilereEkle(int ürünSırası){
        WebElement favorilereEklenecekUrun = Driver.getDriver().findElement(By.xpath("(//span[@title=\"Favorilere ekle\"])["+ürünSırası+"]"));
        favorilereEklenecekUrun.click();
    }





}
