package service;

import model.User;

public class UserCreator {

    public static final String SEARCH_ITEM = "testdata.search.item";
    public static final String NUMBER_OF_INSTANCES = "testdata.number.of.instances";
    public static final String TESTDATA_EMAIL = "testdata.email";

    public static User withCredentialsFromProperty(){
        return new User(TestDataReader.getTestData(SEARCH_ITEM),
                TestDataReader.getTestData(NUMBER_OF_INSTANCES),
                TestDataReader.getTestData(TESTDATA_EMAIL));
    }
}
