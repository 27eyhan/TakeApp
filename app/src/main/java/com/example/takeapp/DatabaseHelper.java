package com.example.takeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "HabitApp.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRSTNAME = "firstname";
    private static final String COLUMN_LASTNAME = "lastname";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FIRSTNAME + " TEXT,"
                + COLUMN_LASTNAME + " TEXT,"
                + COLUMN_EMAIL + " TEXT UNIQUE,"
                + COLUMN_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    public boolean checkUserExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID};
        String selection = COLUMN_EMAIL + "=?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void printAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_FIRSTNAME, COLUMN_LASTNAME, COLUMN_EMAIL};
        Cursor cursor = db.query(TABLE_USERS, columns, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int idIndex = cursor.getColumnIndex(COLUMN_ID);
                int firstnameIndex = cursor.getColumnIndex(COLUMN_FIRSTNAME);
                int lastnameIndex = cursor.getColumnIndex(COLUMN_LASTNAME);
                int emailIndex = cursor.getColumnIndex(COLUMN_EMAIL);

                // Check if the column exists in the cursor
                if (idIndex >= 0 && firstnameIndex >= 0 && lastnameIndex >= 0 && emailIndex >= 0) {
                    int id = cursor.getInt(idIndex);
                    String firstname = cursor.getString(firstnameIndex);
                    String lastname = cursor.getString(lastnameIndex);
                    String email = cursor.getString(emailIndex);

                    // Print or log the user data
                    Log.d("UserData", "ID: " + id + ", First Name: " + firstname + ", Last Name: " + lastname + ", Email: " + email);
                } else {
                    Log.e("UserData", "One or more columns not found in cursor");
                }
            }
            cursor.close();
        }
    }

    public boolean addUser(String firstname, String lastname, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, firstname);
        values.put(COLUMN_LASTNAME, lastname);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);

        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result != 0;
    }

    public Cursor getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_PASSWORD}; // Ensure that this array includes the "password" column
        String selection = COLUMN_EMAIL + "=?";
        String[] selectionArgs = {email};
        return db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
    }
}
