package g2evolution.localdatabasetesting.utility;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by soumyaPC on 3/8/2016.
 */
public class CoffeeMarketData implements Parcelable {
    private  String month;
    private  String internatinalprice;

    private  String domesticprice;

    public CoffeeMarketData(){}
    public CoffeeMarketData(String month, String internatinalprice, String domesticprice){

        this.month = month;
        this.internatinalprice = internatinalprice;
        this.domesticprice = domesticprice;

    }



    public String getDomesticprice() {
        return domesticprice;
    }

    public void setDomesticprice(String domesticprice) {
        this.domesticprice = domesticprice;
    }

    public String getInternatinalprice() {
        return internatinalprice;

    }

    public void setInternatinalprice(String internatinalprice) {
        this.internatinalprice = internatinalprice;
    }

    public String getMonth() {

        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    protected CoffeeMarketData(Parcel in) {
        month = in.readString();
        internatinalprice = in.readString();
        domesticprice = in.readString();
    }

    public static final Creator<CoffeeMarketData> CREATOR = new Creator<CoffeeMarketData>() {
        @Override
        public CoffeeMarketData createFromParcel(Parcel in) {
            return new CoffeeMarketData(in);
        }

        @Override
        public CoffeeMarketData[] newArray(int size) {
            return new CoffeeMarketData[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(month);
        dest.writeString(internatinalprice);
        dest.writeString(domesticprice);
    }
}
