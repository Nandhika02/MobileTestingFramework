package pages;


import org.openqa.selenium.WebElement;


import base.BasePage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePage extends BasePage{
	
	AndroidDriver driver;
	public HomePage(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
	}
	public HomePage(IOSDriver driver) {
		super(driver);
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/toolbar_title\"]")
    private WebElement appTitle;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/toolbar_title\"]")
    private WebElement pageTitle;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    private WebElement countryDropdown;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Select the country where you want to shop\"]")
    private WebElement countryDropdownLabel;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"India\"]")
    private WebElement indiaOption;
    
    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameTextBox;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Your Name\"]")
    private WebElement nameTextBoxLabel;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@resource-id=\"com.androidsample.generalstore:id/radioMale\"]")
    private WebElement maleRadioButton;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@resource-id=\"com.androidsample.generalstore:id/radioFemale\"]")
    private WebElement femaleRadioButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Gender\"]")
    private WebElement genderLabel;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement letsShopButton;

    

    public boolean isAppLaunched() {
        return appTitle.isDisplayed(); // Checks if the app title is visible after launch
    }
    
    public String getPageTitle() {
        return pageTitle.getText();
    }

    public boolean isCountryDropdownCorrect() {
        return countryDropdown.getText().equals("Afghanistan")
                && countryDropdownLabel.getText().equals("Select the country where you want to shop");
    }

    public boolean isNameFieldCorrect() {
        return nameTextBox.getAttribute("text").equals("Enter name here")
                && nameTextBoxLabel.getText().equals("Your Name");
    }

    public boolean isGenderRadioButtonCorrect() {
        return genderLabel.getText().equals("Gender")
                && maleRadioButton.isSelected()
                && femaleRadioButton.isDisplayed();
    }

    public boolean isLetsShopButtonCorrect() {
        return letsShopButton.getText().equals("Let's Shop") && letsShopButton.isDisplayed();
    }
    
    public void selectCountry(String country) {
        countryDropdown.click();
        indiaOption.click(); 
    }
    
    public void enterName(String name) {
        nameTextBox.clear();
        nameTextBox.sendKeys(name);
    }

    public void selectGenderFemale() {
        femaleRadioButton.click();
    }

    public void clickLetsShop() {
        letsShopButton.click();
    }
}

