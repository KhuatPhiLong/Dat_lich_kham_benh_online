package poly.edu.vn.dat_lich_kham_benh_online.FRAGMENT;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.ManagerCategoriesActivity;
import poly.edu.vn.dat_lich_kham_benh_online.ManagerDoctorActivity;
import poly.edu.vn.dat_lich_kham_benh_online.ManagerRoomsActivity;
import poly.edu.vn.dat_lich_kham_benh_online.ManagerServiceActivity;
import poly.edu.vn.dat_lich_kham_benh_online.ProFileUserActivity;
import poly.edu.vn.dat_lich_kham_benh_online.R;

public class FragmentUser extends Fragment {
    private DaoAccount daoUser;
    private ImageView imgUser;
    private TextView tvFullNameUser;
    private Button btnManagerDoctor,btnManaerService,btnManagerCategories,btnManagerRooms;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgUser = view.findViewById(R.id.imgUser);
        tvFullNameUser = view.findViewById(R.id.tvFullNameUser);
        btnManagerDoctor = view.findViewById(R.id.btnManagerDoctor);
        btnManaerService = view.findViewById(R.id.btnManaerService);
        btnManagerCategories = view.findViewById(R.id.btnManagerCategories);
        btnManagerRooms = view.findViewById(R.id.btnManagerRooms);

        //Khởi tạo cơ sở dữ liệu
        daoUser = new DaoAccount(getActivity());
        //Mở cơ sở dữ liệu
        daoUser.open();
        //Lấy ra id của tài khoản đăng nhập
        SharedPreferences preferences = getActivity().getSharedPreferences("getIdUser", Context.MODE_PRIVATE);
        int idUser = preferences.getInt("idUser", -1);
        //lấy ra đối tượng có idUser
        DtoAccount getDtoUser = daoUser.getDtoUser(idUser);

        if(getDtoUser.getRole().equals("User")){
            btnManagerCategories.setVisibility(View.GONE);
            btnManaerService.setVisibility(View.GONE);
            btnManagerRooms.setVisibility(View.GONE);
            btnManagerDoctor.setVisibility(View.GONE);
        }
        else if(getDtoUser.getRole().equals("Doctor")){
            btnManagerCategories.setVisibility(View.GONE);
            btnManaerService.setVisibility(View.GONE);
            btnManagerRooms.setVisibility(View.GONE);
            btnManagerDoctor.setVisibility(View.GONE);
        }
        else if(getDtoUser.getRole().equals("Admin")){
            btnManagerCategories.setVisibility(View.VISIBLE);
            btnManaerService.setVisibility(View.VISIBLE);
            btnManagerRooms.setVisibility(View.VISIBLE);
            btnManagerDoctor.setVisibility(View.VISIBLE);
        }
        //Bắt sự kiện bấm vào quản lí bác sĩ
        btnManagerDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ManagerDoctorActivity.class);
                startActivity(intent);
            }
        });
        //Bắt sự kiện bấm vào quản lí dịch vụ khám
        btnManaerService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ManagerServiceActivity.class);
                startActivity(intent);
            }
        });
        //Bắt sự kiện bấm vòa quản lí phòng khám
        btnManagerRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ManagerRoomsActivity.class);
                startActivity(intent);
            }
        });
        //Bắt sự kiẹn bấm vào quản lí loại khám
        btnManagerCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ManagerCategoriesActivity.class);
                startActivity(intent);
            }
        });
        //Bắt sự kiện bấn vào imgUser
        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, 1);
                    }
                    else {
                        Intent intent = new Intent(getContext(), ProFileUserActivity.class);
                        startActivity(intent);
                    }
                }

            }
        });

        if (getDtoUser.getImg() != null) {
            //Ép kiểu dữ liệu từ string sang uri
            Uri uri = Uri.parse(getDtoUser.getImg().trim());
            imgUser.setImageURI(uri);
        }
        tvFullNameUser.setText(getDtoUser.getFullName());

    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences preferences = getActivity().getSharedPreferences("getIdUser", Context.MODE_PRIVATE);
        int idUser = preferences.getInt("idUser", -1);
        //lấy ra đối tượng có idUser
        DtoAccount getDtoUser = daoUser.getDtoUser(idUser);
        if (getDtoUser.getImg() != null) {
            //Ép kiểu dữ liệu từ string sang uri
            Uri uri = Uri.parse(getDtoUser.getImg().trim());
            imgUser.setImageURI(uri);
        }
    }
}
