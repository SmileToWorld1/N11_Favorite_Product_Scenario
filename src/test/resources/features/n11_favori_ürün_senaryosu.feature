Feature: N11 favori ürün senaryosu

  @wip @UI
  Scenario: Favori listesine eklenen ürünün silinmesi
    Given www.n11.com sitesi açılır
    And Ana sayfanın açıldığı kontrol edilir
    When Siteye login olunur
    And Login işlemi kontrol edilir
    Then "Iphone" kelimesi aranır
    And "Iphone" kelimesi aratıldığı kontrol edilir
    Then Arama sonuçları sayfasından 2. sayfa açılır
    And 2. sayfanın açıldığı kontrol edilir
    Then Sayfadaki 3. ürün favorilere eklenir
    Then Hesabım sekmesinden Favorilerim - Listelerim sayfasına gidilir
    And Favorilerim sayfası açıldığı kontrol edilir
    Then Eklenen ürün favorilerden silinir
    And Silme işlemi kontrol edilir
    And Ürün silindi uyarısına tıklanır ve watchList'in görünür olması beklenir
    Then Üye çıkış işlemi yapılır