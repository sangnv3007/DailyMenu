package com.example.dailymenu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.dailymenu.NoteFragment.database;

public class Login extends AppCompatActivity {
//    List<User> userList;
//    UserAdapter userAdapter;
    Button btndangnhap,btndangky,btnSubmit,btnCancel;
    EditText user,password,userRegister,passwordRegister,repasswordRegister;
    CheckBox showPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Ánh xạ các view trong xml
        user=(EditText) findViewById(R.id.user);
        password=(EditText) findViewById(R.id.password);
        btndangnhap=(Button) findViewById(R.id.btnLogin);
        btndangky=(Button) findViewById(R.id.btnRegister);
        showPassword=(CheckBox) findViewById(R.id.checkboxShowPass);
        //Thực hiện tạo bảng Công thức
        database = new Database(Login.this,"DailyMenu.sqlite",null,1);
        database.NonQueryData("CREATE TABLE IF NOT EXISTS USER(ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,USER NVARCHAR(50),PASSWORD NVARCHAR(100))");
        //Xử lý sự kiện ẩn hiện mật khẩu
        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //Show password
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    //Hide password
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        //Xử lý sự kiện khi click vào button đăng ký
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog= new Dialog(Login.this);
                dialog.setCancelable(false);//Không cho thao tác bên ngoài hộp thoại
                dialog.setContentView(R.layout.dialog_dangky); //Đặt layout là layout đăng kí(activity_register)
                userRegister=(EditText) dialog.findViewById(R.id.userRegiter);
                passwordRegister=(EditText) dialog.findViewById(R.id.passwordRegiter);
                repasswordRegister=(EditText) dialog.findViewById(R.id.RepasswordRegiter);
                btnSubmit=(Button) dialog.findViewById(R.id.btnSubmit);
                btnCancel=(Button) dialog.findViewById(R.id.btnCancel);
                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Xử lý sự kiện khi bấm nút Submit Đăng ký
                        String userregister=userRegister.getText().toString();
                        String passreigter=passwordRegister.getText().toString();
                        String repassregiter=repasswordRegister.getText().toString();
                        if(userregister.equals("") || passreigter.equals("") || repassregiter.equals("")){
                            Toast.makeText(Login.this,"Vui lòng nhập đầy đủ thông tin.",Toast.LENGTH_SHORT).show();
                        }
                        if(passreigter.equals(repassregiter)){
                            Boolean checkuser= database.checkUserName(userregister);
                            if(checkuser==false){
                                database.NonQueryData("INSERT INTO USER VALUES(null,'"+userregister+"','"+passreigter+"')");
                                Toast.makeText(Login.this,"Thêm tài khoản thành công.",Toast.LENGTH_SHORT).show();
//                                dialog.cancel();
                            }
                            else {
                                Toast.makeText(Login.this,"Tài khoản"+" "+userregister+ "đã tồn tại.",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(Login.this,"Mật khẩu không khớp vui lòng kiểm tra lại.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
        //Xử lý sự kiện nhân nút đăng nhập
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userlogin=user.getText().toString().trim();
                String passlogin=password.getText().toString().trim();
                if(userlogin.equals("") || passlogin.equals(""))
                {
                    Toast.makeText(Login.this,"Bạn chưa nhập đủ thông tin tài khoản.",Toast.LENGTH_SHORT).show();
                }
                else {
                   Boolean checkuserPassword= database.checkUserPassWord(userlogin,passlogin);
                   if(checkuserPassword==true){
                        Intent intentuser= new Intent(Login.this,MainActivity.class);
                        intentuser.putExtra("keyuser",userlogin);
                        Toast.makeText(Login.this,"Xin chào "+" "+userlogin,Toast.LENGTH_SHORT).show();
                        startActivity(intentuser);
                   }
                   else {
                       Toast.makeText(Login.this,"Thông tin tài khoản chưa chính xác.Hãy kiểm tra lại!",Toast.LENGTH_SHORT).show();
                   }
                }
            }
        });
    }
}