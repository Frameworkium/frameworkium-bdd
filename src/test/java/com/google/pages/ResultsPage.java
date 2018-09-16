package com.google.pages;

import com.frameworkium.core.ui.annotations.Visible;
import com.frameworkium.core.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.TextBlock;

import java.util.List;
import java.util.stream.Stream;

public class ResultsPage extends BasePage<ResultsPage> {

    @Visible
    @Name("Search results stats")
    @FindBy(id = "resultStats")
    private TextBlock resultStats;

    @Name("Search result titles")
    @Visible(checkAtMost = 1)
    @FindBy(css = "#ires h3.r > a")
    private List<Link> resultTitles;

    @Step("Get search result titles")
    public Stream<String> getResultTitles() {
        return resultTitles
                .stream()
                .map(WebElement::getText);
    }

}
