package poly.edu.vn.dat_lich_kham_benh_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoFile;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoFile;

public class FileActivity extends AppCompatActivity {
    private ImageView imgAccount;
    private TextView tvTitleFullName, tvFullName;
    private TextView tvTitlePhone, tvPhone;
    private TextView tvTitleGender, tvGender;
    private TextView tvTitleBirthDay, tvBirthDay;
    private TextView tvTitleCccd, tvCccd;
    private TextView tvTitleCountry, tvCountry;
    private TextView tvTitleJob, tvJob;
    private TextView tvTitleEmail, tvEmail;
    private TextView tvTitleAddress, tvAddress;
    private TextView tvTitleBhyt, tvBhyt;
    private Button btnSaveFile;
    private DaoAccount daoAccount;
    private DaoFile daoFile;
    private TextView tvAddFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        init();

        //Khởi tạo
        daoAccount = new DaoAccount(this);
        //Mở cơ sở dữ liệu
        daoAccount.open();

        //Khởi tạo
        daoFile = new DaoFile(this);
        //Mở cơ sở dữ liệu
        daoFile.open();
        //Lấy id user
        SharedPreferences preferences = getSharedPreferences("getIdUser",MODE_PRIVATE);
        int idUser = preferences.getInt("idUser",-1);
        //Lấy ra đối tượng uer
        DtoAccount dtoAccount = daoAccount.getDtoAccount(idUser);
        //Lấy ra đối tượng hồ sơ bệnh án
        DtoFile dtoFile = daoFile.getDtoFileByIdAccount(idUser);

        //Check đã đăng kí hồ sơ bệnh án chưa
        boolean check = daoFile.checkFileByIdAcount(idUser);
        //Nếu chưa đăng ký sẽ ẩn hết chỉ hiện button thêm hồ sơ bệnh án
        if(check == false){
            imgAccount.setVisibility(View.GONE);
            tvTitleFullName.setVisibility(View.GONE);
            tvFullName.setVisibility(View.GONE);
            tvTitlePhone.setVisibility(View.GONE);
            tvPhone.setVisibility(View.GONE);
            tvTitleGender.setVisibility(View.GONE);
            tvGender.setVisibility(View.GONE);
            tvTitleBirthDay.setVisibility(View.GONE);
            tvBirthDay.setVisibility(View.GONE);
            tvTitleCccd.setVisibility(View.GONE);
            tvCccd.setVisibility(View.GONE);

            tvTitleCountry.setVisibility(View.GONE);
            tvCountry.setVisibility(View.GONE);
            tvTitleJob.setVisibility(View.GONE);
            tvJob.setVisibility(View.GONE);
            tvTitleEmail.setVisibility(View.GONE);
            tvEmail.setVisibility(View.GONE);
            tvTitleAddress.setVisibility(View.GONE);
            tvAddress.setVisibility(View.GONE);
            tvTitleBhyt.setVisibility(View.GONE);
            tvBhyt.setVisibility(View.GONE);
            btnSaveFile.setVisibility(View.GONE);
            tvAddFile.setVisibility(View.VISIBLE);
        }
        else{
            tvAddFile.setVisibility(View.GONE);
            //Gắn dữ liệu
            tvFullName.setText(dtoAccount.getFullName());
            tvGender.setText(dtoAccount.getGender());
            tvPhone.setText(dtoAccount.getPhone());
            tvBirthDay.setText(dtoFile.getBirthday());
            tvCccd.setText(dtoFile.getCccd());
            tvJob.setText(dtoFile.getJob());
            tvEmail.setText(dtoFile.getEmail());
            tvBhyt.setText(dtoFile.getBhyt());
            tvCountry.setText(dtoFile.getCountry());
            tvAddress.setText(dtoFile.getAddress());

        }
        //Bắt sự kiện cho thêm hồ sơ bệnh án
        tvAddFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),AddFileActivity.class);
                startActivity(intent);
            }
        });
    }

    public void init() {
        imgAccount = findViewById(R.id.imgAccount);
        tvTitleFullName = findViewById(R.id.tvTitleFullName);
        tvFullName = findViewById(R.id.tvFullName);
        tvTitlePhone = findViewById(R.id.tvTitlePhone);
        tvPhone = findViewById(R.id.tvPhone);
        tvTitleGender = findViewById(R.id.tvTitleGender);
        tvGender = findViewById(R.id.tvGender);

        tvTitleBirthDay = findViewById(R.id.tvTitleBirthDay);
        tvBirthDay = findViewById(R.id.tvBirthDay);
        tvTitleCccd = findViewById(R.id.tvTitleCccd);
        tvCccd = findViewById(R.id.tvCccd);

        tvTitleCountry = findViewById(R.id.tvTitleCountry);
        tvCountry = findViewById(R.id.tvCountry);
        tvTitleJob = findViewById(R.id.tvTitleJob);
        tvJob = findViewById(R.id.tvJob);
        tvTitleEmail = findViewById(R.id.tvTitleEmail);
        tvEmail = findViewById(R.id.tvEmail);
        tvTitleAddress = findViewById(R.id.tvTitleAddress);
        tvAddress = findViewById(R.id.tvAddress);
        tvTitleBhyt = findViewById(R.id.tvTitleBhyt);
        tvBhyt = findViewById(R.id.tvBhyt);
        btnSaveFile = findViewById(R.id.btnSaveFile);
        tvAddFile = findViewById(R.id.tvAddFile);
    }
}