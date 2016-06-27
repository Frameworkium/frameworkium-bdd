package com.google.pages.web;

import java.util.List;
import java.util.stream.Stream;

import com.frameworkium.core.ui.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Link;

public class ResultsPage extends BasePage<ResultsPage> {

    @Name("Search result titles")
    //@Visible doesn't seem to work
    @FindBy(css = "#ires h3.r > a")
    private List<Link> resultTitles;
    
    @Step("Get search result titles")
    public Stream<String> getResultTitles() {
        return resultTitles
                .stream()
                .map(WebElement::getText);
    }
    
}
