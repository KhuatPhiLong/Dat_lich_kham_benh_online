package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.Viewholder.BookingDoctorViewHolder;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoFile;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoService;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoTimeWork;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoTimeWorkDetail;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoFile;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoService;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoTimeWork;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoTimeWorkDetail;
import poly.edu.vn.dat_lich_kham_benh_online.OrderActivity;
import poly.edu.vn.dat_lich_kham_benh_online.R;

public class BookingDoctorAdapter extends RecyclerView.Adapter<BookingDoctorViewHolder> {
    ArrayList<DtoDoctor> listDoctor = new ArrayList<>();
    Context context;

    public BookingDoctorAdapter(ArrayList<DtoDoctor> listDoctor, Context context) {
        this.listDoctor = listDoctor;
        this.context = context;
    }

    @NonNull
    @Override
    public BookingDoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_doctor, parent, false);
        return new BookingDoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingDoctorViewHolder holder, int position) {
        DaoAccount daoAccount = new DaoAccount(context);
        daoAccount.open();

        DaoRoom daoRoom = new DaoRoom(context);
        daoRoom.open();

        DaoService daoService = new DaoService(context);
        daoService.open();

        DaoDoctor daoDoctor = new DaoDoctor(context);
        daoDoctor.open();

        DaoTimeWork daoTimeWork = new DaoTimeWork(context);
        daoTimeWork.open();

        DaoTimeWorkDetail daoTimeWorkDetail = new DaoTimeWorkDetail(context);
        daoTimeWorkDetail.open();

        DaoFile daoFile = new DaoFile(context);
        daoFile.open();

        DtoDoctor dtoDoctor = listDoctor.get(position);
        //Lấy ra tài khoản của doctor
        DtoAccount dtoAccount = daoAccount.getDtoAccount(dtoDoctor.getUser_id());
        //Lấy ra phòng khám
        DtoRoom dtoRoom = daoRoom.getDtoRoom(dtoDoctor.getRoom_id());
        //Lấy ra dịch vụ khám
        DtoService dtoService = daoService.getDtoSeriveById(dtoDoctor.getService_id());
        //Lấy ra ca khám
        DtoTimeWork dtoTimeWork = daoTimeWork.getDtoTimeWork(dtoDoctor.getTimework_id());
        //Lấy ra danh sách giờ của ca
        ArrayList<DtoTimeWorkDetail> listDtoTimeWorkDetail = daoTimeWorkDetail.selectTimeWorkDetailByTimeWorkId(dtoTimeWork.getId());
        //Khoi tao adapter ordertimeworkdetail
        OrderTimeWorkDetailAdapter orderTimeWorkDetailAdapter = new OrderTimeWorkDetailAdapter(listDtoTimeWorkDetail, context);
        GridLayoutManager manager = new GridLayoutManager(context, 3);
        //Gắn dữ liệu vào rvtimeworkdetail
        holder.rvTimeWorkDetail.setLayoutManager(manager);
        holder.rvTimeWorkDetail.setAdapter(orderTimeWorkDetailAdapter);

        //Gắn dữ liệu
        holder.tvNameDoctor.setText(dtoAccount.getFullName());



        holder.tvDesDoctor.setText(dtoDoctor.getDescription());
        holder.tvOrderRoom.setText("Phòng khám: " + dtoRoom.getName());
        holder.tvOrderService.setText("Chuyên khoa: " + dtoService.getName());
        holder.tvTimeWork.setText("Ca khám: " + dtoTimeWork.getSession());
        holder.tvPriceService.setText("Giá khám: " + dtoService.getPrice() + "đ");
        holder.btnOrderDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lấy ra id của user
                SharedPreferences preferences = context.getSharedPreferences("getIdUser",context.MODE_PRIVATE);
                int idUser = preferences.getInt("idUser",-1);
                //Lấy ra đối tượng user để lấy được full name
                DtoAccount dtoAccountUser = daoAccount.getDtoAccount(idUser);
                DtoFile dtoFile = daoFile.getDtoFileByIdAccount(idUser);
                //Kiểm tra nếu chưa đăng kí hồ bệnh án sẽ đưa ra thông báo
                if(dtoFile.getId() == 0){
                    Toast.makeText(context, "Bạn cần phải đăng ký hồ sơ bệnh án trước", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(context, OrderActivity.class);
                    intent.putExtra("idDoctor",dtoDoctor.getId());
                    context.startActivity(intent);
                }

            }
        });
        if(dtoAccount.getImg()!=null){
            Uri uri = Uri.parse(dtoAccount.getImg());
            holder.imgDoctor.setImageURI(uri);
        }



    }

    @Override
    public int getItemCount() {
        return listDoctor.size();
    }
}
