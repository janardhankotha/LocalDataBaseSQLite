package g2evolution.localdatabasetesting.task;


import android.os.AsyncTask;


import com.android.volley.RequestQueue;

import java.util.ArrayList;

import g2evolution.localdatabasetesting.callback.FeedDataLoadedListener;
import g2evolution.localdatabasetesting.entity.FeederInfo;
import g2evolution.localdatabasetesting.network.VolleySingleton;
import g2evolution.localdatabasetesting.utility.KaffeCupUtils;

/**
 * Created by brajabasi on 21-03-2016.
 */
public class TaskLoadFeedData extends AsyncTask<Void, Void,ArrayList<FeederInfo>> {
    private FeedDataLoadedListener mComponent;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    public TaskLoadFeedData(FeedDataLoadedListener component){
        mComponent = component;
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();

    }

    @Override
    protected ArrayList<FeederInfo> doInBackground(Void... params) {
        ArrayList<FeederInfo> feederInfos = null;
        feederInfos = KaffeCupUtils.loadFeedData(requestQueue);
        return feederInfos;
    }

    @Override
    protected void onPostExecute(ArrayList<FeederInfo> feederInfos) {
        if (mComponent != null) {
            mComponent.onFeedDataLoaded(feederInfos);
        }
    }
}
