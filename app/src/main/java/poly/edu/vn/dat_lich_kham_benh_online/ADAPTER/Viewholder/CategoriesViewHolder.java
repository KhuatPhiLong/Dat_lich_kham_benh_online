package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.Viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import poly.edu.vn.dat_lich_kham_benh_online.R;

public class CategoriesViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNameCategories,tvDeleteCategories,tvUpdateCategories;
    public CategoriesViewHolder(@NonNull View itemView) {
        super(itemView);
        tvNameCategories = itemView.findViewById(R.id.tvNameCategories);
        tvUpdateCategories = itemView.findViewById(R.id.tvUpdateCategories);
        tvDeleteCategories = itemView.findViewById(R.id.tvDeleteCategories);
    }
}
