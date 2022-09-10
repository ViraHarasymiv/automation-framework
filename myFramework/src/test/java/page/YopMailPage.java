package page;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class YopMailPage extends AbstractPage{

    private final String PAGE_URL = "https://yopmail.com/uk/";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//input[@class='ycptinput']")
    private WebElement yopEmailField;

    @FindBy(xpath = "//button[@class='md']")
    private WebElement checkIncomingEmails;

    @FindBy(xpath = "//div[@class='bname']")
    private WebElement nameOfCreatedEmail;

    @FindBy(xpath = "//h1")
    private WebElement myEstimatedBill;

    @FindBy(xpath = "//h2")
    private WebElement myEstimatedCost;

    @FindBy(xpath = "//iframe[@id='ifmail']")
    private WebElement yopEmailIframe;

    public YopMailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public YopMailPage openPage() {
        driver.navigate().to(PAGE_URL);
        logger.info("YOPmail Page is opened");
        return this;
    }

    public YopMailPage typeTemporaryEmail(User user){
        yopEmailField.sendKeys(user.getTemporaryEmail());
        logger.info("Temporary email is typed");
        return this;
    }

    public YopMailPage createTemporaryEmail(){
        waitElementIsClickable(checkIncomingEmails);
        checkIncomingEmails.click();
        waitVisibility(nameOfCreatedEmail);
        logger.info("Temporary email is checked");
        return this;
    }

    public GoogleCloudCalculatorPage switchToGoogleCloudCalculatorPage(){
      ArrayList<String>tabs = new ArrayList<>(driver.getWindowHandles());
       driver.switchTo().window((tabs.get(0)));
        return new GoogleCloudCalculatorPage(driver);
    }

    public void switchToYopEmailIframe(){
        waitVisibility(yopEmailIframe);
        driver.switchTo().frame(yopEmailIframe);
    }

    public String getReceivedLetter(){
        switchToYopEmailIframe();
        waitVisibility(myEstimatedBill);
        waitVisibility(myEstimatedCost);
        System.out.println(myEstimatedCost.getText());
        return myEstimatedCost.getText();
    }
}
