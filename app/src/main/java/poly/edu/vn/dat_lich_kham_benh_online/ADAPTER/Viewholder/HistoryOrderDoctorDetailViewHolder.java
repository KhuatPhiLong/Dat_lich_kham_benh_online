package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.Viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import poly.edu.vn.dat_lich_kham_benh_online.R;

public class HistoryOrderDoctorDetailViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNameDoctor,tvNameService,tvNameRoom,tvStartDate,tvStartTime,tvPrice;
    public RecyclerView rvServiceOrder;
    public HistoryOrderDoctorDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        tvNameDoctor = itemView.findViewById(R.id.tvNameDoctor);
        tvNameService = itemView.findViewById(R.id.tvNameService);
        tvStartDate = itemView.findViewById(R.id.tvStartDate);
        tvStartTime = itemView.findViewById(R.id.tvStartTime);
        tvPrice = itemView.findViewById(R.id.tvPrice);
        tvNameRoom = itemView.findViewById(R.id.tvNameRoom);
        rvServiceOrder = itemView.findViewById(R.id.rvServiceOrder);
    }
}
