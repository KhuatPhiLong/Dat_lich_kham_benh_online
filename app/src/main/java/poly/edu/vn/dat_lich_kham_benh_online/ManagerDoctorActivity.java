package poly.edu.vn.dat_lich_kham_benh_online;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.DoctorAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.SpinnerRoomAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoRoom;

public class ManagerDoctorActivity extends AppCompatActivity {
    private TextView tvAddDoctor;
    private RecyclerView rvManagerDoctor;
    private DaoDoctor daoDoctor;
    private ArrayList<DtoDoctor> listDoctor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_doctor);
        init();

        //Khởi tạo
        daoDoctor = new DaoDoctor(this);
        //Mở cơ sở dữ liệu
        daoDoctor.open();

        //Lấy danh sách doctor
        listDoctor = daoDoctor.selectAll();
        //Gắn dữ liệu vào adapter
        DoctorAdapter doctorAdapter = new DoctorAdapter(listDoctor,this);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rvManagerDoctor.setLayoutManager(manager);
        rvManagerDoctor.setAdapter(doctorAdapter);

        //Bắt sự kiện cho nut thêm bác sĩ
        tvAddDoctor.setOnClickListener(view->{
            Intent intent = new Intent(getBaseContext(),AddDoctorActivity.class);
            startActivity(intent);
        });
    }
    public void init(){
        tvAddDoctor = findViewById(R.id.tvAddDoctor);
        rvManagerDoctor = findViewById(R.id.rvManagerDoctor);
    }
}