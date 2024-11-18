package tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTests;
import io.appium.java_client.android.AndroidDriver;
import pages.CartPage;
import pages.ProductsPage;
import utils.ExtentReportsUtility;

public class CartsTests extends BaseTests{

	 private AndroidDriver driver;
	    private ProductsPage productsPage;
	    private CartPage cartPage;
	    private ExtentReportsUtility extent;

	    private String priceJordan6Rings;
	    private String pricePG3;

	    @BeforeClass
	    public void setUp() throws Exception {
	    	driver = (AndroidDriver) getDriver("android");
	        productsPage = new ProductsPage(driver);
	        cartPage = new CartPage(driver);
	        
	        extent = ExtentReportsUtility.getInstance();
	        extent.startExtentReport();
	    }

	    @Test(priority = 1)
	    public void navigateToCartPage() {
	        productsPage.navigateToCart();
	        String actualTitle = cartPage.getPageTitle();
	        Assert.assertEquals(actualTitle, "Cart", "The title of the Cart page is incorrect.");
	        System.out.println("Navigated to Cart page successfully. Title: " + actualTitle);
	    }

	    @Test(priority = 3)
	    public void verifyProductsInCart() {
	        Assert.assertTrue(cartPage.areProductsDisplayed(), "Products are not displayed correctly in the Cart.");
	        System.out.println("Verified 'Jordan 6 Rings' and 'PG 3' are displayed in the Cart.");
	    }

	    @Test(priority = 4)
	    public void verifyProductPrices() {
	        String cartPriceJordan6 = cartPage.getProductPrice("Jordan 6 Rings");
	        Assert.assertEquals(cartPriceJordan6, priceJordan6Rings, 
	                "Price for 'Jordan 6 Rings' in the Cart does not match the Products page.");
	        System.out.println("Verified price for 'Jordan 6 Rings'.");

	        String cartPricePG3 = cartPage.getProductPrice("PG 3");
	        Assert.assertEquals(cartPricePG3, pricePG3, 
	                "Price for 'PG 3' in the Cart does not match the Products page.");
	        System.out.println("Verified price for 'PG 3'.");
	    }

	    @Test(priority = 2)
	    public void verifyCartsPageTitle() {
	        extent.startSingleTestReport("verifyCartsPageTitle");
	        try {
	            String actualTitle = cartPage.getPageTitle();
	            Assert.assertEquals(actualTitle, "Carts", "The title of the carts page is incorrect.");
	            extent.logTestpassed("Verified the title of the Carts page: " + actualTitle);
	        } catch (Exception e) {
	            extent.logTestFailedWithException(e);
	            Assert.fail("Failed to verify Carts page title.");
	        }
	    }

	    @AfterClass
	    public void tearDown() {
	        extent.endReport(); // Finalize the ExtentReports
	    }
	}

