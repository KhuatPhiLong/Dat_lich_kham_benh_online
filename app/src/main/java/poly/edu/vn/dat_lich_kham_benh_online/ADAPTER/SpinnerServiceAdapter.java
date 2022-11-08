package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoService;
import poly.edu.vn.dat_lich_kham_benh_online.R;

public class SpinnerServiceAdapter extends BaseAdapter {
    private ArrayList<DtoService> listService = new ArrayList<>();
    private Context context;

    public SpinnerServiceAdapter(ArrayList<DtoService> listService, Context context) {
        this.listService = listService;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listService.size();
    }

    @Override
    public Object getItem(int i) {
        return listService.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        if(view == null){
            row = View.inflate(context, R.layout.item_sp_service,null);
        }
        else{
            row = view;
        }
        DtoService dtoService = listService.get(i);
        TextView tvSpinnerNameService = row.findViewById(R.id.tvSpinnerNameService);
        tvSpinnerNameService.setText(dtoService.getName());
        return row;
    }
}
