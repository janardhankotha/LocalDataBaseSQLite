package g2evolution.localdatabasetesting.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



import java.util.ArrayList;

import g2evolution.localdatabasetesting.R;
import g2evolution.localdatabasetesting.utility.CoffeeMarketData;

/**
 * Created by brajabasi on 05-03-2016.
 */
public class TableRowAdapter extends BaseAdapter {
    String [][]tableRows;
    ArrayList<CoffeeMarketData> mListCoffeeMarketData;
    Context mContext;
    public TableRowAdapter(Context ctx){
        mContext = ctx;
       // tableRows = rows;

    }
    public void setCoffeeMarketData(ArrayList<CoffeeMarketData> listCoffeeMarketData){
        mListCoffeeMarketData = listCoffeeMarketData;
        notifyDataSetChanged();

    }
    @Override
    public int getCount() {
        return mListCoffeeMarketData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListCoffeeMarketData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

       // String []rowValue = tableRows[position];
        CoffeeMarketData marketData = mListCoffeeMarketData.get(position);

        // First let's verify the convertView is not null
        if (convertView == null) {
            // This a new view we inflate the new layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            view = inflater.inflate(R.layout.market_table_row, parent, false);
            // Now we can fill the layout with the right values
            TextView column1 = (TextView) view.findViewById(R.id.column1);
            TextView column2 = (TextView) view.findViewById(R.id.column2);
            TextView column3 = (TextView) view.findViewById(R.id.column3);

            column1.setText(marketData.getMonth());
            column2.setText(marketData.getInternatinalprice());
            column3.setText(marketData.getDomesticprice());
        }
        return view;
    }
}
