package com.example.admin.contact.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.contact.R;
import com.example.admin.contact.data.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<Contact> mContactList;

    public ContactAdapter(List<Contact> contactList) {
        mContactList = contactList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.contact_items, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Contact contact = mContactList.get(i);
        viewHolder.mTextContactName.setText(contact.getContactName());
        viewHolder.mTextContactNumber.setText(contact.getContactNumber());
        try {
            viewHolder.mImageContact.setImageURI(Uri.parse(contact.getImageContact()));
        } catch (Exception e) {
            e.printStackTrace();
            viewHolder.mImageContact.setImageResource(R.drawable.ic_account_box_black_24dp);
        }
    }

    @Override
    public int getItemCount() {
        return (mContactList == null) ? 0 : mContactList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextContactName;
        TextView mTextContactNumber;
        ImageView mImageContact;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextContactName = itemView.findViewById(R.id.text_contactName);
            mTextContactNumber = itemView.findViewById(R.id.text_contactNumber);
            mImageContact = itemView.findViewById(R.id.image_contact);

        }
    }
}
