package com.google.pages;

import com.frameworkium.core.ui.annotations.Visible;
import com.frameworkium.core.ui.pages.BasePage;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class HomePage extends BasePage<HomePage> {

    @Visible
    @Name("Apps link")
    @FindBy(css = "a[title='Google apps']")
    private Link googleApps;

    @Name("Map link")
    @FindBy(id = "gb8")
    private Link googleMapsLink;

    @Visible
    @Name("Search Input Box")
    @FindBy(css = "input#lst-ib")
    private TextInput searchInputBox;

    @Name("Search Button")
    //This is not initially visible
    @FindBy(css = "button[value='Search']")
    private Button runSearchButton;

    @Step("Navigate to the homepage")
    public static HomePage open() {
        return new HomePage().get("http://www.google.com");
    }

    @Step("Search for {0}")
    public ResultsPage runSearch(String searchTerms) {
        searchInputBox.sendKeys(searchTerms);
        runSearchButton.click();
        return new ResultsPage().get();
    }

    @Step("Open Maps Page")
    public MapPage openMaps() {
        googleApps.click();
        wait.until(ExpectedConditions.visibilityOf(googleMapsLink));
        googleMapsLink.click();
        return new MapPage();
    }
}
