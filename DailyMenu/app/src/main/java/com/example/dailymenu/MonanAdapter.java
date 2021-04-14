package com.example.dailymenu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MonanAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<MonAn> monAnList;

    public MonanAdapter(Context context, int layout, List<MonAn> monAnList) {
        this.context = context;
        this.layout = layout;
        this.monAnList = monAnList;
    }

    @Override
    public int getCount() {
        return monAnList.size();
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
        TextView txtTenMonan;
        ImageView imgHinhAnh;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder Hoder;
        if(convertView==null){
            Hoder= new ViewHoder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//Convert từ layout XML sang View code Java
            convertView= inflater.inflate(layout, null);
            //Ánh xạ
            Hoder.txtTenMonan=(TextView) convertView.findViewById(R.id.tvTenMonan);
            Hoder.imgHinhAnh=(ImageView) convertView.findViewById(R.id.imgMonan);
            convertView.setTag(Hoder);//Gán dữ liệu nếu view load nên lần đầu
        }
        else {
             Hoder=(ViewHoder) convertView.getTag();//Lấy dữ liệu đã có không phải load lại
        }
        //Gán giá trị
        MonAn monAn= monAnList.get(position);
        Hoder.txtTenMonan.setText(monAn.getTenMonAn());
        byte[] hinhanh= monAn.getHinhAnh();
        Bitmap bitmap= BitmapFactory.decodeByteArray(hinhanh,0,hinhanh.length);
//      Bitmap bitmap=BitmapFactory.decodeByteArray(congThuc.getAnhMonAn(),0,congThuc.getAnhMonAn().length);
        Hoder.imgHinhAnh.setImageBitmap(bitmap);
        return convertView;
    }
}
