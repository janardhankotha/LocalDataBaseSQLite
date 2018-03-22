package g2evolution.localdatabasetesting.json;


import g2evolution.localdatabasetesting.logging.L;
import g2evolution.localdatabasetesting.utility.UrlEndpoints;

/**
 * Created by Windows on 02-03-2015.
 */
public class Endpoints {




    public static String getRequestUrlMarketData(int limit) {

        return UrlEndpoints.URL_KAFFECUP_MARKET;
    }



    public static String getRequestUrlFeedData(String date) {
        L.m(UrlEndpoints.URL_KAFFECUP_FEED +
                "?last_inserted_date=" + date);
        if(date.isEmpty()) {
            return UrlEndpoints.URL_KAFFECUP_FEED;
        }
        return UrlEndpoints.URL_KAFFECUP_FEED+
                "?last_inserted_date="+ date;
    }
}
