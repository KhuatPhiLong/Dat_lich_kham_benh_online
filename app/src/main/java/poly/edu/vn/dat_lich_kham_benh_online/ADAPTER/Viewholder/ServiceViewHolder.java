package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.Viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import poly.edu.vn.dat_lich_kham_benh_online.R;

public class ServiceViewHolder extends RecyclerView.ViewHolder {
    public ImageView imgService;
    public TextView tvNameService,tvCategories,tvDeleteService,tvUpdateService;
    public ServiceViewHolder(@NonNull View itemView) {
        super(itemView);
        imgService = itemView.findViewById(R.id.imgService);
        tvNameService = itemView.findViewById(R.id.tvNameService);
        tvCategories = itemView.findViewById(R.id.tvCategories);
        tvDeleteService = itemView.findViewById(R.id.tvDeletService);
        tvUpdateService = itemView.findViewById(R.id.tvUpdateService);
    }
}
