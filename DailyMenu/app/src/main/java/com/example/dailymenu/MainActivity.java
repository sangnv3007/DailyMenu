package com.example.dailymenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView txtNameUser;
    //
    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Lấy tên user sau khi đăng nhập
        txtNameUser= (TextView) findViewById(R.id.user_login);
        //Ánh xạ Bottom_nav_view,ViewPager
        bottomNavigationView= (BottomNavigationView) findViewById(R.id.bottom_nav_dailymenu);
        viewPager= (ViewPager) findViewById(R.id.ViewpageDailymenu);
        setUpViewPager();//Hàm xử lý ViewPager
        //Thêm các công thức vào GridView
//        congthucArrayList= new ArrayList<Congthuc>();
//        congthucArrayList.add(new Congthuc("Món Xào",R.drawable.xao2));
//        congthucArrayList.add(new Congthuc("Món Nộm", R.drawable.nom));
//        congthucArrayList.add(new Congthuc("Làm Bánh",R.drawable.donut3));
//        congthucArrayList.add(new Congthuc("Món Chiên",R.drawable.chien));
//        congthucAdapter= new CongthucAdapter(this,R.layout.dong_congthuc,congthucArrayList);
//        gvCongthuc.setAdapter(congthucAdapter);
        //Gán tên user vào txtNameUser
        Intent intentname= getIntent();
        String nameuser=intentname.getStringExtra("keyuser");
        txtNameUser.setText("Xin chào"+" "+nameuser);
        //Hàm xử lý khi click vào các item trong BottomNavigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.action_favorite:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.action_note:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
    }
    //Hàm thiết lập ViewPager
    private void setUpViewPager(){
        //Gán dữ liệu từ ViewPageApdapter sang viewPager
        ViewPageAdapter viewPageAdapter= new ViewPageAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPageAdapter);
        //Hàm xử lý khi vuốt màn hình chuyển các viewPager
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        //Thay đổi màu của item trong bottomNavigationView khi được chọn
                        bottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.action_favorite).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.action_note).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}