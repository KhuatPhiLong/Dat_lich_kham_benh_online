package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.Viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import poly.edu.vn.dat_lich_kham_benh_online.R;

public class OrderTimeWorkDetailViewHolder extends RecyclerView.ViewHolder {
    public TextView tvOrderTimeWorkDetail;
    public OrderTimeWorkDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        tvOrderTimeWorkDetail = itemView.findViewById(R.id.tvOrderTimeWorkDetail);
    }
}
