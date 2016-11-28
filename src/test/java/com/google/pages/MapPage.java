package com.google.pages;

import com.frameworkium.core.ui.annotations.Visible;
import com.frameworkium.core.ui.pages.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class MapPage extends BasePage<MapPage> {

    @Visible
    @Name("Search Box")
    @FindBy(css = "input#searchboxinput")
    private TextInput searchBox;

    @Step("Open maps page")
    public static MapPage open() {
        return new MapPage().get("https://maps.google.co.uk");
    }

    @Step("Search for location {0}")
    public void search(String location) {
        searchBox.sendKeys(location + Keys.ENTER);
    }
}
