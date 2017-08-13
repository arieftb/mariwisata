package com.arieftb.mariwisata.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.arieftb.mariwisata.R;
import com.arieftb.mariwisata.adapter.WisataListAdapter;
import com.arieftb.mariwisata.jsonparser.JSONParser;
import com.arieftb.mariwisata.pojo.DataWisata;
import com.arieftb.mariwisata.session.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ListWisataActivity extends AppCompatActivity {


    private ProgressDialog mProgressDialog;
    private ListView lvListWisata;
    ArrayList listWisata;
    String succes;
    SessionManager mSessionManager;
    DataWisata mDataWisata;
    JSONParser mJSONParser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_wisata);
        lvListWisata = (ListView) findViewById(R.id.lv_listWisata);
        mSessionManager = new SessionManager(ListWisataActivity.this);
        mSessionManager.checkLogin();

        mJSONParser = new JSONParser();
        listWisata = new ArrayList<>();


        new ReadWisata().execute();

    }

    private class ReadWisata extends AsyncTask<String, String, String> {
        ArrayList<HashMap<String,String>> mWisataList = new ArrayList<HashMap<String,String>>();

        final String URL = "http://erporate.com/bootcamp/jsonBootcamp.php";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressDialog = new ProgressDialog(ListWisataActivity.this);
            mProgressDialog.setMessage("Loading Data...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            JSONParser mJSONParser = new JSONParser();
            JSONObject mJSONObject = mJSONParser.getJSONFromUrl(URL);

            try {

                succes = mJSONObject.getString("result");
                Log.e("Notif", " Nilai Sukses = " + succes);

                JSONArray mJSONArray = mJSONObject.getJSONArray("data");

                if (succes.equals("true")) {
                    for (int i = 0; i < mJSONArray.length(); i++) {
                        JSONObject mObject = mJSONArray.getJSONObject(i);

                        String namaWisata = mObject.getString("nama_pariwisata");
                        String alamatWisata = mObject.getString("alamat_pariwisata");
                        String detailWisata = mObject.getString("detail_pariwisata");
                        String gambarWisata = mObject.getString("gambar_pariwisata");

                        URL mURL = new URL(gambarWisata);

                        Bitmap bmGambarWisata = BitmapFactory.decodeStream(mURL.openConnection().getInputStream());

                        mDataWisata = new DataWisata();

                        mDataWisata.setNama(namaWisata);
                        mDataWisata.setAlamat(alamatWisata);
                        mDataWisata.setDetail(detailWisata);
                        mDataWisata.setGambar(bmGambarWisata);

                        listWisata.add(mDataWisata);

                        HashMap mHash = new HashMap();

                        mHash.put("nama_pariwisata", namaWisata);
                        mHash.put("alamat_pariwisata", alamatWisata);
                        mHash.put("detail_pariwisata", detailWisata);
                        mHash.put("gambar_pariwisata", gambarWisata);
                        mWisataList.add(mHash);


                        Log.e("Sukses : ", "Ambil Data");
                    }
                } else {
                    Log.e("Gagal : ", "Tidak Bisa Ambil Data");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

                mProgressDialog.dismiss();

                ListAdapter mWisataAdapter = new WisataListAdapter(ListWisataActivity.this, listWisata);
                lvListWisata.setAdapter(mWisataAdapter);

                lvListWisata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Bundle mBundle = new Bundle();
                        mBundle.putString("nama_wisata", mWisataList.get(position).get("nama_pariwisata"));
                        mBundle.putString("alamat_wisata", mWisataList.get(position).get("alamat_pariwisata"));
                        mBundle.putString("detail_wisata", mWisataList.get(position).get("detail_pariwisata"));
                        mBundle.putString("gambar_wisata", mWisataList.get(position).get("gambar_pariwisata"));
                        Intent mIntent = new Intent(ListWisataActivity.this, DetailWisataActivity.class);
                        mIntent.putExtras(mBundle);
                        startActivity(mIntent);

//                        Toast.makeText(ListWisataActivity.this, "Ini Adalah "
//                                + mWisataList.get(position).get("nama_pariwisata"),
//                                Toast.LENGTH_SHORT).show();
                    }
                });

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_option_menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.opt_logout){
            mSessionManager.logoutUser();
            Toast.makeText(ListWisataActivity.this, "Logout", Toast.LENGTH_SHORT).show();
//            showDialog();
//            Toast.makeText(getActivity(),"Keluar", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
