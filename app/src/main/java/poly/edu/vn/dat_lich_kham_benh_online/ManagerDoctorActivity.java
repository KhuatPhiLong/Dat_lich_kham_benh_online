package poly.edu.vn.dat_lich_kham_benh_online;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.DoctorAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.SpinnerRoomAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.SpinnerServiceAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.SpinnerTimeWorkAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoService;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoTimeWork;

public class ManagerDoctorActivity extends AppCompatActivity {
    private TextView tvAddDoctor;
    private RecyclerView rvManagerDoctor;
    private DaoDoctor daoDoctor;
    private ArrayList<DtoDoctor> listDoctor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_doctor);
        init();

        //Khởi tạo
        daoDoctor = new DaoDoctor(this);
        //Mở cơ sở dữ liệu
        daoDoctor.open();

        //Lấy danh sách doctor
        listDoctor = daoDoctor.selectAll();
        //Gắn dữ liệu vào adapter
        DoctorAdapter doctorAdapter = new DoctorAdapter(listDoctor, this);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rvManagerDoctor.setLayoutManager(manager);
        rvManagerDoctor.setAdapter(doctorAdapter);

        //Bắt sự kiện cho nut thêm bác sĩ
        tvAddDoctor.setOnClickListener(view->{
            Intent intent = new Intent(getBaseContext(),AddDoctorActivity.class);
            startActivity(intent);
        });
    }
    public void init(){
        tvAddDoctor = findViewById(R.id.tvAddDoctor);
        rvManagerDoctor = findViewById(R.id.rvManagerDoctor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listDoctor = daoDoctor.selectAll();
        //Gắn dữ liệu vào adapter
        DoctorAdapter doctorAdapter = new DoctorAdapter(listDoctor, this);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rvManagerDoctor.setLayoutManager(manager);
        rvManagerDoctor.setAdapter(doctorAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    // sửa xóa doctor
    public void updateDoctor(DtoDoctor dtoDoctor, DtoAccount dtoAccount,ArrayList<DtoDoctor> listDoctor,ArrayList<DtoAccount> listAcount , ArrayList<DtoRoom> listRoom, ArrayList<DtoTimeWork> listTimeWork, ArrayList<DtoService> listService, int index, Context context, DaoAccount daoAccount, DaoDoctor daoDoctor1){
        final Dialog dialog = new Dialog(this, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialogh_update_doctor);
        ImageView imgDoctorUp = dialog.findViewById(R.id.imgDoctor_up);
        EditText edFullNameUp = dialog.findViewById(R.id.edFullName_up);
        EditText edPhoneUp = dialog.findViewById(R.id.edPhone_up);
        RadioButton rdoMenUp = dialog.findViewById(R.id.rdoMen_up);
        RadioButton rdoGirlUp = dialog.findViewById(R.id.rdoGirl_up);
        Spinner spRoomsUp = dialog.findViewById(R.id.spRooms_up);
        Spinner spServicesUp = dialog.findViewById(R.id.spServices_up);
        Spinner spTimeWorkUp = dialog.findViewById(R.id.spTimeWork_up);
        EditText edDescriptionUp = dialog.findViewById(R.id.edDescription_up);
        EditText edBirthDayUp = dialog.findViewById(R.id.edBirthDay_up);
        Button btnSaveDoctor = dialog.findViewById(R.id.btnSaveDoctor);
        if(dtoAccount.getImg()!=null){
            Uri uri = Uri.parse(dtoAccount.getImg());
            imgDoctorUp.setImageURI(uri);
        }
        edFullNameUp.setText(dtoAccount.getFullName());
        edBirthDayUp.setText(dtoDoctor.getBirthday());
        edPhoneUp.setText(dtoAccount.getPhone());
        edDescriptionUp.setText(dtoDoctor.getDescription());
        SpinnerRoomAdapter spinnerRoomAdapter = new SpinnerRoomAdapter(listRoom, context);
        SpinnerServiceAdapter spinnerServiceAdapter = new SpinnerServiceAdapter(listService, context);
        SpinnerTimeWorkAdapter spinnerTimeWorkAdapter = new SpinnerTimeWorkAdapter(listTimeWork, context);
        spRoomsUp.setAdapter(spinnerRoomAdapter);
        spServicesUp.setAdapter(spinnerServiceAdapter);
        spTimeWorkUp.setAdapter(spinnerTimeWorkAdapter);
        for(int i=0;i<listRoom.size();i++){
            if(dtoDoctor.getRoom_id()==listRoom.get(i).getId()){
                spRoomsUp.setSelection(i);
                spRoomsUp.setSelected(true);
            }
        }
        for(int i=0;i<listService.size();i++){
            if(dtoDoctor.getService_id()==listService.get(i).getId()){
                spRoomsUp.setSelection(i);
                spRoomsUp.setSelected(true);
            }
        }
        for(int i=0;i<listTimeWork.size();i++){
            if(dtoDoctor.getTimework_id()==listTimeWork.get(i).getId()){
                spRoomsUp.setSelection(i);
                spRoomsUp.setSelected(true);
            }
        }
        if(dtoAccount.getGender().equalsIgnoreCase("Nam")){
            rdoMenUp.setChecked(true);
            rdoGirlUp.setChecked(false);
        }else if(dtoAccount.getGender().equalsIgnoreCase("Nữ")){
            rdoGirlUp.setChecked(true);
            rdoMenUp.setChecked(false);
        }
        btnSaveDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dtoAccount.setPhone(edPhoneUp.getText().toString());
                dtoAccount.setFullName(edFullNameUp.getText().toString());
                if(rdoMenUp.isChecked()){
                    dtoAccount.setGender("Nam");
                }
                else{
                    dtoAccount.setGender("Nữ");
                }
                long res = daoAccount.updateInfor(dtoAccount);
                if(res>0){

                    DtoRoom dtoRoom = (DtoRoom) spRoomsUp.getSelectedItem();
                    dtoDoctor.setRoom_id(dtoRoom.getId());

                    DtoService dtoService = (DtoService) spServicesUp.getSelectedItem();
                    dtoDoctor.setService_id(dtoService.getId());

                    DtoTimeWork dtoTimeWork = (DtoTimeWork) spTimeWorkUp.getSelectedItem();
                    dtoDoctor.setTimework_id(dtoTimeWork.getId());

                    dtoDoctor.setDescription(edDescriptionUp.getText().toString());
                    dtoDoctor.setBirthday(edBirthDayUp.getText().toString());
                    listAcount.set(index, dtoAccount);
                    long res1 = daoDoctor1.updateDoctor(dtoDoctor);
                    if(res1>0){
                        listDoctor.set(index,dtoDoctor);
                        dialog.dismiss();
                        Toast.makeText(context, "Cập nhật bác sĩ thành công", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(context, "Cập nhật bác sĩ không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();

    }
    public void deleteDoctor(DtoDoctor dtoDoctor, DtoAccount dtoAccount,ArrayList<DtoDoctor> listDoctor,ArrayList<DtoAccount> ListAcount, int index, Context context, DaoAccount daoAccount, DaoDoctor daoDoctor1){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("delete");
        builder.setMessage("Bạn có muốn xóa bác sĩ: " + dtoAccount.getFullName());
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int res = daoDoctor1.deleteRow(dtoDoctor);
                int resac = daoAccount.deleteRow(dtoAccount);
                if (res > 0&& resac>0) {
                    listDoctor.remove(index);
                    ListAcount.remove(index);
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}

