package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public abstract class AbstractPage {

    protected WebDriver driver;

    protected abstract AbstractPage openPage();

    protected final int WAIT_TIMEOUT_SECONDS = 5000;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitVisibility(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitElementIsClickable(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public void selectValueFromDropDownList(List<WebElement> list, String text) {
        for (WebElement webElement : list) {
            waitVisibility(webElement);
            if (webElement.getText().contains(text)) {
                waitElementIsClickable(webElement);
                webElement.click();
                break;
            }
        }
    }
    public void javascriptClick(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public Actions actions(){
        return new Actions(driver);
    }

}
