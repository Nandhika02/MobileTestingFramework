package pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CartPage extends BasePage{

		AndroidDriver driver;
		public CartPage(AndroidDriver driver) {
			super(driver);
			this.driver=driver;
		}
		public CartPage(IOSDriver driver) {
			super(driver);
		}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/toolbar_title']")
    private WebElement pageTitle;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productName\" and @text=\"Jordan 6 Rings\"]")
    private WebElement jordan6RingsProduct;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productPrice\" and @text=\"$165.0\"]")
    private WebElement jordan6RingsPrice;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productName\" and @text=\"PG 3\"]")
    private WebElement pg3Product;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productPrice\" and @text=\"$110.0\"]")
    private WebElement pg3Price;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement termsOfConditions;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/alertTitle")
    private WebElement alertTitle;

    @AndroidFindBy(id = "android:id/button1") // Locator for "CLOSE" button in the alert
    private WebElement closeButton;

    @AndroidFindBy(xpath = "//android.widget.CheckBox[@text='Send me e-mails on discounts related to selected products in future']")
    private WebElement emailCheckbox;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\" and @text=\"ADD TO CART\"]")
    private WebElement addToCartButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\" and @text=\"ADDED TO CART\"]")
    private WebElement addedToCartText;

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public boolean areProductsDisplayed() {
        return jordan6RingsProduct.isDisplayed() && pg3Product.isDisplayed();
    }
    
    public String getProductPrice(String productName) {
        if (productName.equalsIgnoreCase("Jordan 6 Rings")) {
            return jordan6RingsPrice.getText();
        } else if (productName.equalsIgnoreCase("PG 3")) {
            return pg3Price.getText();
        }
        throw new IllegalArgumentException("Invalid product name: " + productName);
    }

    public String getTotalPurchaseAmount() {
        double priceJordan6 = Double.parseDouble(jordan6RingsPrice.getText().replace("$", ""));
        double pricePG3 = Double.parseDouble(pg3Price.getText().replace("$", ""));
        return String.valueOf(priceJordan6 + pricePG3);
    }
    
    public boolean isProductDisplayed(String productName) {
        try {
            return driver.findElement(By.xpath("//android.widget.TextView[@text='" + productName + "']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public double calculateTotalPurchaseAmount(String... productNames) {
    	
        double total = 0.0;
        for (String productName : productNames) {
            String priceText = getProductPrice(productName).replace("$", "");
            total += Double.parseDouble(priceText);
        }
        return total;
    }
    public void longPressTermsOfConditions() {
        Actions actions = new Actions(driver);
        actions.clickAndHold(termsOfConditions)
               .pause(Duration.ofSeconds(2))
               .release()
               .perform();
    }

    public String getAlertTitle() {
        return alertTitle.getText();
    }

    public void closeAlert() {
        closeButton.click();
    }

    public void addProductToCart() {
        addToCartButton.click();
    }

    public boolean isProductAddedToCart() {
        return addedToCartText.isDisplayed();
    }

    public boolean isCheckboxDisplayed() {
        return emailCheckbox.isDisplayed();
    }

    public boolean isCheckboxUnchecked() {
        return !emailCheckbox.isSelected();
    }

    public boolean isPageTitleDisplayed(String expectedTitle) {
        return getPageTitle().equals(expectedTitle);
    }
   
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.androidsample.generalstore:id/btnProceed\"]")
    private WebElement visitWebsiteButton;

    public void clickVisitWebsiteButton() {
        visitWebsiteButton.click();
    }

    public boolean switchToWebViewContext() {
        Set<String> contexts = driver.getContextHandles();
        for (String context : contexts) {
            if (context.contains("WEBVIEW")) {
                driver.context(context);
                return true;
            }
        }
        return false;
    }

    public boolean switchToNativeContext() {
        Set<String> contexts = driver.getContextHandles();
        for (String context : contexts) {
            if (context.equals("NATIVE_APP")) {
                driver.context(context);
                return true;
            }
        }
        return false;
    }

    public boolean isGooglePageDisplayed() {
        return driver.getTitle().contains("Google");
    }
    
    public void navigateBack() {
        driver.navigate().back();
    }
}
