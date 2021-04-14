package com.example.dailymenu;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //Truy vấn không trả kết quá:CREATE.DELETE,UPDATE...
    public void NonQueryData(String sql){
        SQLiteDatabase database= getWritableDatabase();
        database.execSQL(sql);
    }
    //
    public void INSERT_CONGTHUC(String tenmonan, byte[] hinhanh){
        SQLiteDatabase database= getWritableDatabase();
        String sql="INSERT INTO CONGTHUC VALUES(null,?,?)";
        SQLiteStatement statement=database.compileStatement(sql);//Biên dịch câu lệnh SQL ở bên trên
        statement.clearBindings();//Clears all existing bindings.
        statement.bindString(1,tenmonan);
        statement.bindBlob(2,hinhanh);
        statement.executeInsert();
    }
    public void INSERT_MONAN(String tenmonan, byte[] hinhanh, int idCT){
        SQLiteDatabase database= getWritableDatabase();
        String sql="INSERT INTO MONAN VALUES(null,?,?,?)";
        SQLiteStatement statement=database.compileStatement(sql);//Biên dịch câu lệnh SQL ở bên trên
        statement.clearBindings();//Clears all existing bindings.
        statement.bindString(1,tenmonan);
        statement.bindBlob(2,hinhanh);
        statement.bindDouble(3,idCT);
        statement.executeInsert();
    }
    //Truy vấn trả lại kết quả:SELECT...
    public Cursor QueryData(String sql){
        SQLiteDatabase database= getReadableDatabase();
        return database.rawQuery(sql,null);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
