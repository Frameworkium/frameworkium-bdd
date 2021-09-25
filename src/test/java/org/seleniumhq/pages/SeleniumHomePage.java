package org.seleniumhq.pages;

import com.frameworkium.core.htmlelements.element.Link;
import com.frameworkium.core.ui.pages.BasePage;
import com.frameworkium.core.ui.pages.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SeleniumHomePage extends BasePage<SeleniumHomePage> {

    @FindBy(css = ".navbar [href='/downloads']")
    private Link downloadLink;

    @FindBy(css = "button[data-target='#main_navbar']")
    private WebElement menuLink;

    public static void open() {
        PageFactory.newInstance(
                SeleniumHomePage.class,
                "https://www.seleniumhq.org");
    }

    public void gotoDownloadsPage() {
        if (menuLink.isDisplayed()) {
            menuLink.click();
        }
        downloadLink.click();
    }
}
