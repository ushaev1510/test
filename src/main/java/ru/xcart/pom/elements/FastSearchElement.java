package ru.xcart.pom.elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import ru.xcart.pom.CouldSearchPage;
import ru.xcart.pom.ProductPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class FastSearchElement {

    private SelenideElement rootElement = $(".instant-search-menu");
    private SelenideElement blockInfoProductInResults = $(".block-products dd.active");

    private ElementsCollection listProductsInResult = $$(".block-products dt");

    private By linkPoweredLocator = By.cssSelector(".powered-by");
    private By linkSeeDetailsLocator = By.cssSelector(".see-details .cs-product-link");
    private By nameLocator = By.cssSelector(".name");

    public FastSearchElement() {
        rootElement.shouldBe(visible);
    }

    @Step("Получить количество продуктов")
    public int getCountProducts() {
        return listProductsInResult.size();
    }

    @Step("Проверить что все продукты содержат текст: '{text}'")
    public FastSearchElement checkWhatAllProductsContainsText(String text) {
        listProductsInResult.forEach(
                s -> s.shouldBe(text(text)));
        return this;
    }

    @Step("Навести курсок на продукт с номером '{number}'")
    public FastSearchElement moveMouseOnProductWithNumber(int number) {
        new Actions(getWebDriver())
                .moveToElement(listProductsInResult.get( ++number ))
                .perform();
        return this;
    }

    @Step("Получить имя продукта с номером '{number}'")
    public String getProductName(int number) {
        return listProductsInResult.get( ++number ).getText();
    }

    @Step("Проверить что имя продукта с номером '{number}' совпадает с именем продукта в деталях")
    public FastSearchElement checkNameViewProductAfterMoveMouse(int number) {
        String name = getProductName(number);
        String actualName = blockInfoProductInResults.find(nameLocator).getText();
        Assert.assertEquals(name, actualName, "Имя не совпадает");
        return this;
    }

    @Step("Открыть продукт в деталях поиска")
    public ProductPage openProductInSearchDetails() {
        blockInfoProductInResults.find(linkSeeDetailsLocator).click();
        return new ProductPage();
    }

    @Step("Перейти по ссылке 'Search powered by CloudSearch'")
    public CouldSearchPage goTolinkPowered() {
        rootElement.find(linkPoweredLocator).click();
        return new CouldSearchPage();
    }
}
