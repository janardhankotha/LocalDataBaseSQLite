package g2evolution.localdatabasetesting.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import g2evolution.localdatabasetesting.entity.FeederInfo;
import g2evolution.localdatabasetesting.logging.L;
import g2evolution.localdatabasetesting.utility.CoffeeMarketData;
import g2evolution.localdatabasetesting.utility.KaffeCupUtils;

/**
 * Created by brajabasi on 08-03-2016.
 */
public class DBKaffeCup {

    private KaffeCupDBHelper mHelper;
    private SQLiteDatabase mDatabase;

    public DBKaffeCup(Context context) {
        mHelper = new KaffeCupDBHelper(context);
        mDatabase = mHelper.getWritableDatabase();
    }





    public ArrayList<CoffeeMarketData> readCoffeMarketData(String table) {
        ArrayList<CoffeeMarketData> listCoffeeMarketData = new ArrayList<>();


        //get a list of columns to be retrieved, we need all of them
        String[] columns = {KaffeCupDBHelper.COLUMN_UID,
                KaffeCupDBHelper.COLUMN_MONTH,
                KaffeCupDBHelper.COLUMN_DOMESTIC_PRICE,
                KaffeCupDBHelper.COLUMN_INTRENATIONAL_PRICE,

        };

        Cursor cursor = mDatabase.query(table, columns, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            L.m("loading entries " + cursor.getCount() + new Date(System.currentTimeMillis()));
            do {

                //create a new movie object and retrieve the data from the cursor to be stored in this movie object
                CoffeeMarketData coffeeMarketData = new CoffeeMarketData();
                //each step is a 2 part process, find the index of the column first, find the data of that column using
                //that index and finally set our blank movie object to contain our data
                coffeeMarketData.setMonth(cursor.getString(cursor.getColumnIndex(KaffeCupDBHelper.COLUMN_MONTH)));


                coffeeMarketData.setInternatinalprice(cursor.getString(cursor.getColumnIndex(KaffeCupDBHelper.COLUMN_DOMESTIC_PRICE)));
                coffeeMarketData.setDomesticprice(cursor.getString(cursor.getColumnIndex(KaffeCupDBHelper.COLUMN_INTRENATIONAL_PRICE)));


                //add the movie to the list of movie objects which we plan to return
                listCoffeeMarketData.add(coffeeMarketData);
            }
            while (cursor.moveToNext());
        }
        return listCoffeeMarketData;
    }





    public void insertFeedData(String table, ArrayList<FeederInfo> listFeedData, boolean clearPrevious) {
        if (clearPrevious) {
            deleteTable(table);
        }
        //create a sql prepared statement
        String sql = "INSERT INTO " + table + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
        //compile the statement and start a transaction
        SQLiteStatement statement = mDatabase.compileStatement(sql);
        mDatabase.beginTransaction();
        for (int i = 0; i < listFeedData.size(); i++) {
            FeederInfo currentFeedData = listFeedData.get(i);
            statement.clearBindings();
            //for a given column index, simply bind the data to be put inside that index
            /*
            statement.bindString(2, currentFeedData.getFeederName());
            statement.bindString(3, currentFeedData.getFeederTitle());
            Date dt = currentFeedData.getFeedingDate();
            statement.bindLong(4, dt == null ? -1 : dt.getTime());
            statement.bindString(5, currentFeedData.getFeedingDesription());
            statement.bindLong(6, currentFeedData.getLikeCount());
            statement.bindLong(7, currentFeedData.getDislikeCount());
            statement.bindLong(8, currentFeedData.getViewCount());
            statement.bindString(9, currentFeedData.getFeederThumbnail());
            statement.bindString(10, currentFeedData.getFeedAttachMent());
            statement.bindLong(11, currentFeedData.getFeedAttachType());
            statement.bindString(12, currentFeedData.getRowid());
*/

            statement.bindString(2, currentFeedData.getRowid());
            statement.bindString(3, currentFeedData.getFeederName());
            statement.bindString(4, currentFeedData.getFeederTitle());
            Date dt = currentFeedData.getFeedingDate();
            statement.bindLong(5, dt == null ? -1 : dt.getTime());
            statement.bindString(6, currentFeedData.getFeedingDesription());
            statement.bindLong(7, currentFeedData.getLikeCount());
            statement.bindLong(8, currentFeedData.getDislikeCount());
            statement.bindLong(9, currentFeedData.getViewCount());
            statement.bindString(10, currentFeedData.getFeederThumbnail());
            statement.bindString(11, currentFeedData.getFeedAttachMent());
            statement.bindLong(12, currentFeedData.getFeedAttachType());


            statement.execute();
        }
        //set the transaction as successful and end the transaction
        L.m("inserting Feed entries " + listFeedData.size() + new Date(System.currentTimeMillis()));
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
    }


    public ArrayList<FeederInfo> readFeedData(String table) {
        ArrayList<FeederInfo> listFeedData = new ArrayList<>();


        //get a list of columns to be retrieved, we need all of them
        String[] columns = {KaffeCupDBHelper.COLUMN_UID,
                KaffeCupDBHelper.ROW_ID,
                KaffeCupDBHelper.COLUMN_FEEDER_NAME,
                KaffeCupDBHelper.COLUMN_FEEDER_TITLE,
                KaffeCupDBHelper.COLUMN_DATE,
                KaffeCupDBHelper.COLUMN_FEED_DESC,
                KaffeCupDBHelper.COLUMN_FEED_LIKE,
                KaffeCupDBHelper.COLUMN_FEED_DISLIKE,
                KaffeCupDBHelper.COLUMN_FEED_VIEWS,
                KaffeCupDBHelper.COLUMN_FEEDER_THUMBNAIL,
                KaffeCupDBHelper.COLUMN_FEED_ATTACHMENT,
                KaffeCupDBHelper.COLUMN_FEED_ATTACH_TYPE,
            };

        Cursor cursor = mDatabase.query(table, columns, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            L.m("loading entries " + cursor.getCount() + new Date(System.currentTimeMillis()));
            do {

                //create a new movie object and retrieve the data from the cursor to be stored in this movie object
                FeederInfo feedData = new FeederInfo();
                //each step is a 2 part process, find the index of the column first, find the data of that column using
                //that index and finally set our blank movie object to contain our data
                feedData.setRowid(cursor.getString(cursor.getColumnIndex(KaffeCupDBHelper.ROW_ID)));
                feedData.setFeederName(cursor.getString(cursor.getColumnIndex(KaffeCupDBHelper.COLUMN_FEEDER_NAME)));
                feedData.setFeederTitle(cursor.getString(cursor.getColumnIndex(KaffeCupDBHelper.COLUMN_FEEDER_TITLE)));
                long feedDateMilliseconds = cursor.getLong(cursor.getColumnIndex(KaffeCupDBHelper.COLUMN_DATE));
                feedData.setFeedingDate(new Date(feedDateMilliseconds));
                feedData.setFeedingDesription(cursor.getString(cursor.getColumnIndex(KaffeCupDBHelper.COLUMN_FEED_DESC)));
                long likeCnt = cursor.getLong(cursor.getColumnIndex(KaffeCupDBHelper.COLUMN_FEED_LIKE));
                long dislikeCnt = cursor.getLong(cursor.getColumnIndex(KaffeCupDBHelper.COLUMN_FEED_DISLIKE));
                long viewCnt = cursor.getLong(cursor.getColumnIndex(KaffeCupDBHelper.COLUMN_FEED_VIEWS));
                feedData.setLikeCount((int) likeCnt);
                feedData.setDislikeCount((int) dislikeCnt);
                feedData.setViewCount((int) viewCnt);

                feedData.setFeederThumbnail(cursor.getString(cursor.getColumnIndex(KaffeCupDBHelper.COLUMN_FEEDER_THUMBNAIL)));
                feedData.setFeedAttachMent(cursor.getString(cursor.getColumnIndex(KaffeCupDBHelper.COLUMN_FEED_ATTACHMENT)));
                feedData.setFeedAttachType((int)cursor.getLong(cursor.getColumnIndex(KaffeCupDBHelper.COLUMN_FEED_ATTACH_TYPE)));

                //add the movie to the list of movie objects which we plan to return
                listFeedData.add(feedData);
            }
            while (cursor.moveToNext());
        }
        return listFeedData;
    }

    public void insertCoffeMarketData(String table, ArrayList<CoffeeMarketData> listCoffeeMarketData, boolean clearPrevious) {
        if (clearPrevious) {
            deleteTable(table);
        }
        //create a sql prepared statement
        String sql = "INSERT INTO " + table + " VALUES (?,?,?,?);";
        //compile the statement and start a transaction
        SQLiteStatement statement = mDatabase.compileStatement(sql);
        mDatabase.beginTransaction();
        for (int i = 0; i < listCoffeeMarketData.size(); i++) {
            CoffeeMarketData coffeeMarketData = listCoffeeMarketData.get(i);
            statement.clearBindings();
            //for a given column index, simply bind the data to be put inside that index
            statement.bindString(2, coffeeMarketData.getMonth());
            statement.bindString(3, coffeeMarketData.getInternatinalprice());
            statement.bindString(4, coffeeMarketData.getDomesticprice());

            statement.execute();
        }
        //set the transaction as successful and end the transaction
        L.m("inserting Market entries " + listCoffeeMarketData.size() + new Date(System.currentTimeMillis()));
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
    }


    public void deleteTable(String table) {
        mDatabase.delete(table, null, null);
    }

    public int getRowCount(String table) {
        long cnt  = DatabaseUtils.queryNumEntries(mDatabase, table);
        return (int)cnt;
    }

    public String getLastDateFromFeedTable(){
        String lastUpdatedDate = "";
        if(getRowCount(KaffeCupDBHelper.TABLE_FEEDS) > 0){
            String[] columns = {KaffeCupDBHelper.COLUMN_DATE };
            Cursor cursor = mDatabase.query(KaffeCupDBHelper.TABLE_FEEDS, columns, null, null, null,
                    null, KaffeCupDBHelper.COLUMN_DATE +" DESC", "1");
            if (cursor != null && cursor.moveToFirst()) {
                long feedDateMilliseconds = cursor.getLong(cursor.getColumnIndex(KaffeCupDBHelper.COLUMN_DATE));
                Date date =new Date(feedDateMilliseconds);
                lastUpdatedDate = KaffeCupUtils.DateToStr(date);
            }
        }
        return lastUpdatedDate;
    }
}
