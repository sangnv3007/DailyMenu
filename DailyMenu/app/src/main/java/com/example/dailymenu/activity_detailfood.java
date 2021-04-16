package com.example.dailymenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.dailymenu.NoteFragment.database;

public class activity_detailfood extends AppCompatActivity {
    WebView webView;
    TextView tenmonan;
    public String layten;
    public int id_mon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailfood);
        webView=(WebView) findViewById(R.id.WebviewChitiet);
        tenmonan=(TextView) findViewById(R.id.tenChitietMonan);
        //Tạo bảng chitietmonan
        database = new Database(activity_detailfood.this,"DailyMenu.sqlite",null,1);
        database.NonQueryData("CREATE TABLE IF NOT EXISTS CHITIETMONAN (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,NOIDUNG NVARCHAR(50),ID_MONAN INTEGER NOT NULL,FOREIGN KEY (ID_MONAN) REFERENCES MONAN(ID))");
//        Toast.makeText(this,"Tạo bảng thành công",Toast.LENGTH_SHORT).show();

        Intent intentname= getIntent();
        String namefood=intentname.getStringExtra("tenmon");
        id_mon=intentname.getIntExtra("idmon",0);
        tenmonan.setText(namefood);
        acGetnd();
        //Insert dữ liệu
        //Thêm dữ liệu bằng sự kiện khi click vào các ô listView trong bảng Món Ăn
//        database.INSERT_CHITIETMONAN("sohuyetsaotoi",1);
//        database.INSERT_CHITIETMONAN("cachlamphoxao",2);
//        database.INSERT_CHITIETMONAN("raubixaotomnon",3);
//        database.INSERT_CHITIETMONAN("raumuongxao",4);
//        //5,6,7,9,10,11,13,17,21,25
//        database.INSERT_CHITIETMONAN("mixaothitbobongcai",5);
//        database.INSERT_CHITIETMONAN("phoxaothapcam",6);
//        database.INSERT_CHITIETMONAN("myxaogion",7);
//        database.INSERT_CHITIETMONAN("nemchuagian",9);
//        database.INSERT_CHITIETMONAN("banhkhoaitaychienmemthom",10);
//        database.INSERT_CHITIETMONAN("banhcuonngotcampuchia",13);
//        database.INSERT_CHITIETMONAN("xalachtrondaugiam",17);
//        database.INSERT_CHITIETMONAN("gatamuoixongkhoi",21);
//        database.INSERT_CHITIETMONAN("thitbaphamraucu",25);
//        tenmonan.setText(namefood);
        //Đổ dữ liệu từ các trang .hrml lên Webview
        WebSettings webSettings= webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String url= "file:///android_asset/" +layten+ ".html";
        webView.loadUrl(url);
    }

    //Hàm hiển thị menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.favorite:
                SaveData.IDMonAN=id_mon;
                Toast.makeText(activity_detailfood.this,"Bạn đã thêm món ăn yêu thích thành công.",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tangcochu:
                Toast.makeText(activity_detailfood.this,"Tăng cỡ chữ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.giamcochu:
                Toast.makeText(activity_detailfood.this,"Giảm cỡ chữ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rate:
                Toast.makeText(activity_detailfood.this,"Đánh giá ứng dụng",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void acGetnd(){
        Cursor cursor= database.QueryData("SELECT * FROM CHITIETMONAN WHERE ID_MONAN='"+id_mon+"'");
        while (cursor.moveToNext()){
            String tenmonhtml=cursor.getString(1);
            layten=tenmonhtml;
        }
    }
}