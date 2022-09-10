package page;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class GoogleCloudCalculatorPage extends AbstractPage{

    private final String PAGE_URL = "https://cloud.google.com/products/calculator#id=5b77d328-4122-4c99-8fbd-45274c3adcd6";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//md-card-content[@id='mainForm']//md-tab-item//div[@title='Compute Engine' and @class='tab-holder compute']")
    private WebElement computerEngine;

    @FindBy(xpath = "//iframe[@id='myFrame']")
    private WebElement secondCalculatorIframe;

    @FindBy(xpath = "//iframe[contains(@src, 'https://cloud.')]")
    private WebElement firstCalculatorIframe;

    @FindBy(xpath = "//div[@class='compute-engine-block']")
    private WebElement computerBlock;

    @FindBy(xpath = "//input[contains(@ng-model, 'listingCtrl.computeServer.q')]")
    private WebElement numberOfInstancesField;

    @FindBy(xpath = "//md-select[@placeholder='Instance type']")
    private WebElement machineTypeField;

    @FindBy(xpath = "//md-option[@ng-repeat='instance in typeInfo']//div")
    private List<WebElement> machineTypeList;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.location']")
    private WebElement dataCenterLocationField;

    @FindBy(xpath = "//md-option[contains(@ng-repeat, 'item in listingCtrl.fullRegionList | filter:listingCtrl.inputRegionText.c')]//div")
    private List<WebElement> datacenterLocationList;

    @FindBy(xpath = "//md-select[@ng-disabled='listingCtrl.isCudDisabled']")
    private WebElement committedUsageField;

    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//div[contains(text(),'1 Year')]")
    private WebElement committedUsageOfOneYear;

    @FindBy(xpath = "//button[contains(text(), 'Add to Estimate')][contains(@ng-click, 'listingCtrl.addC')]")
    private WebElement addToEstimateButton;

    @FindBy(xpath = "//md-content[@id='compute']")
    private WebElement computeEngineContent;

    @FindBy(xpath = "//span[contains(text(), ' x ')]")
    private WebElement selectedNumberOfInstances;

    @FindBy(xpath = "//div[contains(text(), 'Region')]")
    private WebElement selectedRegion;

    @FindBy(xpath = "//div[contains(text(), 'Commitment term:')]")
    private WebElement selectedUsage;

    @FindBy(xpath = "//div[contains(text(), 'Instance type:')]")
    private WebElement selectedMachineType;

    @FindBy(xpath = "//b[contains(text(), 'Estimated Component Cost:')]")
    private WebElement estimatedCost;

    @FindBy(xpath = "//button[@id='email_quote']")
    private WebElement emailButton;

    @FindBy(xpath = "//md-card-content[@ng-if='appCtrl.CartData.hasItems()']")
    private WebElement estimateContent;

    @FindBy(xpath = "//form[@name='emailForm']")
    private WebElement emailForm;

    @FindBy(xpath = "//input[@name='description' and @type='email']")
    private WebElement calculatorEmailField;

    @FindBy(xpath = "//button[contains(text(), 'Send Email')]")
    private WebElement sendEmailButton;

    public GoogleCloudCalculatorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver,this);
    }

    @Override
    public GoogleCloudCalculatorPage openPage() {
        driver.navigate().to(PAGE_URL);
        logger.info("Google Cloud Calculator Page is opened");
        return this;
    }

    public void switchToCalculatorIframes(){
        waitVisibility(firstCalculatorIframe);
        driver.switchTo().frame(firstCalculatorIframe);
        waitVisibility(secondCalculatorIframe);
        driver.switchTo().frame(secondCalculatorIframe);
    }

    public GoogleCloudCalculatorPage activateComputerEngineSection(){
        switchToCalculatorIframes();
        computerEngine.click();
        logger.info("Compute Engine is activated");
        return this;
    }

    public GoogleCloudCalculatorPage typeNumberOfInstances(User user){
        waitVisibility(computerBlock);
        actions().click(numberOfInstancesField).sendKeys(user.getNumberOfInstances()).perform();
        logger.info("Number of instances are typed");
        return this;
    }

    public GoogleCloudCalculatorPage selectMachineType(User user){
        machineTypeField.click();
        selectValueFromDropDownList(machineTypeList, user.getMachineType());
        logger.info("Machine type is selected");
        return this;
    }

    public GoogleCloudCalculatorPage selectDatacenterLocation(User user){
        dataCenterLocationField.click();
        selectValueFromDropDownList(datacenterLocationList,user.getDatacenterLocation());
        logger.info("Datacenter Location is selected");
        return this;
    }
    public GoogleCloudCalculatorPage selectCommittedUsage(User user){
        committedUsageField.click();
        waitVisibility(committedUsageOfOneYear);
        waitElementIsClickable(committedUsageOfOneYear);
        committedUsageOfOneYear.click();
        logger.info("Committed usage is selected");
        return this;
    }

    public GoogleCloudCalculatorPage clickOnAddToEstimateButton(){
        waitElementIsClickable(addToEstimateButton);
        addToEstimateButton.click();
        return this;
    }

    public boolean computeEngineContentContainsSelectedValues(User user){
        waitVisibility(computeEngineContent);
        logger.info("All typed values are seen and checked");
        return selectedNumberOfInstances.getText().contains(user.getNumberOfInstances())
                && selectedMachineType.getText().contains(user.getMachineType())
                && selectedRegion.getText().contains(user.getDatacenterLocation())
                && selectedUsage.getText().contains(user.getCommittedUsage());
    }

    public GoogleCloudCalculatorPage clickOnEmailButton(){
        switchToCalculatorIframes();
        waitVisibility(estimateContent);
        waitElementIsClickable(emailButton);
        javascriptClick(emailButton);
        return this;
    }

    public GoogleCloudCalculatorPage typeTemporaryEmail(){
        waitVisibility(emailForm);
        waitVisibility(calculatorEmailField);
        waitElementIsClickable(calculatorEmailField);
        calculatorEmailField.click();
        calculatorEmailField.sendKeys(StringUtils.getGeneratedTemporaryEmail());
        waitVisibility(sendEmailButton);
        waitElementIsClickable(sendEmailButton);
        sendEmailButton.click();
        logger.info("Created temporary email is typed in Email form");
        return new GoogleCloudCalculatorPage(driver);

    }

    public YopMailPage goToYopMailPage(){
        driver.switchTo().defaultContent();
        driver.switchTo().newWindow(WindowType.TAB);
        return new YopMailPage(driver);
    }

    public YopMailPage switchToPreviousPage(){
        ArrayList<String>tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window((tabs.get(1)));
        return new YopMailPage(driver);
    }
}
