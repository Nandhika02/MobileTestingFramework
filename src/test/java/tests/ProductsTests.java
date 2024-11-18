package tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTests;
import io.appium.java_client.android.AndroidDriver;
import pages.ProductsPage;
import utils.ExtentReportsUtility;


public class ProductsTests extends BaseTests {

    private AndroidDriver driver;
    private ProductsPage productspage;
    private String priceJordan6Rings;
    private String pricePG3;
    private ExtentReportsUtility extent;

    @BeforeClass
    public void setUp() throws Exception {
        driver = (AndroidDriver) getDriver("android");
        productspage = new ProductsPage(driver);

        // Initialize ExtentReports
        extent = ExtentReportsUtility.getInstance();
        extent.startExtentReport();
    }

    @Test(priority = 1)
    public void verifyProductsPageTitle() {
        extent.startSingleTestReport("verifyProductsPageTitle");
        try {
            String actualTitle = productspage.getPageTitle();
            Assert.assertEquals(actualTitle, "Products", "The title of the Products page is incorrect.");
            extent.logTestpassed("Verified the title of the Products page: " + actualTitle);
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to verify Products page title.");
        }
    }
    
    @Test(priority = 2)
    public void scrollToProduct() {
        productspage.scrollToProduct("Jordan 6 Rings");
        Assert.assertTrue(productspage.isProductVisible("Jordan 6 Rings"), "Product 'Jordan 6 Rings' is not visible.");
        System.out.println("Verified 'Jordan 6 Rings' is visible after scrolling.");
    }

    @Test(priority = 2)
    public void verifyProductDetails() {
        extent.startSingleTestReport("verifyProductDetails");
        try {
            Assert.assertTrue(productspage.isProductVisible("Jordan 6 Rings"), "Product 'Jordan 6 Rings' is not visible.");
            extent.logTestInfo("Verified 'Jordan 6 Rings' is visible.");

            String price = productspage.getProductPrice("Jordan 6 Rings");
            Assert.assertNotNull(price, "Product price is not displayed.");
            extent.logTestInfo("Retrieved product price: " + price);

            Assert.assertTrue(productspage.isAddToCartButtonDisplayed("Jordan 6 Rings"), "'ADD TO CART' button is not displayed.");
            extent.logTestpassed("Verified 'ADD TO CART' button is displayed for 'Jordan 6 Rings'.");
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to verify product details.");
        }
    }

    @Test(priority = 3, dependsOnMethods = "verifyProductDetails")
    public void addProductToCart() {
        extent.startSingleTestReport("addProductToCart");
        try {
            productspage.addProductToCart("Jordan 6 Rings");
            extent.logTestInfo("Clicked 'ADD TO CART' for 'Jordan 6 Rings'.");

            Assert.assertTrue(productspage.isProductAddedToCart("Jordan 6 Rings"), "'Jordan 6 Rings' was not added to the cart.");
            extent.logTestpassed("Verified 'Jordan 6 Rings' was successfully added to the cart.");
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to add product to the cart.");
        }
    }

    @Test(priority = 4)
    public void selectAndAddJordan6Rings() {
        extent.startSingleTestReport("selectAndAddJordan6Rings");
        try {
            Assert.assertTrue(productspage.isProductVisible("Jordan 6 Rings"), "Jordan 6 Rings product is not visible.");
            extent.logTestInfo("'Jordan 6 Rings' is visible.");

            priceJordan6Rings = productspage.getProductPrice("Jordan 6 Rings");
            Assert.assertNotNull(priceJordan6Rings, "Price for 'Jordan 6 Rings' is not displayed.");
            extent.logTestInfo("Retrieved price for 'Jordan 6 Rings': " + priceJordan6Rings);

            productspage.addProductToCart("Jordan 6 Rings");
            extent.logTestpassed("Added 'Jordan 6 Rings' to the cart successfully.");
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to add 'Jordan 6 Rings' to the cart.");
        }
    }

    @Test(priority = 5)
    public void selectAndAddPG3() {
        extent.startSingleTestReport("selectAndAddPG3");
        try {
            Assert.assertTrue(productspage.isProductVisible("PG 3"), "PG 3 product is not visible.");
            extent.logTestInfo("'PG 3' is visible.");

            pricePG3 = productspage.getProductPrice("PG 3");
            Assert.assertNotNull(pricePG3, "Price for 'PG 3' is not displayed.");
            extent.logTestInfo("Retrieved price for 'PG 3': " + pricePG3);

            productspage.addProductToCart("PG 3");
            extent.logTestpassed("Added 'PG 3' to the cart successfully.");
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to add 'PG 3' to the cart.");
        }
    }

    @AfterClass
    public void tearDown() {
        extent.endReport(); // Finalize the ExtentReports
    }
}
