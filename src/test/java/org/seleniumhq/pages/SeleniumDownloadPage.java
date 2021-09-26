package org.seleniumhq.pages;

import com.frameworkium.core.htmlelements.element.Link;
import com.frameworkium.core.ui.annotations.Visible;
import com.frameworkium.core.ui.pages.BasePage;
import org.openqa.selenium.support.FindBy;


public class SeleniumDownloadPage extends BasePage<SeleniumDownloadPage> {

    @Visible
    @FindBy(css = "body .td-main div:nth-child(3) > div:nth-child(2) a")
    private Link latestDownloadLink;

    public String getLatestVersion() {
        return latestDownloadLink.getText();
    }

}
