package org.seleniumhq.pages;

import com.frameworkium.core.ui.annotations.Visible;
import com.frameworkium.core.ui.pages.BasePage;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Link;

public class SeleniumDownloadPage extends BasePage<SeleniumDownloadPage> {

    @Visible
    @FindBy(css = "#mainContent > p:nth-child(5) > a")
    private Link latestDownloadLink;

    public String getLatestVersion() {
        return latestDownloadLink.getText();
    }

}
