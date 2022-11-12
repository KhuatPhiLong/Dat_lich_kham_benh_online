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
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.Viewholder.CategoriesViewHolder;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoCategories;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoCategories;
import poly.edu.vn.dat_lich_kham_benh_online.R;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder>  {
    private ArrayList<DtoCategories> listCategories = new ArrayList<>();
    private Context context;
    private DaoCategories daoCategories;

    public CategoriesAdapter(ArrayList<DtoCategories> listCategories, DaoCategories daoCategories) {
        this.listCategories = listCategories;
        this.daoCategories = daoCategories;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categories,parent,false);
        context = parent.getContext();
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        daoCategories = new DaoCategories(context);
        daoCategories.open();
        DtoCategories dtoCategories = listCategories.get(position);
        final int index = position;
        holder.tvNameCategories.setText(dtoCategories.getName());
        holder.tvDeleteCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int res = daoCategories.deleteRow(dtoCategories);
                if(res>0){
                    listCategories.remove(dtoCategories);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa thành công loại khám", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.tvUpdateCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickUpdate(dtoCategories,context,index);
            }
        });
    }

    private void onClickUpdate(DtoCategories dtoCategories, Context context, int index) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_update_categories);
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        else{
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        EditText edUpdateNameCategories = dialog.findViewById(R.id.edUpdateNameCategories);
        Button btnUpdateSaveCategories = dialog.findViewById(R.id.btnUpdateSaveCategories);
        edUpdateNameCategories.setText(dtoCategories.getName());

        btnUpdateSaveCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dtoCategories.setName(edUpdateNameCategories.getText().toString());

                int res = daoCategories.updateRow(dtoCategories);
                if(res>0){
                    listCategories.set(index,dtoCategories);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Sửa loại khám thành công", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, "Sửa loại khám không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }

    @Override
    public int getItemCount() {
        return listCategories.size();
    }
}
