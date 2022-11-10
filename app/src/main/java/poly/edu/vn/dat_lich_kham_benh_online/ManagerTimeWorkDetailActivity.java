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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.SpinnerTimeWorkAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.TimeWorkDetailAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoTimeWork;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoTimeWorkDetail;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoTimeWork;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoTimeWorkDetail;

public class ManagerTimeWorkDetailActivity extends AppCompatActivity {
    private TextView tvAddTimeWorkDetail;
    private DaoTimeWork daoTimeWork;
    private DaoTimeWorkDetail daoTimeWorkDetail;
    private RecyclerView rvManagerTimeWorkDetail;
    private TimeWorkDetailAdapter timeWorkDetailAdapter;
    private ArrayList<DtoTimeWorkDetail> listDtoTimeWorkDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_time_work_detail);
        tvAddTimeWorkDetail = findViewById(R.id.tvAddTimeWorkDetail);
        rvManagerTimeWorkDetail = findViewById(R.id.rvManagerTimeWorkDetail);


        //Khởi tạo
        daoTimeWork = new DaoTimeWork(this);
        //Mở cơ sở dữ liệu
        daoTimeWork.open();

        //Khởi tạo
        daoTimeWorkDetail = new DaoTimeWorkDetail(this);
        //Mở cơ sở dữ liệu
        daoTimeWorkDetail.open();

        //Lấy danh sách timeworkdetail
        listDtoTimeWorkDetail = daoTimeWorkDetail.selectAll();
        TimeWorkDetailAdapter timeWorkDetailAdapter = new TimeWorkDetailAdapter(listDtoTimeWorkDetail,this);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rvManagerTimeWorkDetail.setLayoutManager(manager);
        rvManagerTimeWorkDetail.setAdapter(timeWorkDetailAdapter);

        tvAddTimeWorkDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(ManagerTimeWorkDetailActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_add_timework_detail);
                Window window = dialog.getWindow();
                if(window == null){
                    return;
                }
                else{
                    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }
                Spinner spTimeWork = dialog.findViewById(R.id.spTimeWork);

                ArrayList<DtoTimeWork> listTimeWork = daoTimeWork.selectAll();
                SpinnerTimeWorkAdapter spinnerTimeWorkAdapter = new SpinnerTimeWorkAdapter(listTimeWork,ManagerTimeWorkDetailActivity.this);
                spTimeWork.setAdapter(spinnerTimeWorkAdapter);

                EditText edTimeWorkDetail = dialog.findViewById(R.id.edTimeWorkDetail);

                Button btnSaveTimeWorkDetail = dialog.findViewById(R.id.btnSaveTimeWorkDetail);
                //Bắt sự kiện cho nút save room
                btnSaveTimeWorkDetail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DtoTimeWorkDetail dtoTimeWorkDetail = new DtoTimeWorkDetail();
                        dtoTimeWorkDetail.setTime(edTimeWorkDetail.getText().toString());
                        DtoTimeWork dtoTimeWork = (DtoTimeWork) spTimeWork.getSelectedItem();
                        dtoTimeWorkDetail.setTimework_id(dtoTimeWork.getId());

                        long res = daoTimeWorkDetail.insertRow(dtoTimeWorkDetail);
                        if(res>0){
                            listDtoTimeWorkDetail.clear();
                            listDtoTimeWorkDetail.addAll(daoTimeWorkDetail.selectAll());
                            timeWorkDetailAdapter.notifyDataSetChanged();
                            Toast.makeText(ManagerTimeWorkDetailActivity.this, "Thêm giờ làm thành công", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(ManagerTimeWorkDetailActivity.this, "Thêm giờ làm không thành công", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                dialog.show();
            }
        });


    }

}