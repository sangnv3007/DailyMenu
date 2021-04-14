package com.example.dailymenu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class NoteFragment extends Fragment
{
    public static Database database;
    ListView listView_note;
    List<Ghichu> ghichuList;
    GhichuAdapter ghichuAdapter;
    Button btnAddNote,btnUpdateNote;
    EditText edttenGhichu,edtndGhichu;
    private int vitri;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        listView_note=(ListView) view.findViewById(R.id.lv_note);//
        edttenGhichu= (EditText) view.findViewById(R.id.editText_tenNote);
        edtndGhichu= (EditText) view.findViewById(R.id.editText_ndNote);
        btnAddNote= (Button) view.findViewById(R.id.button_addnote);
        btnUpdateNote= (Button) view.findViewById(R.id.button_updatenote);
        //
        ghichuList= new ArrayList<>();
        ghichuAdapter= new GhichuAdapter(getActivity(),R.layout.dong_ghichu,ghichuList);
        listView_note.setAdapter(ghichuAdapter);
        //
        btnUpdateNote.setEnabled(false);
        //Tạo database
        database = new Database(getActivity(),"DailyMenu.sqlite",null,1);
        //Tạo bảng ghi chú
        database.NonQueryData("CREATE TABLE IF NOT EXISTS GHICHU (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,NAME NVARCHAR(50),NOIDUNG NVARCHAR(200))");
        //Thêm dữ liệu vào bảng
//        ghichuHelper.NonQueryData("INSERT INTO GHICHU VALUES(null,'Ghi chú 1','Không nấu gan động vật với cà rốt, rau cần.')");
//        ghichuHelper.NonQueryData("INSERT INTO GHICHU VALUES(null,'Ghi chú 2','Không uống sữa bò và nước hoa quả chua (Cam, quýt)')");
//        ghichuHelper.NonQueryData("INSERT INTO GHICHU VALUES(null,'Ghi chú 3','Không ăn Tỏi + trứng vịt.')");
//        ghichuHelper.NonQueryData("INSERT INTO GHICHU VALUES(null,'Ghi chú 4','Không Sữa đậu nành và đường đen.')");
        //Hiển thị dữ liệu bằng select

        actionGetData();//Hàm hiển thị danh sách các item ListView
        //Xử lý sự kiện button thêm ghi chú
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenGC=edttenGhichu.getText().toString().trim();
                String ndGC=edtndGhichu.getText().toString().trim();
                if(tenGC.isEmpty() && ndGC.isEmpty()){
                    Toast.makeText(getActivity(),"Vui lòng nhập đầy đủ thông tin ghi chú !!!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(ndGC.length()<10){
                    Toast.makeText(getActivity(),"Ghi chú quá ngắn",Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    Toast.makeText(getActivity(),"Thêm ghi chú thành công !",Toast.LENGTH_SHORT).show();
                    database.NonQueryData("INSERT INTO GHICHU VALUES(null,'"+tenGC+"','"+ndGC+"')");
                    actionGetData();
                }
            }
        });
        //Đổ dữ liệu từ listView lên ô nhập ghi chú
        listView_note.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnUpdateNote.setEnabled(true);
                edttenGhichu.setText(ghichuList.get(position).getTenGhichu());
                edtndGhichu.setText(ghichuList.get(position).getNdGhichu());
                vitri=ghichuList.get(position).getId();//Lấy vị trí để cập nhật
            }
        });
        //Xử lý sự kiện cập nhật dữ liệu
        btnUpdateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenMoi=edttenGhichu.getText().toString().trim();
                String ndMoi= edtndGhichu.getText().toString().trim();
                if(ndMoi.isEmpty() && tenMoi.isEmpty()){
                    Toast.makeText(getActivity(),"Nội dung cần sửa chưa được nhập !!!",Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    database.NonQueryData("UPDATE GHICHU SET NAME='"+tenMoi+"', NOIDUNG='"+ndMoi+"' WHERE ID='"+vitri+"'");
                    Toast.makeText(getActivity(),"Bạn đã sửa ghi chú thành công.",Toast.LENGTH_SHORT).show();
                    btnUpdateNote.setEnabled(false);
                    actionGetData();
                }
            }
        });
        //Xử lý sự kiện Xóa(Long click) các Ghi chú
        listView_note.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(getActivity());
                dialogBuilder.setTitle("Xác nhận");
                dialogBuilder.setMessage("Bạn có chắc chẵn muốn xóa ghi chú này ?");
                dialogBuilder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialogBuilder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database.NonQueryData("DELETE FROM GHICHU WHERE ID='"+vitri+"'");
                        Toast.makeText(getActivity(),"Bạn đã xóa thành công ^^",Toast.LENGTH_SHORT).show();
                        actionGetData();
                    }
                });
                dialogBuilder.show();
                return false;
            }
        });
        //
//        listData_note= new ArrayList<>();
//        listData_note.add("Không nấu gan động vật với cà rốt, rau cần.");
//        listData_note.add("Không uống sữa bò và nước hoa quả chua (Cam, quýt)");
//        listData_note.add("Không ăn Tỏi + trứng vịt.");
//        listData_note.add("Không Sữa đậu nành và đường đen.");
//        adapterNote= new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,listData_note);
//        listView_note.setAdapter(adapterNote);
//        //Xử lý sự kiện thêm ghi chú
//        btnAddNote.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String add_itemlist= editTextNote.getText().toString().trim();
//                if(add_itemlist.isEmpty()){
//                    Toast.makeText(getActivity(),"Bạn chưa nhập ghi chú !!!",Toast.LENGTH_SHORT).show();
//                }
//                if(add_itemlist.length()<10){
//                    Toast.makeText(getActivity(),"Ghi chú quá ngắn vui lòng nhập thêm !",Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(getActivity(),"Thêm ghi chú thành công !",Toast.LENGTH_SHORT).show();
//                    listData_note.add(add_itemlist);
//                    adapterNote.notifyDataSetChanged();
//                }
//            }
//        });
//        //Đổ dữ liệu từ listView lên ô nhập ghi chú
//        listView_note.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                btnUpdateNote.setEnabled(true);
//                editTextNote.setText(listData_note.get(position));
//                vitri=position;//Lấy vị trí để cập nhật
//            }
//        });
//        //Sự kiện nút cập nhật ghi chú
//        btnUpdateNote.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    listData_note.set(vitri,editTextNote.getText().toString().trim());
//                    adapterNote.notifyDataSetChanged();
//            }
//        });
//        //Xử lý sự kiện LongClick vào các Item trong listView
//        listView_note.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(getActivity());
//                dialogBuilder.setTitle("Xác nhận");
//                dialogBuilder.setMessage("Bạn có chắc chẵn muốn xóa ghi chú này ?");
//                dialogBuilder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//                dialogBuilder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        listData_note.remove(position);
//                        adapterNote.notifyDataSetChanged();
//                        btnUpdateNote.setEnabled(false);
//                    }
//                });
//                AlertDialog alertDialog= dialogBuilder.create();
//                alertDialog.show();
//                return false;
//            }
//        });
        return view;
    }

    private void actionGetData() {
        Cursor dataGhichu= database.QueryData("SELECT* FROM GHICHU");
        ghichuList.clear();
        while (dataGhichu.moveToNext())
        {
            int id=dataGhichu.getInt(0);
            String tenGhichu=dataGhichu.getString(1);
            String ndGhichu=dataGhichu.getString(2);
            ghichuList.add(new Ghichu(id,tenGhichu,ndGhichu));
        }
        ghichuAdapter.notifyDataSetChanged();
    }
}

