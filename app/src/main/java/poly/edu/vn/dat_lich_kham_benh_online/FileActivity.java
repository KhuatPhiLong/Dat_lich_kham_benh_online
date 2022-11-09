package poly.edu.vn.dat_lich_kham_benh_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoAccount;

public class FileActivity extends AppCompatActivity {
    private TextView tvFullName,tvPhone,tvGender;
    private DaoAccount daoAccount;
    private ImageView imgAccount;
    private Button btnSaveFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        init();

        //Khởi tạo
        daoAccount = new DaoAccount(this);
        //Mở cơ sở dữ liệu
        daoAccount.open();

        //Lấy ra id của tài khoản đăng nhập
        SharedPreferences preferences = getSharedPreferences("getIdUser", Context.MODE_PRIVATE);
        int idUser = preferences.getInt("idUser", -1);
        //lấy ra đối tượng có idUser
        DtoAccount getDtoAccount = daoAccount.getDtoAccount(idUser);

        //Gắn dữ liệu
        tvFullName.setText(getDtoAccount.getFullName());
        tvGender.setText(getDtoAccount.getGender());
        tvPhone.setText(getDtoAccount.getPhone());

        if (getDtoAccount.getImg() != null) {
            //Ép kiểu
            Uri uri = Uri.parse(getDtoAccount.getImg());
            imgAccount.setImageURI(uri);
        }
        btnSaveFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public void init(){
        tvFullName = findViewById(R.id.tvFullName);
        tvPhone = findViewById(R.id.tvPhone);
        tvGender = findViewById(R.id.tvGender);
        imgAccount = findViewById(R.id.imgAccount);
        btnSaveFile = findViewById(R.id.btnSaveFile);
    }
}