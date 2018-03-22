package g2evolution.localdatabasetesting.task;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import g2evolution.localdatabasetesting.callback.MarketDataLoadedListener;
import g2evolution.localdatabasetesting.logging.L;
import g2evolution.localdatabasetesting.network.VolleySingleton;
import g2evolution.localdatabasetesting.utility.KaffeCupUtils;
import g2evolution.localdatabasetesting.utility.UrlEndpoints;

/**
 * Created by brajabasi on 14-03-2016.
 */
public class TaskLoadMarketData extends AsyncTask<Void, Void,Object[]> {
    private MarketDataLoadedListener mComponent;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    public TaskLoadMarketData(MarketDataLoadedListener component){
        mComponent = component;
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }
    @Override
    protected Object[] doInBackground(Void... params) {
        Object[] listOfMarketData = null;
        listOfMarketData = KaffeCupUtils.loadMarketData(requestQueue);
        try {
            Document doc = Jsoup.connect(UrlEndpoints.GOOGLE_XCHANGE_URL).get();
            Elements els = doc.select("span.bld");
            listOfMarketData[2] = els.get(0).text();
            L.m("INR Value" + els.get(0).text());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfMarketData;
    }

    @Override
    protected void onPostExecute(Object[] listMarketData) {
        if (mComponent != null) {
            mComponent.onMarketDataLoaded(listMarketData);
        }
    }
}
