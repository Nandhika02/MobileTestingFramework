package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductsPage extends BasePage {

	AndroidDriver driver;
	public ProductsPage(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
	}
	public ProductsPage(IOSDriver driver) {
		super(driver);
	}
	
	 @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/toolbar_title\"]")
	    private WebElement pageTitle;

	    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productName\" and @text=\"Jordan 6 Rings\"]")
	    private WebElement productJordan6Rings;

	    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productPrice\" and @text=\"$165.0\"]")
	    private WebElement productPrice;

	    @AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[1]")
	    private WebElement addToCartButton;
	    
	    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	    private WebElement cartButton;



	    public String getPageTitle() {
	        return pageTitle.getText();
	    }

	    public boolean isProductVisible() {
	        return productJordan6Rings.isDisplayed();
	    }

	    public String getProductPrice() {
	        return productPrice.getText();
	    }

	    public boolean isAddToCartButtonDisplayed() {
	        return addToCartButton.isDisplayed();
	    }

	    public void addToCart() {
	        addToCartButton.click();
	    }

	    public boolean isProductAddedToCart() {
	        return addToCartButton.getText().equals("ADDED TO CART");
	    }
	
	    public void navigateToCart() {
	        cartButton.click();
	    }
	    
	    public void scrollToProduct(String productName) {
	        String uiScrollable = "new UiScrollable(new UiSelector().scrollable(true))"
	                + ".scrollIntoView(new UiSelector().text(\"" + productName + "\"))";
	        driver.findElement(AppiumBy.androidUIAutomator(uiScrollable));
	    }

	    public boolean isProductVisible(String productName) {
	        try {
	            scrollToProduct(productName);
	            return driver.findElement(By.xpath("//android.widget.TextView[@text='" + productName + "']")).isDisplayed();
	        } catch (Exception e) {
	            return false;
	        }
	    }

	    public String getProductPrice(String productName) {
	        scrollToProduct(productName);
	        return driver.findElement(By.xpath("//android.widget.TextView[@text='" + productName + "']/following-sibling::android.widget.TextView")).getText();
	    }

	    public void addProductToCart(String productName) {
	        scrollToProduct(productName);
	        driver.findElement(By.xpath("//android.widget.TextView[@text='" + productName + "']/following-sibling::android.widget.Button")).click();
	    }
	    
	    public boolean isAddToCartButtonDisplayed(String productName) {
	        scrollToProduct(productName);
	        return driver.findElement(By.xpath("//android.widget.TextView[@text='" + productName + "']/following-sibling::android.widget.Button")).isDisplayed();
	    }

	    public boolean isProductAddedToCart(String productName) {
	        scrollToProduct(productName);
	        return driver.findElement(By.xpath("//android.widget.TextView[@text='" + productName + "']/following-sibling::android.widget.Button")).getText().equals("ADDED TO CART");
	    }
}
