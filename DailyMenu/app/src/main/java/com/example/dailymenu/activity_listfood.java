package com.example.dailymenu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.dailymenu.NoteFragment.database;

public class activity_listfood extends AppCompatActivity {
    ImageView imgMonan;
    TextView tvTenMonan;
    ListView listView;
    List<MonAn> monAnList;
    MonanAdapter monanAdapter;
    public int ID_CT;
    public int id_Mon;
    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listfood);
        imgMonan=(ImageView) findViewById(R.id.imgMonan);
        tvTenMonan=(TextView) findViewById(R.id.tvTenMonan);
        listView=(ListView) findViewById(R.id.listviewMonan);
        monAnList= new ArrayList<>();
        monanAdapter= new MonanAdapter(activity_listfood.this,R.layout.dong_monan,monAnList);
        listView.setAdapter(monanAdapter);
        //Các câu truy vấn SQL tạo bảng, thêm dữ liệu
        database = new Database(this,"DailyMenu.sqlite",null,1);
        database.NonQueryData("CREATE TABLE IF NOT EXISTS MONAN (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,TENMONAN NVARCHAR(50),HINHANH BLOB,ID_CONGTHUC INTEGER NOT NULL,FOREIGN KEY (ID_CONGTHUC) REFERENCES CONGTHUC(ID))");

        //Lấy Id của Công thức hiển thị lên activity_listfood
        Intent intent= getIntent();
        int idCT=intent.getIntExtra("id",0);//Lấy ra giá  trị id của công thức món ăn vừa click
        ID_CT=idCT;//Gán giá trị id Công thức món ăn vào biến ID_CT
        acGetData();
       //Thêm dữ liệu vào bảng
            //Các món xào
        //Chuyển dữ liệu dạng image-> byte[]
//        Bitmap MonAn=((BitmapDrawable) getResources().getDrawable(R.drawable.sohuyetxaotoi)).getBitmap();
//        ByteArrayOutputStream byteArray= new ByteArrayOutputStream();
//        MonAn.compress(Bitmap.CompressFormat.PNG,100,byteArray);
//        byte[] hinhanh=byteArray.toByteArray();
//        database.INSERT_MONAN("Cách làm món sò huyết xào tỏi cực ngon.",hinhanh,idCT);
//        Toast.makeText(this,"Thêm món ăn thành công thành công",Toast.LENGTH_SHORT).show();
//        //
//        Bitmap MonAn1=((BitmapDrawable) getResources().getDrawable(R.drawable.phoxao)).getBitmap();
//        ByteArrayOutputStream byteArray1= new ByteArrayOutputStream();
//        MonAn1.compress(Bitmap.CompressFormat.PNG,100,byteArray1);
//        byte[] hinhanh1=byteArray.toByteArray();
//        database.INSERT_MONAN("Cách làm phở xào",hinhanh1,idCT);
//        Toast.makeText(this,"Thêm món ăn thành công thành công",Toast.LENGTH_SHORT).show();
//        //
//        Bitmap MonAn2=((BitmapDrawable) getResources().getDrawable(R.drawable.raubixaotom)).getBitmap();
//        ByteArrayOutputStream byteArray2= new ByteArrayOutputStream();
//        MonAn2.compress(Bitmap.CompressFormat.PNG,100,byteArray2);
//        byte[] hinhanh2=byteArray.toByteArray();
//        database.INSERT_MONAN("Ngọt thơm món rau bí xào tôm nõn.",hinhanh2,idCT);
//        Toast.makeText(this,"Thêm món ăn thành công thành công",Toast.LENGTH_SHORT).show();
//        //
//        Bitmap MonAn3=((BitmapDrawable) getResources().getDrawable(R.drawable.raumuongxao)).getBitmap();
//        ByteArrayOutputStream byteArray3= new ByteArrayOutputStream();
//        MonAn3.compress(Bitmap.CompressFormat.PNG,100,byteArray3);
//        byte[] hinhanh3=byteArray.toByteArray();
//        database.INSERT_MONAN("Rau muống xào món ăn khkoong thể thiếu vào mùa hè.",hinhanh3,idCT);
//        Toast.makeText(this,"Thêm món ăn thành công thành công",Toast.LENGTH_SHORT).show();
        //
        //Sự kiện click vào các Item trong listView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                id_Mon=monAnList.get(position).getIdMonAn();
                Toast.makeText(activity_listfood.this,monAnList.get(position).getTenMonAn(),Toast.LENGTH_SHORT).show();
            }
        });
        //Sự kiện long click vào các ItemListView
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(activity_listfood.this);
                dialogBuilder.setTitle("Xác nhận");
                dialogBuilder.setMessage("Bạn có chắc chẵn muốn xóa món ăn này ?");
                dialogBuilder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialogBuilder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database.NonQueryData("DELETE FROM MONAN WHERE ID='"+id_Mon+"'");
                        Toast.makeText(activity_listfood.this,"Bạn đã xóa thành công ^^",Toast.LENGTH_SHORT).show();
                        acGetData();
                    }
                });
                dialogBuilder.show();
                return false;
            }
        });
    }
    private void acGetData(){
        Cursor cursor= database.QueryData("SELECT* FROM MONAN WHERE ID_CONGTHUC='"+ID_CT+"'");
        monAnList.clear();
        while (cursor.moveToNext()){
            int id= cursor.getInt(0);
            String tenMonAn= cursor.getString(1);
            byte [] hinhanh=cursor.getBlob(2);
            int id_CT=cursor.getInt(3);
            monAnList.add(new MonAn(id,tenMonAn,hinhanh,id_CT));
        }

        monanAdapter.notifyDataSetChanged();
    }
}