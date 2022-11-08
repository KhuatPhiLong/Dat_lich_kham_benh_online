package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.Viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import poly.edu.vn.dat_lich_kham_benh_online.R;

public class RoomViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNameRoom,tvLocation,tvDeleteRoom,tvUpdateRoom;
    public RoomViewHolder(@NonNull View itemView) {
        super(itemView);
        tvNameRoom = itemView.findViewById(R.id.tvNameRoom);
        tvLocation = itemView.findViewById(R.id.tvLocation);
        tvDeleteRoom = itemView.findViewById(R.id.tvDeleteRoom);
        tvUpdateRoom = itemView.findViewById(R.id.tvUpdateRoom);
    }
}
