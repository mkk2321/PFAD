package com.example.pfad1.entities.user;

import java.util.Date;

public class UserEntity {
    protected String id;
    protected String email ;
    protected String password;
    protected Date createdAt;
    protected Date updatedAt;
    protected String name;
    protected String birth;
    protected int gender = 0;
    protected String contactCompany;
    protected String contactFirst;
    protected String contactSecond;
    protected String contactThird;
    protected String addressPostal;
    protected String addressPrimary;
    protected String addressSecondary;
    protected boolean isAdmin;
    protected boolean isEmailVerified;
    protected boolean isSuspended;
    protected boolean isDeleted;
    protected boolean isSmsMarketing;
    protected boolean isEmailMarketing;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;

    }

    public String getContactCompany() {
        return contactCompany;
    }

    public void setContactCompany(String contactCompany) {
        this.contactCompany = contactCompany;
    }

    public String getContactFirst() {
        return contactFirst;
    }

    public void setContactFirst(String contactFirst) {
        this.contactFirst = contactFirst;
    }

    public String getContactSecond() {
        return contactSecond;
    }

    public void setContactSecond(String contactSecond) {
        this.contactSecond = contactSecond;
    }

    public String getContactThird() {
        return contactThird;
    }

    public void setContactThird(String contactThird) {
        this.contactThird = contactThird;
    }

    public String getAddressPostal() {
        return addressPostal;
    }

    public void setAddressPostal(String addressPostal) {
        this.addressPostal = addressPostal;
    }

    public String getAddressPrimary() {
        return addressPrimary;
    }

    public void setAddressPrimary(String addressPrimary) {
        this.addressPrimary = addressPrimary;
    }

    public String getAddressSecondary() {
        return addressSecondary;
    }

    public void setAddressSecondary(String addressSecondary) {
        this.addressSecondary = addressSecondary;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isSmsMarketing() {
        return isSmsMarketing;
    }

    public void setSmsMarketing(boolean smsMarketing) {
        isSmsMarketing = smsMarketing;
    }

    public boolean isEmailMarketing() {
        return isEmailMarketing;
    }

    public void setEmailMarketing(boolean emailMarketing) {
        isEmailMarketing = emailMarketing;
    }
}
