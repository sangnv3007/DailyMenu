package com.example.dailymenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class GhichuAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Ghichu> ghichuList;

    public GhichuAdapter(Context context, int layout, List<Ghichu> ghichuList) {
        this.context = context;
        this.layout = layout;
        this.ghichuList = ghichuList;
    }

    @Override
    public int getCount() {
        return ghichuList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHoder{
        TextView tvtenGhichu;
        TextView tvndGhichu;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder hoder;
        if(convertView == null){
            hoder= new ViewHoder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//Convert từ layout XML sang View code Java
            convertView= inflater.inflate(layout, null);
            //Ánh xạ

            hoder.tvtenGhichu=(TextView) convertView.findViewById(R.id.tenghichu);
            hoder.tvndGhichu=(TextView) convertView.findViewById(R.id.ndghichu);
            convertView.setTag(hoder);//Gán dữ liệu nếu view load nên lần đầu
        }
        else {
            hoder=(ViewHoder) convertView.getTag();//Lấy dữ liệu đã có không phải load lại
        }
        Ghichu ghichu= ghichuList.get(position);
        hoder.tvtenGhichu.setText(ghichu.getTenGhichu());
        hoder.tvndGhichu.setText(ghichu.getNdGhichu());
        return convertView;
    }
}
