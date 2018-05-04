package com.microsoft.pages;

import com.frameworkium.ui.annotations.Visible;
import com.frameworkium.ui.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TemplatesPage extends BasePage<TemplatesPage> {

    @Visible
    @FindBy(className = "odcom-fabric-heading-1")
    private WebElement title;

    public String getTitle(){
        return title.getText();
    }
}
