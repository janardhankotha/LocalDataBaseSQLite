package g2evolution.localdatabasetesting.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import g2evolution.localdatabasetesting.R;


/**
 * Created by brajabasi on 07-03-2016.
 */
public class ViewHolderFeed extends RecyclerView.ViewHolder {
    public ImageView feederThumbnail;
    public TextView feederName;
    public TextView feederLocation;
    public TextView feededWhen;
    public TextView feedDescription;
    public TextView likeCount;
    public ImageView likeImageId;
    public ImageView dislikeimageId;
    public ImageButton Viewmorebut;
    public TextView dislikeCount;
    public TextView feedViewCounter;
    public ImageView feedAttachment;

    AdapterFeeds.FeederItemListener mFeedItemListener;
    public ViewHolderFeed(View itemView, AdapterFeeds.FeederItemListener feedItemListener) {
        super(itemView);
        feederThumbnail = (ImageView) itemView.findViewById(R.id.fedderthumbnail);
        feederName = (TextView)itemView.findViewById(R.id.feedername);
        feederLocation = (TextView)itemView.findViewById(R.id.feederlocation);
        feededWhen=(TextView)itemView.findViewById(R.id.feededwhen);
        feedDescription=(TextView)itemView.findViewById(R.id.feeddescription);
        likeCount=(TextView)itemView.findViewById(R.id.likecountid) ;
        likeImageId=(ImageView)itemView.findViewById(R.id.likeimageid);
        dislikeimageId=(ImageView)itemView.findViewById(R.id.dislikeimageid);
        Viewmorebut=(ImageButton)itemView.findViewById(R.id.viewmoreid);
        dislikeCount = (TextView)itemView.findViewById(R.id.dislikecountid);
        feedViewCounter = (TextView)itemView.findViewById(R.id.feedviewcount);
        feedAttachment=(ImageView)itemView.findViewById(R.id.attachmentid) ;
        mFeedItemListener = feedItemListener;

    }
}
