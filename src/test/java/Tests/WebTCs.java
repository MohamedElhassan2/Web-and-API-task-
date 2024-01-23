package Tests;

import Pages.CartPage;
import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

public class WebTCs extends TestBase{

    HomePage homePage;
    CartPage cartPage;
    @Test(priority = 1)
    public void CheckItemExist() throws Exception
    {
        homePage = new HomePage(driver);
        Assert.assertTrue(homePage.Thinking_In_HTML_Card.isDisplayed());
    }
    @Test(priority = 2)
    public void AddToBasket() throws Exception
    {
        homePage.ClickOnAddBasketBtnForThinkingInHTMLCard();
        homePage.ClickOnShoppingCartIcon();
    }
    @Test(priority = 3)
    public void checkItemAddToCart() throws Exception
    {
        Integer PriceInHome = Integer.valueOf(homePage.getPrice());
        Integer PriceInCartPage = Integer.valueOf(cartPage.getProductPriceFromCartPage());
        Assert.assertEquals(homePage.ItemName, cartPage.ItemName);
        Assert.assertEquals(PriceInHome, PriceInCartPage);
        cartPage.ClickOnCheckOutBtn();
    }
    @Test(priority = 4)
    public void checkBillingDetails() throws Exception {
        Integer tax = Integer.valueOf(cartPage.getTaxAmount());
        Integer total = Integer.valueOf(cartPage.getCartTotal());
        Integer Subtotal = Integer.valueOf(cartPage.getSubTotal());
        Assert.assertEquals(Optional.of(total), (tax + Subtotal));
    }


}