package poly.edu.vn.dat_lich_kham_benh_online;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.OrderDoctorAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoFile;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoOrderDetail;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoOrders;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoFile;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoOrderDetail;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoOrderDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoOrders;

public class ComfirmOrderActivity extends AppCompatActivity {
    private RecyclerView rvOrderDoctor;
    private TextView tvSumPrice, tvAddOrder;
    private Button btnComfirmOrder;
    private DaoOrders daoOrders;
    private DaoOrderDetail daoOrderDetail;
    private DaoFile daoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comfirm_order);

        daoFile = new DaoFile(this);
        daoFile.open();

        daoOrderDetail = new DaoOrderDetail(this);
        daoOrderDetail.open();

        daoOrders = new DaoOrders(this);
        daoOrders.open();

        init();
        ArrayList<DtoOrderDoctor> listOrderDoctor = OrderActivity.listOrderDoctor;
        OrderDoctorAdapter doctorAdapter = new OrderDoctorAdapter(listOrderDoctor, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvOrderDoctor.setLayoutManager(manager);
        rvOrderDoctor.setAdapter(doctorAdapter);

        float total = 0;
        for (int i = 0; i < listOrderDoctor.size(); i++) {
            DtoOrderDoctor dtoOrderDoctor = listOrderDoctor.get(i);
            total += dtoOrderDoctor.getTotal();
        }
        tvSumPrice.setText(total + "đ");

        tvAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ListDoctorActivity.class);
                startActivity(intent);
            }
        });
        //Lấy ra id uer
        SharedPreferences preferences = getSharedPreferences("getIdUser", MODE_PRIVATE);
        int idUser = preferences.getInt("idUser", -1);

        //Lấy ra hồ sơ bệnh án
        DtoFile dtoFile = daoFile.getDtoFileByIdAccount(idUser);
        //Lấy ra ngày hiện tại
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        String date = year + "/" + (month + 1) + "/" + day;

        //Lấy ra giờ hiện tại
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        String time = hour + ":" + minute;

        btnComfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DtoOrders dtoOrders = new DtoOrders();
                dtoOrders.setFile_id(dtoFile.getId());
                dtoOrders.setOrder_date(date);
                dtoOrders.setOrder_time(time);

                long res = daoOrders.inserRow(dtoOrders);
                DtoOrders dtoOrders1 = daoOrders.getDtoOrder();

                for (int i = 0; i < listOrderDoctor.size(); i++) {
                    DtoOrderDoctor dtoOrderDoctor = listOrderDoctor.get(i);

                    DtoOrderDetail dtoOrderDetail = new DtoOrderDetail();
                    dtoOrderDetail.setOrder_id(dtoOrders1.getId());
                    dtoOrderDetail.setOrderDoctor_id(dtoOrderDoctor.getId());
                    long res1 = daoOrderDetail.insertRow(dtoOrderDetail);
                }
                Toast.makeText(ComfirmOrderActivity.this, "Đặt lịch khám thành công", Toast.LENGTH_SHORT).show();
                OrderActivity.listOrderDoctor.clear();
            }
        });
    }

    public void init() {
        rvOrderDoctor = findViewById(R.id.rvOrderDoctor);
        tvSumPrice = findViewById(R.id.tvSumPrice);
        tvAddOrder = findViewById(R.id.tvAddOrder);
        btnComfirmOrder = findViewById(R.id.btnComfirmOrder);
    }
}