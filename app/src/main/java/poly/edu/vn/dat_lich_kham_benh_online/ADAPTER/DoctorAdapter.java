package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER;


import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.Viewholder.DoctorViewHolder;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoService;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoTimeWork;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoService;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoTimeWork;
import poly.edu.vn.dat_lich_kham_benh_online.ManagerDoctorActivity;
import poly.edu.vn.dat_lich_kham_benh_online.R;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorViewHolder> {
    private ArrayList<DtoDoctor> listDoctor = new ArrayList<>();
    private Context context;
    ManagerDoctorActivity managerDoctorActivity;
    ArrayList<DtoRoom> listRoom;
    ArrayList<DtoService> listService;
    ArrayList<DtoAccount> listAcount;

    public DoctorAdapter(ArrayList<DtoDoctor> listDoctor, ManagerDoctorActivity managerDoctorActivity) {
        this.listDoctor = listDoctor;
        this.managerDoctorActivity = managerDoctorActivity;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_doctor, parent, false);
        context = parent.getContext();
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        final int index = position;
        DaoAccount daoAccount = new DaoAccount(context);
        daoAccount.open();
        listAcount= daoAccount.getAll();

        DaoRoom daoRoom = new DaoRoom(context);
        daoRoom.open();
        listRoom = daoRoom.selectAll();

        DaoService daoService = new DaoService(context);
        daoService.open();
        listService= daoService.selectAll();
        DaoDoctor daoDoctor = new DaoDoctor(context);
        daoDoctor.open();

        DaoTimeWork daoTimWork = new DaoTimeWork(context);
        daoTimWork.open();
        ArrayList<DtoTimeWork> listTimeWork =daoTimWork.selectAll();

        DtoDoctor dtoDoctor = listDoctor.get(index);
        DtoAccount dtoAccount = daoAccount.getDtoAccount(dtoDoctor.getUser_id());

        holder.tvNameDoctor.setText(dtoAccount.getFullName());
        if(dtoAccount.getImg()!=null){
            Uri uri = Uri.parse(dtoAccount.getImg());
            holder.imgDoctor.setImageURI(uri);
        }
        DtoRoom dtoRoom = daoRoom.getDtoRoom(dtoDoctor.getRoom_id());
        holder.tvNameRoom.setText(dtoRoom.getName());
        holder.tvNameService.setText(dtoDoctor.getService_id()+"");
        DtoService dtoService = daoService.getDtoSeriveById(1);
        holder.tvNameService.setText(dtoService.getName());
        holder.tvDescription.setText(dtoDoctor.getDescription());
        holder.tvUpdateDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managerDoctorActivity.updateDoctor(dtoDoctor, dtoAccount, listDoctor, listAcount,listRoom,listTimeWork, listService,index, context,daoAccount,daoDoctor);
                notifyItemChanged(index);
            }
        });
        holder.tvDeleteDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managerDoctorActivity.deleteDoctor(dtoDoctor, dtoAccount,listDoctor, listAcount, index, context,daoAccount,daoDoctor);
            notifyItemRemoved(index);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDoctor.size();
    }




}
