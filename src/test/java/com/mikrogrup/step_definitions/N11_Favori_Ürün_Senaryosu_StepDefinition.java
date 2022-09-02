package com.mikrogrup.step_definitions;

import com.mikrogrup.pages.N11_FacebookPage;
import com.mikrogrup.pages.N11_GirişYapPage;
import com.mikrogrup.pages.N11_HomePage;
import com.mikrogrup.pages.N11_IstekListemPage;
import com.mikrogrup.utilities.BrowserUtils;
import com.mikrogrup.utilities.ConfigurationReader;
import com.mikrogrup.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class N11_Favori_Ürün_Senaryosu_StepDefinition {
    N11_HomePage homePage = new N11_HomePage();
    N11_GirişYapPage girişYapPage = new N11_GirişYapPage();
    N11_FacebookPage facebookPage = new N11_FacebookPage();
    N11_IstekListemPage istekListemPage = new N11_IstekListemPage();
    String mainWindow;
    String expectedTitle;

    @Given("Kullanıcı n11 giriş sayfasındadır")
    public void kullanıcı_n11_giriş_sayfasındadır() {
        //-	www.n11.com sitesi açılır
        Driver.getDriver().get(ConfigurationReader.getProperty("n11.test.url"));

        // Tüm çerezler reddedilir
        homePage.tümünüReddetCookie.click();

        //-	Ana sayfanın açıldığı kontrol edilir.
        expectedTitle = "n11 - Hayat Sana Gelir";
        BrowserUtils.verifyTitle(expectedTitle);
    }

    @When("Siteye giriş işlemi yapılır")
    public void siteye_giriş_işlemi_yapılır() {
        homePage.girişYapButton.click();

        // autocomplete="off" => Bu attribute sebebiyle siteye facebook hesabı ile giriş yapılır
        girişYapPage.loginWithFacebookButton.click();

        mainWindow = Driver.getDriver().getWindowHandle();

        String expectedInURL = "https://www.facebook.com/login";
        BrowserUtils.switchToWindow(expectedInURL);
        facebookPage.loginWithFacebook();

        //Facebook sayfası kapanıncaya kadar beklenir
        BrowserUtils.waitNumberOfWindowsToBe(1);

        Driver.getDriver().switchTo().window(mainWindow);

        // Kullanıcı giriş işlemini tamamlanmadan çıkış yap butonu görünür olamaz.
        BrowserUtils.hover(homePage.hesabımButton);
        Assert.assertTrue(homePage.cıkışYapButton.isDisplayed());
    }

    @Then("{string} kelimesi arama çubuğunda aratılır")
    public void kelimesi_arama_çubuğunda_aratılır(String kelime) {
        homePage.aramaÇubuğu.sendKeys("Iphone" + Keys.ENTER);

        // Listelenen her ürünün içerisinde Iphone yazısının bulunması gerekmektedir
        for (WebElement herBirÜrün : homePage.arananÜrünler) {
            Assert.assertTrue(herBirÜrün.getText().toLowerCase().contains(kelime.toLowerCase()));
        }

    }

    @Then("Arama sonuçları sayfasından {int} inci sayfa açılır")
    public void arama_sonuçları_sayfasından_inci_sayfa_açılır(Integer sayfa) {
        homePage.aramaSonuçlarındanSeçiliSayfayaGit(sayfa);

        //-	2. sayfanın açıldığı kontrol edilir.
        expectedTitle = "Iphone - n11.com - Sayfa " +sayfa;
        BrowserUtils.verifyTitle(expectedTitle);
    }

    @Then("Sayfadaki {int} üncü ürün favorilere eklenir")
    public void sayfadaki_üncü_ürün_favorilere_eklenir(Integer ürünSırası) {
        homePage.seçiliÜrünüFavorilereEkle(ürünSırası);
    }

    @Then("Hesabım sekmesinden Favorilerim - Listelerim sayfasına gidilir")
    public void hesabım_sekmesinden_favorilerim_listelerim_sayfasına_gidilir() {
        BrowserUtils.hover(homePage.hesabımButton);
        homePage.favorilerim_Listelerim_Button.click();

        //	“Favorilerim” sayfası açıldığı kontrol edilir.
        expectedTitle = "İstek Listem - n11.com";
        BrowserUtils.verifyTitle(expectedTitle);
    }

    @Then("Eklenen ürün favorilerden silinir")
    public void eklenen_ürün_favorilerden_silinir() {
        istekListemPage.favorilerim.click();
        istekListemPage.ürünüSilButton.click();

        Assert.assertTrue(istekListemPage.ürünSilindiUyarıYazısı.isDisplayed());
        istekListemPage.ürünSilindiUyarıButton.click();

        // Favorilerde ekli olan tek ürün de silindiği için watchList'in (listede herhangi bir ürünün bulunmadığı bilgisi) görünür olması gerekmektedir
        BrowserUtils.waitForVisibility(istekListemPage.watchList,7);
    }

    @Then("Üye çıkış işlemi yapılır")
    public void üye_çıkış_işlemi_yapılır() {
        BrowserUtils.hover(homePage.hesabımButton);
        homePage.cıkışYapButton.click();
    }


}
