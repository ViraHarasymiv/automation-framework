package test;

import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.GoogleCloudPage;
import service.UserCreator;


public class GoogleCloudCalculatorTests extends CommonConditions {

    @Test(priority = 1)
    public void checkThatEngineContentContainsSelectedValues() {
        User testUser = UserCreator.withCredentialsFromProperty();
        boolean computeEngineHasSelectedValues = new GoogleCloudPage(driver)
                .openPage()
                .clickOnSearchButton()
                .typeSearchInformation(testUser)
                .openPage()
                .selectGoogleCloudPricingCalculator()
                .activateComputerEngineSection()
                .typeNumberOfInstances(testUser)
                .selectMachineType(testUser)
                .selectDatacenterLocation(testUser)
                .selectCommittedUsage(testUser)
                .clickOnAddToEstimateButton()
                .computeEngineContentContainsSelectedValues(testUser);
        Assert.assertTrue(computeEngineHasSelectedValues);
    }

    @Test(priority = 2)
    public void checkThatTotalEstimatedCostInTheReceivedLetterCorrespondsToCloudCalculator(){
        User testUser = UserCreator.withCredentialsFromProperty();
        String receivedCost = new GoogleCloudPage(driver)
                .openPage()
                .clickOnSearchButton()
                .typeSearchInformation(testUser)
                .openPage()
                .selectGoogleCloudPricingCalculator()
                .activateComputerEngineSection()
                .typeNumberOfInstances(testUser)
                .selectMachineType(testUser)
                .selectDatacenterLocation(testUser)
                .selectCommittedUsage(testUser)
                .clickOnAddToEstimateButton()
                .goToYopMailPage()
                .openPage()
                .typeTemporaryEmail(testUser)
                .createTemporaryEmail()
                .switchToGoogleCloudCalculatorPage()
                .openPage()
                .clickOnEmailButton()
                .typeTemporaryEmail()
                .switchToPreviousPage()
                .getReceivedLetter();
        Assert.assertTrue(receivedCost.contains(testUser.getActualPrice()));
    }
}

