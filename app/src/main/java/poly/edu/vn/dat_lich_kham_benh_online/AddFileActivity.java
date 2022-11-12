package poly.edu.vn.dat_lich_kham_benh_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoFile;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoFile;

public class AddFileActivity extends AppCompatActivity {
    private TextView tvFullName,tvPhone,tvGender;
    private DaoAccount daoAccount;
    private Button btnSaveFile;
    private EditText edBirthDay,edCccd,edCountry,edJob,edEmail,edAddress;
    private DaoFile daoFile;
    private RadioButton rdoYes,rdoNo;
    private int idUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_file);
        init();
        openSql();

        //Lấy ra id của tài khoản đăng nhập
        SharedPreferences preferences = getSharedPreferences("getIdUser", Context.MODE_PRIVATE);
        idUser = preferences.getInt("idUser", -1);
        //lấy ra đối tượng có idUser
        DtoAccount getDtoAccount = daoAccount.getDtoAccount(idUser);

        //Gắn dữ liệu
        tvFullName.setText(getDtoAccount.getFullName());
        tvGender.setText(getDtoAccount.getGender());
        tvPhone.setText(getDtoAccount.getPhone());
        rdoNo.setChecked(true);

        btnSaveFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               clickSaveFile();
            }
        });
    }
    public void clickSaveFile(){
        DtoFile dtoFile = new DtoFile();
        dtoFile.setUser_id(idUser);
        dtoFile.setBirthday(edBirthDay.getText().toString());
        dtoFile.setCccd(edCccd.getText().toString());
        dtoFile.setCountry(edCountry.getText().toString());
        if(rdoNo.isChecked()){
            dtoFile.setBhyt("Không");
        }
        if(rdoYes.isChecked()){
            dtoFile.setBhyt("Có");
        }
        dtoFile.setJob(edJob.getText().toString());
        dtoFile.setEmail(edEmail.getText().toString());
        dtoFile.setAddress(edAddress.getText().toString());

        long res = daoFile.insertRow(dtoFile);

        if(res>0){
            Toast.makeText(AddFileActivity.this, "Thêm hồ sơ bệnh án thành công", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(AddFileActivity.this, "Thêm hồ sơ bệnh án không thành công", Toast.LENGTH_SHORT).show();
        }
    }
    public void openSql(){
        //Khởi tạo
        daoAccount = new DaoAccount(this);
        //Mở cơ sở dữ liệu
        daoAccount.open();
        //Khởi tạo
        daoFile = new DaoFile(this);
        //Mở cơ sở dữ liệu
        daoFile.open();
    }
    public void init(){
        tvFullName = findViewById(R.id.tvFullName);
        tvPhone = findViewById(R.id.tvPhone);
        tvGender = findViewById(R.id.tvGender);
        btnSaveFile = findViewById(R.id.btnSaveFile);
        edBirthDay = findViewById(R.id.edBirthDay);
        edCccd  =findViewById(R.id.edCccd);
        edCountry = findViewById(R.id.edCountry);
        edJob = findViewById(R.id.edJob);
        edEmail = findViewById(R.id.edEmail);
        edAddress = findViewById(R.id.edAddress);
        rdoYes = findViewById(R.id.rdoYes);
        rdoNo = findViewById(R.id.rdoNo);
    }
}