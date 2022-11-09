package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.Viewholder.OrderDoctorViewHolder;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoService;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoTimWork;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoTimeWorkDetail;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoService;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoTimeWork;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoTimeWorkDetail;
import poly.edu.vn.dat_lich_kham_benh_online.R;

public class OrderDoctorAdapter extends RecyclerView.Adapter<OrderDoctorViewHolder> {
    ArrayList<DtoDoctor> listDoctor  =new ArrayList<>();
    Context context;

    public OrderDoctorAdapter(ArrayList<DtoDoctor> listDoctor, Context context) {
        this.listDoctor = listDoctor;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderDoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_doctor,parent,false);
        return new OrderDoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDoctorViewHolder holder, int position) {
        DaoAccount daoAccount = new DaoAccount(context);
        daoAccount.open();

        DaoRoom daoRoom = new DaoRoom(context);
        daoRoom.open();

        DaoService daoService = new DaoService(context);
        daoService.open();

        DaoDoctor daoDoctor = new DaoDoctor(context);
        daoDoctor.open();

        DaoTimWork daoTimWork = new DaoTimWork(context);
        daoTimWork.open();

        DaoTimeWorkDetail daoTimeWorkDetail = new DaoTimeWorkDetail(context);
        daoTimeWorkDetail.open();

        DtoDoctor dtoDoctor = listDoctor.get(position);
        //Lấy ra tài khoản của doctor
        DtoAccount dtoAccount = daoAccount.getDtoAccount(dtoDoctor.getUser_id());
        //Lấy ra phòng khám
        DtoRoom dtoRoom = daoRoom.getDtoRoom(dtoDoctor.getRoom_id());
        //Lấy ra dịch vụ khám
        DtoService dtoService = daoService.getDtoSeriveById(dtoDoctor.getService_id());
        //Lấy ra ca khám
        DtoTimeWork dtoTimeWork = daoTimWork.getDtoTimeWork(dtoDoctor.getTimework_id());
        //Lấy ra danh sách giờ của ca
        ArrayList<DtoTimeWorkDetail> listDtoTimeWorkDetail = daoTimeWorkDetail.selectTimeWorkDetailByTimeWorkId(dtoTimeWork.getId());
        //Khoi tao adapter ordertimeworkdetail
        OrderTimeWorkDetailAdapter orderTimeWorkDetailAdapter = new OrderTimeWorkDetailAdapter(listDtoTimeWorkDetail,context);
        LinearLayoutManager manager  =new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        //Gắn dữ liệu vào rvtimeworkdetail
        holder.rvTimeWorkDetail.setLayoutManager(manager);
        holder.rvTimeWorkDetail.setAdapter(orderTimeWorkDetailAdapter);

        //Gắn dữ liệu
    holder.tvNameDoctor.setText(dtoAccount.getFullName());
        holder.tvDesDoctor.setText(dtoDoctor.getDescription());
        holder.tvOrderRoom.setText("Phòng khám: "+dtoRoom.getName());
        holder.tvOrderService.setText("Chuyên khoa: "+dtoService.getName());
        holder.tvTimeWork.setText("Ca khám: "+dtoTimeWork.getSession());
        holder.tvPriceService.setText("Giá khám: "+dtoService.getPrice()+"đ");

    }

    @Override
    public int getItemCount() {
        return listDoctor.size();
    }
}
