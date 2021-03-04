package com.haulmont.bank.data.dto;

import java.util.Objects;
import java.util.UUID;

public class ClientDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String phoneNumber;
    private String email;
    private Integer passportNumber;

    public ClientDto() {
    }

    public ClientDto(UUID id, String firstName, String lastName,
                     String patronymic, String phoneNumber, String email,
                     Integer passportNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passportNumber = passportNumber;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(Integer passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDto clientDto = (ClientDto) o;
        return Objects.equals(id, clientDto.id) && Objects.equals(firstName, clientDto.firstName) && Objects.equals(lastName, clientDto.lastName) && Objects.equals(patronymic, clientDto.patronymic) && Objects.equals(phoneNumber, clientDto.phoneNumber) && Objects.equals(email, clientDto.email) && Objects.equals(passportNumber, clientDto.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, patronymic, phoneNumber, email, passportNumber);
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", passportNumber=" + passportNumber +
                '}';
    }
}
