package com.example.dailymenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gvCongthuc;
    ArrayList<Congthuc> congthucArrayList;
    CongthucAdapter congthucAdapter;
    TextView txtNameUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gvCongthuc= (GridView) findViewById(R.id.grvCT);
        //Lấy tên user sau khi đăng nhập
        txtNameUser= (TextView) findViewById(R.id.userlogin);
        //Thêm các công thức vào GridView
        congthucArrayList= new ArrayList<>();
        congthucArrayList.add(new Congthuc("Món Xào",R.drawable.xao2));
        congthucArrayList.add(new Congthuc("Món Nộm", R.drawable.nom));
        congthucArrayList.add(new Congthuc("Làm Bánh",R.drawable.donut3));
        congthucArrayList.add(new Congthuc("Món Chiên",R.drawable.chien));
        congthucAdapter= new CongthucAdapter(this,R.layout.dong_congthuc,congthucArrayList);
        gvCongthuc.setAdapter(congthucAdapter);
        //Gán tên user vào txtNameUser
        Intent intentname= getIntent();
        String nameuser=intentname.getStringExtra("key1");
        txtNameUser.setText("Xin chào"+" "+nameuser);
    }

}