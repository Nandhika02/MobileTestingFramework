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

public class TermsAndCartTests extends BaseTests {

    private AndroidDriver driver;
    private CartPage cartPage;
    private ProductsPage productsPage;
    private ExtentReportsUtility extent;

    @BeforeClass
    public void setUp() throws Exception {
        driver = (AndroidDriver) getDriver("android");
        cartPage = new CartPage(driver);

        // Initialize ExtentReports
        extent = ExtentReportsUtility.getInstance();
        extent.startExtentReport();
    }

    @Test(priority = 1)
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
    
    @Test(priority = 2)
    public void verifyTermsOfConditionsAlert() {
        extent.startSingleTestReport("verifyTermsOfConditionsAlert");
        try {
            cartPage.longPressTermsOfConditions();
            extent.logTestInfo("Performed long press on 'Please read our terms of conditions'.");

            Assert.assertEquals(cartPage.getAlertTitle(), "Terms Of Conditions", "Alert title is incorrect.");
            extent.logTestpassed("Verified alert title: Terms Of Conditions.");
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to verify Terms Of Conditions alert.");
        }
    }

    @Test(priority = 3)
    public void closeAlert() {
        extent.startSingleTestReport("closeAlert");
        try {
            cartPage.closeAlert();
            extent.logTestInfo("Closed the alert.");

            Assert.assertTrue(cartPage.isPageTitleDisplayed("Cart"), "Cart page is not displayed after closing the alert.");
            extent.logTestpassed("Verified Cart page is displayed after closing the alert.");
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to close alert.");
        }
    }

   

    @Test(priority = 4)
    public void verifyEmailCheckbox() {
        extent.startSingleTestReport("verifyEmailCheckbox");
        try {
            Assert.assertTrue(cartPage.isCheckboxDisplayed(), "Checkbox is not displayed.");
            extent.logTestInfo("Verified checkbox is displayed.");

            Assert.assertTrue(cartPage.isCheckboxUnchecked(), "Checkbox is not unchecked by default.");
            extent.logTestpassed("Verified checkbox is unchecked by default.");
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to verify email checkbox.");
        }
    }
    
   

    @AfterClass
    public void tearDown() {
        extent.endReport(); // Finalize the ExtentReports
    }
}
