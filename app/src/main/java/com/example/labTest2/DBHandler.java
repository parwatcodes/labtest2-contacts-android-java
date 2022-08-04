package com.example.labTest2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DBHandler extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "ContactDB";
    private static final String CONTACTS_TABLE = "contacts";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String NUMBER = "mobileNo";
    private static final String EMAIL = "email";
    private static final String ADDRESS = "address";

    public DBHandler(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLES =
                "CREATE TABLE " + CONTACTS_TABLE
                        + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + NAME + " TEXT, "
                        + NUMBER + " TEXT, "
                        + EMAIL + " TEXT, "
                        + ADDRESS + " TEXT)";

        db.execSQL(CREATE_CONTACTS_TABLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + CONTACTS_TABLE;
        db.execSQL(sql);
        onCreate(db);

    }

    public void addContacts(Contacts contacts) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();

        System.out.println("inside " + contacts);

        contentValues.put(NAME, contacts.getName());
        contentValues.put(NUMBER, contacts.getMobileNo());
        contentValues.put(EMAIL, contacts.getEmail());
        contentValues.put(ADDRESS, contacts.getAddress());

        sqLiteDatabase.insert(CONTACTS_TABLE, null, contentValues);
        sqLiteDatabase.close();
    }

    public Contacts getContact(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(
                CONTACTS_TABLE,
                new String[]{ID, NAME, NUMBER, EMAIL, ADDRESS},
                ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null
        );

        Contacts contacts;
        if (cursor != null) {
            cursor.moveToFirst();
            contacts = new Contacts((cursor.getInt(0))
                    , cursor.getString(1)
                    , cursor.getString(2)
                    , cursor.getString(3)
                    , cursor.getString(4));
            return contacts;
        } else {
            return null;
        }
    }

    public ArrayList<Contacts> getAllContacts() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Contacts> contacts = new ArrayList<>();
        String query = "SELECT * FROM " + CONTACTS_TABLE;

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Contacts contact = new Contacts();
                contact.setId(cursor.getInt(0));
                contact.setName(cursor.getString(cursor.getColumnIndex("name")));
                contact.setMobileNo(cursor.getString(cursor.getColumnIndex("mobileNo")));
                contact.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                contact.setAddress(cursor.getString(4));

                contacts.add(contact);
            }
            while (cursor.moveToNext());
        }

        System.out.println("----------" + contacts);
        return contacts;
    }

    public int updateContact(Contacts contacts) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, contacts.getName());
        contentValues.put(NUMBER, contacts.getMobileNo());
        contentValues.put(EMAIL, contacts.getEmail());
        contentValues.put(ADDRESS, contacts.getAddress());


//when edit please change the ID+"=?" to "Iid=?" This was error maybe
        return sqLiteDatabase.update
                (CONTACTS_TABLE,
                        contentValues,
                        ID + "=?",
                        new String[]{String.valueOf(contacts.getId())}
                );
    }

    //when edit please change the ID+"=?" to "id=?" This was error maybe and this.getWritableDatabase()
    public void deleteContact(Contacts contacts) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.delete(CONTACTS_TABLE, ID + "=?", new String[]{String.valueOf(contacts.getId())});
        sqLiteDatabase.close();
    }

    public int getContactCount() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + CONTACTS_TABLE;

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor.getCount();
    }


}
