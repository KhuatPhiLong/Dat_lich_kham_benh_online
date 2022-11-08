package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.R;

public class SpinnerRoomAdapter extends BaseAdapter {
    ArrayList<DtoRoom> listRoom = new ArrayList<>();
    Context context;

    public SpinnerRoomAdapter(ArrayList<DtoRoom> listRoom, Context context) {
        this.listRoom = listRoom;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listRoom.size();
    }

    @Override
    public Object getItem(int i) {
        DtoRoom dtoRoom = listRoom.get(i);
        return dtoRoom;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        if(view == null){
            row = View.inflate(context,R.layout.item_sp_room,null);
        }
        else{
            row = view;
        }
        DtoRoom dtoRoom = listRoom.get(i);
        TextView tvSpinnerNameRoom = row.findViewById(R.id.tvSpinnerNameRoom);
        tvSpinnerNameRoom.setText(dtoRoom.getName());
        return row;
    }
}
