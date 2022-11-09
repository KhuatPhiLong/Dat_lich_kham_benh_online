package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.Viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import poly.edu.vn.dat_lich_kham_benh_online.R;

public class OrderDoctorViewHolder extends RecyclerView.ViewHolder {
    public ImageView imgDoctor;
    public TextView tvNameDoctor,tvDesDoctor,tvTimeWork,tvOrderRoom,tvOrderService,tvPriceService;
    public RecyclerView rvTimeWorkDetail;
    public OrderDoctorViewHolder(@NonNull View itemView) {
        super(itemView);
        imgDoctor = itemView.findViewById(R.id.imgDoctor);
        tvNameDoctor = itemView.findViewById(R.id.tvNameDoctor);
        tvDesDoctor = itemView.findViewById(R.id.tvDesDoctor);
        tvTimeWork = itemView.findViewById(R.id.tvTimeWork);
        rvTimeWorkDetail = itemView.findViewById(R.id.rvTimeWorkDetail);
        tvOrderRoom = itemView.findViewById(R.id.tvOrderRoom);
        tvOrderService = itemView.findViewById(R.id.tvOrderSerivce);
        tvPriceService = itemView.findViewById(R.id.tvPriceService);
    }
}
