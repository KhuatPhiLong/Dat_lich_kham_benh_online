package poly.edu.vn.dat_lich_kham_benh_online;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.SpinnerRoomAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.SpinnerServiceAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.SpinnerTimeWorkAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoService;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoTimeWork;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoService;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoTimeWork;

public class AddDoctorActivity extends AppCompatActivity {
    private ImageView imgDoctor;
    private EditText edFullName, edPhone, edPassWord, edUserName, edDescription, edBirthDay;
    private RadioButton rdoMen, rdoGirl;
    private Spinner spRooms, spServices, spTimeWork;
    private DaoRoom daoRoom;
    private Button btnSaveDoctor;
    private DaoService daoService;
    private DaoAccount daoUser;
    private DaoDoctor daoDoctor;
    private DaoTimeWork daoTimeWork;
    private String uriImg;
    private String TAG = "zzzzzzzzzzzzzzz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);
        init();
        openSql();

        //Lấy ra danh sách ca
        ArrayList<DtoTimeWork> listTimeWork = daoTimeWork.selectAll();
        //Gắn dữ liệu vào spTimeWork
        SpinnerTimeWorkAdapter spinnerTimeWorkAdapter = new SpinnerTimeWorkAdapter(listTimeWork, this);
        spTimeWork.setAdapter(spinnerTimeWorkAdapter);

        //Lấy ra danh sách phòng khám
        ArrayList<DtoRoom> listRoom = daoRoom.selectAll();
        //Gắn dữ liệu vào spRooms
        SpinnerRoomAdapter spinnerRoomAdapter = new SpinnerRoomAdapter(listRoom, this);
        spRooms.setAdapter(spinnerRoomAdapter);

        //Lấy ra danh sách service
        ArrayList<DtoService> listService = daoService.selectAll();
        //Gắn dữ liệu vòa spService
        SpinnerServiceAdapter spinnerServiceAdapter = new SpinnerServiceAdapter(listService, this);
        spServices.setAdapter(spinnerServiceAdapter);

        //Bắt sự kiện bấm vào imgDoctor sẽ mở ra thư viện ảnh
        imgDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission();
            }
        });

        rdoMen.setChecked(true);
        //Bắt sự kiện cho save
        btnSaveDoctor.setOnClickListener(view -> {
            clickSaveDoctor();
        });
    }

    public void openSql() {
        //Khởi tạo
        daoTimeWork = new DaoTimeWork(this);
        //Mở cơ sở dữ liệu
        daoTimeWork.open();

        //Khởi tạo
        daoDoctor = new DaoDoctor(this);
        //Mở cơ sở dữ liệu
        daoDoctor.open();

        //Khởi tạo
        daoUser = new DaoAccount(this);
        //Mở cơ sở dữ liệu
        daoUser.open();

        //Khởi tạo
        daoRoom = new DaoRoom(this);
        //Mở cơ sở dữ liệu
        daoRoom.open();

        //Khởi tạo
        daoService = new DaoService(this);
        //Mở cớ sở dữ liệu
        daoService.open();
    }

    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (AddDoctorActivity.this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissions, 1);
            } else {
                Intent intentGrallary = new Intent(Intent.ACTION_PICK);
                intentGrallary.setType("image/*");
                startActivityForResult(intentGrallary, 1);
            }
        }
    }

    public void clickSaveDoctor() {
        if (uriImg == null) {
            Toast.makeText(this, "Bạn chưa chọn ảnh", Toast.LENGTH_SHORT).show();
            return;
        }

        DtoAccount dtoUser = new DtoAccount();
        dtoUser.setUserName(edUserName.getText().toString());
        dtoUser.setPassWord(edPassWord.getText().toString());
        dtoUser.setPhone(edPhone.getText().toString());
        dtoUser.setFullName(edFullName.getText().toString());
        if (rdoMen.isChecked()) {
            dtoUser.setGender("Nam");
        } else {
            dtoUser.setGender("Nữ");
        }
        //Gắn cứng cho đăng ký là tài khoản  user
        dtoUser.setRole("Doctor");
        dtoUser.setImg(uriImg);
        long res = daoUser.insertRow(dtoUser);
        if (res > 0) {
            DtoAccount dtoUser1 = daoUser.getDtoUserTopIdOne();
            DtoDoctor dtoDoctor = new DtoDoctor();
            dtoDoctor.setUser_id(dtoUser1.getId());

            DtoRoom dtoRoom = (DtoRoom) spRooms.getSelectedItem();
            dtoDoctor.setRoom_id(dtoRoom.getId());

            DtoService dtoService = (DtoService) spServices.getSelectedItem();
            dtoDoctor.setService_id(dtoService.getId());

            dtoDoctor.setDescription(edDescription.getText().toString());
            dtoDoctor.setBirthday(edBirthDay.getText().toString());

            DtoTimeWork dtoTimeWork = (DtoTimeWork) spTimeWork.getSelectedItem();
            dtoDoctor.setTimework_id(dtoTimeWork.getId());
            long res1 = daoDoctor.insertRow(dtoDoctor);
            if (res1 > 0) {
                Toast.makeText(this, "Thêm bác sĩ thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Thêm bác sĩ không thành công", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    public void init() {
        imgDoctor = findViewById(R.id.imgDoctor);
        edFullName = findViewById(R.id.edFullName);
        edPhone = findViewById(R.id.edPhone);
        edPassWord = findViewById(R.id.edPassWord);
        rdoGirl = findViewById(R.id.rdoGirl);
        rdoMen = findViewById(R.id.rdoMen);
        spRooms = findViewById(R.id.spRooms);
        spServices = findViewById(R.id.spServices);
        btnSaveDoctor = findViewById(R.id.btnSaveDoctor);
        edUserName = findViewById(R.id.edUserName);
        edDescription = findViewById(R.id.edDescription);
        edBirthDay = findViewById(R.id.edBirthDay);
        spTimeWork = findViewById(R.id.spTimeWork);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (data != null) {
                Uri uri = data.getData();
                //Lấy ra uri của ảnh
                uriImg = uri + "";
                //Gắn ảnh vào imgEditUser
                imgDoctor.setImageURI(uri);
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}