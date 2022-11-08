package poly.edu.vn.dat_lich_kham_benh_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoAccount;

public class LoginActivity extends AppCompatActivity {
    private TextView tvRegister;
    private EditText edUserName,edPassWord;
    private Button btnLogin;
    private DaoAccount daoUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        //Khởi tạo cơ sở dữ liệu
        daoUser = new DaoAccount(this);
        //Mở cơ sở dữ liệu
        daoUser.open();
        //Bắt sự kiện cho chữ đăng ký để sang activity đăng ký
        tvRegister.setOnClickListener(view->{
            Intent intent = new Intent(getBaseContext(),RegisterActivity.class);
            startActivity(intent);
        });
        edUserName.setText("Admin");
        edPassWord.setText("Admin");
        //Bắt sự kiện cho nút đăng nhập
        btnLogin.setOnClickListener(view ->{
            String userName = edUserName.getText().toString();
            String passWord = edPassWord.getText().toString();
            boolean checkLogin = daoUser.checkLogin(userName,passWord);
            if(checkLogin == true){
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Tên tài khoản hoặc mật khẩu đã bị sai", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void init(){
        tvRegister = findViewById(R.id.tvRegister);
        edUserName = findViewById(R.id.edLoginUsername);
        edPassWord = findViewById(R.id.edLoginPassWord);
        btnLogin = findViewById(R.id.btnLogin);
    }
}