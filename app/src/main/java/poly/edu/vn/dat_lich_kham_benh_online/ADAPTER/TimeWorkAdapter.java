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

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.Viewholder.TimeWorkViewHolder;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoTimeWork;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoTimeWork;
import poly.edu.vn.dat_lich_kham_benh_online.R;

public class TimeWorkAdapter extends RecyclerView.Adapter<TimeWorkViewHolder> {
    private ArrayList<DtoTimeWork> listTimeWork = new ArrayList<>();
    private Context context;
    private DaoTimeWork daoTimeWork;

    public TimeWorkAdapter(ArrayList<DtoTimeWork> listTimeWork, Context context) {
        this.listTimeWork = listTimeWork;
        this.context = context;
    }

    @NonNull
    @Override
    public TimeWorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_timework,parent,false);
        return new TimeWorkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeWorkViewHolder holder, int position) {
        DtoTimeWork dtoTimeWork = listTimeWork.get(position);
        final int index = position;
        holder.tvTimeWork.setText(dtoTimeWork.getSession());
        holder.tvUpdateTimeWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickUpdateTimeWork(dtoTimeWork,context,index);
            }
        });

    }

    private void onClickUpdateTimeWork(DtoTimeWork dtoTimeWork, Context context, int index) {
        daoTimeWork = new DaoTimeWork(context);
        daoTimeWork.open();
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_timework);
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        else{
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        EditText edTimeWork = dialog.findViewById(R.id.edTimeWork);
        edTimeWork.setText(dtoTimeWork.getSession());
        Button btnSaveTimeWork = dialog.findViewById(R.id.btnSaveTimeWork);
        //Bắt sự kiện cho nút save room
        btnSaveTimeWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dtoTimeWork.setSession(edTimeWork.getText().toString());

                int res = daoTimeWork.updateRow(dtoTimeWork);
                if(res>0){
                    listTimeWork.set(index,dtoTimeWork);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Thêm ca làm việc thành công", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, "Thêm ca làm việc không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return listTimeWork.size();
    }
}
