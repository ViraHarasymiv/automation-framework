package model;

import java.util.Objects;

public class User {

    private String searchItem;
    private String numberOfInstances;
    private String email;

    public User(String searchItem, String numberOfInstances, String email) {
        this.searchItem = searchItem;
        this.numberOfInstances = numberOfInstances;
        this.email = email;
    }

    public String getSearchItem() {
        return searchItem;
    }

    public void setSearchItem(String searchItem) {
        this.searchItem = searchItem;
    }

    public String getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(String numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return numberOfInstances == user.numberOfInstances && Objects.equals(searchItem, user.searchItem) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchItem, numberOfInstances, email);
    }
}
