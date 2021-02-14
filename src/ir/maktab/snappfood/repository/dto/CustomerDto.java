package ir.maktab.snappfood.repository.dto;

import java.util.Date;

public class CustomerDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date registrationDate;
    private Long totalPurchase;

    public CustomerDto() {
    }

    public CustomerDto(String firstName, String lastName, String phoneNumber, Date registrationDate, Long totalPurchase) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
        this.totalPurchase = totalPurchase;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public Long getTotalPurchase() {
        return totalPurchase;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", registerDate=" + registrationDate +
                ", totalPurchase=" + totalPurchase +
                '}';
    }
}
