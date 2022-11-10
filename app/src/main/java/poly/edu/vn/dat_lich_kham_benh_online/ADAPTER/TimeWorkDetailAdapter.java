package poly.edu.vn.dat_lich_kham_benh_online.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.Viewholder.TimeWorkDetailViewHolder;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoTimeWork;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoTimeWorkDetail;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoTimeWork;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoTimeWorkDetail;
import poly.edu.vn.dat_lich_kham_benh_online.R;

public class TimeWorkDetailAdapter extends RecyclerView.Adapter<TimeWorkDetailViewHolder> {
    private ArrayList<DtoTimeWorkDetail> listTimeWorkDetail = new ArrayList<>();
    private Context context;
    private DaoTimeWorkDetail daoTimeWorkDetail;

    public TimeWorkDetailAdapter(ArrayList<DtoTimeWorkDetail> listTimeWorkDetail, Context context) {
        this.listTimeWorkDetail = listTimeWorkDetail;
        this.context = context;
    }

    @NonNull
    @Override
    public TimeWorkDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_timework_detail,parent,false);
        return new TimeWorkDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeWorkDetailViewHolder holder, int position) {
        DaoTimeWork daoTimeWork = new DaoTimeWork(context);
        daoTimeWork.open();
        DtoTimeWorkDetail dtoTimeWorkDetail = listTimeWorkDetail.get(position);
        holder.tvTimeWorkDetail.setText(dtoTimeWorkDetail.getTime());
        DtoTimeWork dtoTimeWork = daoTimeWork.getDtoTimeWork(dtoTimeWorkDetail.getTimework_id());
        holder.tvTimeWork.setText(dtoTimeWork.getSession());
    }

    @Override
    public int getItemCount() {
        return listTimeWorkDetail.size();
    }
}
