package poly.edu.vn.dat_lich_kham_benh_online;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.OrderDoctorAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoOrderDoctor;

public class ComfirmOrderActivity extends AppCompatActivity {
    private RecyclerView rvOrderDoctor;
    private TextView tvSumPrice,tvAddOrder;
    private Button btnComfirmOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comfirm_order);
        init();
        ArrayList<DtoOrderDoctor> listOrderDoctor = OrderActivity.listOrderDoctor;
        OrderDoctorAdapter doctorAdapter = new OrderDoctorAdapter(listOrderDoctor, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvOrderDoctor.setLayoutManager(manager);
        rvOrderDoctor.setAdapter(doctorAdapter);

        float total = 0;
        for(int i=0;i<listOrderDoctor.size();i++){
            DtoOrderDoctor dtoOrderDoctor = listOrderDoctor.get(i);
            total+=dtoOrderDoctor.getTotal();
    }
        tvSumPrice.setText(total+"đ");

        tvAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ComfirmOrderActivity.this, "Long", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(),ListDoctorActivity.class);
                startActivity(intent);
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