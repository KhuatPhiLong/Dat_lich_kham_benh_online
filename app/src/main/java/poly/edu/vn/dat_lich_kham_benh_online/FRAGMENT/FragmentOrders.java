package poly.edu.vn.dat_lich_kham_benh_online.FRAGMENT;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.HistoryOrderAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoFile;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoOrderDetail;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoFile;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoOrderDetail;
import poly.edu.vn.dat_lich_kham_benh_online.R;

public class FragmentOrders extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_orders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvHistoyOrder = view.findViewById(R.id.rvHistoyOrder);
        DaoOrderDetail daoOrderDetail = new DaoOrderDetail(getContext());
        daoOrderDetail.open();

        SharedPreferences preferences = getActivity().getSharedPreferences("getIdUser", Context.MODE_PRIVATE);
        int idUser = preferences.getInt("idUser", -1);

        DaoFile daoFile = new DaoFile(getContext());
        daoFile.open();
        DtoFile dtoFile = daoFile.getDtoFileByIdAccount(idUser);

        ArrayList<DtoOrderDetail> listHistoryOrder = daoOrderDetail.selectAllByIdFile(dtoFile.getId());
        HistoryOrderAdapter historyOrderAdapter = new HistoryOrderAdapter(listHistoryOrder, getContext());
        LinearLayoutManager manager= new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvHistoyOrder.setLayoutManager(manager);
        rvHistoyOrder.setAdapter(historyOrderAdapter);


    }

}
