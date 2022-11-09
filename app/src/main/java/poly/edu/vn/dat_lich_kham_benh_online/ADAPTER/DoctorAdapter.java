package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.Viewholder.DoctorViewHolder;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoService;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoService;
import poly.edu.vn.dat_lich_kham_benh_online.R;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorViewHolder> {
    private ArrayList<DtoDoctor> listDoctor = new ArrayList<>();
    private Context context;

    public DoctorAdapter(ArrayList<DtoDoctor> listDoctor, Context context) {
        this.listDoctor = listDoctor;
        this.context = context;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_doctor,parent,false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        DaoAccount daoAccount = new DaoAccount(context);
        daoAccount.open();

        DaoRoom daoRoom = new DaoRoom(context);
        daoRoom.open();

        DaoService daoService = new DaoService(context);
        daoService.open();
        
        DaoDoctor daoDoctor = new DaoDoctor(context);
        daoDoctor.open();

        DtoDoctor dtoDoctor = listDoctor.get(position);
        DtoAccount dtoAccount = daoAccount.getDtoAccount(dtoDoctor.getUser_id());
        holder.tvNameDoctor.setText(dtoAccount.getFullName());
        Uri uri = Uri.parse(dtoAccount.getImg());
        holder.imgDoctor.setImageURI(uri);

        DtoRoom dtoRoom = daoRoom.getDtoRoom(dtoDoctor.getRoom_id());
        holder.tvNameRoom.setText(dtoRoom.getName());

        holder.tvNameService.setText(dtoDoctor.getService_id()+"");
        DtoService dtoService = daoService.getDtoSeriveById(1);
        holder.tvNameService.setText(dtoService.getName());
        holder.tvDescription.setText(dtoDoctor.getDescription());
        
        holder.tvDeleteDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int res = daoDoctor.deleteRow(dtoDoctor);
                if(res>0){
                    listDoctor.remove(dtoDoctor);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa bác sĩ thành công", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, "Xóa bác sĩ không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDoctor.size();
    }
}
