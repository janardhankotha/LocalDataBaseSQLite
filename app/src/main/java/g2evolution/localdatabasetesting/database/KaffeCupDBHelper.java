package g2evolution.localdatabasetesting.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import g2evolution.localdatabasetesting.logging.L;


/**
 * Created by brajabasi on 08-03-2016.
 */
public class KaffeCupDBHelper extends SQLiteOpenHelper {
        public static final String TABLE_MNC = "buyer_mnc";
        public static final String TABLE_LOCAL = "buyer_local";
        public static final String TABLE_MNC_RATE = "buyer_mnc_rate";
        public static final String TABLE_LOCAL_RATE = "buyer_local_rate";
        public static final String TABLE_ARABICA_RATE = "coffee_arabica_rate";
        public static final String TABLE_ROBUSTA_RATE = "coffee_robusta_rate";
        public static final String TABLE_FEEDS= "feeds_table";

       // Columns for MNC/LOCAL Table
        public static final String COLUMN_UID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_IMAGE_URL = "image";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_MOBILE_NUMBER = "mobile";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_STATE= "state";

        //Column for Coffee price detail
        public static final String COLUMN_UID_PARENT = "id_p";
        public static final String COLUMN_LOCATION = "location";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_FLAVOUR= "flavour";
        public static final String COLUMN_QUANTITY= "quantity";
        public static final String COLUMN_MAX_PRICE= "maxprice";
        public static final String COLUMN_MIN_PRICE= "minprice";

       // Column for market
       public static final String COLUMN_MONTH = "month";
       public static final String COLUMN_INTRENATIONAL_PRICE = "internationalprice";
       public static final String COLUMN_DOMESTIC_PRICE= "domesticprice";

       // Column for Feeds
       public static final String ROW_ID= "feeder_id";
       public static final String COLUMN_FEEDER_NAME= "feeder_name";
       public static final String COLUMN_FEEDER_TITLE= "feeder_title";
       public static final String COLUMN_FEED_DESC = "feed_description";
       public static final String COLUMN_FEED_LIKE= "feed_like";
       public static final String COLUMN_FEED_DISLIKE= "feed_dislike";
       public static final String COLUMN_FEED_VIEWS= "feed_views";
       public static final String COLUMN_FEEDER_THUMBNAIL= "feeder_thumbnail";
       public static final String COLUMN_FEED_ATTACHMENT = "feed_attachment";
       public static final String COLUMN_FEED_ATTACH_TYPE= "feed_attach_type";



    private static final String CREATE_TABLE_FEED = "CREATE TABLE " + TABLE_FEEDS + " (" +
            COLUMN_UID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ROW_ID + " TEXT," +
            COLUMN_FEEDER_NAME + " TEXT," +
            COLUMN_FEEDER_TITLE + " TEXT," +
            COLUMN_DATE + " INTEGER," +
            COLUMN_FEED_DESC + " TEXT," +
            COLUMN_FEED_LIKE + " INTEGER," +
            COLUMN_FEED_DISLIKE + " INTEGER," +
            COLUMN_FEED_VIEWS + " INTEGER," +
            COLUMN_FEEDER_THUMBNAIL + " TEXT," +
            COLUMN_FEED_ATTACHMENT + " TEXT," +
            COLUMN_FEED_ATTACH_TYPE + " INTEGER" +
            ");";

        private static final String CREATE_TABLE_MNC = "CREATE TABLE " + TABLE_MNC + " (" +
                COLUMN_UID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_IMAGE_URL + " TEXT," +
                COLUMN_ADDRESS + " TEXT," +
                COLUMN_MOBILE_NUMBER + " TEXT," +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_STATE + " TEXT" +
               ");";

    //table for local buyers
    private static final String CREATE_TABLE_LOCAL = "CREATE TABLE " + TABLE_LOCAL + " (" +
            COLUMN_UID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_NAME + " TEXT," +
            COLUMN_IMAGE_URL + " TEXT," +
            COLUMN_ADDRESS + " TEXT," +
            COLUMN_MOBILE_NUMBER + " INTEGER," +
            COLUMN_EMAIL + " TEXT," +
            COLUMN_STATE + " TEXT" +
            ");";

    private static final String CREATE_TABLE_MNC_RATE = "CREATE TABLE " + TABLE_MNC_RATE+ " (" +
            COLUMN_UID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_UID_PARENT + " TEXT," +
            COLUMN_LOCATION + " TEXT," +
            COLUMN_DATE + " INTEGER," +
            COLUMN_FLAVOUR + " TEXT," +
            COLUMN_QUANTITY + " TEXT," +
            COLUMN_MAX_PRICE + " INTEGER," +
            COLUMN_MIN_PRICE + " INTEGER" +
            ");";

    private static final String CREATE_TABLE_LOCAL_RATE = "CREATE TABLE " + TABLE_LOCAL_RATE+ " (" +
            COLUMN_UID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_UID_PARENT + " TEXT," +
            COLUMN_LOCATION + " TEXT," +
            COLUMN_DATE + " INTEGER," +
            COLUMN_FLAVOUR + " TEXT," +
            COLUMN_QUANTITY + " TEXT," +
            COLUMN_MAX_PRICE + " INTEGER," +
            COLUMN_MIN_PRICE + " INTEGER" +
            ");";

    private static final String CREATE_TABLE_ARABICA = "CREATE TABLE " + TABLE_ARABICA_RATE + " (" +
            COLUMN_UID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_MONTH + " TEXT," +
            COLUMN_DOMESTIC_PRICE + " TEXT," +
            COLUMN_INTRENATIONAL_PRICE + " TEXT" +
            ");";

    private static final String CREATE_TABLE_ROBUST = "CREATE TABLE " + TABLE_ROBUSTA_RATE + " (" +
            COLUMN_UID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_MONTH + " TEXT," +
            COLUMN_DOMESTIC_PRICE + " TEXT," +
            COLUMN_INTRENATIONAL_PRICE + " TEXT" +
            ");";

    private static final String DB_NAME = "kaffecup_db";
        private static final int DB_VERSION = 1;
        private Context mContext;

        public KaffeCupDBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            mContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE_MNC);
                db.execSQL(CREATE_TABLE_LOCAL);
                db.execSQL(CREATE_TABLE_MNC_RATE);
                db.execSQL(CREATE_TABLE_LOCAL_RATE);
                db.execSQL(CREATE_TABLE_ARABICA);
                db.execSQL(CREATE_TABLE_ROBUST);
                db.execSQL(CREATE_TABLE_FEED);
                L.m("create table KaffeCup executed");
            } catch (SQLiteException exception) {
                L.t(mContext, exception + "");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                L.m("upgrade table box office executed");
                db.execSQL(" DROP TABLE " + TABLE_MNC + " IF EXISTS;");
                db.execSQL(" DROP TABLE " + TABLE_LOCAL + " IF EXISTS;");
                db.execSQL(" DROP TABLE " + TABLE_MNC_RATE + " IF EXISTS;");
                db.execSQL(" DROP TABLE " + TABLE_LOCAL_RATE + " IF EXISTS;");
                db.execSQL(" DROP TABLE " + TABLE_ARABICA_RATE + " IF EXISTS;");
                db.execSQL(" DROP TABLE " + TABLE_ROBUSTA_RATE + " IF EXISTS;");
                db.execSQL(" DROP TABLE " + TABLE_FEEDS+ " IF EXISTS;");
                onCreate(db);
            } catch (SQLiteException exception) {
                L.t(mContext, exception + "");
            }
        }

}
