package com.example.labTest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContactDetail extends AppCompatActivity {

    Contacts editContacts;
    TextView tvCContactName;
    TextView etCEmail, etCPhoneNo, etCAddress;
    FloatingActionButton fabBack, fabEditContact;
    int index;
    int contactIdToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        contactIdToEdit = getIntent().getIntExtra("contact", -1); //give id of editing contact
        //position of item on which item clicked
        index = getIntent().getIntExtra("position", -1);

        init();
        editContacts = new Contacts();
        DBHandler dbHandler = new DBHandler(this);
        editContacts = dbHandler.getContact(contactIdToEdit); //get Contact to edit on which clicked
        setData(); //set Data on Detail Activity XML
        buttonClick();

    }

    private void buttonClick() {

        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ContactDetail.this, com.example.labTest2.MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        //Functionality of EditContact
        fabEditContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ContactDetail.this, com.example.labTest2.EditExistingContacts.class);
                intent2.putExtra("contactID", editContacts.getId());
                intent2.putExtra("positionEdit", index);
                startActivity(intent2);
                finish();
            }
        });


    }

    private void setData() {

        tvCContactName.setText(editContacts.getName());
        etCEmail.setText(editContacts.getEmail());
        etCPhoneNo.setText(editContacts.getMobileNo());
        etCAddress.setText(editContacts.getAddress());
    }

    private void init() {
        tvCContactName = findViewById(R.id.tvCContactName);
        etCEmail = findViewById(R.id.email);
        etCPhoneNo = findViewById(R.id.phoneNo);
        etCAddress = findViewById(R.id.address);
        fabBack = findViewById(R.id.fabBack);
        fabEditContact = findViewById(R.id.fabEditContact);
    }
}