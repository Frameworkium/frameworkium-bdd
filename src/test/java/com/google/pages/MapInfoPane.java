package com.google.pages;

import com.frameworkium.ui.annotations.Visible;
import com.frameworkium.ui.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.qameta.allure.Step;
import ru.yandex.qatools.htmlelements.annotations.Name;

public class MapInfoPane extends BasePage<MapInfoPane> {

    @Visible
    @Name("Place information header")
    @FindBy(css = "#pane .section-hero-header-title")
    private WebElement infoHeader;

    @Name("Place information address")
    @FindBy(css = "#pane span.section-info-text span:nth-of-type(3)")
    private WebElement addressSpan;

    @Step
    public String getHeader() {
        return infoHeader.getText();
    }

    @Step
    public String getAddress() {
        return addressSpan.getText();
    }
}
