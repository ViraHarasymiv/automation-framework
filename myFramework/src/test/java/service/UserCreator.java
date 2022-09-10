package service;

import model.User;

public class UserCreator {

    public static final String SEARCH_INFORMATION = "testdata.search.information";
    public static final String NUMBER_OF_INSTANCES = "testdata.number.of.instances";
    public static final String MACHINE_TYPE = "testdata.machine.type";
    public static final String DATACENTER_LOCATION = "testdata.datacenter.location";
    public static final String COMMITTED_USAGE = "testdata.committed.usage";
    public static final String TEMPORARY_EMAIL = "testdata.temporary.email";
    public static final String ACTUAL_PRICE = "testdata.actual.price";

    public static User withCredentialsFromProperty(){
        return new User(TestDataReader.getTestData(SEARCH_INFORMATION),
                TestDataReader.getTestData(NUMBER_OF_INSTANCES),
                TestDataReader.getTestData(MACHINE_TYPE),
                TestDataReader.getTestData(DATACENTER_LOCATION),
                TestDataReader.getTestData(COMMITTED_USAGE),
                TestDataReader.getTestData(TEMPORARY_EMAIL),
                TestDataReader.getTestData(ACTUAL_PRICE));
    }
}
