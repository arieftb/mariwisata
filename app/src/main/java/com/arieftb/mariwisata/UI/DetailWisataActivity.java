package com.arieftb.mariwisata.UI;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arieftb.mariwisata.R;
import com.arieftb.mariwisata.session.SessionManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class DetailWisataActivity extends AppCompatActivity {

    private ImageView ivGambarWisata;
    private TextView tvNamaWisata, tvAlamatWisata, tvDetailWisata;
    SessionManager mSessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);

        Bundle mBundle = getIntent().getExtras();
        getSupportActionBar().setTitle(mBundle.getString("nama_wisata"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ivGambarWisata = (ImageView) findViewById(R.id.iv_gambarWisata);
        tvNamaWisata = (TextView) findViewById(R.id.tv_namaWisata);
        tvAlamatWisata = (TextView) findViewById(R.id.tv_alamatWisata);
        tvDetailWisata = (TextView) findViewById(R.id.tv_detailWisata);

        tvNamaWisata.setText(mBundle.getString("nama_wisata"));
        tvAlamatWisata.setText(mBundle.getString("alamat_wisata"));
        tvDetailWisata.setText(mBundle.getString("detail_wisata"));

        mSessionManager = new SessionManager(DetailWisataActivity.this);
        mSessionManager.checkLogin();

        new LoadImage().execute();

        Log.e("Gambar ", mBundle.getString("gambar_wisata"));
    }


    private class LoadImage extends AsyncTask<Object, Object, Bitmap> {

        @Override
        protected Bitmap doInBackground(Object... params) {

            Bundle mBundle = getIntent().getExtras();
            try {
                URL url = new URL(mBundle.getString("gambar_wisata"));
                //try this url = "http://0.tqn.com/d/webclipart/1/0/5/l/4/floral-icon-5.jpg"
                HttpGet httpRequest = null;

                httpRequest = new HttpGet(url.toURI());

                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = (HttpResponse) httpclient
                        .execute(httpRequest);

                HttpEntity entity = response.getEntity();
                BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
                InputStream input = b_entity.getContent();

                Bitmap bitmap = BitmapFactory.decodeStream(input);
                return bitmap;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap s) {
            super.onPostExecute(s);

            ivGambarWisata.setImageBitmap(s);
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
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        if (item.getItemId()==R.id.opt_logout){
            mSessionManager.logoutUser();
            Toast.makeText(DetailWisataActivity.this, "Logout", Toast.LENGTH_SHORT).show();
//            showDialog();
//            Toast.makeText(getActivity(),"Keluar", Toast.LENGTH_SHORT).show();
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}
