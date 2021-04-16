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
    GhichuAdapter ghichuAdapter;//để truyền dữ liệu từ đối tượng sang giao diện
    Button btnAddNote,btnUpdateNote;//Khai báo
    EditText edttenGhichu,edtndGhichu;
    private int vitri;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        //ánh xạ các view trong file giao diện
        listView_note=(ListView) view.findViewById(R.id.lv_note);//
        edttenGhichu= (EditText) view.findViewById(R.id.editText_tenNote);
        edtndGhichu= (EditText) view.findViewById(R.id.editText_ndNote);
        btnAddNote= (Button) view.findViewById(R.id.button_addnote);
        btnUpdateNote= (Button) view.findViewById(R.id.button_updatenote);
        //gán dữ liệu vào ghichuList
        ghichuList= new ArrayList<>();
        ghichuAdapter= new GhichuAdapter(getActivity(),R.layout.dong_ghichu,ghichuList);
        listView_note.setAdapter(ghichuAdapter);
        //cho nút update ẩn đi khi chưa clic vào iteam vào listview
        btnUpdateNote.setEnabled(false);
        //Tạo database
        database = new Database(getActivity(),"DailyMenu.sqlite",null,1);
        //Tạo bảng ghi chú
        database.NonQueryData("CREATE TABLE IF NOT EXISTS GHICHU (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,NAME NVARCHAR(50),NOIDUNG NVARCHAR(200))");
        actionGetData();//Hàm hiển thị danh sách các item ListView
        //Xử lý sự kiện button thêm ghi chú
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenGC=edttenGhichu.getText().toString().trim();//gán tên ghi chú vào tênGC
                String ndGC=edtndGhichu.getText().toString().trim();//gán nội dung ghi chú vào ndGC
                //kiểm tra dữ liệu đầu vào
                if(tenGC.isEmpty() || ndGC.isEmpty()){//nếu để trống tên hoặc nội dung sẽ hiện vui lofg nhập đày đủ
                    Toast.makeText(getActivity(),"Vui lòng nhập đầy đủ thông tin ghi chú !!!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(ndGC.length()<10){
                    Toast.makeText(getActivity(),"Ghi chú quá ngắn",Toast.LENGTH_SHORT).show();
                    return;
                }
                else {//nếu k rơi 2 trường hợp thì thỏa mãn :thêm ghi chú thành công
                    database.NonQueryData("INSERT INTO GHICHU VALUES(null,'"+tenGC+"','"+ndGC+"')");
                    Toast.makeText(getActivity(),"Thêm ghi chú thành công !",Toast.LENGTH_SHORT).show();//
                    actionGetData();//để hiển thị cập nhật dữ liệu trên listview
                }
            }
        });
        //Đổ dữ liệu từ listView lên ô nhập ghi chú
        listView_note.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnUpdateNote.setEnabled(true);//cho nút update mở ra
                edttenGhichu.setText(ghichuList.get(position).getTenGhichu());
                edtndGhichu.setText(ghichuList.get(position).getNdGhichu());
                vitri=ghichuList.get(position).getId();//Lấy vị trí để cập nhật
            }
        });
        //Xử lý sự kiện cập nhật dữ liệu
        btnUpdateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenMoi=edttenGhichu.getText().toString().trim();//gán dữ liệu
                String ndMoi= edtndGhichu.getText().toString().trim();//gán dữ liệu
                if(ndMoi.isEmpty() || tenMoi.isEmpty()){//nếu mà nội dung , tên cập nhật mà trống thì hiển thị thông báo:nội dung cần sửa chưa đc nhập
                    Toast.makeText(getActivity(),"Nội dung cần sửa chưa được nhập !!!",Toast.LENGTH_SHORT).show();//hiện thị thông báo trên màn hình
                    return;
                }
                else {
                    //cập nhật dữ liệu và thông báo
                    database.NonQueryData("UPDATE GHICHU SET NAME='"+tenMoi+"', NOIDUNG='"+ndMoi+"' WHERE ID='"+vitri+"'");
                    Toast.makeText(getActivity(),"Bạn đã sửa ghi chú thành công.",Toast.LENGTH_SHORT).show();
                    btnUpdateNote.setEnabled(false);//ấn nút update
                    actionGetData();//cập nhật listview
                }
            }
        });
        //Xử lý sự kiện Xóa(Long click) các Ghi chú
        listView_note.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(getActivity());//hộp thoại hiển thị đồng ý hay hủy
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
                dialogBuilder.show();//hiển thị alerdialog
                return false;
            }
        });
        return view;
    }
    //cập nhật dữ liệu mới
    private void actionGetData() {
        Cursor dataGhichu= database.QueryData("SELECT* FROM GHICHU");
        ghichuList.clear();//xóa dữ liệu cũ
        while (dataGhichu.moveToNext())//duyệt các bản ghi từ đầu đến cuối
        {
            int id=dataGhichu.getInt(0);//gán id
            String tenGhichu=dataGhichu.getString(1);//gán tên
            String ndGhichu=dataGhichu.getString(2);//gán nd gi chú
            ghichuList.add(new Ghichu(id,tenGhichu,ndGhichu));//thêm dữ liệu mới vào listview
        }
        ghichuAdapter.notifyDataSetChanged();//cập nhật adapter
    }
}

