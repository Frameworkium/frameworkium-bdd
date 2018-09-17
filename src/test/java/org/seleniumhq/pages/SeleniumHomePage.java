package org.seleniumhq.pages;

import com.frameworkium.core.ui.pages.BasePage;
import com.frameworkium.core.ui.pages.PageFactory;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Link;

public class SeleniumHomePage extends BasePage<SeleniumHomePage> {

    @FindBy(css = "#menu_download > a")
    private Link downloadLink;

    public static void open() {
        PageFactory.newInstance(
                SeleniumHomePage.class,
                "https://www.seleniumhq.org");
    }

    public void gotoDownloadsPage() {
        downloadLink.click();
    }
}
