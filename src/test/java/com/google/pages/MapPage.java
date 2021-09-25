package com.google.pages;

import com.frameworkium.core.htmlelements.element.TextInput;
import com.frameworkium.core.ui.annotations.Visible;
import com.frameworkium.core.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;


public class MapPage extends BasePage<MapPage> {

    @Visible
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
