package page;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleCloudPage extends AbstractPage {

    private final String PAGE_URL = "https://cloud.google.com/";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//button[contains(@class, 'devsite')][@aria-label='Open search']")
    private WebElement searchButton;

    @FindBy(xpath = "//devsite-search[@tenant-name='Google Cloud']")
    private WebElement searchField;

    @FindBy(xpath = "//div[@id='devsite-search-popout-container-id-1']")
    private WebElement searchForm;

    public GoogleCloudPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public GoogleCloudPage openPage() {
        driver.navigate().to(PAGE_URL);
        logger.info("Google Cloud Page is opened");
        return this;
    }

    public GoogleCloudPage clickOnSearchButton() {
        actions().click(searchButton);
        logger.info("Click on Search button");
        return this;
    }

    public SearchResultsPage typeSearchInformation(User user){
        actions().sendKeys(searchField, user.getSearchInformation()).perform();
        logger.info(" 'Google Cloud Platform Pricing Calculator' is typed in Search field");
        waitVisibility(searchForm);
        return new SearchResultsPage(driver);
    }
}
