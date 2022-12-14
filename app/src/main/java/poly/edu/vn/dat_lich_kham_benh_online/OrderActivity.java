package poly.edu.vn.dat_lich_kham_benh_online;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.SpinnerOrderTimeWorkDetailAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoCategories;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoFile;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoOrderDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoService;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoTimeWork;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoTimeWorkDetail;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoCategories;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoFile;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoOrderDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoService;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoTimeWork;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoTimeWorkDetail;

public class OrderActivity extends AppCompatActivity {
    public static ArrayList<DtoOrderDoctor> listOrderDoctor = new ArrayList<>();


    private DaoDoctor daoDoctor;
    private DaoAccount daoAccount;
    private DaoFile daoFile;
    private DaoRoom daoRoom;
    private DaoCategories daoCategories;
    private DaoService daoService;
    private DaoTimeWorkDetail daoTimeWorkDetail;
    private DaoTimeWork daoTimeWork;
    private TextView tvFullName, tvNameDoctor, tvService, tvRooms, tvCategories, tvDate, tvTimeOrder;
    private Button btnOrder;
    private Spinner spTimeWorkDetail;
    private String check;
    private DtoDoctor dtoDoctor;
    private DaoOrderDoctor daoOrderDoctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        init();
        //Kh???i t???o
        daoDoctor = new DaoDoctor(this);
        //M??? c?? s??? d??? li???u
        daoDoctor.open();

        //Kh???i t???o
        daoOrderDoctor = new DaoOrderDoctor(this);
        //M??? c?? s??? d??? li???u
        daoOrderDoctor.open();


        //Kh???i t???o
        daoAccount = new DaoAccount(this);
        //M??? c?? s??? d??? li???u
        daoAccount.open();


        //Kh???i t???o
        daoCategories = new DaoCategories(this);
        //M??? c?? s??? d??? li???u
        daoCategories.open();


        //Kh???i t???o
        daoRoom = new DaoRoom(this);
        //M??? c?? s??? d??? li???u
        daoRoom.open();


        //Kh???i t???o
        daoService = new DaoService(this);
        //M??? c?? s??? d??? li???u
        daoService.open();

        //Kh???i t???o
        daoFile = new DaoFile(this);
        //M??? c?? s??? d??? li???u
        daoFile.open();

        //Kh???i t???o
        daoFile = new DaoFile(this);
        //M??? c?? s??? d??? li???u
        daoFile.open();

        //Kh???i t???o
        daoTimeWork = new DaoTimeWork(this);
        //M??? c?? s??? d??? li???u
        daoTimeWork.open();

        //Kh???i t???o
        daoTimeWorkDetail = new DaoTimeWorkDetail(this);
        //M??? c?? s??? d??? li???u
        daoTimeWorkDetail.open();

        Intent intent = getIntent();
        int idDoctor = intent.getIntExtra("idDoctor", -1);


        //L???y ra ?????i t?????ng b??c s?? theo id
        dtoDoctor = daoDoctor.selectDtoDoctorById(idDoctor);

        //L???y ra ?????i t?????ng account c???a b??c s?? ????? l???y ???????c t??n b??c s??
        DtoAccount dtoAccountDoctor = daoAccount.getDtoAccount(dtoDoctor.getUser_id());

        //G???n t??n b??c s??
        tvNameDoctor.setText(dtoAccountDoctor.getFullName());

        //L???y ra id c???a user
        SharedPreferences preferences = getSharedPreferences("getIdUser", MODE_PRIVATE);
        int idUser = preferences.getInt("idUser", -1);
        //L???y ra ?????i t?????ng user ????? l???y ???????c full name
        DtoAccount dtoAccountUser = daoAccount.getDtoAccount(idUser);

        //G???n t??n b???nh nh??n
        tvFullName.setText(dtoAccountUser.getFullName());
        //L???y ra h??? s?? b???nh ??n
        DtoFile dtoFile = daoFile.getDtoFileByIdAccount(idUser);


        //L???y ra ?????i t?????ng ph??ng kh??m c???a doctor
        DtoRoom dtoRoom = daoRoom.getDtoRoom(dtoDoctor.getRoom_id());
        //G???n t??n room
        tvRooms.setText(dtoRoom.getName());

        //L???y ra ?????i t?????ng service
        DtoService dtoService = daoService.getDtoSeriveById(dtoDoctor.getService_id());
        //G???n t??n service
        tvService.setText(dtoService.getName());

        //L???y ra ?????i t?????ng categories
        DtoCategories dtoCategories = daoCategories.getDtoCategories(dtoService.getCategories_id());
        //G???n t??n categories
        tvCategories.setText(dtoCategories.getName());

        //Ch???n ng??y
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(OrderActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String date = year + "/" + (month +1)+ "/" + day;
                        tvDate.setText(date);
                    }
                }, year, month, day);
                dialog.show();
            }
        });
        //L???y ra ca
        DtoTimeWork dtoTimeWork = daoTimeWork.getDtoTimeWork(dtoDoctor.getTimework_id());
        //L???y ra danh s??ch gi??? c???a ca
        ArrayList<DtoTimeWorkDetail> listDtoTimeWorkDetail = daoTimeWorkDetail.selectTimeWorkDetailByTimeWorkId(dtoTimeWork.getId());
        //Ch???n gi???
        SpinnerOrderTimeWorkDetailAdapter spinnerOrderTimeWorkDetailAdapter = new SpinnerOrderTimeWorkDetailAdapter(listDtoTimeWorkDetail,this);
        spTimeWorkDetail.setAdapter(spinnerOrderTimeWorkDetailAdapter);


        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DtoOrderDoctor dtoOrderDoctor = new DtoOrderDoctor();
                dtoOrderDoctor.setFile_id(dtoFile.getId());
                dtoOrderDoctor.setDoctor_id(dtoDoctor.getId());
                dtoOrderDoctor.setStart_date(tvDate.getText().toString());
                DtoTimeWorkDetail dtoTimeWorkDetail = (DtoTimeWorkDetail) spTimeWorkDetail.getSelectedItem();
                dtoOrderDoctor.setStart_time(dtoTimeWorkDetail.getTime());
                dtoOrderDoctor.setTotal(dtoService.getPrice());
                long res = daoOrderDoctor.insertRow(dtoOrderDoctor);
                DtoOrderDoctor dtoOrderDoctor1 = daoOrderDoctor.getDtoOrderDoctor();
                if(res>0){
                    listOrderDoctor.add(dtoOrderDoctor1);
                    Intent intent1 =new Intent(getBaseContext(),ComfirmOrderActivity.class);
                    startActivity(intent1);
                }
                else{
                    Toast.makeText(OrderActivity.this, "Dat hang khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void init() {
        tvFullName = findViewById(R.id.tvFullName);
        tvNameDoctor = findViewById(R.id.tvNameDoctor);
        tvService = findViewById(R.id.tvService);
        tvRooms = findViewById(R.id.tvRooms);
        tvCategories = findViewById(R.id.tvCategories);
        tvDate = findViewById(R.id.tvDate);
        spTimeWorkDetail = findViewById(R.id.spTimeWorkDetail);
        btnOrder = findViewById(R.id.btnOrder);

    }
}