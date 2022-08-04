package com.example.labTest2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ContactsAdapter.ItemClicked { //
    //Recycler View For FavContacy & All Contacts

    RecyclerView allContactRV, favContactRV;
    //Manager For Layouts
    RecyclerView.LayoutManager allContactManager;
    //Adapters
    RecyclerView.Adapter allContactAdapter;
    //ArrayList of Contacts
    ArrayList<Contacts> contacts;
    DBHandler dbHandler, dbHandler1;
    ImageView imgEdit;
    Context context;
    FloatingActionButton addContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        TextView allContacts = findViewById(R.id.allContacts);
        imgEdit = findViewById(R.id.imgEdit);
        allContacts.setVisibility(View.INVISIBLE);
        dbHandler = new DBHandler(this);
        dbHandler1 = new DBHandler(this);
        allContactRV = findViewById(R.id.contactList);
        allContactRV.setHasFixedSize(true);
        allContactManager = new LinearLayoutManager(this);
        allContactRV.setLayoutManager(allContactManager);
        addContact = findViewById(R.id.addContact);

        contacts = new ArrayList<Contacts>();
        contacts = dbHandler.getAllContacts();

        if (!contacts.isEmpty()) {
            allContacts.setVisibility(View.VISIBLE);

        }

        allContactAdapter = new ContactsAdapter(this, contacts);
        allContactRV.setAdapter(allContactAdapter);

        addButtonClick();
        editButtonClick();
    }

    private void addButtonClick() {
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.labTest2.AddNewContact.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void editButtonClick() {

        imgEdit.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                RecyclerView rv = findViewById(R.id.contactList);
                for (int i = 0; i < rv.getChildCount(); i++) {
                    rv.getChildAt(i).findViewById(R.id.imgDel).setVisibility(View.INVISIBLE);
                }

                return true;
            }
        });

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView rv = findViewById(R.id.contactList);
                for (int i = 0; i < rv.getChildCount(); i++) {
                    rv.getChildAt(i).findViewById(R.id.imgDel).setVisibility(View.VISIBLE);
                }
            }
        });


    }

    @Override
    public void onItemClicked(int index) {
        Intent intent = new Intent(this, com.example.labTest2.ContactDetail.class);
        intent.putExtra("contact", contacts.get(index).getId());
        intent.putExtra("position", index);
        startActivity(intent);
        finish();

        Toast.makeText(this, "" + contacts.get(index).getName(), Toast.LENGTH_SHORT).show();
    }

}