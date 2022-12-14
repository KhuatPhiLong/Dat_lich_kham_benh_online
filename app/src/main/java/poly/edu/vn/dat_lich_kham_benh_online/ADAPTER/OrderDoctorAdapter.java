package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.Viewholder.OrderDoctorViewHolder;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoFile;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoOrderDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoService;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoTimeWork;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoTimeWorkDetail;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoOrderDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoService;
import poly.edu.vn.dat_lich_kham_benh_online.R;

public class OrderDoctorAdapter extends RecyclerView.Adapter<OrderDoctorViewHolder> {
    ArrayList<DtoOrderDoctor> listOrderDoctor= new ArrayList<>();
    Context context;

    public OrderDoctorAdapter(ArrayList<DtoOrderDoctor> listOrderDoctor, Context context) {
        this.listOrderDoctor = listOrderDoctor;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderDoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order,parent,false);
        return new OrderDoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDoctorViewHolder holder, int position) {
        DaoOrderDoctor daoOrderDoctor = new DaoOrderDoctor(context);
        daoOrderDoctor.open();

        DaoAccount daoAccount = new DaoAccount(context);
        daoAccount.open();

        DaoRoom daoRoom = new DaoRoom(context);
        daoRoom.open();

        DaoService daoService = new DaoService(context);
        daoService.open();

        DaoDoctor daoDoctor = new DaoDoctor(context);
        daoDoctor.open();

        DtoOrderDoctor dtoOrderDoctor = listOrderDoctor.get(position);
        //L???y ra t??i kho???n c???a doctor

        holder.tvStartDate.setText(dtoOrderDoctor.getStart_date());
        holder.tvStartTime.setText(dtoOrderDoctor.getStart_time());

        DtoDoctor dtoDoctor = daoDoctor.selectDtoDoctorById(dtoOrderDoctor.getDoctor_id());
        DtoAccount dtoAccount = daoAccount.getDtoAccount(dtoDoctor.getUser_id());
        holder.tvNameDoctor.setText(dtoAccount.getFullName());

        DtoService dtoService = daoService.getDtoSeriveById(dtoDoctor.getService_id());
        holder.tvNameService.setText(dtoService.getName());

        DtoRoom dtoRoom = daoRoom.getDtoRoom(dtoDoctor.getRoom_id());
        holder.tvNameRoom.setText(dtoRoom.getName());

        holder.tvPrice.setText(dtoOrderDoctor.getTotal()+"??");
        holder.tvIdOder.setText(dtoOrderDoctor.getId()+"");
        holder.tvDeleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int res = daoOrderDoctor.deleteRow(dtoOrderDoctor);
                if(res>0){
                    listOrderDoctor.remove(dtoOrderDoctor);
                    notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listOrderDoctor.size();
    }
}
