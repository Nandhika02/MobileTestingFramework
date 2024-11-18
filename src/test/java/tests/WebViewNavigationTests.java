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

public class WebViewNavigationTests extends BaseTests {

	 private AndroidDriver driver;
	    private CartPage cartPage;
	    private ProductsPage productsPage;
	    private ExtentReportsUtility extent;

	    @BeforeClass
	    public void setUp() throws Exception {
	        driver = (AndroidDriver) getDriver("android");
	        cartPage = new CartPage(driver);
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
	    public void navigateToWebView() {
	        extent.startSingleTestReport("navigateToWebView");
	        try {
	          
	            cartPage.clickVisitWebsiteButton();
	            extent.logTestInfo("Clicked on 'Visit to the website to complete the purchase' button.");

	            
	            cartPage.switchToWebViewContext();
	            extent.logTestInfo("Switched to WebView context.");

	            
	            Assert.assertTrue(cartPage.isGooglePageDisplayed(), "The Google Web page is not displayed.");
	            extent.logTestpassed("Google Web page is displayed successfully.");
	        } catch (Exception e) {
	            extent.logTestFailedWithException(e);
	            Assert.fail("Failed to navigate to WebView.");
	        }
	    }

	    @Test(priority = 2, dependsOnMethods = "navigateToWebView")
	    public void navigateBackToNativeContext() {
	        extent.startSingleTestReport("navigateBackToNativeContext");
	        try {
	            
	            cartPage.navigateBack();
	            extent.logTestInfo("Navigated back from WebView.");

	         
	            cartPage.switchToNativeContext();
	            extent.logTestInfo("Switched back to Native context.");

	            
	            Assert.assertTrue(cartPage.isPageTitleDisplayed("Cart"), "Failed to navigate back to the General Store application.");
	            extent.logTestpassed("Navigated back to the General Store application successfully.");
	        } catch (Exception e) {
	            extent.logTestFailedWithException(e);
	            Assert.fail("Failed to navigate back to Native context.");
	        }
	    }

	    @AfterClass
	    public void tearDown() {
	        extent.endReport(); 
	    }
	
	}
