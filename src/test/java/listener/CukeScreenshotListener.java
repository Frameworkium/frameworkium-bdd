package listener;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.frameworkium.core.ui.UITestLifecycle.get;

public class CukeScreenshotListener implements ConcurrentEventListener {
    private final AllureLifecycle allureLifecycle;

    public CukeScreenshotListener() {
        this.allureLifecycle = Allure.getLifecycle();
    }

    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepFinished.class, this::handleTestStepFinished);
    }

    private void handleTestStepFinished(final TestStepFinished event) {

        if (event.getResult().getStatus() == Status.FAILED) {
            try {
                byte[] screenshot = ((TakesScreenshot) get().getWebDriver())
                        .getScreenshotAs(OutputType.BYTES);
                byte[] source = get().getWebDriver().getPageSource().getBytes();
                allureLifecycle.addAttachment("Screenshot on failure", "image/png", "png", screenshot);
                allureLifecycle.addAttachment("Source on failure", "text/html", "html", source);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
