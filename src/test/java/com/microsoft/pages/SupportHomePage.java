package com.microsoft.pages;

import com.frameworkium.ui.annotations.Visible;
import com.frameworkium.ui.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SupportHomePage extends BasePage<SupportHomePage> {


    @Visible
    @FindBy(id = "uhf-c-nav")
    private WebElement navbar;

    @Visible
    @FindBy(id = "shellmenu_29")
    private WebElement templatesTab;

    public static SupportHomePage open(){
        return new SupportHomePage().get("https://support.office.com/");
    }

    public TemplatesPage clickTemplatesTab(){
        templatesTab.click();
        return new TemplatesPage().get();
    }





}
