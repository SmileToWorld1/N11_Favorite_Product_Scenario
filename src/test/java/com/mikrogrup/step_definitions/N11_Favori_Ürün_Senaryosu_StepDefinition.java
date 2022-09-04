package com.mikrogrup.step_definitions;

// Bütün page ler import edildiği için yıldızlandı
import com.mikrogrup.pages.*;
import com.mikrogrup.pages.N11_IstekListemPage;
import com.mikrogrup.utilities.BrowserUtils;
import com.mikrogrup.utilities.ConfigurationReader;
import com.mikrogrup.utilities.Driver;
//Birden fazla kullanıldığı için yıldızlandı
import io.cucumber.java.en.*;
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
    Integer sayfaNumarası;


    @Given("www.n11.com sitesi açılır")
    public void www_n11_com_sitesi_açılır() {
        Driver.getDriver().get(ConfigurationReader.getProperty("n11.url"));
        // Tüm çerezler reddedilir
        BrowserUtils.waitForClickAbility(homePage.tümünüReddetCookie,10).click();
    }

    @When("Siteye login olunur")
    public void siteye_login_olunur() {
        BrowserUtils.waitForClickAbility(homePage.girişYapButton,10).click();
        // autocomplete="off" => Bu attribute sebebiyle siteye facebook hesabı ile giriş yapılır
        BrowserUtils.waitForClickAbility(girisYapPage.loginWithFacebookButton,10).click();
        mainWindow = Driver.getDriver().getWindowHandle();
        String expectedInURL = ConfigurationReader.getProperty("expected.facebook.url");
        BrowserUtils.switchToWindow(expectedInURL);
        facebookPage.loginWithFacebook();
        //Facebook sayfası kapanıncaya kadar beklenir
        BrowserUtils.waitNumberOfWindowsToBe(1);
        Driver.getDriver().switchTo().window(mainWindow);
    }

    @Then("{string} kelimesi aranır")
    public void kelimesi_aranır(String product) {
        //Daha sonra expected title içerisinde kullanılabilmesi için product global variable yapılır
        this.product = product;
        homePage.aramaÇubuğu.sendKeys(product + Keys.ENTER);
    }

    @Then("Arama sonuçları sayfasından {int}. sayfa açılır")
    public void arama_sonuçları_sayfasından_sayfa_açılır(Integer sayfaNumarası) {
        this.sayfaNumarası = sayfaNumarası;
        homePage.aramaSonuçlarındanSeçiliSayfayaGit(sayfaNumarası);
    }

    @Then("Sayfadaki {int}. ürün favorilere eklenir")
    public void sayfadaki_ürün_favorilere_eklenir(Integer ürünSırası) {
        homePage.seçiliÜrünüFavorilereEkle(ürünSırası);
    }

    @Then("Hesabım sekmesinden Favorilerim - Listelerim sayfasına gidilir")
    public void hesabım_sekmesinden_favorilerim_listelerim_sayfasına_gidilir() {
        BrowserUtils.hover(homePage.hesabımButton);
        BrowserUtils.waitForClickAbility(homePage.favorilerim_Listelerim_Button,10).click();
    }

    @Then("Eklenen ürün favorilerden silinir")
    public void eklenen_ürün_favorilerden_silinir() {
        BrowserUtils.waitForClickAbility(istekListemPage.favorilerim,10).click();
        BrowserUtils.waitForClickAbility(istekListemPage.ürünüSilButton,10).click();
    }

    @And("Ürün silindi uyarısına tıklanır ve watchList'in görünür olması beklenir")
    public void ürünSilindiUyarısınaTıklanırVeWatchListinGörünürOlmasıBeklenir() {
        BrowserUtils.waitForClickAbility(istekListemPage.ürünSilindiUyarıButton,10).click();
        BrowserUtils.waitForVisibility(istekListemPage.watchList,10);
    }

    @Then("Üye çıkış işlemi yapılır")
    public void üye_çıkış_işlemi_yapılır() {
        BrowserUtils.hover(homePage.hesabımButton);
        BrowserUtils.waitForClickAbility(homePage.cıkışYapButton,10).click();
    }

    @And("{string} kontrol edilir")
    public void kontrolEdilir(String işlem) {
        switch (işlem){
            case "Ana sayfa":
                expectedTitle = ConfigurationReader.getProperty("anasayfa");
                BrowserUtils.verifyTitle(expectedTitle);
                break;
            case "login işlemi":
                // Kullanıcı giriş işlemini tamamladıktan sonra çıkış yap butonu görünür hale gelir.
                BrowserUtils.hover(homePage.hesabımButton);
                Assert.assertTrue("Çıkış yap butonunu kontrol et!",homePage.cıkışYapButton.isDisplayed());
                break;
            case "ürün":
                for (WebElement eachProduct : homePage.arananÜrünler) {
                    Assert.assertTrue("Ürün adı sayfadaki her üründe yer almamaktadır!",eachProduct.getText().toLowerCase().contains(product.toLowerCase()));
                }
                break;
            case "açılan pencere":
                expectedTitle = product +" - n11.com - Sayfa " + sayfaNumarası;
                BrowserUtils.verifyTitle(expectedTitle);
                break;
            case "favorilerim sayfası":
                expectedTitle = "İstek Listem - n11.com";
                BrowserUtils.verifyTitle(expectedTitle);
                break;
            case "silme işlemi":
                Assert.assertTrue("Silme işlemi başarısız!",istekListemPage.ürünSilindiUyarıYazısı.isDisplayed());
                break;
        }

    }
}
