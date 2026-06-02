package testClass;

import com.aventstack.extentreports.ExtentTest;
import common_Actions.CommonFileActions;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import testObjects.Login_Object;

public class loginTest extends CommonFileActions {

    @Test
    public void test() throws InterruptedException {
        ExtentTest test=reports.createTest("Login Test");
        org.apache.logging.log4j.Logger logger = LogManager.getLogger(loginTest.class);
        logger.info("login test case");
        PageFactory.initElements(driver, Login_Object.class);
        Login_Object.username.sendKeys(properties.getProperty("username"));
        Login_Object.password.sendKeys(properties.getProperty("password"));
        Login_Object.submitBtn.click();

    }
}