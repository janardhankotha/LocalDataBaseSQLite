package g2evolution.localdatabasetesting.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;


import com.amulyakhare.textdrawable.TextDrawable;
import com.bumptech.glide.Glide;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import g2evolution.localdatabasetesting.R;
import g2evolution.localdatabasetesting.activity.JSONParser;
import g2evolution.localdatabasetesting.entity.FeederInfo;
import g2evolution.localdatabasetesting.logging.L;

/**
 * Created by brajabasi on 07-03-2016.
 */
public class AdapterFeeds extends RecyclerView.Adapter<ViewHolderFeed> {
    //contains the list of buyers
    private ArrayList<FeederInfo> mListFeeds;
    private LayoutInflater mInflater;
    // private VolleySingleton mVolleySingleton;
    // private ImageLoader mImageLoader;
    private int mPreviousPosition = 0;
    private Context mContext;
    private FeederItemListener feedItemListner;
    private final SparseBooleanArray mCollapsedStatus;

    String rowid, cusid;
    String clicking;
    JSONParser jsonParser = new JSONParser();

    public AdapterFeeds(Context context, ArrayList<FeederInfo> feedList){
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mListFeeds=feedList;
        mCollapsedStatus = new SparseBooleanArray();

    }

    public void SetListener(FeederItemListener listener){
        feedItemListner = listener;
    }

    public void setData( ArrayList<FeederInfo> feedList){
        mListFeeds=feedList;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolderFeed onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.notification_item_row,parent,false);
        ViewHolderFeed viewHolderFeed = new ViewHolderFeed(view,feedItemListner);
        return viewHolderFeed;
    }

    public  long getDifferenceDays( Date d1) {
        Calendar c = Calendar.getInstance();
        long diff = c.getTimeInMillis() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public void onBindViewHolder(final ViewHolderFeed holder, int position) {
        final FeederInfo feederInfo = mListFeeds.get(position);
        TextDrawable drawable = null;
        String firstLetter =null;
        String feedDesc=null;
        if(feederInfo.getFeederName().isEmpty() == false) {
             firstLetter = feederInfo.getFeederName().substring(0, 1).toUpperCase();
        }else if(feederInfo.getFeederTitle().isEmpty() == false){
            firstLetter = feederInfo.getFeederTitle().substring(0, 1).toUpperCase();
        }else{
            firstLetter = "A";
        }

      /*  Picasso.with(mContext).load(feederInfo.getFeederThumbnail())
                .error(drawable)
                .placeholder(drawable)
                .into(holder.feederThumbnail);*/


       /* if(feederInfo.getFeedAttachMent().equals("")||feederInfo.getFeedAttachMent().contains("http://thememoirs.in//kaffeecup//feader//uploads//")){
            holder.feedAttachment.setVisibility(View.INVISIBLE);
        }else if (feederInfo.getFeedAttachMent().contains(".jpg")||feederInfo.getFeedAttachMent().contains(".png")||feederInfo.getFeedAttachMent().contains(".jpeg")||feederInfo.getFeedAttachMent().contains(".jpg")||feederInfo.getFeedAttachMent().contains(".gif")){

            holder.feedAttachment.setVisibility(View.VISIBLE);
            holder.feedAttachment.setImageResource(R.drawable.ic_photo_black_24dp);

        }else if (feederInfo.getFeedAttachMent().contains(".mp3")||feederInfo.getFeedAttachMent().contains(".ogg")||feederInfo.getFeedAttachMent().contains(".m4a")||feederInfo.getFeedAttachMent().contains(".Zvr")||feederInfo.getFeedAttachMent().contains(".ram")||feederInfo.getFeedAttachMent().contains(".wav")){

            holder.feedAttachment.setVisibility(View.VISIBLE);
            holder.feedAttachment.setImageResource(R.drawable.ic_videocam_black_24dp);

        }else if (feederInfo.getFeedAttachMent().contains(".mp4")||feederInfo.getFeedAttachMent().contains(".flv")||feederInfo.getFeedAttachMent().contains(".vob")||feederInfo.getFeedAttachMent().contains(".m4v")||feederInfo.getFeedAttachMent().contains(".f4v")){

            holder.feedAttachment.setVisibility(View.VISIBLE);
            holder.feedAttachment.setImageResource(R.drawable.ic_audiotrack_black_24dp);

        }else{
            holder.feedAttachment.setVisibility(View.VISIBLE);
            holder.feedAttachment.setImageResource(R.drawable.galleryicon);
        }
*/
/*
        Glide.with(mContext).load(feederInfo.getFeederThumbnail())
                .error(drawable)
                .placeholder(drawable)
                .crossFade()
                .into(holder.feederThumbnail);*/
        holder.feederName.setText(feederInfo.getFeederName());
        holder.feederLocation.setText(feederInfo.getFeederTitle());
        long daysAgo = getDifferenceDays(feederInfo.getFeedingDate());
        Log.e("testing","date of days is = "+daysAgo);
        holder.feededWhen.setText(daysAgo + " days ago");


        holder.likeCount.setText(Integer.toString(feederInfo.getLikeCount()));
        holder.dislikeCount.setText(Integer.toString(feederInfo.getDislikeCount()));
        holder.feedViewCounter.setText(Integer.toString(feederInfo.getViewCount())+ " Views");

        holder.feederThumbnail.setImageResource(feederInfo.getPhoto());

        L.m("View More:" + feederInfo.getFeedingDesription().length());

        if (feederInfo.getFeedingDesription().length() > 100){
            feedDesc = feederInfo.getFeedingDesription().substring(0,100) + "...";

        }else{
            feedDesc = feederInfo.getFeedingDesription();
        }
        holder.feedDescription.setText(feedDesc);
        switch(feederInfo.getFeedAttachType()){



        }



        mPreviousPosition = position;


        SharedPreferences prefuserdata = mContext.getSharedPreferences("registerUser", 0);


        cusid= prefuserdata.getString("id", "0");


        Log.e("testing", "id profile ==" + cusid);
//--------------------------------Fetching image button-----------------
      /*  holder.feedAttachment.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Intent myIntent = new Intent(mContext, Videouploading.class);
                myIntent.putExtra("URLVID", feederInfo.getFeedAttachMent());
                Log.e("testing", "URLVID before activity===" + feederInfo.getFeedAttachMent());

                v.getContext().startActivity(myIntent);

            }


        });*/

        //------------------------------for like image----------------------------
        holder.likeImageId.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                rowid = feederInfo.getRowid();

                Log.e("testing", "id for row id start of likeimageid== " + feederInfo.getRowid());

                // new likecount().execute();
                //new likecountcm().execute;
                new Fetchprofileimage().execute();
                likecountcm var = new likecountcm();
                var.execute();



                /*feedListRowHolder.likeCount.setText((likecount));
                feedListRowHolder.dislikeCount.setText((dislikecount));
                feedListRowHolder.feedViewCounter.setText((viewcount) + " views");
*/


                clicking = "like";

                Log.e("testing", "id for cus id== " + cusid);
                Log.e("testing", "id for row id== " + rowid);
                Log.e("testing", "id for clicking name== " + clicking);
                /*Intent viewmoreintent = new Intent(mContext, LikeActivity.class);
                viewmoreintent.putExtra("rid", feedItem.getrowid());
                viewmoreintent.putExtra("like", "like");
                Log.e("testing", "description===" + feedItem.getrowid());

                v.getContext().startActivity(viewmoreintent);*/

            }

            class likecountcm extends AsyncTask<String,String, String> {
                String responce;
                String message;

                String likec, dislikec, viewsc;

                private ProgressDialog pDialog;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    pDialog = new ProgressDialog(mContext);
                    pDialog.setMessage("Loading");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(false);
                    pDialog.show();

                }

                protected String doInBackground(String... args) {
                    List<NameValuePair> userpramas = new ArrayList<NameValuePair>();



                    userpramas.add(new BasicNameValuePair("feed_id", rowid));

                    Log.e("testing", "id is for like is === "+rowid);

                    JSONObject json = jsonParser.makeHttpRequest("http://kaffeecup.com/feader/cust_response_individual.php", "POST", userpramas);

                    Log.e("testing", "jsonParser"+json);
                    try {
                        String arrayresponce = json.getString("customer_response_individuals");

                        final JSONArray responcearray = new JSONArray(arrayresponce);

                        for (int i = 0; i < responcearray.length(); i++) {



                            JSONObject currentobj = responcearray.getJSONObject(i);




                            likec = currentobj.getString("Like");
                            dislikec = currentobj.getString("Dislike");
                            viewsc = currentobj.getString("Views");

                            Log.e("testing","likec is = "+ likec);
                            Log.e("testing", "dislikec is =="+dislikec);
                            Log.e("testing", "viewc is =="+viewsc);




                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("testing", "post execute");
                    }
                    return responce;

                }

                @Override
                protected void onPostExecute(String result) {
                    super.onPostExecute(result);

                    holder.likeCount.setText((likec));
                    holder.dislikeCount.setText((dislikec));
                    holder.feedViewCounter.setText((viewsc) + " views");


                    pDialog.dismiss();

                    Log.e("testing", "adapter");
                }





            }

        });


        //------------------------------------for dislike image-----------------------
        holder.dislikeimageId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowid = feederInfo.getRowid();
                new Fetchprofileimage2().execute();
                likecountcm var = new likecountcm();
                var.execute();
                clicking = "dislike";

                Log.e("testing", "id for cus id== " + cusid);
                Log.e("testing", "id for row id== " + rowid);
                Log.e("testing", "id for clicking name== " + clicking);
               /* Intent viewmoreintent = new Intent(mContext, LikeActivity.class);
                viewmoreintent.putExtra("rid", feedItem.getrowid());
                viewmoreintent.putExtra("like", "dislike");
                Log.e("testing", "description===" + feedItem.getrowid());


                v.getContext().startActivity(viewmoreintent);*/

            }

            class likecountcm extends AsyncTask<String,String, String>{
                String responce;
                String message;

                String likec, dislikec, viewsc;

                private ProgressDialog pDialog;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    pDialog = new ProgressDialog(mContext);
                    pDialog.setMessage("Loading");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(false);
                    pDialog.show();

                }

                protected String doInBackground(String... args) {
                    List<NameValuePair> userpramas = new ArrayList<NameValuePair>();



                    userpramas.add(new BasicNameValuePair("feed_id", rowid));

                    Log.e("testing", "id is for like is === "+rowid);

                    JSONObject json = jsonParser.makeHttpRequest("http://kaffeecup.com/feader/cust_response_individual.php", "POST", userpramas);

                    Log.e("testing", "jsonParser"+json);
                    try {
                        String arrayresponce = json.getString("customer_response_individuals");

                        final JSONArray responcearray = new JSONArray(arrayresponce);

                        for (int i = 0; i < responcearray.length(); i++) {



                            JSONObject currentobj = responcearray.getJSONObject(i);




                            likec = currentobj.getString("Like");
                            dislikec = currentobj.getString("Dislike");
                            viewsc = currentobj.getString("Views");

                            Log.e("testing","likec is = "+ likec);
                            Log.e("testing", "dislikec is =="+dislikec);
                            Log.e("testing", "viewc is =="+viewsc);




                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("testing", "post execute");
                    }
                    return responce;

                }

                @Override
                protected void onPostExecute(String result) {
                    super.onPostExecute(result);
                    holder.likeCount.setText((likec));
                    holder.dislikeCount.setText((dislikec));
                    holder.feedViewCounter.setText((viewsc) + " views");


                    pDialog.dismiss();

                    Log.e("testing", "adapter");
                }





            }

        });

    }

    @Override
    public int getItemCount() {
        return mListFeeds.size();
    }

    public interface FeederItemListener {
        public void onFeedClicked(int position, int resid);
    }


    public static void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {

        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {

                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (maxLine == 0) {
                    int lineEndIndex = tv.getLayout().getLineEnd(0);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    int lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else {
                    int lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                }
            }
        });

    }

    private static SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned, final TextView tv,
                                                                            final int maxLine, final String spanableText, final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText)) {
            ssb.setSpan(new ClickableSpan() {

                @Override
                public void onClick(View widget) {

                    if (viewMore) {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, -1, "View Less", false);
                    } else {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, 3, "View More", true);
                    }

                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);

        }
        return ssb;

    }




    //-----------------sending like count-------------------
    class Fetchprofileimage extends AsyncTask<String, String, String> {

        String status;


        private ProgressDialog pDialog;

        protected void onPreExecute() {
            super.onPreExecute();


            pDialog = new ProgressDialog(mContext);
            pDialog.setMessage("Processing");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }
        protected String doInBackground(String... params) {


            {

                List<NameValuePair> userpramas = new ArrayList<NameValuePair>();
                userpramas.add(new BasicNameValuePair("feed_id", rowid));
                userpramas.add(new BasicNameValuePair("cust_reg_id", cusid));
                userpramas.add(new BasicNameValuePair("like", "1"));

                Log.e("testing", "params ===== " + userpramas);

                JSONObject json = jsonParser.makeHttpRequest("http://kaffeecup.com/feader/cust_response.php", "POST", userpramas);

                Log.e("testing", "json result = " + json);

                try {
                    status = json.getString("status");




                }



                catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }



        }


        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pDialog.dismiss();


        }

    }
    //-----------sending dislike count-----------
    class Fetchprofileimage2 extends AsyncTask<String, String, String> {

        String status;


        private ProgressDialog pDialog;

        protected void onPreExecute() {
            super.onPreExecute();


            pDialog = new ProgressDialog(mContext);
            pDialog.setMessage("Processing");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }
        protected String doInBackground(String... params) {


            {

                List<NameValuePair> userpramas = new ArrayList<NameValuePair>();
                userpramas.add(new BasicNameValuePair("feed_id", rowid));
                userpramas.add(new BasicNameValuePair("cust_reg_id", cusid));
                userpramas.add(new BasicNameValuePair("dislike", "1"));
                Log.e("testing", "params ===== " + userpramas);

                JSONObject json = jsonParser.makeHttpRequest("http://kaffeecup.com/feader/cust_response.php", "POST", userpramas);

                Log.e("testing", "json result = " + json);

                try {
                    status = json.getString("status");




                }



                catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }



        }


        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pDialog.dismiss();


        }

    }

    //----------for view more---------------------
    class Fetchprofileimage3 extends AsyncTask<String, String, String> {

        String status;


        private ProgressDialog pDialog;

        protected void onPreExecute() {
            super.onPreExecute();


            pDialog = new ProgressDialog(mContext);
            pDialog.setMessage("Processing");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }
        protected String doInBackground(String... params) {


            {

                List<NameValuePair> userpramas = new ArrayList<NameValuePair>();
                userpramas.add(new BasicNameValuePair("feed_id", rowid));
                userpramas.add(new BasicNameValuePair("cust_reg_id", cusid));
                userpramas.add(new BasicNameValuePair("views", "1"));
                Log.e("testing", "params ===== " + userpramas);

                JSONObject json = jsonParser.makeHttpRequest("http://kaffeecup.com/feader/cust_response.php", "POST", userpramas);

                Log.e("testing", "json result = " + json);

                try {
                    status = json.getString("status");




                }



                catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }



        }


        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pDialog.dismiss();


        }

    }

}

