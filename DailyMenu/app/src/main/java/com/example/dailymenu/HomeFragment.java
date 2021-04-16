package com.example.dailymenu;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.dailymenu.NoteFragment.database;

public class HomeFragment extends Fragment {
    GridView gridViewCongThuc;
    List<CongThuc> congThucArrayList;
    CongThucAdapter congThucAdapter;
    public int idCT;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //Ánh xạ
        gridViewCongThuc=(GridView) view.findViewById(R.id.GrvCongthuc);
        congThucArrayList= new ArrayList<>();
        congThucAdapter = new CongThucAdapter(getActivity(),R.layout.dong_congthuc,congThucArrayList);
        gridViewCongThuc.setAdapter(congThucAdapter);
        //Thực hiện tạo bảng Công thức
        database = new Database(getActivity(),"DailyMenu.sqlite",null,1);
        database.NonQueryData("CREATE TABLE IF NOT EXISTS CONGTHUC(ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,TENCONGTHUC NVARCHAR(50),HINHANH BLOB)");

        acGetData();
        //Insert dữ liệu vào bảng
        //--Chuyển data Image -> byte[]
            //Món Xào
//        Bitmap MonXao=((BitmapDrawable) getResources().getDrawable(R.drawable.xao)).getBitmap();
//        ByteArrayOutputStream byteArray= new ByteArrayOutputStream();
//        MonXao.compress(Bitmap.CompressFormat.PNG,100,byteArray);
//        byte[] monxao=byteArray.toByteArray();
//        database.INSERT_CONGTHUC("Món Xào",monxao);
//        Toast.makeText(getActivity(),"Thêm công thức xào thành công",Toast.LENGTH_SHORT).show();
////            //Món Chiên
//        Bitmap MonChien=((BitmapDrawable) getResources().getDrawable(R.drawable.chien)).getBitmap();
//        ByteArrayOutputStream byteArray1= new ByteArrayOutputStream();
//        MonChien.compress(Bitmap.CompressFormat.PNG,100,byteArray1);
//        byte[] monchien=byteArray1.toByteArray();
//        database.INSERT_CONGTHUC("Món Chiên",monchien);
//        Toast.makeText(getActivity(),"Thêm công thức thành công",Toast.LENGTH_SHORT).show();
////            //Món Chay
//        Bitmap MonChay=((BitmapDrawable) getResources().getDrawable(R.drawable.chay)).getBitmap();
//        ByteArrayOutputStream byteArray2= new ByteArrayOutputStream();
//        MonChay.compress(Bitmap.CompressFormat.PNG,100,byteArray2);
//        byte[] monchay=byteArray2.toByteArray();
//        database.INSERT_CONGTHUC("Món Chay",monchay);
//        Toast.makeText(getActivity(),"Thêm công thức món chay thành công",Toast.LENGTH_SHORT).show();
////            //Món Salad
//        Bitmap MonSalad=((BitmapDrawable) getResources().getDrawable(R.drawable.salad)).getBitmap();
//        ByteArrayOutputStream byteArray3= new ByteArrayOutputStream();
//        MonSalad.compress(Bitmap.CompressFormat.PNG,100,byteArray3);
//        byte[] monsalad=byteArray3.toByteArray();
//        database.INSERT_CONGTHUC("Món Salad",monsalad);
//        Toast.makeText(getActivity(),"Thêm công thức món salad thành công",Toast.LENGTH_SHORT).show();
////            //Món Nhậu
//        Bitmap MonNhau=((BitmapDrawable) getResources().getDrawable(R.drawable.nhau)).getBitmap();
//        ByteArrayOutputStream byteArray4= new ByteArrayOutputStream();
//        MonNhau.compress(Bitmap.CompressFormat.PNG,100,byteArray4);
//        byte[] monnhau=byteArray4.toByteArray();
//        database.INSERT_CONGTHUC("Món Nhậu",monnhau);
//        Toast.makeText(getActivity(),"Thêm công thức món nhậu thành công",Toast.LENGTH_SHORT).show();
////            //Món Hầm
//        Bitmap MonHam=((BitmapDrawable) getResources().getDrawable(R.drawable.ham)).getBitmap();
//        ByteArrayOutputStream byteArray5= new ByteArrayOutputStream();
//        MonHam.compress(Bitmap.CompressFormat.PNG,100,byteArray5);
//        byte[] monham=byteArray5.toByteArray();
//        database.INSERT_CONGTHUC("Món Hầm",monham);
//        Toast.makeText(getActivity(),"Thêm công thức món hầm thành công",Toast.LENGTH_SHORT).show();
        gridViewCongThuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(getActivity(),activity_listfood.class);
                //intentuser.putExtra("keyuser",user.getText().toString());
                idCT=congThucArrayList.get(position).getId();
                intent.putExtra("id",idCT);
                startActivity(intent);
            }
        });
        return view;
    }
    private void acGetData(){
        Cursor cursor= database.QueryData("SELECT* FROM CONGTHUC");
        congThucArrayList.clear();
        while (cursor.moveToNext()){
            int id= cursor.getInt(0);
            String tenCongThuc= cursor.getString(1);
            byte [] hinhanh=cursor.getBlob(2);
            congThucArrayList.add(new CongThuc(id,tenCongThuc,hinhanh));
        }
        congThucAdapter.notifyDataSetChanged();
    }
}
