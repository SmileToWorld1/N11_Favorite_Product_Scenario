Feature: N11 favori ürün senaryosu

  @wip @UI
  Scenario: Favori listesine eklenen ürünün silinmesi
    Given www.n11.com sitesi açılır
    And "Ana sayfa" kontrol edilir
    When Siteye login olunur
    And "login işlemi" kontrol edilir
    Then "Iphone" kelimesi aranır
    And "ürün" kontrol edilir
    Then Arama sonuçları sayfasından 2. sayfa açılır
    And "açılan pencere" kontrol edilir
    Then Sayfadaki 3. ürün favorilere eklenir
    Then Hesabım sekmesinden Favorilerim - Listelerim sayfasına gidilir
    And "favorilerim sayfası" kontrol edilir
    Then Eklenen ürün favorilerden silinir
    And "silme işlemi" kontrol edilir
    And Ürün silindi uyarısına tıklanır ve watchList'in görünür olması beklenir
    Then Üye çıkış işlemi yapılır









