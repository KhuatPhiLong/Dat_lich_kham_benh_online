package poly.edu.vn.dat_lich_kham_benh_online;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.BookingDoctorAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoDoctor;

public class ListDoctorActivity extends AppCompatActivity {
    private RecyclerView rvOrderDoctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_doctor);
        rvOrderDoctor = findViewById(R.id.rvOrderDoctor);

        DaoDoctor daoDoctor = new DaoDoctor(this);
        daoDoctor.open();

        ArrayList<DtoDoctor> listDocTor = daoDoctor.selectAll();
        BookingDoctorAdapter doctorAdapter  =new BookingDoctorAdapter(listDocTor,this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        rvOrderDoctor.setLayoutManager(manager);
        rvOrderDoctor.setAdapter(doctorAdapter);
    }
}