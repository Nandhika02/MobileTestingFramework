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


public class ProductsAndCartTests extends BaseTests {

    private AndroidDriver driver;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private String priceJordan6Rings;
    private String pricePG3;
    private ExtentReportsUtility extent;

    @BeforeClass
    public void setUp() throws Exception {
        driver = (AndroidDriver) getDriver("android");
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);

        // Initialize ExtentReports
        extent = ExtentReportsUtility.getInstance();
        extent.startExtentReport();
    }

    @Test(priority = 1)
    public void verifyProductsPageTitle() {
        extent.startSingleTestReport("verifyProductsPageTitle");
        try {
            String actualTitle = productsPage.getPageTitle();
            Assert.assertEquals(actualTitle, "Products", "The title of the Products page is incorrect.");
            extent.logTestpassed("Verified the title of the Products page: " + actualTitle);
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to verify the Products page title.");
        }
    }

    @Test(priority = 2)
    public void scrollToJordan6Rings() {
        extent.startSingleTestReport("scrollToJordan6Rings");
        try {
            productsPage.scrollToProduct("Jordan 6 Rings");
            extent.logTestInfo("Scrolled to 'Jordan 6 Rings'.");

            Assert.assertTrue(productsPage.isProductVisible("Jordan 6 Rings"), "Product 'Jordan 6 Rings' is not visible.");
            extent.logTestpassed("Verified 'Jordan 6 Rings' is visible.");
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to scroll to 'Jordan 6 Rings'.");
        }
    }

    @Test(priority = 3)
    public void verifyJordan6RingsDetails() {
        extent.startSingleTestReport("verifyJordan6RingsDetails");
        try {
            priceJordan6Rings = productsPage.getProductPrice("Jordan 6 Rings");
            Assert.assertNotNull(priceJordan6Rings, "Price for 'Jordan 6 Rings' is not displayed.");
            extent.logTestInfo("Retrieved price for 'Jordan 6 Rings': " + priceJordan6Rings);

            Assert.assertTrue(productsPage.isAddToCartButtonDisplayed("Jordan 6 Rings"), "'ADD TO CART' button is not displayed.");
            extent.logTestpassed("Verified 'Jordan 6 Rings' details.");
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to verify details for 'Jordan 6 Rings'.");
        }
    }

    @Test(priority = 4)
    public void addJordan6RingsToCart() {
        extent.startSingleTestReport("addJordan6RingsToCart");
        try {
            productsPage.addProductToCart("Jordan 6 Rings");
            extent.logTestInfo("Clicked on 'ADD TO CART' button for 'Jordan 6 Rings'.");

            Assert.assertTrue(productsPage.isProductAddedToCart("Jordan 6 Rings"), "'Jordan 6 Rings' was not added to the cart.");
            extent.logTestpassed("Verified 'Jordan 6 Rings' was added to the cart.");
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to add 'Jordan 6 Rings' to cart.");
        }
    }

    @Test(priority = 5)
    public void scrollToPG3() {
        extent.startSingleTestReport("scrollToPG3");
        try {
            productsPage.scrollToProduct("PG 3");
            extent.logTestInfo("Scrolled to 'PG 3'.");

            Assert.assertTrue(productsPage.isProductVisible("PG 3"), "Product 'PG 3' is not visible.");
            extent.logTestpassed("Verified 'PG 3' is visible.");
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to scroll to 'PG 3'.");
        }
    }

    @Test(priority = 6)
    public void verifyPG3Details() {
        extent.startSingleTestReport("verifyPG3Details");
        try {
            pricePG3 = productsPage.getProductPrice("PG 3");
            Assert.assertNotNull(pricePG3, "Price for 'PG 3' is not displayed.");
            extent.logTestInfo("Retrieved price for 'PG 3': " + pricePG3);

            Assert.assertTrue(productsPage.isAddToCartButtonDisplayed("PG 3"), "'ADD TO CART' button is not displayed.");
            extent.logTestpassed("Verified 'PG 3' details.");
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to verify details for 'PG 3'.");
        }
    }

    @Test(priority = 7)
    public void addPG3ToCart() {
        extent.startSingleTestReport("addPG3ToCart");
        try {
            productsPage.addProductToCart("PG 3");
            extent.logTestInfo("Clicked on 'ADD TO CART' button for 'PG 3'.");

            Assert.assertTrue(productsPage.isProductAddedToCart("PG 3"), "'PG 3' was not added to the cart.");
            extent.logTestpassed("Verified 'PG 3' was added to the cart.");
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to add 'PG 3' to cart.");
        }
    }

    @Test(priority = 8)
    public void navigateToCartPage() {
        extent.startSingleTestReport("navigateToCartPage");
        try {
            productsPage.navigateToCart();
            String actualTitle = cartPage.getPageTitle();
            Assert.assertEquals(actualTitle, "Cart", "The title of the Cart page is incorrect.");
            extent.logTestpassed("Navigated to the Cart page successfully. Title: " + actualTitle);
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to navigate to the Cart page.");
        }
    }

    @Test(priority = 9)
    public void verifyCartPageTitle() {
        extent.startSingleTestReport("verifyCartPageTitle");
        try {
            String actualTitle = cartPage.getPageTitle();
            Assert.assertEquals(actualTitle, "Cart", "The title of the Cart page is incorrect.");
            extent.logTestpassed("Verified the title of the Cart page: " + actualTitle);
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to verify the Cart page title.");
        }
    }

    @Test(priority = 10)
    public void verifyCartProducts() {
        extent.startSingleTestReport("verifyCartProducts");
        try {
            Assert.assertTrue(cartPage.isProductDisplayed("Jordan 6 Rings"), "'Jordan 6 Rings' is not displayed in the Cart.");
            Assert.assertTrue(cartPage.isProductDisplayed("PG 3"), "'PG 3' is not displayed in the Cart.");
            extent.logTestpassed("Verified both 'Jordan 6 Rings' and 'PG 3' are displayed in the Cart.");
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to verify products in the Cart.");
        }
    }

    @Test(priority = 11)
    public void verifyTotalPurchaseAmount() {
        extent.startSingleTestReport("verifyTotalPurchaseAmount");
        try {
            double totalPurchaseAmount = cartPage.calculateTotalPurchaseAmount("Jordan 6 Rings", "PG 3");
            double expectedTotal = Double.parseDouble(priceJordan6Rings.replace("$", "")) + Double.parseDouble(pricePG3.replace("$", ""));
            Assert.assertEquals(totalPurchaseAmount, expectedTotal, "Total purchase amount in the Cart is incorrect.");
            extent.logTestpassed("Verified the total purchase amount: " + totalPurchaseAmount);
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to verify total purchase amount.");
        }
    }

    @AfterClass
    public void tearDown() {
        extent.endReport(); // Finalize the ExtentReports
    }
}
