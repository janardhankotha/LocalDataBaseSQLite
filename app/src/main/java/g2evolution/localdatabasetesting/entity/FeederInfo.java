package g2evolution.localdatabasetesting.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by brajabasi on 07-03-2016.
 */
public class FeederInfo implements Parcelable{
    private String feederName;
    private String feederTitle;
    private Date   feedingDate;
    private String feedingDesription;
    private Integer likeCount;
    private Integer dislikeCount;
    private Integer viewCount;
    private String feederThumbnail;
    private String feedAttachMent;
    private int feedAttachType;
    private String rowid;
    private  int photo;

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public static Creator<FeederInfo> getCREATOR() {
        return CREATOR;
    }

    public int getFeedAttachType() {
        return feedAttachType;
    }

    public void setFeedAttachType(int feedAttachType) {
        this.feedAttachType = feedAttachType;
    }

    public String getFeedAttachMent() {
        return feedAttachMent;
    }

    public void setFeedAttachMent(String feedAttachMent) {
        this.feedAttachMent = feedAttachMent;
    }









    public String getFeederThumbnail() {
        if(feederThumbnail == null){
            return "";
        }
        return feederThumbnail;
    }

    public void setFeederThumbnail(String feederThumbnail) {
        this.feederThumbnail = feederThumbnail;
    }



    public FeederInfo(){}

    public String getRowid() {
        if(rowid == null){
            return "";
        }
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }



    public String getFeederName() {
          if(feederName == null){
              return "";
          }
          return feederName;
    }

    public void setFeederName(String feederName) {
        this.feederName = feederName;
    }

    public String getFeederTitle() {
        if(feederTitle == null){
            return "";
        }
        return feederTitle;
    }

    public void setFeederTitle(String feederTitle) {
        this.feederTitle = feederTitle;
    }

    public Date getFeedingDate() {
        if(feedingDate == null){
            return new Date(-1);
        }
        return feedingDate;
    }

    public void setFeedingDate(Date feedingDate) {
        this.feedingDate = feedingDate;
    }

    public String getFeedingDesription() {
        if(feedingDesription == null){
            return "";
        }
        return feedingDesription;
    }

    public void setFeedingDesription(String feedingDesription) {
        this.feedingDesription = feedingDesription;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(Integer dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    protected FeederInfo(Parcel in) {
        feederName = in.readString();
        feederTitle = in.readString();
        feedingDate = new Date(in.readLong());
        feedingDesription = in.readString();
        likeCount=in.readInt();
        dislikeCount=in.readInt();
        viewCount=in.readInt();
        feederThumbnail = in.readString();
        feedAttachMent = in.readString();
        feedAttachType = in.readInt();

    }

    public static final Creator<FeederInfo> CREATOR = new Creator<FeederInfo>() {
        @Override
        public FeederInfo createFromParcel(Parcel in) {
            return new FeederInfo(in);
        }

        @Override
        public FeederInfo[] newArray(int size) {
            return new FeederInfo[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(feederName);
        dest.writeString(feederTitle);
        dest.writeLong(feedingDate.getTime());
        dest.writeString(feedingDesription);
        dest.writeInt(likeCount);
        dest.writeInt(dislikeCount);
        dest.writeInt(viewCount);
        dest.writeString(feederThumbnail);
        dest.writeString(feedAttachMent);
        dest.writeInt(feedAttachType);

    }
}
