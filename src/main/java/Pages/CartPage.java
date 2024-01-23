package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends PageBase {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//td[@class='product-price']//span[@class='woocommerce-Price-amount amount']")
    WebElement ProductPrice;

    @FindBy(xpath = "//tr[@class='cart-subtotal']//span[@class='woocommerce-Price-amount amount']")
    WebElement Subtotal;

    @FindBy(xpath = "//tr[@class='tax-rate tax-rate-in-tax-1']//span[@class='woocommerce-Price-amount amount']")
    WebElement Tax;

    @FindBy(xpath = "//tr[@class='order-total']//span[@class='woocommerce-Price-amount amount']")
    WebElement Total;

    @FindBy(className = "checkout-button button alt wc-forward")
    WebElement ProceedToCheckoutBtn;
    @FindBy(className = "product-name")
    WebElement ThinkingInHTMLTitle;
    public String ItemName = ThinkingInHTMLTitle.getText();

    public String getProductPriceFromCartPage() {
        return ProductPrice.getText();
    }

    public String getSubTotal() {
        return Subtotal.getText();
    }

    public String getTaxAmount() {
        return Tax.getText();
    }

    public void ClickOnCheckOutBtn() {
        ClickButton(ProceedToCheckoutBtn);
    }

    public String getCartTotal()
    {
        return Total.getText();
    }
}