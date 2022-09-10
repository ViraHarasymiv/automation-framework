package model;

import java.util.Objects;

public class User {

    private String searchInformation;
    private String numberOfInstances;
    private String machineType;
    private String datacenterLocation;
    private String committedUsage;
    private String temporaryEmail;
    private String actualPrice;

    public User(String searchInformation, String numberOfInstances, String machineType,
                String datacenterLocation, String committedUsage, String temporaryEmail, String actualPrice)
    {
        this.searchInformation = searchInformation;
        this.numberOfInstances = numberOfInstances;
        this.machineType = machineType;
        this.datacenterLocation = datacenterLocation;
        this.committedUsage = committedUsage;
        this.temporaryEmail = temporaryEmail;
        this.actualPrice = actualPrice;
    }

    public String getSearchInformation() {
        return searchInformation;
    }

    public void setSearchInformation(String searchInformation) {
        this.searchInformation = searchInformation;
    }

    public String getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(String numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    public String getMachineType() { return machineType;}

    public void setMachineType(String machineType) { this.machineType = machineType; }

    public String getDatacenterLocation() { return datacenterLocation;}

    public void setDatacenterLocation(String datacenterLocation) { this.datacenterLocation = datacenterLocation;}

    public String getCommittedUsage() { return committedUsage;}

    public String getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(String actualPrice) {
        this.actualPrice = actualPrice;
    }

    public void setCommittedUsage(String committedUsage) { this.committedUsage = committedUsage; }

    public String getTemporaryEmail() { return temporaryEmail;}

    public void setTemporaryEmail(String temporaryEmail) { this.temporaryEmail = temporaryEmail; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(searchInformation, user.searchInformation) && Objects.equals(numberOfInstances, user.numberOfInstances) && Objects.equals(machineType, user.machineType) && Objects.equals(datacenterLocation, user.datacenterLocation) && Objects.equals(committedUsage, user.committedUsage) && Objects.equals(temporaryEmail, user.temporaryEmail) && Objects.equals(actualPrice, user.actualPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchInformation, numberOfInstances, machineType, datacenterLocation, committedUsage, temporaryEmail, actualPrice);
    }
}
