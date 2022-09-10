package util;

import model.User;
import service.UserCreator;

public class StringUtils {

    private static final String SECOND_PART_OF_TEMPORARY_EMAIL = "@yopmail.com";


    public static String getGeneratedTemporaryEmail(){
        User testUser = UserCreator.withCredentialsFromProperty();
        return testUser.getTemporaryEmail() + SECOND_PART_OF_TEMPORARY_EMAIL;
    }
}
