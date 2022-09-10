package page;

import model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsPage extends AbstractPage{

    private final String SEARCH_RESULTS_URL = "https://cloud.google.com/s/results?q=Google%20Cloud%20Platform%20Pricing%20Calculator";

    protected SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver,this);
    }

    @FindBy(xpath = "//div[@class='gs-title']//a[@class='gs-title']//b[text()='Google Cloud']")
    private List<WebElement>searchResults;

    @FindBy(xpath = "//div[@class='gs-title']//a[@class='gs-title']//b[text()='Google Cloud Pricing Calculator']")
    private WebElement googleCloudPricingCalculator;

    @Override
    public SearchResultsPage openPage() {
        driver.navigate().to(SEARCH_RESULTS_URL);
        return this;
    }

    public List<String> getSearchInformation(User user){
        return searchResults
                .stream()
                .map(webElement -> webElement.getText())
                .collect(Collectors.toList());
    }

    public GoogleCloudCalculatorPage selectGoogleCloudPricingCalculator(){
        googleCloudPricingCalculator.click();
        return new GoogleCloudCalculatorPage(driver);
    }
}
