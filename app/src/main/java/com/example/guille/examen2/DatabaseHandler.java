package com.example.guille.examen2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


    public class DatabaseHandler extends SQLiteOpenHelper {
        // All Static variables
        // Database Version
        private static final int DATABASE_VERSION = 3;

        // Database Name
        private static final String DATABASE_NAME = "contactsManager";

        // Contacts table name
        private static final String TABLE_USERS = "usuarios";

        // Contacts Table Columns names
        private static final String KEY_ID = "id";
        private static final String KEY_PASS = "pass";
        private static final String KEY_EMAIL = "email";

        public DatabaseHandler(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        // Creating Tables
        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                    + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PASS + " TEXT,"
                    + KEY_EMAIL + " TEXT" + ")";
            db.execSQL(CREATE_CONTACTS_TABLE);
        }

        // Upgrading database
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

            // Create tables again
            onCreate(db);
        }

        // Adding new contact
        public void addContact(Contact contact) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_ID, contact.get_id()); // Contact Name
            values.put(KEY_PASS, contact.get_pass()); // Contact Name
            values.put(KEY_EMAIL, contact.get_email()); // ContactEmail

            // Inserting Row
            db.insert(TABLE_USERS, null, values);
            db.close(); // Closing database connection
        }

        // Getting single contact
        public Contact getContact(int id) {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID,
                            KEY_PASS, KEY_EMAIL }, KEY_ID + "=?",
                    new String[] { String.valueOf(id) }, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();

            Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2));
            // return contact
            return contact;
        }
        // Getting All Contacts
        public List<Contact> getAllContacts() {
            List<Contact> contactList = new ArrayList<Contact>();
            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_USERS;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Contact contact = new Contact();
                    contact.set_id(Integer.parseInt(cursor.getString(0)));
                    contact.set_pass(cursor.getString(1));
                    contact.set_email(cursor.getString(2));
                    // Adding contact to list
                    contactList.add(contact);
                } while (cursor.moveToNext());
            }

            // return contact list
            return contactList;
        }
    }
