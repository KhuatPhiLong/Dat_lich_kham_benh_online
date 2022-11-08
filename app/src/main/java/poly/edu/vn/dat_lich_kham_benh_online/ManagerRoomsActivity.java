package poly.edu.vn.dat_lich_kham_benh_online;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.RoomAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoRoom;

public class ManagerRoomsActivity extends AppCompatActivity {
    private TextView tvAddRoom;
    private DaoRoom daoRoom;
    private RecyclerView rvManagerRoom;
    private RoomAdapter roomAdapter;
    private ArrayList<DtoRoom> listRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_rooms);
        init();
        //Khởi tạo
        daoRoom = new DaoRoom(this);
        daoRoom.open();
        
        tvAddRoom.setOnClickListener(view->{
            Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_add_rooms);
            Window window = dialog.getWindow();
            if(window == null){
                return;
            }
            else{
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            EditText edNameRoom = dialog.findViewById(R.id.edNameRoom);
            EditText edLocation = dialog.findViewById(R.id.edLocation);
            Button btnSaveRoom = dialog.findViewById(R.id.btnSaveRoom);
            //Bắt sự kiện cho nút save room
            btnSaveRoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DtoRoom dtoRoom = new DtoRoom();
                    dtoRoom.setName(edNameRoom.getText().toString());
                    dtoRoom.setLocation(edLocation.getText().toString());
                    
                    long res = daoRoom.insertRow(dtoRoom);
                    if(res>0){
                        listRoom.clear();
                        listRoom.addAll(daoRoom.selectAll());
                        roomAdapter.notifyDataSetChanged();
                        Toast.makeText(ManagerRoomsActivity.this, "Thêm phòng khám thành công", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(ManagerRoomsActivity.this, "Thêm phòng khám  thất ", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            dialog.show();
        });

        //Lấy danh sách room
        listRoom = daoRoom.selectAll();
        //Gắn dữ liệu vào adapter
        roomAdapter = new RoomAdapter(listRoom,this);

        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rvManagerRoom.setLayoutManager(manager);
        //Gắn adapter cho rv
        rvManagerRoom.setAdapter(roomAdapter);

    }
    public void init(){
        tvAddRoom = findViewById(R.id.tvAddRoom);
        rvManagerRoom = findViewById(R.id.rvManagerRoom);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listRoom = daoRoom.selectAll();
        //Gắn dữ liệu vào adapter
        roomAdapter = new RoomAdapter(listRoom,this);

        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rvManagerRoom.setLayoutManager(manager);
        //Gắn adapter cho rv
        rvManagerRoom.setAdapter(roomAdapter);
    }
}