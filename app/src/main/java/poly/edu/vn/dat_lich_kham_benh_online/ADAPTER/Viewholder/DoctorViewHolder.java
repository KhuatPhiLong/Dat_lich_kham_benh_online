package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.Viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import poly.edu.vn.dat_lich_kham_benh_online.R;

public class DoctorViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNameDoctor,tvNameService,tvNameRoom,tvDescription,tvDeleteDoctor,tvUpdateDoctor;
    public ImageView imgDoctor;
    public DoctorViewHolder(@NonNull View itemView) {
        super(itemView);
        tvNameDoctor = itemView.findViewById(R.id.tvNameDoctor);
        tvNameService = itemView.findViewById(R.id.tvNameService);
        tvNameRoom = itemView.findViewById(R.id.tvNameRoom);
        tvDescription = itemView.findViewById(R.id.tvDescription);
        tvDeleteDoctor = itemView.findViewById(R.id.tvDeleteDoctor);
        tvUpdateDoctor = itemView.findViewById(R.id.tvUpdateDoctor);
        imgDoctor = itemView.findViewById(R.id.imgDoctor);
    }
}
