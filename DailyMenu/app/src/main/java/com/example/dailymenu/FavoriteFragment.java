package com.example.dailymenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import static com.example.dailymenu.NoteFragment.database;

public class FavoriteFragment extends Fragment {
    List<MonAn> listFavorite;
    ListView listviewFavo;
    ImageView imgageFavo;
    TextView textViewFavo;
    MonanAdapter monanyeuthichAdapter;
    BottomNavigationView bottomNavigationView;
    private static final String STATE="Trang thai";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        Log.e(STATE, "onCreateView: ");
        //Ánh xạ
        listviewFavo= (ListView) view.findViewById(R.id.favoriteListView);
        imgageFavo=(ImageView) view.findViewById(R.id.imgMonan);
        textViewFavo=(TextView) view.findViewById(R.id.tvTenMonan);
        listFavorite= new ArrayList<>();
        monanyeuthichAdapter = new MonanAdapter(getActivity(),R.layout.dong_monan,listFavorite);
        listviewFavo.setAdapter(monanyeuthichAdapter);
        bottomNavigationView= (BottomNavigationView) view.findViewById(R.id.bottom_nav_dailymenu);
        database = new Database(getActivity(),"DailyMenu.sqlite",null,1);
        database.NonQueryData("CREATE TABLE IF NOT EXISTS MONAN (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,TENMONAN NVARCHAR(50),HINHANH BLOB,ID_CONGTHUC INTEGER NOT NULL,FOREIGN KEY (ID_CONGTHUC) REFERENCES CONGTHUC(ID))");

        return view;
    }
    private void acGetData(){
        SaveData.IDNhan=SaveData.IDMonAN;
        Cursor cursor= database.QueryData("SELECT * FROM MONAN WHERE ID='"+SaveData.IDNhan+"'");
        listFavorite.clear();
        while (cursor.moveToNext()){
            int id= cursor.getInt(0);
            String tenmon=cursor.getString(1);
            byte[] hinhanh=cursor.getBlob(2);
            int idCongthuc=cursor.getInt(3);
            listFavorite.add(new MonAn(id,tenmon,hinhanh,idCongthuc));
        }
        monanyeuthichAdapter.notifyDataSetChanged();
    }
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        Log.e(STATE, "onAttach: ");
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Log.e(STATE, "onCreate: ");
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        acGetData();
//        super.onActivityCreated(savedInstanceState);
//        Log.e(STATE, "onActivityCreated: ");
//    }
    @Override
    //
    public void onStart() {
        acGetData();
        super.onStart();
        Log.e(STATE, "onStart: ");
    }
    @Override
    public void onResume() {

        acGetData();
        super.onResume();
        Log.e(STATE, "onResume: ");
    }
////    public void onStop() {
////        acGetData();
////        super.onStop();
////        Log.e(STATE, "onStop: ");
////    }
////    //Khi thoát ứng dụng và load lại vẫn còn danh sách yêu thích cũ
////    @Override
////    public void onDestroyView() {
////        acGetData();
////        super.onDestroyView();
////        Log.e(STATE, " onDestroyView: ");
////    }
    @Override
        public void onDestroy() {
        acGetData();
        super.onDestroy();
        Log.e(STATE, "onDestroy: ");
    }

}
