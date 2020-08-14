package BBhelpers;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

// Helper add-ons to get screenshot results when a test fails
public class GetScreenshot {
    public static void capture(WebDriver driver, String fileName) {
        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File file = takesScreenshot.getScreenshotAs((OutputType.FILE));
            FileUtils.copyFile(file, new File("./screenshots/"+fileName+".png"));
        } catch (IOException e) {
            System.out.println("IO Problem");
        }
    }
}
