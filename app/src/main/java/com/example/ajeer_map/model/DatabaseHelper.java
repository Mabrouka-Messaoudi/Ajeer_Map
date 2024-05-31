package com.example.ajeer_map.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 2; // Incremented the version number
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PHONE_NUMBER = "phone_number";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_JOB = "job";
    private static final String COLUMN_IDENTITY_CARD_PHOTO = "identity_card_photo";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PHONE_NUMBER + " TEXT, "
                + COLUMN_PASSWORD + " TEXT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_JOB + " TEXT, "
                + COLUMN_IDENTITY_CARD_PHOTO + " BLOB)";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PHONE_NUMBER, user.getPhoneNumber());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_JOB, user.getJob());
        values.put(COLUMN_IDENTITY_CARD_PHOTO, user.getIdentityCardPhoto());
        long newRowId = db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public User getUser(String phoneNumber) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_USERS,
                new String[]{COLUMN_PHONE_NUMBER, COLUMN_PASSWORD, COLUMN_NAME, COLUMN_JOB, COLUMN_IDENTITY_CARD_PHOTO},
                COLUMN_PHONE_NUMBER + "=?",
                new String[]{phoneNumber},
                null,
                null,
                null
        );

        User user = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                user = new User(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE_NUMBER)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_JOB)),
                        cursor.getBlob(cursor.getColumnIndexOrThrow(COLUMN_IDENTITY_CARD_PHOTO))
                );
            }
            cursor.close();
        }
        db.close();
        return user;
    }
}
