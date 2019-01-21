package myapp.com.vedio;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "vedioDB.db";
    private static final int DB_VER = 1;

    // table names
    private static final String TABLE_CUSTOMER = "customer";
    private static final String TABLE_MOVIE = "movie";


    // field names.
    public static final String CUSTOMER_NAME = "customer_name";
    public static final String MOVIE_TYPE = "movie_type";
    public static final String RECEIVED_DATE = "received_date";
    public static final String RETURN_DATE = "return_date";
    public static final String MOBILE_NO = "mobile_no";

    public static final String PRICE = "price";



    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CUSTOMER);
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        onCreate(db);
    }




   public void createCustomerTable(){
       SQLiteDatabase db = this.getWritableDatabase();
       String CREATE_CUSTOMER_TABLE = "Create table IF NOT EXISTS " + TABLE_CUSTOMER + " ("+ CUSTOMER_NAME + " TEXT, " +
               MOBILE_NO + " INTEGER ," + MOVIE_TYPE + " TEXT, " + RECEIVED_DATE + " TEXT, " + RETURN_DATE + " TEXT);";
       db.execSQL(CREATE_CUSTOMER_TABLE);

   }

    public void createMovieTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String CREATE_MOVIE_TABLE = "Create table IF NOT EXISTS " + TABLE_MOVIE + "("
                + MOVIE_TYPE + " TEXT PRIMARY KEY," + PRICE + " DOUBLE " + ")";
        db.execSQL(CREATE_MOVIE_TABLE);

    }


    public void insertCustomer(String customer_name, String mobile_no, String movie_type, String received_date,String return_date) {
        SQLiteDatabase db = this.getWritableDatabase();
        String INSERT_CUSTOMER = "insert into " + TABLE_CUSTOMER + " values('" + customer_name + "','"
                + mobile_no + "','" + movie_type + "','" + received_date + "','" + return_date +  "')";
        db.execSQL(INSERT_CUSTOMER);
    }


    public void updateCustomer(String mobile_no,String return_date) {
        SQLiteDatabase db = this.getWritableDatabase();
        String UPDATED_CUSTOMER = "UPDATE " + TABLE_CUSTOMER +" SET " +  RETURN_DATE + "='" + return_date + "' WHERE " + MOBILE_NO + "='" + mobile_no + "'";
        db.execSQL(UPDATED_CUSTOMER);

    }

    public void insertMovie( String movie_type,String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        String INSERT_MOVIE = "insert or replace into " + TABLE_MOVIE + " values('" + movie_type + "','" + price + "')";
        db.execSQL(INSERT_MOVIE);
    }


    public Double getMoviePrice(String movie_type){

        Double moviePrs = 0.0;
        String priceOfMovie = "SELECT PRICE FROM " + TABLE_MOVIE + " WHERE " + MOVIE_TYPE + " = '" + movie_type + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(priceOfMovie, null);
        if (cursor.moveToFirst()) {
            do {
                moviePrs = (cursor.getDouble(cursor.getColumnIndex(PRICE)));
            } while (cursor.moveToNext());
        }
        return moviePrs;
    }

    public ArrayList<String> getreceivedDate(String mobile_no,String movie_type) {
        String receDate = null;
        ArrayList<String> arr = new ArrayList<String>();
        String receivedDate = "SELECT RECEIVED_DATE FROM " + TABLE_CUSTOMER + " WHERE " + MOBILE_NO + " = '" + mobile_no + "' AND " + MOVIE_TYPE + " = '" + movie_type+ "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(receivedDate, null);
        if (cursor.moveToFirst()) {
            do {
                receDate = (cursor.getString(cursor.getColumnIndex(RECEIVED_DATE)));
                arr.add(String.valueOf(receDate));
            } while (cursor.moveToNext());
        }
        return arr;
    }


    public ArrayList<String> getreturnDte(String mobile_no, String movie_type){
        String retDate = null;
        ArrayList<String> ar = new ArrayList<String>();
        String returnDte = "SELECT RETURN_DATE FROM " + TABLE_CUSTOMER + " WHERE " + MOBILE_NO + " = '" + mobile_no + "' AND " + MOVIE_TYPE + " = '" + movie_type+ "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(returnDte, null);
        if (cursor.moveToFirst()) {
            do {
                retDate = (cursor.getString(cursor.getColumnIndex(RETURN_DATE)));
                ar.add(String.valueOf(retDate));
            } while (cursor.moveToNext());
        }
        return ar;
        }

}
