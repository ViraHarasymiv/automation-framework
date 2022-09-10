package test;

import model.User;
import org.testng.annotations.Test;
import page.GoogleCloudPage;
import service.UserCreator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class SearchFieldTest extends CommonConditions {

    @Test
    public void checkThatSearchResultsContainFirstTwoWordsOfSearchInformation() {
        User testUser = UserCreator.withCredentialsFromProperty();
        List<String> listOfFirstTwoWordsOfSearchInformation = new GoogleCloudPage(driver)
                .openPage()
                .clickOnSearchButton()
                .typeSearchInformation(testUser)
                .openPage()
                .getSearchInformation(testUser);
        assertThat(listOfFirstTwoWordsOfSearchInformation, everyItem(startsWith(testUser.getSearchInformation().substring(0, 1))));
    }
}
