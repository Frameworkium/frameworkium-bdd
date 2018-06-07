package com.google.pages;

import com.frameworkium.ui.annotations.Visible;
import com.frameworkium.ui.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
////import io.qameta.allure.Step;
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
    //@Visible doesn't seem to work
    @FindBy(css = "#ires h3.r > a")
    private List<Link> resultTitles;
    
    ////@Step("Get search result titles")
    public Stream<String> getResultTitles() {
        return resultTitles
                .stream()
                .map(WebElement::getText);
    }
    
}
