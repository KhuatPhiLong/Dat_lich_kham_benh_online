package poly.edu.vn.dat_lich_kham_benh_online;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.sax.RootElement;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.ServiceAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.SpinnerCategoriesAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.SpinnerRoomAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoCategories;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoService;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoCategories;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoService;

public class ManagerServiceActivity extends AppCompatActivity {
    private TextView tvAddService;
    private DaoService daoService;
    private String uriImgService;
    DaoRoom daoRoom;
    ArrayList<DtoRoom> listRoom;
    private ArrayList<DtoService> listService;
    private ServiceAdapter serviceAdapter;
    private RecyclerView rvManagerService;
    SpinnerRoomAdapter adapterRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_service);
        init();

        //Khởi tạo
        daoService = new DaoService(this);
        //Mở cơ sở dữ liệu
        daoService.open();
        //Lấy danh sách servcie
        listService = daoService.selectAll();
        //Gắn dữ liệu vào cho adapter
        serviceAdapter = new ServiceAdapter(listService,this);
        LinearLayoutManager manager  = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        //Gắn dữ liệu cho rv
        rvManagerService.setLayoutManager(manager);
        rvManagerService.setAdapter(serviceAdapter);

        tvAddService.setOnClickListener(view->{
            Intent intent = new Intent(getBaseContext(),AddSerivceActivity.class);
            startActivity(intent);
        });
    }
    public void init(){
        tvAddService = findViewById(R.id.tvAddService);
        rvManagerService = findViewById(R.id.rvManagerService);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listService = daoService.selectAll();
        serviceAdapter = new ServiceAdapter(listService,this);
        LinearLayoutManager manager  = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        //Gắn dữ liệu cho rv
        rvManagerService.setLayoutManager(manager);
        rvManagerService.setAdapter(serviceAdapter);
    }
}