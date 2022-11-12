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
        //Khởi tạo
        daoDoctor = new DaoDoctor(this);
        //Mở cơ sở dữ liệu
        daoDoctor.open();

        //Khởi tạo
        daoOrderDoctor = new DaoOrderDoctor(this);
        //Mở cơ sở dữ liệu
        daoOrderDoctor.open();


        //Khởi tạo
        daoAccount = new DaoAccount(this);
        //Mở cơ sở dữ liệu
        daoAccount.open();


        //Khởi tạo
        daoCategories = new DaoCategories(this);
        //Mở cơ sở dữ liệu
        daoCategories.open();


        //Khởi tạo
        daoRoom = new DaoRoom(this);
        //Mở cơ sở dữ liệu
        daoRoom.open();


        //Khởi tạo
        daoService = new DaoService(this);
        //Mở cơ sở dữ liệu
        daoService.open();

        //Khởi tạo
        daoFile = new DaoFile(this);
        //Mở cơ sở dữ liệu
        daoFile.open();

        //Khởi tạo
        daoFile = new DaoFile(this);
        //Mở cơ sở dữ liệu
        daoFile.open();

        //Khởi tạo
        daoTimeWork = new DaoTimeWork(this);
        //Mở cơ sở dữ liệu
        daoTimeWork.open();

        //Khởi tạo
        daoTimeWorkDetail = new DaoTimeWorkDetail(this);
        //Mở cơ sở dữ liệu
        daoTimeWorkDetail.open();

        Intent intent = getIntent();
        int idDoctor = intent.getIntExtra("idDoctor", -1);


        //Lấy ra đối tượng bác sĩ theo id
        dtoDoctor = daoDoctor.selectDtoDoctorById(idDoctor);

        //Lấy ra đối tượng account của bác sĩ để lấy được tên bác sĩ
        DtoAccount dtoAccountDoctor = daoAccount.getDtoAccount(dtoDoctor.getUser_id());

        //Gắn tên bác sĩ
        tvNameDoctor.setText(dtoAccountDoctor.getFullName());

        //Lấy ra id của user
        SharedPreferences preferences = getSharedPreferences("getIdUser", MODE_PRIVATE);
        int idUser = preferences.getInt("idUser", -1);
        //Lấy ra đối tượng user để lấy được full name
        DtoAccount dtoAccountUser = daoAccount.getDtoAccount(idUser);

        //Gắn tên bệnh nhân
        tvFullName.setText(dtoAccountUser.getFullName());
        //Lấy ra hồ sơ bệnh án
        DtoFile dtoFile = daoFile.getDtoFileByIdAccount(idUser);


        //Lấy ra đối tượng phòng khám của doctor
        DtoRoom dtoRoom = daoRoom.getDtoRoom(dtoDoctor.getRoom_id());
        //Gắn tên room
        tvRooms.setText(dtoRoom.getName());

        //Lấy ra đối tượng service
        DtoService dtoService = daoService.getDtoSeriveById(dtoDoctor.getService_id());
        //Gắn tên service
        tvService.setText(dtoService.getName());

        //Lẩy ra đối tượng categories
        DtoCategories dtoCategories = daoCategories.getDtoCategories(dtoService.getCategories_id());
        //Gắn tên categories
        tvCategories.setText(dtoCategories.getName());

        //Chọn ngày
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
        //Lấy ra ca
        DtoTimeWork dtoTimeWork = daoTimeWork.getDtoTimeWork(dtoDoctor.getTimework_id());
        //Lấy ra danh sách giờ của ca
        ArrayList<DtoTimeWorkDetail> listDtoTimeWorkDetail = daoTimeWorkDetail.selectTimeWorkDetailByTimeWorkId(dtoTimeWork.getId());
        //Chọn giờ
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