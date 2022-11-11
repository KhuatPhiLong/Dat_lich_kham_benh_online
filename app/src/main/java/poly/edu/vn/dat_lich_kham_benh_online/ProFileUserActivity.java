package poly.edu.vn.dat_lich_kham_benh_online;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoAccount;

public class ProFileUserActivity extends AppCompatActivity {
    private EditText edEditFullName,edEditUserName;
    private TextView tvGender,tvEditPhone;
    private String uriImg;
    private Button btnEditSave;
    private ImageView imgEditUser;
    private DaoAccount daoUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_file_user);
        init();
        //Khởi tạo cơ sở dữ liệu
        daoUser = new DaoAccount(this);
        //Mở cơ sở dữ liệu
        daoUser.open();
        //Lấy ra id của tài khoản đăng nhập
        SharedPreferences preferences = getSharedPreferences("getIdUser",MODE_PRIVATE);
        int idUser = preferences.getInt("idUser",-1);
        //lấy ra đối tượng có idUser
        DtoAccount getDtoUser = daoUser.getDtoAccount(idUser);
        //Gắn dũ liệu
        edEditFullName.setText(getDtoUser.getFullName());
        tvEditPhone.setText(getDtoUser.getPhone());
        edEditUserName.setText(getDtoUser.getUserName());
        tvGender.setText(getDtoUser.getGender());
        //Ép kiểu dữ liệu từ string sang uri
        if(getDtoUser.getImg()!=null){
            Uri uri = Uri.parse(getDtoUser.getImg().trim());
            imgEditUser.setImageURI(uri);
        }


        //Bắt sự kiện cho nút save để update user
        btnEditSave.setOnClickListener(view->{
            getDtoUser.setUserName(edEditUserName.getText().toString());
            getDtoUser.setFullName(edEditFullName.getText().toString());
            getDtoUser.setImg(uriImg);
            int res  = daoUser.updateRow(getDtoUser);
            if(res>0){
                Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
            }

        });

        //Bắt sự kiện cho imgUser để mở ra thư viện lấy ảnh
        imgEditUser.setOnClickListener(view->{
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            });
    }
    public void init(){
        edEditFullName = findViewById(R.id.edEditFullName);
        tvEditPhone = findViewById(R.id.tvEditPhone);
        edEditUserName = findViewById(R.id.edEditUserName);
        tvGender = findViewById(R.id.tvGender);
        btnEditSave = findViewById(R.id.btnEditSave);
        imgEditUser = findViewById(R.id.imgEditUser);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1){
            if(data!=null){
                Uri uri = data.getData();
                //Lấy ra uri của ảnh
                uriImg = uri+"";
                //Gắn ảnh vào imgEditUser
                imgEditUser.setImageURI(uri);
            }

        }

    }
}