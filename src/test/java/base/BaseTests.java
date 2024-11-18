package base;

import java.net.MalformedURLException;
import java.net.URL;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import constants.CapabilitiesConst;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTests {
	

	static AppiumDriver driver = null;
	AppiumDriverLocalService service;
	
	@BeforeSuite
	public void startServer() {
		/*service = new AppiumServiceBuilder()
				.withAppiumJS(new File("/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();*/
		
		AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
		AppiumDriverLocalService server = AppiumDriverLocalService.buildService(serviceBuilder);
		server.start();
		
	}
	
	@AfterSuite
	public void stopServer() {
		//service.stop();
		
	}
	
	
	public static AppiumDriver getDriver(String driverName) throws MalformedURLException {

		String name = driverName.toLowerCase();
		switch (name) {
		case "android":
			URL url = new URL("http://127.0.0.1:4723/");
			UiAutomator2Options uio = new UiAutomator2Options();
			uio.setPlatformName("android");
			uio.setDeviceName("3300c3ba410c63af");
			uio.setPlatformVersion("7.1.1");
			uio.setAutomationName("UIAutomator2");
			uio.setAppPackage(CapabilitiesConst.PACKAGE_NAME);
			uio.setAppActivity(CapabilitiesConst.ACTIVITY_NAME);
			uio.setApp(System.getProperty("user.dir") + "/src/test/resources/apkFiles/General-Store.apk");
			driver = new AndroidDriver(url, uio);
			break;

		case "ios":
			URL url1 = new URL("http://127.0.0.1:4723/");
			XCUITestOptions xct = new XCUITestOptions();
			xct.setPlatformName("android");
			xct.setDeviceName("Samsung");
			xct.setPlatformVersion("13");
			xct.setAutomationName("XCUITDriver");
			xct.setApp("");
			driver = new IOSDriver(url1, xct);
			break;

		default:
			break;
		}
		return driver;
	}

	

}
