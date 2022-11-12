package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.Viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import poly.edu.vn.dat_lich_kham_benh_online.R;

public class HistoryOrderViewHolder extends RecyclerView.ViewHolder {
    public TextView tvIdOrder,tvOrder_date,tvOrder_time,tvSumPrice;
    public RecyclerView rvServiceOrder;
    public HistoryOrderViewHolder(@NonNull View itemView) {
        super(itemView);
        tvIdOrder = itemView.findViewById(R.id.tvIdOrder);
        tvOrder_date = itemView.findViewById(R.id.tvOrder_date);
        tvOrder_time = itemView.findViewById(R.id.tvOrder_time);
        tvSumPrice = itemView.findViewById(R.id.tvSumPrice);
        rvServiceOrder = itemView.findViewById(R.id.rvServiceOrder);
    }
}
