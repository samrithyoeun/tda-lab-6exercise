package com.fluffy.samrith.lab6exercise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by samrith on 2/12/17.
 */


public class DBAdapter {
    static final String KEY_ROWID="_id";
    static final String KEY_NAME ="name";
    static final String KEY_EMAIL = "email";
    static final String TAG = "DBAdapter";
    static final String DATABASE_NAME = "MyDB";
    static final String DATABASE_TABLE = "contacts";
    static final int DATABASE_VERSION = 2;
    static final String DATABASE_CREATE = "create table contacts (_id integer primary key autoincrement, " + "name text not null, email text not null);";
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;


    public DBAdapter(Context context) {
        this.context = context;
        DBHelper = new DatabaseHelper(context);
    }

    //open a database
    public DBAdapter open()throws  SQLException{
        db =  DBHelper.getWritableDatabase();
        return this;
    }

    //close a database
    public void close(){
        DBHelper.close();
    }

    //isnert a contace to database
    public long insertContact(String name, String email){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME,name);
        initialValues.put(KEY_EMAIL,email);
        return db.insert(DATABASE_TABLE,null,initialValues);
    }

    //delete a contact
    public boolean deleteContact(long rowID){
        return db.delete(DATABASE_TABLE,KEY_ROWID+"="+rowID,null)>0;
    }


    //retrieve all conatcts
    public Cursor getAllContacts(){
        return db.query(DATABASE_TABLE,new String[]{KEY_ROWID,KEY_NAME,KEY_EMAIL},null,null,null,null,null);
    }

    //retreive a particular contact
    public Cursor getContact(long rowID) throws  SQLException{
        Cursor mCursor =db.query(DATABASE_TABLE,new String[]{KEY_ROWID,KEY_NAME,KEY_EMAIL,},KEY_ROWID+"="+rowID,null,null,null,null,null);
        if(mCursor!=null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //update a conctact
    public boolean updateContact(long rowID, String name, String email){
        ContentValues args = new ContentValues();
        args.put(KEY_NAME,name);
        args.put(KEY_EMAIL,email);
        return db.update(DATABASE_TABLE,args,KEY_ROWID+"="+rowID,null)>0;

    }

    //inner class
    public class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }




    }
}

