package com.example.rodrigo.cajaamor2017.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rodrigo.cajaamor2017.Objects.Caja;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodrigo on 11/26/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "CajaAmor.sqlite";
    public static final String DBLOCATION = "/data/data/com.example.rodrigo.cajaamor2017/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public List<Caja> getListDevueltas() {
        Caja Caja = null;
        List<Caja> CajaList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("select * from CajaAmor where Status =\"Devuelta\"", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Caja = new Caja(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));
            CajaList.add(Caja);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return CajaList;
    }
    public String getCountEntregadas()
    {
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("select count(*) from CajaAmor where Status =\"Devuelta\"", null);
        cursor.moveToFirst();
        String resul = cursor.getString(0);
        cursor.close();
        closeDatabase();
        return resul;
    }
    public String getCountPendientes()
    {
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("select count(*) from CajaAmor where Status =\"Entregada\"", null);
        cursor.moveToFirst();
        String resul = cursor.getString(0);
        cursor.close();
        closeDatabase();
        return resul;
    }
    public List<Caja> getListPendientes() {
        Caja Caja = null;
        List<Caja> CajaList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("select * from CajaAmor where Status = null or Status =\"Entregada\" ", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Caja = new Caja(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));
            CajaList.add(Caja);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return CajaList;
    }

    public Caja getCajaById(String id) {
        Caja Caja = null;
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM CajaAmor WHERE _id =?", new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        Caja = new Caja(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));
        //Only 1 resul
        cursor.close();
        closeDatabase();
        return Caja;
    }
    public Caja getCajaByNumber(String id) {
        Caja Caja = null;
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM CajaAmor WHERE Numero =?", new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        Caja = new Caja(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));
        //Only 1 resul
        cursor.close();
        closeDatabase();
        return Caja;
    }
    public long updateCaja(String id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Status", "Devuelta");
        contentValues.put("Entregada_a", "");
        contentValues.put("Telefono", "");
        String[] whereArgs = {(id)};
        openDatabase();
        long returnValue = mDatabase.update("CajaAmor",contentValues, "_id=?", whereArgs);
        closeDatabase();
        return returnValue;
    }

    public long manualUpdateCaja(String id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Status", "Devuelta");
        contentValues.put("Entregada_a", "");
        contentValues.put("Telefono", "");
        String[] whereArgs = {(id)};
        openDatabase();
        long returnValue = mDatabase.update("CajaAmor",contentValues, "Numero=?", whereArgs);
        closeDatabase();
        return returnValue;
    }



}
