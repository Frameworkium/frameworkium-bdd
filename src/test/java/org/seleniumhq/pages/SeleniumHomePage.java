package org.seleniumhq.pages;

import com.common.Config;
import com.frameworkium.core.htmlelements.element.Link;
import com.frameworkium.core.ui.UITestLifecycle;
import com.frameworkium.core.ui.pages.BasePage;
import com.frameworkium.core.ui.pages.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SeleniumHomePage extends BasePage<SeleniumHomePage> {

    @FindBy(css = ".navbar [href='/downloads']")
    private Link downloadLink;

    @FindBy(css = "button[data-target='#main_navbar']")
    private WebElement menuLink;

    public static void open() {
        PageFactory.newInstance(
                SeleniumHomePage.class, Config.getBaseURL());
        System.out.println(System.getProperty("qa.baseURL"));
    }

    public void gotoDownloadsPage() {
        if (menuLink.isDisplayed()) {
            menuLink.click();
        }
        UITestLifecycle.get().getWait().until(ExpectedConditions.elementToBeClickable(downloadLink));
        downloadLink.click();
    }
}
