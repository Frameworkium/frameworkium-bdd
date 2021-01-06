package com.google.pages;

import com.frameworkium.core.ui.annotations.Visible;
import com.frameworkium.core.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

public class MapInfoPane extends BasePage<MapInfoPane> {

    @Visible
    @Name("Place information header")
    @FindBy(css = "#pane .section-hero-header-title")
    private WebElement infoHeader;

    @Name("Place information address")
    @FindBy(css = "#pane .widget-pane-content [data-item-id='address']")
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
