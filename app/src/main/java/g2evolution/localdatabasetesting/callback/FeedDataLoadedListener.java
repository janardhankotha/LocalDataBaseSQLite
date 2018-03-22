package g2evolution.localdatabasetesting.callback;



import java.util.ArrayList;

import g2evolution.localdatabasetesting.entity.FeederInfo;

/**
 * Created by brajabasi on 21-03-2016.
 */
public interface FeedDataLoadedListener {
    public void onFeedDataLoaded(ArrayList<FeederInfo> listBuyers);
}
