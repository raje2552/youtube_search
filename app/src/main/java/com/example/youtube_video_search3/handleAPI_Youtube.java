package com.example.youtube_video_search3;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class handleAPI_Youtube extends AsyncTask<String , Integer , String>{

        /**
         * this variables are for hold with items own API and we will set value for this variables
         * across constructor for give flex for class
         * */
        private final String Key = "AIzaSyCT7T-TiE0cAAHj0foXXy17-RzA1vq0N3s";
        private String H_URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&type=video";
        private String part = null; // refer for title that want searching about it
        private String MAX_RESULTS = null;
        private OkHttpClient client ;
        private  Context context;
        private RecyclerView recyclerView;



        //edit
//    the constructor receive 4 parameter then type String (URL , QUERY , MAX_RESULTS , PRINT_TYPE)
        public handleAPI_Youtube(String URL , String Part , String max_RESULTS , Context context , RecyclerView recyclerView){ // edit argument in MainActivity
            part = Part;
            MAX_RESULTS = max_RESULTS;
            client = new OkHttpClient();
            this.context = context;
            this.recyclerView = recyclerView;
        }
        // write description
        private String CreateHTTP_URL(){
            if(this.H_URL != null){
                HttpUrl.Builder URL_Builder = HttpUrl.parse(this.H_URL).newBuilder();
                System.out.println("pront ffff : "+ part);
                URL_Builder.addQueryParameter("q" , this.part);
                URL_Builder.addQueryParameter("maxResults" , "10"); // convert for variable
                URL_Builder.addQueryParameter("key" , this.Key);

                System.out.println("/////45453533"+URL_Builder.toString());

                return URL_Builder.build().toString();
            }
            return null;
        }
        @Override
        protected String doInBackground(String... strings) {
            if (CreateHTTP_URL() != null) {
                String Url_final = CreateHTTP_URL();
                System.out.println("URL ; :Ddfdf :  "+Url_final);
                Request request = new Request.Builder().url(Url_final).build();
                try{
                    Response response = client.newCall(request).execute();
                    System.out.println("result 4546456 : "+response.body().source().toString());
                    return response.body() != null ? response.body().toString() : null;
                }catch (IOException e){
                    e.printStackTrace();
                    return null;
                }
            }else{
                System.out.println("Error 34: fail in build Request");
                return null;
            }


        }


        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            List<video> videoList = new ArrayList<>();

            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray itemsArray = jsonObject.getJSONArray("items");

                for (int i = 0; i < itemsArray.length(); i++) {
                    JSONObject item = itemsArray.getJSONObject(i);
                    JSONObject snippet = item.getJSONObject("snippet");

                    String title = snippet.getString("title");
                    String description = snippet.getString("description");
                    String publishTime = snippet.getString("publishedAt");
                    String channelTitle = snippet.getString("channelTitle");
                    String thumbnailUrl = snippet.getJSONObject("thumbnails")
                            .getJSONObject("medium")
                            .getString("url");

                    video video = new video(title, description, publishTime, channelTitle, thumbnailUrl);
                    videoList.add(video);
                }

                VideoAdapter adapter = new VideoAdapter(context, videoList);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
}




