package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoTimeWorkDetail;
import poly.edu.vn.dat_lich_kham_benh_online.R;

public class SpinnerOrderTimeWorkDetailAdapter extends BaseAdapter {
    ArrayList<DtoTimeWorkDetail> listOrderTimeWorkDetail = new ArrayList<>();
    Context context;

    public SpinnerOrderTimeWorkDetailAdapter(ArrayList<DtoTimeWorkDetail> listOrderTimeWorkDetail, Context context) {
        this.listOrderTimeWorkDetail = listOrderTimeWorkDetail;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listOrderTimeWorkDetail.size();
    }

    @Override
    public Object getItem(int i) {
        return listOrderTimeWorkDetail.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        if(view == null){
            row = LayoutInflater.from(context).inflate(R.layout.item_spinner_order_timeworkdetail,null);
        }
        else{
            row = view;
        }
        DtoTimeWorkDetail dtoTimeWorkDetail = listOrderTimeWorkDetail.get(i);
        TextView tvOrderTimeWorkDetail = row.findViewById(R.id.tvOrderTimeWorkDetail);
        tvOrderTimeWorkDetail.setText(dtoTimeWorkDetail.getTime());
        return row;
    }
}
