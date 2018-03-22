package g2evolution.localdatabasetesting.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import g2evolution.localdatabasetesting.R;
import g2evolution.localdatabasetesting.adapter.AdapterFeeds;
import g2evolution.localdatabasetesting.app.KaffeCupApp;
import g2evolution.localdatabasetesting.callback.FeedDataLoadedListener;
import g2evolution.localdatabasetesting.database.KaffeCupDBHelper;
import g2evolution.localdatabasetesting.entity.FeederInfo;
import g2evolution.localdatabasetesting.task.TaskLoadFeedData;

public class Activity_Feed extends AppCompatActivity implements FeedDataLoadedListener {
    private RecyclerView mFeedRecyler;
    private ArrayList<FeederInfo> mListFeederInfo;
    private AdapterFeeds adapter;
    AdapterFeeds mAdapterFeeds;
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("dd/MM/yyyy");
    private static final Date invalidDate = new Date(0);
    private static final String STATE_FEED_LIST = "state_feeds";
    String []Name = new String[]{"Prasenjit","Neha","Gyan Kumar","Fazil","Brajabasi"};
    String []Location = new String[]{"Bangalore(India","Carlifonia(US)","London(UK)","Chennai(Tamilnadu)","Bangalore(India)"};
    Date[] dates = {
            fromString("01/01/2010"),
            fromString("16/09/2010"),
            fromString("21/03/2010"),
            fromString("23/02/2016"),
            fromString("01/03/2016"),
    };

    String[] desc = new String[]{ "A very jewish deli in old downdown MayFieldWhich is another Palo Alto business district",
            "A very jewish deli in old downdown MayFieldWhich is another Palo Alto business district",
            "A very jewish deli in old downdown MayFieldWhich is another Palo Alto business district",
            "A very jewish deli in old downdown MayFieldWhich is another Palo Alto business district",
            "A very jewish deli in old downdown MayFieldWhich is another Palo Alto business district"};
    Integer[] likeCount = {2000,3455,1500,6000,7000};
    Integer[] dislikeCount={100,200,300,400,50};
    Integer[] viewCount={233,345,6700,1200,2000};
    Integer[] images={R.drawable.backgrndimg,R.drawable.backgrndimg,R.drawable.backgrndimg,R.drawable.backgrndimg,R.drawable.backgrndimg};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        mFeedRecyler = (RecyclerView)findViewById(R.id.feedrecycler);
        mFeedRecyler.setLayoutManager(new LinearLayoutManager(this));
        setUpRecycler();

        if (savedInstanceState != null) {
            //if this fragment starts after a rotation or configuration change, load the existing data  from a parcelable
            mListFeederInfo =  savedInstanceState.getParcelableArrayList(STATE_FEED_LIST);
            mAdapterFeeds.setData(mListFeederInfo);

        }else {
            ReadFromDB();
            if(mListFeederInfo.isEmpty()){
                new TaskLoadFeedData(this).execute();
            }else{
                mAdapterFeeds.setData(mListFeederInfo);
                //new TaskLoadFeedData(this).execute();
            }

        }
    }
    private static final Date fromString( String spec ) {
        try {
            return dateFormat.parse( spec );
        } catch( ParseException dfe ) {
            return invalidDate;
        }
    }
    private void setUpRecycler() {
        mListFeederInfo =new ArrayList<FeederInfo>();

        for(int i=0;i<Name.length;i++){
            FeederInfo feedInfo = new FeederInfo();
            feedInfo.setFeederName(Name[i]);
            feedInfo.setFeederThumbnail(Location[i]);
            feedInfo.setFeedingDate(dates[i]);
            feedInfo.setFeedingDesription(desc[i]);
            feedInfo.setLikeCount(likeCount[i]);
            feedInfo.setDislikeCount(dislikeCount[i]);
            feedInfo.setViewCount(viewCount[i]);
            feedInfo.setPhoto(images[i]);
            mListFeederInfo.add(feedInfo);
        }

        mAdapterFeeds= new AdapterFeeds(this,mListFeederInfo);
        mFeedRecyler.setAdapter(mAdapterFeeds);

    }

    private void ReadFromDB() {
        mListFeederInfo= KaffeCupApp.getWritableDatabase().readFeedData(KaffeCupDBHelper.TABLE_FEEDS);
    }

    @Override
    public void onFeedDataLoaded(ArrayList<FeederInfo> listFeeds) {
        if(listFeeds != null && listFeeds.size() > 0) {
            mListFeederInfo = listFeeds;
            mAdapterFeeds.setData(mListFeederInfo);
        }
    }
}
