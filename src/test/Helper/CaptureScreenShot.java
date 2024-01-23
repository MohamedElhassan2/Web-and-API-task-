package Helper;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CaptureScreenShot {

    // Method take screen shot when the test case is fail

    public static void CaptureScreenShot (WebDriver driver , String screenshotname)
    {
        // screen shot name will be as Test Case name
        Path destination = Paths.get("./ScreenShots" , screenshotname +".png");
        try {
            Files.createDirectories(destination.getParent());
            FileOutputStream out = new FileOutputStream(destination.toString());
            out.write(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
            out.close();

        } catch (IOException e) {
            System.out.println("Exception while taking screenshot" + e.getMessage());
        }
    }
}