package poly.edu.vn.dat_lich_kham_benh_online;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoAccount;

public class RegisterActivity extends AppCompatActivity {
    private EditText edFullName,edPhone,edUserName,edPassWord;
    private RadioButton rdoMen,rdoGirl;
    private Button btnRegister;
    private DaoAccount daoUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        //Khởi tạo cơ sở dữ liệu
        daoUser = new DaoAccount(this);
        //Mở cơ sở dữ liệu
        daoUser.open();
        //Gắn mặc định cho rdoMen đã chọn để đỡ phải check không chọn
        rdoMen.setChecked(true);
        btnRegister.setOnClickListener(view ->{
            //Khởi tạo đối tượng
            DtoAccount dtoUser = new DtoAccount();
            dtoUser.setUserName(edUserName.getText().toString());
            dtoUser.setPassWord(edPassWord.getText().toString());
            dtoUser.setPhone(edPhone.getText().toString());
            dtoUser.setFullName(edFullName.getText().toString());
            if(rdoMen.isChecked()){
                dtoUser.setGender("Nam");
            }
            else{
                dtoUser.setGender("Nữ");
            }
            //Gắn cứng cho đăng ký là tài khoản  user
            dtoUser.setRole("User");
            long res = daoUser.insertRow(dtoUser);
            if(res>0){
                finish();
                Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Đăng ký thất bạn", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void init(){
        edFullName = findViewById(R.id.edFullName);
        edPhone = findViewById(R.id.edPhone);
        edUserName = findViewById(R.id.edUserName);
        edPassWord = findViewById(R.id.edPassWord);
        rdoGirl = findViewById(R.id.rdoGirl);
        rdoMen = findViewById(R.id.rdoMen);
        btnRegister = findViewById(R.id.btnRegister);
    }
}