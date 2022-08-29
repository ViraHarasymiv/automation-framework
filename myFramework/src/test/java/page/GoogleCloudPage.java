package page;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.naming.directory.SearchResult;
import java.time.Duration;

public class GoogleCloudPage extends AbstractPage {

    private final String PAGE_URL = "https://cloud.google.com/";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//input[@aria-label='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//devsite-search[@tenant-name='Google Cloud']")
    private WebElement searchField;


    @FindBy(xpath = "//div[@id='devsite-search-popout-container-id-1']")
    private WebElement searchForm;

    public GoogleCloudPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver,this);
    }

    @Override
    public GoogleCloudPage openPage() {
        driver.navigate().to(PAGE_URL);
        logger.info("Google Cloud Page is opened");
        return this;
    }

    public GoogleCloudPage clickOnSearchButton(){
        searchButton.click();
        logger.info("Click on Search button");
        return this;
    }

    public SearchResultsPage typeSearchItem(User user){
        Actions actions = new Actions(driver);
        actions.sendKeys(searchField,user.getSearchItem()).perform();
        logger.info("Type: 'Google Cloud Platform Pricing Calculator' in Search field");
        WebElement waitVisibilityOfSearchForm = new  WebDriverWait(driver,Duration.ofSeconds(1000))
                .until(ExpectedConditions.visibilityOf(searchForm));
        return new SearchResultsPage(driver);
    }
}
