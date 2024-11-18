package tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTests;
import io.appium.java_client.android.AndroidDriver;
import pages.HomePage;
import utils.ExtentReportsUtility;

public class HomeTests extends BaseTests  {

	private AndroidDriver driver;
    private HomePage homepage;
    private ExtentReportsUtility extent;

    @BeforeClass
    public void setUp() throws Exception {
        driver = (AndroidDriver) getDriver("android");
        homepage = new HomePage(driver);
        extent = ExtentReportsUtility.getInstance();
        extent.startExtentReport();
    }

    @Test
    public void testLaunchApp() {
        extent.startSingleTestReport("testLaunchApp");
        try {
            Assert.assertTrue(homepage.isAppLaunched(), "Failed to launch the General Store application.");
            extent.logTestpassed("General Store application launched successfully.");
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to launch the application.");
        }
    }

    @Test(priority = 1)
    public void verifyLaunchPageTitle() {
        extent.startSingleTestReport("verifyLaunchPageTitle");
        try {
            String actualTitle = homepage.getPageTitle();
            Assert.assertEquals(actualTitle, "General Store", "The title of the launch page is incorrect.");
            extent.logTestpassed("Verified the title of the launch page: " + actualTitle);
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to verify launch page title.");
        }
    }

    @Test(priority = 2)
    public void verifyCountryDropdown() {
        extent.startSingleTestReport("verifyCountryDropdown");
        try {
            Assert.assertTrue(homepage.isCountryDropdownCorrect(),
                    "The country dropdown default value or label is incorrect.");
            extent.logTestpassed("Verified the country dropdown label and default value.");
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to verify country dropdown.");
        }
    }

    @Test(priority = 3)
    public void verifyNameField() {
        extent.startSingleTestReport("verifyNameField");
        try {
            Assert.assertTrue(homepage.isNameFieldCorrect(),
                    "The name field placeholder or label is incorrect.");
            extent.logTestpassed("Verified the name field label and placeholder.");
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to verify name field.");
        }
    }

    @Test(priority = 4)
    public void verifyGenderRadioButton() {
        extent.startSingleTestReport("verifyGenderRadioButton");
        try {
            Assert.assertTrue(homepage.isGenderRadioButtonCorrect(),
                    "The gender radio button label or default selection is incorrect.");
            extent.logTestpassed("Verified the gender radio button label and options.");
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to verify gender radio button.");
        }
    }

    @Test(priority = 5)
    public void verifyLetsShopButton() {
        extent.startSingleTestReport("verifyLetsShopButton");
        try {
            Assert.assertTrue(homepage.isLetsShopButtonCorrect(),
                    "The 'Let's Shop' button label or visibility is incorrect.");
            extent.logTestpassed("Verified the 'Let's Shop' button.");
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to verify 'Let's Shop' button.");
        }
    }

    @Test(priority = 7)
    public void testNavigateToProductsPage() {
        extent.startSingleTestReport("testNavigateToProductsPage");
        try {
            homepage.selectCountry("India");
            extent.logTestInfo("Country 'India' selected successfully.");

            homepage.enterName("Nandhika");
            extent.logTestInfo("Name entered successfully.");

            homepage.selectGenderFemale();
            extent.logTestInfo("Gender 'Female' selected successfully.");

            homepage.clickLetsShop();
            extent.logTestpassed("Navigated to the Products page successfully.");
        } catch (Exception e) {
            extent.logTestFailedWithException(e);
            Assert.fail("Failed to navigate to the Products page.");
        }
    }

    @AfterClass
    public void tearDown() {
        extent.endReport(); // Finalize the ExtentReports
    }
}