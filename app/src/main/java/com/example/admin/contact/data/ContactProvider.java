package com.example.admin.contact.data;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class ContactProvider {

    public static ArrayList<Contact> getContact(Context context) {
        ArrayList<Contact> contactArrayList = new ArrayList<>();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        Cursor cursor = context.getContentResolver()
                .query(uri,
                        null,
                        null,
                        null,
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

        int indexID = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID);
        int indexName = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int indexPhone = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        int indexPhoto = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String id = cursor.getString(indexID);
                String name = cursor.getString(indexName);
                String phone = cursor.getString(indexPhone);
                String photo = cursor.getString(indexPhoto);
                contactArrayList.add(new Contact(id, name, phone, photo));
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return contactArrayList;
    }

}
