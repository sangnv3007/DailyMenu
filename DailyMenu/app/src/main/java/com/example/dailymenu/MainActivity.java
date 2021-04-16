package com.example.dailymenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView txtNameUser;
    ImageView call,order;
    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Lấy tên user sau khi đăng nhập
        txtNameUser= (TextView) findViewById(R.id.user_login);
        //Ánh xạ Bottom_nav_view,ViewPager
        call=(ImageView) findViewById(R.id.call);
        order=(ImageView) findViewById(R.id.order);
        bottomNavigationView= (BottomNavigationView) findViewById(R.id.bottom_nav_dailymenu);
        viewPager= (ViewPager) findViewById(R.id.ViewpageDailymenu);
        setUpViewPager();//Hàm xử lý ViewPager
        //Gán tên user vào txtNameUser
        Intent intentname= getIntent();
        String nameuser=intentname.getStringExtra("keyuser");
        //Nhận dữ liệu từ
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
                }
                return true;
            }
        });
        //Hàm thực hiện khi click vào image order đồ ăn
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intentorder = new Intent();
            intentorder.setAction(Intent.ACTION_VIEW);
            intentorder.setData(Uri.parse("https://www.foody.vn/"));
            startActivity(intentorder);
            }
        });
        //Hàm thực hiện khi click vào image Call
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentcall= new Intent();
                intentcall.setAction(Intent.ACTION_CALL);
                intentcall.setData(Uri.parse("tel:0373853447"));
                startActivity(intentcall);
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