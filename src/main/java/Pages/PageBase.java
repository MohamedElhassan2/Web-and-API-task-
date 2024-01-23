package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;


public class PageBase {
    public Wait<WebDriver> wait;
    protected WebDriver driver;
    public JavascriptExecutor js;
    // Create Contractor
    public PageBase(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        js =  (JavascriptExecutor) driver;
        this.wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
    }
    // sleep method
    public static void sleep(int seconds)
    {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // click method
    public static void ClickButton (WebElement button)
    {
        sleep(10);
        button.click();
    }
    // sendkey method
    public static void SetElementText (WebElement TextElement , String Value)
    {
        TextElement.clear();
        TextElement.sendKeys(Value);
    }

    public void ScrollDown()
    {
        js.executeScript("window.scrollBy(0,800)");
    }
}
