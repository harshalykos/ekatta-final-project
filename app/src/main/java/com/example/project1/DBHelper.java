package com.example.project1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserDB.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "users";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_NUMBER = "number";
    public static final String COL_EMAIL = "email";
    public static final String COL_DOB = "dob";
    public static final String COL_GENDER = "gender";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT, "
                + COL_NUMBER + " TEXT UNIQUE, "
                + COL_EMAIL + " TEXT, "
                + COL_DOB + " TEXT, "
                + COL_GENDER + " TEXT)";
        db.execSQL(createTable);
    }

    // Upgrade DB
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert User
    public boolean insertUser(String name, String number, String email, String dob, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();

        // check if user already exists
        if (checkUserExists(number)) {
            return false;
        }

        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_NUMBER, number);
        values.put(COL_EMAIL, email);
        values.put(COL_DOB, dob);
        values.put(COL_GENDER, gender);

        long result = db.insert(TABLE_NAME, null, values);
        return result != -1; // true if inserted
    }

    // Check if user exists by number
    public boolean checkUserExists(String number) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_ID},
                COL_NUMBER + "=?", new String[]{number},
                null, null, null);

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
}
