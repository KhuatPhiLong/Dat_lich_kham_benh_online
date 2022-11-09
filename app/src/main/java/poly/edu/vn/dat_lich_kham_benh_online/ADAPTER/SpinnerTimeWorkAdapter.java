package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoTimeWork;
import poly.edu.vn.dat_lich_kham_benh_online.R;

public class SpinnerTimeWorkAdapter extends BaseAdapter {
    private ArrayList<DtoTimeWork> listTimeWork = new ArrayList<>();
    private Context context;

    public SpinnerTimeWorkAdapter(ArrayList<DtoTimeWork> listTimeWork, Context context) {
        this.listTimeWork = listTimeWork;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listTimeWork.size();
    }

    @Override
    public Object getItem(int i) {
        return listTimeWork.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        if(view == null){
            row = View.inflate(context, R.layout.item_sp_timework,null);
        }
        else{
            row = view;
        }
        DtoTimeWork dtoTimeWork = listTimeWork.get(i);
        TextView tvSpinnerTimeWork = row.findViewById(R.id.tvSpinnerTimeWork);
        tvSpinnerTimeWork.setText(dtoTimeWork.getSession());
        return row;
    }
}
