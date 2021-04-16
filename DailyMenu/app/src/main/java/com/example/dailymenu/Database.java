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
        SQLiteDatabase database= getWritableDatabase();//Hàm
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
    public void INSERT_CHITIETMONAN(String nd, int idMonAn){
        SQLiteDatabase database= getWritableDatabase();
        String sql="INSERT INTO CHITIETMONAN VALUES(null,?,?)";
        SQLiteStatement statement=database.compileStatement(sql);//Biên dịch câu lệnh SQL ở bên trên
        statement.clearBindings();//Clears all existing bindings.
        statement.bindString(1,nd);
        statement.bindDouble(2,idMonAn);
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
    public boolean checkUserName(String username){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM USER WHERE USER=?", new String[] {username});
        if(cursor.getCount()>0){
            return true;
        }
        else {return false;}
    }
    public boolean checkUserPassWord(String username, String password){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM USER WHERE USER=? AND PASSWORD=?",new String[] {username, password});
        if(cursor.getCount()>0){
            return true;
        }
        else {return false;}
    }
}
