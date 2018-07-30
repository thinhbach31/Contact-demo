package com.example.admin.contact.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.admin.contact.R;
import com.example.admin.contact.adapter.ContactAdapter;
import com.example.admin.contact.data.Contact;
import com.example.admin.contact.data.ContactProvider;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Contact> mContactArrayList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final int REQUEST_CONTACT = 1;

    public static final String OK = "Ok";
    public static final String CANCEL = "Cancel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        mRecyclerView = findViewById(R.id.recyclerView_contactRecycler);

        if (ContextCompat.checkSelfPermission(ContactActivity.this,
                Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.permission_required);
            builder.setPositiveButton(OK, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions(ContactActivity.this,
                            new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CONTACT);
                }
            });
            builder.setNegativeButton(CANCEL, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CONTACT);
        }
    }

    @Override
    public void onRequestPermissionsResult
            (int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CONTACT:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadRecyclerView();
                } else {
                    Toast.makeText(this, String.valueOf(R.string.permission_denied),
                            Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void loadRecyclerView() {

        mLayoutManager = new LinearLayoutManager(ContactActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //add divider
        DividerItemDecoration dividerItemDecoration = new
                DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        mContactArrayList = ContactProvider.getContact(this);
        mAdapter = new ContactAdapter(mContactArrayList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
