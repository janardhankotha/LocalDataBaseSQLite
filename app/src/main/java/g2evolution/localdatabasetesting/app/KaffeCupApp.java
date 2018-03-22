package g2evolution.localdatabasetesting.app;

import android.app.Application;
import android.content.Context;

import g2evolution.localdatabasetesting.database.DBKaffeCup;


/**
 * Created by brpadhy on 3/2/2016.
 */
public class KaffeCupApp extends Application {

    private static KaffeCupApp sInstance;

    private static DBKaffeCup mDatabase;
    public synchronized static DBKaffeCup getWritableDatabase() {
        if (mDatabase == null) {
            mDatabase = new DBKaffeCup(getAppContext());
        }
        return mDatabase;
    }

    public static KaffeCupApp getInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mDatabase = new DBKaffeCup(this);

    }


}
