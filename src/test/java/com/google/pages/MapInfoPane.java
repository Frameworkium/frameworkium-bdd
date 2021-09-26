package com.google.pages;

import com.frameworkium.core.ui.annotations.Visible;
import com.frameworkium.core.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MapInfoPane extends BasePage<MapInfoPane> {

    @Visible
    @FindBy(css = "#pane h1")
    private WebElement infoHeader;

    @FindBy(css = "#pane [data-item-id='address']")
    private WebElement addressSpan;

    @Step
    public String getInfoHeaderText() {
        return infoHeader.getText();
    }

    @Step
    public String getAddress() {
        return addressSpan.getText();
    }
}
