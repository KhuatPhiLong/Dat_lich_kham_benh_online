package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.Viewholder.RoomViewHolder;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.R;

public class RoomAdapter extends RecyclerView.Adapter<RoomViewHolder> {
    private ArrayList<DtoRoom> listRoom = new ArrayList<>();
    private Context context;
    private DaoRoom daoRoom;

    public RoomAdapter(ArrayList<DtoRoom> listRoom, Context context) {
        this.listRoom = listRoom;
        this.context = context;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_room,parent,false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
         daoRoom = new DaoRoom(context);
        daoRoom.open();
        
        DtoRoom dtoRoom = listRoom.get(position);
        final int index = position;
        holder.tvNameRoom.setText(dtoRoom.getName());
        holder.tvLocation.setText(dtoRoom.getLocation());
        holder.tvDeleteRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int res = daoRoom.delteRow(dtoRoom);
                if(res>0){
                    listRoom.remove(dtoRoom);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa phòng khám thành công", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, "Xóa phòng khám không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.tvUpdateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickUpdate(dtoRoom,context,index);
            }
        });
    }

    private void onClickUpdate(DtoRoom dtoRoom, Context context, int index) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_rooms);
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        else{
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        EditText edNameRoom = dialog.findViewById(R.id.edNameRoom);
        EditText edLocation = dialog.findViewById(R.id.edLocation);
        Button btnSaveRoom = dialog.findViewById(R.id.btnSaveRoom);

        //Gắn dữ liệu
        edNameRoom.setText(dtoRoom.getName());
        edLocation.setText(dtoRoom.getLocation());
        //Bắt sự kiện cho nút save room
        btnSaveRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dtoRoom.setName(edNameRoom.getText().toString());
                dtoRoom.setLocation(edLocation.getText().toString());

                int res = daoRoom.updateRow(dtoRoom);
                if(res>0){
                    listRoom.set(index,dtoRoom);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Sửa phòng khám thành công", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, "Sửa phòng khám  thất ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return listRoom.size();
    }

}
