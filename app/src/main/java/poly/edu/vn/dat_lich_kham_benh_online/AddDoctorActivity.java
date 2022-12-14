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

        //L???y ra danh s??ch ca
        ArrayList<DtoTimeWork> listTimeWork = daoTimeWork.selectAll();
        //G???n d??? li???u v??o spTimeWork
        SpinnerTimeWorkAdapter spinnerTimeWorkAdapter = new SpinnerTimeWorkAdapter(listTimeWork, this);
        spTimeWork.setAdapter(spinnerTimeWorkAdapter);

        //L???y ra danh s??ch ph??ng kh??m
        ArrayList<DtoRoom> listRoom = daoRoom.selectAll();
        //G???n d??? li???u v??o spRooms
        SpinnerRoomAdapter spinnerRoomAdapter = new SpinnerRoomAdapter(listRoom, this);
        spRooms.setAdapter(spinnerRoomAdapter);

        //L???y ra danh s??ch service
        ArrayList<DtoService> listService = daoService.selectAll();
        //G???n d??? li???u v??a spService
        SpinnerServiceAdapter spinnerServiceAdapter = new SpinnerServiceAdapter(listService, this);
        spServices.setAdapter(spinnerServiceAdapter);

        //B???t s??? ki???n b???m v??o imgDoctor s??? m??? ra th?? vi???n ???nh
        imgDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission();
            }
        });

        rdoMen.setChecked(true);
        //B???t s??? ki???n cho save
        btnSaveDoctor.setOnClickListener(view -> {
            clickSaveDoctor();
        });
    }

    public void openSql() {
        //Kh???i t???o
        daoTimeWork = new DaoTimeWork(this);
        //M??? c?? s??? d??? li???u
        daoTimeWork.open();

        //Kh???i t???o
        daoDoctor = new DaoDoctor(this);
        //M??? c?? s??? d??? li???u
        daoDoctor.open();

        //Kh???i t???o
        daoUser = new DaoAccount(this);
        //M??? c?? s??? d??? li???u
        daoUser.open();

        //Kh???i t???o
        daoRoom = new DaoRoom(this);
        //M??? c?? s??? d??? li???u
        daoRoom.open();

        //Kh???i t???o
        daoService = new DaoService(this);
        //M??? c??? s??? d??? li???u
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
            Toast.makeText(this, "B???n ch??a ch???n ???nh", Toast.LENGTH_SHORT).show();
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
            dtoUser.setGender("N???");
        }
        //G???n c???ng cho ????ng k?? l?? t??i kho???n  user
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
                Toast.makeText(this, "Th??m b??c s?? th??nh c??ng", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Th??m b??c s?? kh??ng th??nh c??ng", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Th???t b???i", Toast.LENGTH_SHORT).show();
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
                //L???y ra uri c???a ???nh
                uriImg = uri + "";
                //G???n ???nh v??o imgEditUser
                imgDoctor.setImageURI(uri);
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}