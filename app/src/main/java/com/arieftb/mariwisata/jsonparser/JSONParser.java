package com.arieftb.mariwisata.jsonparser;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by root on 11/08/17.
 */

public class JSONParser {

    static InputStream mInputStream = null;
    static JSONObject mJSONObject = null;
    static String json = "";


    public JSONObject getJSONFromUrl(String url) {
        try {
            DefaultHttpClient mHttpClient = new DefaultHttpClient();
            HttpPost mHttpPost = new HttpPost(url);

            HttpResponse mHttpResonse = mHttpClient.execute(mHttpPost);
            HttpEntity mHttpEntity = mHttpResonse.getEntity();
            mInputStream = mHttpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        try {
            BufferedReader mBufferedReader = new BufferedReader(new InputStreamReader(
                    mInputStream, "iso-8859-1"),8);
            StringBuilder mStringBuilder = new StringBuilder();
            String line = null;
            while ((line = mBufferedReader.readLine()) != null){
                mStringBuilder.append(line + "\n");
            }
            mInputStream.close();
            json = mStringBuilder.toString();

        } catch (IOException e) {
            Log.e("Buffer Error", "Error Converting Result " + e.toString());
        }


        try {
            mJSONObject = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return mJSONObject;
    }

    public JSONObject makeHttpRequest(String url, String method, List<NameValuePair> params){

        try{

            if (method == "POST"){
                DefaultHttpClient mDefaultHttpClient = new DefaultHttpClient();
                HttpPost mHttpPost = new HttpPost(url);
                mHttpPost.setEntity(new UrlEncodedFormEntity(params));

                HttpResponse mHttpResponse = mDefaultHttpClient.execute(mHttpPost);
                HttpEntity mHttpEntity = mHttpResponse.getEntity();
                mInputStream = mHttpEntity.getContent();

                Log.e("Info : ", "Berhasil");
            } else if (method == "GET"){
                DefaultHttpClient mDefaultHttpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                HttpGet mHttpGet = new HttpGet(url);

                HttpResponse mHttpResponse = mDefaultHttpClient.execute(mHttpGet);
                HttpEntity mHttpEntity = mHttpResponse.getEntity();
                mInputStream = mHttpEntity.getContent();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader mBufferedReader = new BufferedReader(new InputStreamReader(
                    mInputStream, "iso-8859-1"),8);
            StringBuilder mStringBuilder = new StringBuilder();
            String line = null;
            while ((line = mBufferedReader.readLine()) != null){
                mStringBuilder.append(line + "\n");
            }
            mInputStream.close();
            json = mStringBuilder.toString();

        } catch (IOException e) {
            Log.e("Buffer Error", "Error Converting Result " + e.toString());
        }

        try {
            mJSONObject = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return mJSONObject;
    }

}
