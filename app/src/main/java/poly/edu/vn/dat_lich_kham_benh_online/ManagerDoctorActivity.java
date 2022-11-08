package poly.edu.vn.dat_lich_kham_benh_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.SpinnerRoomAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoRoom;

public class ManagerDoctorActivity extends AppCompatActivity {
    private TextView tvAddDoctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_doctor);
        init();


        //Bắt sự kiện cho nut thêm bác sĩ
        tvAddDoctor.setOnClickListener(view->{
            Intent intent = new Intent(getBaseContext(),AddDoctorActivity.class);
            startActivity(intent);
        });
    }
    public void init(){
        tvAddDoctor = findViewById(R.id.tvAddDoctor);
    }
}