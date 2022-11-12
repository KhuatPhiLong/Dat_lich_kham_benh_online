package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.Viewholder.ServiceViewHolder;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoCategories;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoService;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoCategories;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoService;
import poly.edu.vn.dat_lich_kham_benh_online.R;
import poly.edu.vn.dat_lich_kham_benh_online.UpdateServiceActivity;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceViewHolder> {
    private ArrayList<DtoService> listService = new ArrayList<>();
    private Context context;
    private DaoCategories daoCategories;

    public ServiceAdapter(ArrayList<DtoService> listService, Context context) {
        this.listService = listService;
        this.context = context;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_service,parent,false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        DaoService daoService = new DaoService(context);
        daoService.open();
        daoCategories  =new DaoCategories(context);
        daoCategories.open();
        DtoService dtoService = listService.get(position);
        holder.tvNameService.setText(dtoService.getName());
        DtoCategories dtoCategories = daoCategories.getDtoCategories(dtoService.getCategories_id());
        holder.tvCategories.setText(dtoCategories.getName());
        if(dtoService.getImg()!=null){
            Uri uri = Uri.parse(dtoService.getImg().toString());
            holder.imgService.setImageURI(uri);
        }
        holder.tvDeleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int res = daoService.deleteRow(dtoService);
                if(res>0){
                    listService.remove(dtoService);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa thành công dịch vụ khám", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, "Xóa không thành công dịch vụ khám", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.tvUpdateService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateServiceActivity.class);
                intent.putExtra("getIdService",dtoService.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listService.size();
    }
}
