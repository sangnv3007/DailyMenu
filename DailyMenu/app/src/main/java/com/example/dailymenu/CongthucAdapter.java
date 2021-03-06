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

public class CongThucAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<CongThuc> congThucList;

    public CongThucAdapter(Context context, int layout, List<CongThuc> congThucList) {
        this.context = context;
        this.layout = layout;
        this.congThucList = congThucList;
    }

    @Override
    public int getCount() {
        return congThucList.size();
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
        TextView txtTenmonan;
        ImageView imgAnhmonan;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CongThucAdapter.ViewHoder Hoder;
        if(convertView==null){
            Hoder= new ViewHoder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//Convert từ layout XML sang View code Java
            convertView= inflater.inflate(layout, null);
            //Ánh xạ
            Hoder.txtTenmonan=(TextView) convertView.findViewById(R.id.tvTenCT);
            Hoder.imgAnhmonan=(ImageView) convertView.findViewById(R.id.imgCT);
            convertView.setTag(Hoder);//Gán dữ liệu nếu view load nên lần đầu
        }
        else {
            Hoder= (ViewHoder) convertView.getTag();//Lấy dữ liệu đã có không phải load lại
        }
        //Gán giá trị
        CongThuc congThuc= congThucList.get(position);
        Hoder.txtTenmonan.setText(congThuc.getTenMonan());
        //Chuyển mảng byte[] ->bitmap
        byte[] hinhanh= congThuc.getAnhMonAn();
        Bitmap bitmap= BitmapFactory.decodeByteArray(hinhanh,0,hinhanh.length);
//      Bitmap bitmap=BitmapFactory.decodeByteArray(congThuc.getAnhMonAn(),0,congThuc.getAnhMonAn().length);
        Hoder.imgAnhmonan.setImageBitmap(bitmap);
        return convertView;
    }
}
