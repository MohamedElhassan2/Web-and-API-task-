package Pages;

import org.openqa.selenium.NoSuchContextException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h3[text()='Thinking in HTML']")
    public WebElement Thinking_In_HTML_Card;
    public String ItemName = Thinking_In_HTML_Card.getText();

    @FindBy(xpath = "//h3[text()='Thinking in HTML']/../../a[2]")
    WebElement AddBasketBtnForThinkingInHTMLCard;

    @FindBy(className = "wpmenucart-icon-shopping-cart-0")
    WebElement ShoppingCartICon;

    @FindBy(xpath = "//h3[text()='Thinking in HTML']/..//span[@class='price']//ins//span[@class='woocommerce-Price-amount amount']")
    WebElement PriceAfterDiscount;

    @FindBy(id = "dismiss-button")
    WebElement dismissBtn;

    public void ClickOnAddBasketBtnForThinkingInHTMLCard() {
        ClickButton(AddBasketBtnForThinkingInHTMLCard);
    }

    public void ClickOnShoppingCartIcon() {
        ClickButton(ShoppingCartICon);
    }

    public String getPrice() {
        return PriceAfterDiscount.getText();
    }

    public void dismissAlert() {
        try {
            if (dismissBtn.isDisplayed() && dismissBtn.isEnabled()) {
                ClickButton(dismissBtn);
            } else {
                System.out.println("The alert isn't displayed");
            }
        }catch (NoSuchContextException e){
            System.out.println("The alert isn't displayed");
        }
    }

}