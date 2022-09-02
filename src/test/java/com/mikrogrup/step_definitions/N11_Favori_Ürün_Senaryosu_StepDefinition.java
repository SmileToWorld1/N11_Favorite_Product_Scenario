package com.mikrogrup.step_definitions;

import com.mikrogrup.pages.N11_FacebookPage;
import com.mikrogrup.pages.N11_GirişYapPage;
import com.mikrogrup.pages.N11_HomePage;
import com.mikrogrup.pages.N11_IstekListemPage;
import com.mikrogrup.utilities.BrowserUtils;
import com.mikrogrup.utilities.ConfigurationReader;
import com.mikrogrup.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class N11_Favori_Ürün_Senaryosu_StepDefinition {

    N11_HomePage homePage = new N11_HomePage();
    N11_GirişYapPage girisYapPage = new N11_GirişYapPage();
    N11_FacebookPage facebookPage = new N11_FacebookPage();
    N11_IstekListemPage istekListemPage = new N11_IstekListemPage();
    String mainWindow;
    String expectedTitle;
    String product;


    @Given("www.n11.com sitesi açılır")
    public void www_n11_com_sitesi_açılır() {
        Driver.getDriver().get(ConfigurationReader.getProperty("n11.test.url"));
        // Tüm çerezler reddedilir
        homePage.tümünüReddetCookie.click();
    }

    @Given("Ana sayfanın açıldığı kontrol edilir")
    public void ana_sayfanın_açıldığı_kontrol_edilir() {
        expectedTitle = "n11 - Hayat Sana Gelir";
        BrowserUtils.verifyTitle(expectedTitle);
    }

    @When("Siteye login olunur")
    public void siteye_login_olunur() {
        homePage.girişYapButton.click();
        // autocomplete="off" => Bu attribute sebebiyle siteye facebook hesabı ile giriş yapılır
        girisYapPage.loginWithFacebookButton.click();
        mainWindow = Driver.getDriver().getWindowHandle();
        String expectedInURL = "https://www.facebook.com/login";
        BrowserUtils.switchToWindow(expectedInURL);
        facebookPage.loginWithFacebook();
        //Facebook sayfası kapanıncaya kadar beklenir
        BrowserUtils.waitNumberOfWindowsToBe(1);
        Driver.getDriver().switchTo().window(mainWindow);
    }

    @When("Login işlemi kontrol edilir")
    public void login_işlemi_kontrol_edilir() {
        // Kullanıcı giriş işlemini tamamladıktan sonra çıkış yap butonu görünür hale gelir.
        BrowserUtils.hover(homePage.hesabımButton);
        Assert.assertTrue(homePage.cıkışYapButton.isDisplayed());
    }

    @Then("{string} kelimesi aranır")
    public void kelimesi_aranır(String product) {
        //Daha sonra expected title içerisinde kullanılabilmesi için product global variable yapılır
        this.product = product;
        homePage.aramaÇubuğu.sendKeys(product + Keys.ENTER);
    }

    @Then("{string} kelimesi aratıldığı kontrol edilir")
    public void kelimesi_aratıldığı_kontrol_edilir(String product) {
        // Listelenen her ürünün içerisinde Iphone yazısının bulunması gerekmektedir
        for (WebElement eachProduct : homePage.arananÜrünler) {
            Assert.assertTrue(eachProduct.getText().toLowerCase().contains(product.toLowerCase()));
        }
    }

    @Then("Arama sonuçları sayfasından {int}. sayfa açılır")
    public void arama_sonuçları_sayfasından_sayfa_açılır(Integer sayfaNumarası) {
        homePage.aramaSonuçlarındanSeçiliSayfayaGit(sayfaNumarası);
    }

    @Then("{int}. sayfanın açıldığı kontrol edilir")
    public void sayfanın_açıldığı_kontrol_edilir(Integer sayfaNumarası) {
        expectedTitle = product +" - n11.com - Sayfa " +sayfaNumarası;
        BrowserUtils.verifyTitle(expectedTitle);
    }

    @Then("Sayfadaki {int}. ürün favorilere eklenir")
    public void sayfadaki_ürün_favorilere_eklenir(Integer ürünSırası) {
        homePage.seçiliÜrünüFavorilereEkle(ürünSırası);
    }

    @Then("Hesabım sekmesinden Favorilerim - Listelerim sayfasına gidilir")
    public void hesabım_sekmesinden_favorilerim_listelerim_sayfasına_gidilir() {
        BrowserUtils.hover(homePage.hesabımButton);
        homePage.favorilerim_Listelerim_Button.click();
    }
    @Then("Favorilerim sayfası açıldığı kontrol edilir")
    public void favorilerim_sayfası_açıldığı_kontrol_edilir() {
        expectedTitle = "İstek Listem - n11.com";
        BrowserUtils.verifyTitle(expectedTitle);
    }

    @Then("Eklenen ürün favorilerden silinir")
    public void eklenen_ürün_favorilerden_silinir() {
        istekListemPage.favorilerim.click();
        istekListemPage.ürünüSilButton.click();
    }

    @And("Silme işlemi kontrol edilir")
    public void silmeIşlemiKontrolEdilir() {
        Assert.assertTrue(istekListemPage.ürünSilindiUyarıYazısı.isDisplayed());
    }

    @And("Ürün silindi uyarısına tıklanır ve watchList'in görünür olması beklenir")
    public void ürünSilindiUyarısınaTıklanırVeWatchListinGörünürOlmasıBeklenir() {
        istekListemPage.ürünSilindiUyarıButton.click();
        BrowserUtils.waitForVisibility(istekListemPage.watchList,7);
    }

    @Then("Üye çıkış işlemi yapılır")
    public void üye_çıkış_işlemi_yapılır() {
        BrowserUtils.hover(homePage.hesabımButton);
        homePage.cıkışYapButton.click();
    }



}
