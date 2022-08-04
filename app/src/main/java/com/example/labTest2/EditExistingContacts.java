package com.example.labTest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditExistingContacts extends AppCompatActivity {

    Contacts editContacts;
    ImageView imgEContact;
    TextView tvContactName;
    EditText etEEmail, etEPhoneNo, etEAddress, etEDOB;
    FloatingActionButton fabSaveBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_existing_contacts);

        init();
        ///index of item click
        int editContactID = getIntent().getIntExtra("contactID", -1);
        int index = getIntent().getIntExtra("positionEdit", -1);


        editContacts = new Contacts();
        DBHandler dbHandler = new DBHandler(this);
        editContacts = dbHandler.getContact(editContactID);
        setData();

        fabSaveBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isValidate()) {
                    editContacts.setMobileNo(etEPhoneNo.getText().toString());
                    editContacts.setAddress(etEAddress.getText().toString());
                    editContacts.setEmail(etEEmail.getText().toString());

                    dbHandler.updateContact(editContacts);
                    Intent intent = new Intent(EditExistingContacts.this, com.example.labTest2.MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }


    private void setData() {
        tvContactName.setText(editContacts.getName());
        etEEmail.setText(editContacts.getEmail(), TextView.BufferType.EDITABLE);
        etEPhoneNo.setText(editContacts.getMobileNo(), TextView.BufferType.EDITABLE);
        etEAddress.setText(editContacts.getAddress(), TextView.BufferType.EDITABLE);
    }


    private void init() {
        tvContactName = findViewById(R.id.tvContactName);
        imgEContact = findViewById(R.id.imgEContact);
        etEEmail = findViewById(R.id.editEmail);
        etEPhoneNo = findViewById(R.id.editPhoneNo);
        etEAddress = findViewById(R.id.editAddress);

        fabSaveBack = findViewById(R.id.fabSaveBack);
    }

    private boolean isValidate() {

        boolean flag = true;

        String email = etEEmail.getText().toString();
        String phoneNo = etEPhoneNo.getText().toString();
        String address = etEAddress.getText().toString();
        String dob = etEDOB.getText().toString();

        if (email.isEmpty() || !email.contains("@") || email.equals(" ")) {
            etEEmail.setError("Email");
            flag = false;
        }
        if (phoneNo.isEmpty() || phoneNo.equals(" ")) {
            etEPhoneNo.setError("Phone No");
            flag = false;
        }
        if (address.isEmpty() || address.equals(" ")) {
            etEAddress.setError("Address");
            flag = false;
        }
        if (dob.isEmpty() || dob.equals(" ")) {
            etEDOB.setError("Date of Birth");
            flag = false;
        }
        return flag;
    }

}