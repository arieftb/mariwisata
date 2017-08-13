package com.arieftb.mariwisata.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.arieftb.mariwisata.R;
import com.arieftb.mariwisata.pojo.DataWisata;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11/08/17.
 */

public class WisataListAdapter extends BaseAdapter{
    ArrayList itemWisata;
    Activity activity;

    public WisataListAdapter(Activity activity, ArrayList itemWisata) {
        this.itemWisata = itemWisata;
        this.activity = activity;

//        super(context, R.layout.list_wisata_custom, itemWisata);
    }

    @Override
    public int getCount() {
        return itemWisata.size();
    }

    @Override
    public Object getItem(int position) {
        return itemWisata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View mView = convertView;
        ViewHolder mHolder = null;

        if (mView == null) {
            mHolder = new ViewHolder();
            LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            mView = mInflater.inflate(R.layout.list_wisata_custom, null);
            mHolder.ivGambarWisata = (ImageView) mView.findViewById(R.id.iv_gambarWisata);
            mHolder.tvNamaWisata = (TextView) mView.findViewById(R.id.tv_namaWisata);
            mHolder.tvAlamatWisata = (TextView) mView.findViewById(R.id.tv_alamatWisata);
            mView.setTag(mHolder);

        } else {
            mHolder = (ViewHolder) mView.getTag();
        }
        DataWisata mDataWisata = (DataWisata) getItem(position);
        mHolder.ivGambarWisata.setImageBitmap(mDataWisata.getGambar());
//        mHolder.ivGambarWisata.setImageResource(R.drawable.candiprambanan);
        mHolder.tvNamaWisata.setText(mDataWisata.getNama());
        mHolder.tvAlamatWisata.setText(mDataWisata.getAlamat());


        return mView;
    }

    static class ViewHolder {
        ImageView ivGambarWisata;
        TextView tvNamaWisata, tvAlamatWisata;
    }
}
