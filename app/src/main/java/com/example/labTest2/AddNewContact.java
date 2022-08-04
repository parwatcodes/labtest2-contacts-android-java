package com.example.labTest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Random;

public class AddNewContact extends AppCompatActivity {

    //public static int index;
    DBHandler dbHandler;
    ImageView imgAddContact;
    EditText etAddName, etAddEmail, etAddPhoneNo, etAddAddress;
    Button btnAddContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);
        init();


        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidate()) {

                    Contacts contacts;
                    contacts = new Contacts(etAddName.getText().toString(), etAddPhoneNo.getText().toString(), etAddEmail.getText().toString(), etAddAddress.getText().toString());

                    System.out.println("contaaa" + contacts);

                    //Add Contact - in Database
                    dbHandler = new DBHandler(AddNewContact.this);
                    dbHandler.addContacts(contacts);

                    startActivity(new Intent(AddNewContact.this, com.example.labTest2.MainActivity.class));
                    finish();
                }
            }
        });
    }

    boolean isValidate() {
        boolean flag = true;

        String name = etAddName.getText().toString();
        String email = etAddEmail.getText().toString();
        String phoneNo = etAddPhoneNo.getText().toString();
        String address = etAddAddress.getText().toString();

        if (name.isEmpty() || name.equals(" ")) {
            etAddName.setError("Name");
            flag = false;
        }
        if (email.isEmpty() || !email.contains("@") || email.equals(" ")) {
            etAddEmail.setError("Email");
            flag = false;
        }
        if (phoneNo.isEmpty() || phoneNo.equals(" ")) {
            etAddPhoneNo.setError("Phone No");
            flag = false;
        }
        if (address.isEmpty() || address.equals(" ")) {
            etAddAddress.setError("Address");
            flag = false;
        }

        return flag;
    }

    private void init() {
        etAddName = findViewById(R.id.addName);
        etAddEmail = findViewById(R.id.addEmail);
        etAddPhoneNo = findViewById(R.id.addPhoneNo);
        etAddAddress = findViewById(R.id.addAddress);

        btnAddContact = findViewById(R.id.btnAddContact);
    }
}