package g2evolution.localdatabasetesting.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import g2evolution.localdatabasetesting.R;
import g2evolution.localdatabasetesting.adapter.AdapterFeeds;
import g2evolution.localdatabasetesting.adapter.TableRowAdapter;
import g2evolution.localdatabasetesting.app.KaffeCupApp;
import g2evolution.localdatabasetesting.callback.FeedDataLoadedListener;
import g2evolution.localdatabasetesting.callback.MarketDataLoadedListener;
import g2evolution.localdatabasetesting.database.KaffeCupDBHelper;
import g2evolution.localdatabasetesting.entity.FeederInfo;
import g2evolution.localdatabasetesting.logging.L;

import g2evolution.localdatabasetesting.task.TaskLoadMarketData;
import g2evolution.localdatabasetesting.utility.CoffeeMarketData;

public class MainActivity extends AppCompatActivity implements MarketDataLoadedListener {

    private ListView mArabicaListView;
    private ListView mRobustListView;
    private TextView mDollarRate;
    private TableRowAdapter mRobustaAdapter;
    private TableRowAdapter mArabicaAdapter;
    SharedPreferences kaffeeCupSF;

    String sSpinner;
    private ArrayList<CoffeeMarketData> mRobustaList;
    private ArrayList<CoffeeMarketData> mArabicaList;
    private String mDollarXchangeRate;
    private static final String STATE_ARABICA = "state_arabica";
    private static final String STATE_ROBUSTA = "state_robusta";
    private static final String STATE_XCHANGE = "state_xchange";
    SharedPreferences.Editor sfEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mArabicaListView = (ListView) findViewById(R.id.arabicalistview);
        mRobustListView = (ListView)findViewById(R.id.robustalistview);
        mDollarRate = (TextView)findViewById(R.id.textviewdollar);
        kaffeeCupSF = getSharedPreferences("kaffecup", Context.MODE_PRIVATE);




        setUpArabica();
        setUpRobusta();
        if (savedInstanceState != null) {
            //if this fragment starts after a rotation or configuration change, load the existing data  from a parcelable
            mArabicaList = savedInstanceState.getParcelableArrayList(STATE_ARABICA);
            mRobustaList = savedInstanceState.getParcelableArrayList(STATE_ROBUSTA);
            mDollarXchangeRate = savedInstanceState.getString(STATE_XCHANGE);
            UpdateUI();
        }
        else {
            mArabicaList= KaffeCupApp.getWritableDatabase().readCoffeMarketData(KaffeCupDBHelper.TABLE_ARABICA_RATE);
            mRobustaList= KaffeCupApp.getWritableDatabase().readCoffeMarketData(KaffeCupDBHelper.TABLE_ROBUSTA_RATE);
            if( !mArabicaList.isEmpty() && !mRobustaList.isEmpty()) {
                mDollarXchangeRate =kaffeeCupSF.getString(STATE_XCHANGE,"65.00 INR");
                UpdateUI();
            }
            new TaskLoadMarketData(this).execute();
        }
    }
    private void setUpRobusta() {
        ArrayList<CoffeeMarketData> marketData = new ArrayList<CoffeeMarketData>();
        mRobustaAdapter = new TableRowAdapter(MainActivity.this);
        mRobustaAdapter.setCoffeeMarketData(marketData);
        mRobustListView.setAdapter(mRobustaAdapter);

    }

    private void setUpArabica() {
        ArrayList<CoffeeMarketData> marketData = new ArrayList<CoffeeMarketData>();
        mArabicaAdapter = new TableRowAdapter( MainActivity.this);
        mArabicaAdapter.setCoffeeMarketData(marketData);
        mArabicaListView.setAdapter(mArabicaAdapter);
    }
    private void UpdateUI(){
        if(mRobustaList != null)
            mRobustaAdapter.setCoffeeMarketData(mRobustaList);
        if(mArabicaList != null)
            mArabicaAdapter.setCoffeeMarketData(mArabicaList);
       /* if(mDollarXchangeRate != null)
            if (sSpinner.equals("Kannada")){
                mDollarRate.setText("1 ಡಾಲರ್ ಬೆಲೆ="+mDollarXchangeRate);
            }else{
                mDollarRate.setText("1 Dollar Price="+mDollarXchangeRate);
            }*/

    }

    @Override
    public void onMarketDataLoaded(Object[] marketData) {
        if(marketData[0] != null)
            mRobustaList = (ArrayList<CoffeeMarketData>)marketData[0];
        if(marketData[1] != null)
            mArabicaList = (ArrayList<CoffeeMarketData>)marketData[1];
        if(marketData[2] != null){
            mDollarXchangeRate = (String)marketData[2];
            sfEditor = kaffeeCupSF.edit();
            sfEditor.putString(STATE_XCHANGE, mDollarXchangeRate);
            sfEditor.commit();
            L.m("XChange Rate:" + mDollarXchangeRate + ":" + kaffeeCupSF.getString(STATE_XCHANGE, "65.00 INR"));
        }

        UpdateUI();
    }
}

