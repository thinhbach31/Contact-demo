package com.example.admin.contact.data;

public class Contact {

    private String mID;
    private String mContactName;
    private String mContactNumber;
    private String mImageContact;

    public Contact(String ID, String contactName, String contactNumber, String imageContact) {
        mID = ID;
        mContactName = contactName;
        mContactNumber = contactNumber;
        mImageContact = imageContact;
    }

    public String getID() {
        return mID;
    }

    public void setID(String ID) {
        mID = ID;
    }

    public String getContactName() {
        return mContactName;
    }

    public void setContactName(String contactName) {
        mContactName = contactName;
    }

    public String getContactNumber() {
        return mContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        mContactNumber = contactNumber;
    }

    public String getImageContact() {
        return mImageContact;
    }

    public void setImageContact(String imageContact) {
        mImageContact = imageContact;
    }
}
