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

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.TimeWorkAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoTimeWork;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoTimeWork;

public class ManagerTimeWorkActivity extends AppCompatActivity {
    private TextView tvAddTimeWork;
    private DaoTimeWork daoTimeWork;
    private RecyclerView rvManagerTimeWork;
    private ArrayList<DtoTimeWork> listTimeWork;
    private TimeWorkAdapter timeWorkAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_time_work);
        tvAddTimeWork = findViewById(R.id.tvAddTimeWork);
        rvManagerTimeWork = findViewById(R.id.rvManagerTimeWork);
        
        //Khởi tạo
        daoTimeWork = new DaoTimeWork(this);
        //Mở cơ sở dữ liệu
        daoTimeWork.open();

        //Lấy ra danh sách time work
        listTimeWork = daoTimeWork.selectAll();

        //Gắn dữ liệu vào adapter
        timeWorkAdapter = new TimeWorkAdapter(listTimeWork,this);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rvManagerTimeWork.setLayoutManager(manager);
        rvManagerTimeWork.setAdapter(timeWorkAdapter);

        //Bắt sự kiện cho tvAddTimeWork
        tvAddTimeWork.setOnClickListener(view->{
            Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_add_timework);
            Window window = dialog.getWindow();
            if(window == null){
                return;
            }
            else{
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            EditText edTimeWork = dialog.findViewById(R.id.edTimeWork);
            Button btnSaveTimeWork = dialog.findViewById(R.id.btnSaveTimeWork);
            //Bắt sự kiện cho nút save room
            btnSaveTimeWork.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DtoTimeWork dtoTimeWork = new DtoTimeWork();
                    dtoTimeWork.setSession(edTimeWork.getText().toString());
                    
                    long res = daoTimeWork.insertRow(dtoTimeWork);
                    if(res>0){
                        Toast.makeText(ManagerTimeWorkActivity.this, "Thêm ca làm việc thành công", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(ManagerTimeWorkActivity.this, "Thêm ca làm việc không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            dialog.show();
        });
    }
}