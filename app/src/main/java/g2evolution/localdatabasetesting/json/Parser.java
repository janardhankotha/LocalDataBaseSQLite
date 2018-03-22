package g2evolution.localdatabasetesting.json;




import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import g2evolution.localdatabasetesting.entity.FeederInfo;
import g2evolution.localdatabasetesting.utility.AttachmentType;
import g2evolution.localdatabasetesting.utility.CoffeeMarketData;
import g2evolution.localdatabasetesting.utility.KaffeCupUtils;


/**
 * Created by Windows on 02-03-2015.
 */
public class Parser {


    public static Object[] parseMarketJSON(JSONObject response) {
        // ArrayList<CoffeeMarketData>[] listOfMarketData = new ArrayList[2];
        Object[] listOfMarketData = new Object[3];

        if (response != null && response.length() > 0) {
            try {
                JSONArray arrayCoffeMarketDetails = response.getJSONArray(JsonKeys.KEY_MARKET_DETAILS);

                for (int i = 0; i < arrayCoffeMarketDetails.length(); i++) {
                    JSONObject currentCoffeType = arrayCoffeMarketDetails.getJSONObject(i);
                    if (Utils.contains(currentCoffeType, JsonKeys.KEY_ARABICA_DETAILS)) {
                        JSONArray arrayCoffePriceDetails = currentCoffeType.getJSONArray(JsonKeys.KEY_ARABICA_DETAILS);
                        listOfMarketData[0] = getCoffeMarketData(arrayCoffePriceDetails);

                    }

                    if (Utils.contains(currentCoffeType, JsonKeys.KEY_ROBUSTA_DETAILS)) {

                        JSONArray arrayCoffePriceDetails = currentCoffeType.getJSONArray(JsonKeys.KEY_ROBUSTA_DETAILS);
                        listOfMarketData[1] = getCoffeMarketData(arrayCoffePriceDetails);
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return listOfMarketData;

    }
    private static ArrayList<CoffeeMarketData> getCoffeMarketData(JSONArray arrayCoffePriceDetails) {
        ArrayList<CoffeeMarketData> marketPriceDetail = new ArrayList<CoffeeMarketData>();
        String month = JsonKeys.NA;
        String internationalPrice = JsonKeys.NA;
        String domesticPrice = JsonKeys.NA;
        for (int j = 0; j < arrayCoffePriceDetails.length(); j++) {
            JSONObject currentCoffePrice = null;
            try {
                currentCoffePrice = arrayCoffePriceDetails.getJSONObject(j);

                CoffeeMarketData marketData = new CoffeeMarketData();

                if (Utils.contains(currentCoffePrice, JsonKeys.KEY_MONTH)) {

                    month = currentCoffePrice.getString(JsonKeys.KEY_MONTH);
                    marketData.setMonth(month);
                }

                if (Utils.contains(currentCoffePrice, JsonKeys.KEY_DOMESTIC_PRICE)) {
                    domesticPrice = currentCoffePrice.getString(JsonKeys.KEY_DOMESTIC_PRICE);
                    marketData.setDomesticprice(domesticPrice);
                }

                if (Utils.contains(currentCoffePrice, JsonKeys.KEY_INTERNATIONAL_PRICE)) {
                    internationalPrice = currentCoffePrice.getString(JsonKeys.KEY_INTERNATIONAL_PRICE);
                    marketData.setInternatinalprice(internationalPrice);
                }


                marketPriceDetail.add(marketData);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return marketPriceDetail;
    }


    public static ArrayList<FeederInfo> parseFeedJSON(JSONObject response) {
        ArrayList<FeederInfo> listOfFeedData = new ArrayList<FeederInfo>();


        if (response != null && response.length() > 0) {
            try {
                JSONArray arrayFeedDetails = response.getJSONArray(JsonKeys.KEY_FEED_DETAILS);

                for (int i = 0; i < arrayFeedDetails.length(); i++) {
                    JSONObject currentFeed = arrayFeedDetails.getJSONObject(i);
                    FeederInfo feederInfo = new FeederInfo();

                    if (Utils.contains(currentFeed, JsonKeys.ROW_ID)) {
                        String value = currentFeed.getString(JsonKeys.ROW_ID);
                        feederInfo.setRowid(value);

                    }

                    if (Utils.contains(currentFeed, JsonKeys.KEY_FEED_NAME)) {
                        String value = currentFeed.getString(JsonKeys.KEY_FEED_NAME);
                        feederInfo.setFeederName(value);

                    }
                    if (Utils.contains(currentFeed, JsonKeys.KEY_FEED_TITLE)) {
                        String value = currentFeed.getString(JsonKeys.KEY_FEED_TITLE);
                        feederInfo.setFeederTitle(value);

                    }
                    if (Utils.contains(currentFeed, JsonKeys.KEY_FEED_DATE)) {
                        String value = currentFeed.getString(JsonKeys.KEY_FEED_DATE);
                        feederInfo.setFeedingDate(KaffeCupUtils.StrToDate(value));

                    }
                    if (Utils.contains(currentFeed, JsonKeys.KEY_FEED_DESC)) {
                        String value = currentFeed.getString(JsonKeys.KEY_FEED_DESC);
                        if(value == null){
                            feederInfo.setFeedingDesription("");
                        }else {
                            feederInfo.setFeedingDesription(value);
                        }

                    }
                    if (Utils.contains(currentFeed, JsonKeys.KEY_FEED_IMAGE)) {
                        String value = currentFeed.getString(JsonKeys.KEY_FEED_IMAGE);
                        feederInfo.setFeederThumbnail(value);

                    }

                    if (Utils.contains(currentFeed, JsonKeys.KEY_FEED_LIKE)) {
                        String value = currentFeed.getString(JsonKeys.KEY_FEED_LIKE);
                        if(value.isEmpty()){
                            feederInfo.setLikeCount(0);
                        }else {
                            feederInfo.setLikeCount(Integer.valueOf(value));
                        }

                    }
                    if (Utils.contains(currentFeed, JsonKeys.KEY_FEED_DISLIKE)) {
                        String value = currentFeed.getString(JsonKeys.KEY_FEED_DISLIKE);
                        if(value.isEmpty())
                            feederInfo.setDislikeCount(0);
                        else
                           feederInfo.setDislikeCount(Integer.valueOf(value));

                    }
                    if (Utils.contains(currentFeed, JsonKeys.KEY_FEED_VIEWS)) {
                        String value = currentFeed.getString(JsonKeys.KEY_FEED_VIEWS);
                        if(value.isEmpty()){
                            feederInfo.setViewCount(0);
                        }else {
                            feederInfo.setViewCount(Integer.valueOf(value));
                        }

                    }

                    if (Utils.contains(currentFeed, JsonKeys.KEY_FEED_UPLOADS)) {
                        AttachmentType attachmentType = new AttachmentType();
                        String value = currentFeed.getString(JsonKeys.KEY_FEED_UPLOADS);

                        int attachType = attachmentType.getFileType(value);
                        feederInfo.setFeedAttachType(attachType);
                        feederInfo.setFeedAttachMent(value);
                    }

                    listOfFeedData.add(feederInfo);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

           // Collections.sort(listOfFeedData, new DateComparator());

        }

        return listOfFeedData;

    }

}
