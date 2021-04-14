package com.example.dailymenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button btndangnhap,btndangky,btnSubmit,btnCancel;
    EditText user,password,userRegister,passwordRegister;
    CheckBox showPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user=(EditText) findViewById(R.id.user);
        password=(EditText) findViewById(R.id.password);
        btndangnhap=(Button) findViewById(R.id.btnLogin);
        btndangky=(Button) findViewById(R.id.btnRegister);
        showPassword=(CheckBox) findViewById(R.id.checkboxShowPass);
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
                dialog.setContentView(R.layout.activity_register); //Đặt layout là layout đăng kí(activity_register)
                userRegister=(EditText) dialog.findViewById(R.id.userRegiter);
                passwordRegister=(EditText) dialog.findViewById(R.id.passwordRegiter);
                btnSubmit=(Button) dialog.findViewById(R.id.btnSubmit);
                btnCancel=(Button) dialog.findViewById(R.id.btnCancel);
                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Xử lý sự kiện khi bấm nút Submit Đăng ký
                        dialog.cancel();
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
                if(user.getText().length()!=0 && password.getText().length()!=0)
                {
                    if(user.getText().toString().equals(userRegister) && password.getText().toString().equals(passwordRegister))
                    {
                        //Xử lý sự kiện ở đây
                    }
                    else if (user.getText().toString().equals("admin") && password.getText().toString().equals("admin"))
                    {
                        Intent intentuser= new Intent(Login.this,MainActivity.class);
                        intentuser.putExtra("keyuser",user.getText().toString());
                        startActivity(intentuser);

                    }
                    else {
                        Toast.makeText(Login.this,"Sai tên tài khoản hoặc Password.",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Login.this,"Bạn chưa nhập đủ thông tin tài khoản.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}