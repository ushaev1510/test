package ru.xcart;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.xcart.pom.SearchPage;

import static ru.xcart.steps.CommonSteps.openSearchPage;

public class TestSearch extends BaseTest{

    private String url = "https://demostore.x-cart.com";
    private String SEARCH_TEXT = "apple";
    private String CATEGORY = "Healthy Food & Snacks";

    @Test
    @Description("Search test-case 1")
    public void test1() {
        SearchPage searchPage = openSearchPage(url)
                .fillSeatchField(SEARCH_TEXT);

        Assert.assertEquals(3,
                searchPage.getFastSearchElement().getCountProducts(),
                "Количество продуктов не соответствует ожидаемому");

        searchPage
                .getFastSearchElement()
                .checkWhatAllProductsContainsText(SEARCH_TEXT)
                .moveMouseOnProductWithNumber(2)
                .checkNameViewProductAfterMoveMouse(2);
    }

    @Test
    @Description("Search test-case 2")
    public void test2() {
        SearchPage searchPage = openSearchPage(url)
                .fillSeatchField(SEARCH_TEXT);

        Assert.assertEquals(3,
                searchPage.getFastSearchElement().getCountProducts(),
                "Количество продуктов не соответствует ожидаемому");

        String name = searchPage
                .getFastSearchElement()
                .checkWhatAllProductsContainsText(SEARCH_TEXT)
                .moveMouseOnProductWithNumber(2)
                .checkNameViewProductAfterMoveMouse(2)
                .getProductName(2);

        searchPage
                .getFastSearchElement()
                .openProductInSearchDetails()
                .checkProductName(name);
    }

    @Test
    @Description("Search test-case 3")
    public void test3() {
        SearchPage searchPage = openSearchPage(url)
                .fillSeatchField(SEARCH_TEXT);

        Assert.assertEquals(3,
                searchPage.getFastSearchElement().getCountProducts(),
                "Количество продуктов не соответствует ожидаемому");

        searchPage
                .getFastSearchElement()
                .checkWhatAllProductsContainsText(SEARCH_TEXT)
                .goTolinkPowered()
                .checkPageUrl();
    }


    @Test
    @Description("Search test-case 4")
    public void test4() {
        SearchPage searchPage = openSearchPage(url)
                .fillSeatchField(SEARCH_TEXT)
                .clickSearchButton();

        Assert.assertEquals(3,
                searchPage.getCountFindProducts(),
                "Количество продуктов не соответствует ожидаемому");
    }

    @Test
    @Description("Search test-case 5")
    public void test5() {
        SEARCH_TEXT = "Made with freshly";
        SearchPage searchPage = openSearchPage(url)
                .openAdvancedSearch()
                .selectByDescriptionInOptionsSearch()
                .fillSeatchField(SEARCH_TEXT)
                .clickSearchButton();

        Assert.assertEquals(1,
                searchPage.getCountFindProducts(),
                "Количество продуктов не соответствует ожидаемому");
    }

    @Test
    @Description("Search test-case 6")
    public void test6() {
        SEARCH_TEXT = "qqqqqqqqq";
        SearchPage searchPage = openSearchPage(url)
                .fillSeatchField(SEARCH_TEXT)
                .clickSearchButton();

        Assert.assertEquals(0,
                searchPage.getCountFindProducts(),
                "Количество продуктов не соответствует ожидаемому");
    }

    @Test
    @Description("Search test-case 7")
    public void test7() {
        SEARCH_TEXT = "Made with freshly";
        SearchPage searchPage = openSearchPage(url)
                .openAdvancedSearch()
                .selectCategoryInOptionsSearch(CATEGORY)
                .fillSeatchField(SEARCH_TEXT)
                .clickSearchButton();

        Assert.assertEquals(1,
                searchPage.getCountFindProducts(),
                "Количество продуктов не соответствует ожидаемому");
    }
}
