package com.google.pages;

import com.frameworkium.core.ui.annotations.Visible;
import com.frameworkium.core.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class HomePage extends BasePage<HomePage> {

    @Visible
    @Name("Search Input Box")
    @FindBy(name = "q")
    private TextInput searchInputBox;

    @Step("Navigate to the homepage")
    public static HomePage open() {
        return new HomePage().get("https://www.google.co.uk");
    }

    @Step("Search for {0}")
    public void runSearch(String searchTerms) {
        searchInputBox.sendKeys(searchTerms);
        searchInputBox.submit();
    }
}
