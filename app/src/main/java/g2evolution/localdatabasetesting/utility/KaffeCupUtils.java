package g2evolution.localdatabasetesting.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.RequestQueue;


import org.json.JSONObject;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import g2evolution.localdatabasetesting.app.KaffeCupApp;
import g2evolution.localdatabasetesting.database.KaffeCupDBHelper;
import g2evolution.localdatabasetesting.entity.FeederInfo;
import g2evolution.localdatabasetesting.json.Endpoints;
import g2evolution.localdatabasetesting.json.Requestor;
import g2evolution.localdatabasetesting.json.Parser;
import g2evolution.localdatabasetesting.logging.L;


/**
 * Created by brajabasi on 12-03-2016.
 */
public class KaffeCupUtils {


    public static Object[] loadMarketData(RequestQueue requestQueue) {
        JSONObject response = Requestor.requestBuyerDataJSON(requestQueue, Endpoints.getRequestUrlMarketData(0));
        if(response != null) {
            L.m(response.toString());
        }
        Object[] listOfMarketData = Parser.parseMarketJSON(response);
        if(listOfMarketData[0] !=null) {
            KaffeCupApp.getWritableDatabase().insertCoffeMarketData(KaffeCupDBHelper.TABLE_ARABICA_RATE,
                    (ArrayList<CoffeeMarketData>) listOfMarketData[0], true);
        }
        if(listOfMarketData[1] !=null) {
            KaffeCupApp.getWritableDatabase().insertCoffeMarketData(KaffeCupDBHelper.TABLE_ROBUSTA_RATE,
                    (ArrayList<CoffeeMarketData>) listOfMarketData[1], true);
        }
        return listOfMarketData;

    }

    public static ArrayList<FeederInfo> loadFeedData(RequestQueue requestQueue) {
        String lastUpdatedDate = KaffeCupApp.getWritableDatabase().getLastDateFromFeedTable();
        String replacedString= lastUpdatedDate.replace(" ","%20");
        JSONObject response = Requestor.requestBuyerDataJSON(requestQueue, Endpoints.getRequestUrlFeedData(replacedString));
        if(response != null) {
            L.m(response.toString());
        }
        ArrayList<FeederInfo> listOfFeedData = Parser.parseFeedJSON(response);
        if(listOfFeedData.size() > 0) {
            UpdateKaffeCupFeeds(listOfFeedData);
            KaffeCupApp.getWritableDatabase()
                    .insertFeedData(KaffeCupDBHelper.TABLE_FEEDS, listOfFeedData, false);
        }
        return listOfFeedData;

    }

    private static void UpdateKaffeCupFeeds(ArrayList<FeederInfo> listOfFeedData) {
        int currentFeedCount = KaffeCupApp.getWritableDatabase()
                .getRowCount(KaffeCupDBHelper.TABLE_FEEDS);
        String value= readPreferences(KaffeCupApp.getAppContext(),
                KaffeCupConstants.KAFFECUP_NEW_FEEDS,
                "0");
        int storedFeedCount = Integer.valueOf(value);

       /* if(listOfFeedData.size() > currentFeedCount){
            storedFeedCount+= (listOfFeedData.size()- currentFeedCount);
            savePreferences(KaffeCupApp.getAppContext(),
                    KaffeCupConstants.KAFFECUP_NEW_FEEDS,String.valueOf(storedFeedCount));
        }*/
        if(listOfFeedData.size() > 0) {
            storedFeedCount+= listOfFeedData.size();
            savePreferences(KaffeCupApp.getAppContext(),
                    KaffeCupConstants.KAFFECUP_NEW_FEEDS,String.valueOf(storedFeedCount));
        }

    }

    public static void savePreferences(Context context, String key, String value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();

    }

    public static String readPreferences(Context context, String key, String defaultValue){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(key, defaultValue);
    }

    public static Date StrToDate(String strDate){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            //a parse exception generated here will store null in the release date, be sure to handle it
        }
        return date;
    }

    public static String DateToStr(Date dt){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = dateFormat.format(dt);
        return dateStr;
    }
}
