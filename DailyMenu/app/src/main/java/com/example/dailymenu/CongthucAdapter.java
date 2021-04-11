package com.example.dailymenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CongthucAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Congthuc> CongthucList;
    public CongthucAdapter(Context context, int layout, List<Congthuc> congthucList) {
        this.context = context;
        this.layout = layout;
        CongthucList = congthucList;
    }
    //Trả về số dòng trong Gridview
    @Override
    public int getCount() {
        return CongthucList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(layout,null);
        //Ánh xạ convertView
        TextView txtTenCT= (TextView) convertView.findViewById(R.id.tvTenCT);
        ImageView imgCT=(ImageView) convertView.findViewById(R.id.imgCT);
        //Gán giá trị
        Congthuc congthuc= CongthucList.get(position);
        txtTenCT.setText(congthuc.getTenCT());
        imgCT.setImageResource(congthuc.getImgAnh());
        return convertView;
    }
}
