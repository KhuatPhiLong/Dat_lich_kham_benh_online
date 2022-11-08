package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoCategories;
import poly.edu.vn.dat_lich_kham_benh_online.R;

public class SpinnerCategoriesAdapter extends BaseAdapter {
    private ArrayList<DtoCategories> listCategories = new ArrayList<>();
    private Context context;

    public SpinnerCategoriesAdapter(ArrayList<DtoCategories> listCategories, Context context) {
        this.listCategories = listCategories;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listCategories.size();
    }

    @Override
    public Object getItem(int i) {
        return listCategories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        if(view == null){
            row = View.inflate(context, R.layout.item_sp_categories,null);
        }
        else{
            row = view;
        }
        DtoCategories dtoCategories = listCategories.get(i);
        TextView tvSpinnerNameCategories = row.findViewById(R.id.tvSpinnerNameCategories);
        tvSpinnerNameCategories.setText(dtoCategories.getName());
        return row;
    }
}
