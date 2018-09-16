package com.google.pages;

import com.frameworkium.core.ui.annotations.Visible;
import com.frameworkium.core.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class HomePage extends BasePage<HomePage> {

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
        searchInputBox.submit();
        return new ResultsPage().get();
    }
}
