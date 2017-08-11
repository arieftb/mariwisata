package com.arieftb.mariwisata.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.arieftb.mariwisata.R;
import com.arieftb.mariwisata.session.SessionManager;

public class ListWisataActivity extends AppCompatActivity {

    SessionManager mSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_wisata);
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
