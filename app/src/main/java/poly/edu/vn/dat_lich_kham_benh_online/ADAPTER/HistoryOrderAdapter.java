package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.Viewholder.HistoryOrderViewHolder;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoOrderDetail;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoOrderDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoOrders;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoOrderDetail;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoOrderDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoOrders;
import poly.edu.vn.dat_lich_kham_benh_online.R;

public class HistoryOrderAdapter extends RecyclerView.Adapter<HistoryOrderViewHolder> {
    ArrayList<DtoOrderDetail> listOrderDetail = new ArrayList<>();
    Context context;

    public HistoryOrderAdapter(ArrayList<DtoOrderDetail> listOrderDetail, Context context) {
        this.listOrderDetail = listOrderDetail;
        this.context = context;
    }

    @NonNull
    @Override
    public HistoryOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_history_order,parent,false);
        return new HistoryOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryOrderViewHolder holder, int position) {
        DaoOrders daoOrders = new DaoOrders(context);
        daoOrders.open();
        DaoOrderDoctor daoOrderDoctor = new DaoOrderDoctor(context);
        daoOrderDoctor.open();

        DtoOrderDetail dtoOrderDetail = listOrderDetail.get(position);
        DtoOrders dtoOrders = daoOrders.getDtoOrderbyIdOrder(dtoOrderDetail.getOrder_id());
        holder.tvOrder_time.setText(dtoOrders.getOrder_time());
        holder.tvOrder_date.setText(dtoOrders.getOrder_date());
        holder.tvIdOrder.setText(dtoOrderDetail.getOrder_id()+"");

        ArrayList<DtoOrderDoctor> listOrderDoctor = daoOrderDoctor.getDtoOrderDoctorByIdOrderDetail(dtoOrderDetail.getOrder_id());
        LinearLayoutManager manager = new LinearLayoutManager(context,RecyclerView.VERTICAL,false);
        HistoryOrderDoctorDetailAdapter adapter = new HistoryOrderDoctorDetailAdapter(listOrderDoctor,context);
        holder.rvServiceOrder.setLayoutManager(manager);
        holder.rvServiceOrder.setAdapter(adapter);

        float sumPrice = 0;
        for(int i=0;i<listOrderDoctor.size();i++){
            DtoOrderDoctor dtoOrderDoctor = listOrderDoctor.get(i);
            sumPrice+=dtoOrderDoctor.getTotal();
        }
        holder.tvSumPrice.setText(sumPrice+"vnđ");

   }

    @Override
    public int getItemCount() {
        return listOrderDetail.size();
    }
}
