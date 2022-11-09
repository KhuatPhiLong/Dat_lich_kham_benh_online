package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.Viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import poly.edu.vn.dat_lich_kham_benh_online.R;

public class TimeWorkViewHolder extends RecyclerView.ViewHolder{
    public TextView tvUpdateTimeWork,tvTimeWork;
    public TimeWorkViewHolder(@NonNull View itemView) {
        super(itemView);
        tvUpdateTimeWork = itemView.findViewById(R.id.tvUpdateTimeWork);
        tvTimeWork = itemView.findViewById(R.id.tvTimeWork);
    }
}
