package test;

import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.GoogleCloudPage;
import service.UserCreator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class searchFieldTests extends CommonConditions {

    @Test
    public void checkThatResultsContainsFirstTwoWordsOfSearchItem() {
        User testUser = UserCreator.withCredentialsFromProperty();
        List<String> listOfFirstTwoWordsOfSearchItems = new GoogleCloudPage(driver)
                .openPage()
                .clickOnSearchButton()
                .typeSearchItem(testUser)
                .openPage()
                .firstTwoWordsOfSearchItem(testUser);
        assertThat(listOfFirstTwoWordsOfSearchItems, everyItem(startsWith(testUser.getSearchItem().substring(0, 1))));
    }

    @Test
    public void checkThatResultsAreNotEmpty() {
        User testUser = UserCreator.withCredentialsFromProperty();
        boolean testResults = new GoogleCloudPage(driver)
                .openPage()
                .clickOnSearchButton()
                .typeSearchItem(testUser)
                .openPage()
                .hasSearchResults();
        Assert.assertTrue(testResults);
    }
}
