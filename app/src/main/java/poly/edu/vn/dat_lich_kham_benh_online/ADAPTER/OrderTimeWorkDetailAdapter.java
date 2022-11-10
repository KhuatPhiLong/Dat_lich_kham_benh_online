package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.Viewholder.OrderTimeWorkDetailViewHolder;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoTimeWorkDetail;
import poly.edu.vn.dat_lich_kham_benh_online.R;

public class OrderTimeWorkDetailAdapter extends RecyclerView.Adapter<OrderTimeWorkDetailViewHolder> {
    public static String orderTime = "";
    ArrayList<DtoTimeWorkDetail> listOrderTimeWorkDetail = new ArrayList<>();
    Context context;
    public OrderTimeWorkDetailAdapter(ArrayList<DtoTimeWorkDetail> listOrderTimeWorkDetail, Context context) {
        this.listOrderTimeWorkDetail = listOrderTimeWorkDetail;
        this.context = context;
    }
    @NonNull
    @Override
    public OrderTimeWorkDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_timeworkdetail,parent,false);
        return new OrderTimeWorkDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderTimeWorkDetailViewHolder holder, int position) {
        DtoTimeWorkDetail dtoTimeWorkDetail = listOrderTimeWorkDetail.get(position);
        holder.tvOrderTimeWorkDetail.setText(dtoTimeWorkDetail.getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyDataSetChanged();
                OrderTimeWorkDetailAdapter.orderTime = dtoTimeWorkDetail.getTime();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOrderTimeWorkDetail.size();
    }
}
