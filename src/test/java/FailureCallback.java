import com.frameworkium.core.ui.tests.BaseTest;
import org.openqa.selenium.OutputType;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.cucumberjvm.callback.OnFailureCallback;

/**
 * Created by anoronha on 27/07/2017.
 */
public class FailureCallback implements OnFailureCallback {

    @Attachment(type = "image/png")
    public byte[] failureScreenshot() {
        System.out.println("Attaching screenshot in callback");
        return BaseTest.getDriver().getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public Object call() {
        failureScreenshot();
        return null;
    }
}
