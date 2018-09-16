package com.microsoft.pages;

import com.frameworkium.core.ui.annotations.Visible;
import com.frameworkium.core.ui.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TemplatesPage extends BasePage<TemplatesPage> {

    @Visible
    @FindBy(css = "h1.odcom-fabric-heading-1")
    private WebElement title;

    public String getTitle() {
        return title.getText();
    }
}
