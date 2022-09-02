Feature: N11 favori ürün senaryosu

  @wip @UI
  Scenario: Favori listesine eklenen ürünün silinmesi
    Given Kullanıcı n11 giriş sayfasındadır
    When Siteye giriş işlemi yapılır
    Then "Iphone" kelimesi arama çubuğunda aratılır
    And Arama sonuçları sayfasından 2 inci sayfa açılır
    Then Sayfadaki 3 üncü ürün favorilere eklenir
    And Hesabım sekmesinden Favorilerim - Listelerim sayfasına gidilir
    Then Eklenen ürün favorilerden silinir
    And Üye çıkış işlemi yapılır
