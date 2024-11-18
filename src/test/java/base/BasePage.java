package base;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BasePage {


		public BasePage(AppiumDriver driver) {
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		}
		public BasePage(IOSDriver driver) {
			PageFactory.initElements(driver, this);
		}
	

}
