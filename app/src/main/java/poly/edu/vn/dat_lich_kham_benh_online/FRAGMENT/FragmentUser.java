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
import poly.edu.vn.dat_lich_kham_benh_online.AddFileActivity;
import poly.edu.vn.dat_lich_kham_benh_online.FileActivity;
import poly.edu.vn.dat_lich_kham_benh_online.ManagerCategoriesActivity;
import poly.edu.vn.dat_lich_kham_benh_online.ManagerDoctorActivity;
import poly.edu.vn.dat_lich_kham_benh_online.ManagerRoomsActivity;
import poly.edu.vn.dat_lich_kham_benh_online.ManagerServiceActivity;
import poly.edu.vn.dat_lich_kham_benh_online.ManagerTimeWorkActivity;
import poly.edu.vn.dat_lich_kham_benh_online.ProFileUserActivity;
import poly.edu.vn.dat_lich_kham_benh_online.R;
import poly.edu.vn.dat_lich_kham_benh_online.ManagerTimeWorkDetailActivity;

public class FragmentUser extends Fragment {
    private DaoAccount daoAccount;
    private ImageView imgUser;
    private TextView tvFullNameUser,tvRole;
    private Button btnManagerDoctor,btnManaerService,btnManagerCategories,btnManagerRooms,btnManagerTimeWorkDetail,btnManagerTimeWork,btnFile;

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
        btnManagerTimeWorkDetail = view.findViewById(R.id.btnManagerTimeWorkDetail);
        btnManagerTimeWork = view.findViewById(R.id.btnManagerTimeWork);
        btnFile = view.findViewById(R.id.btnFile);
        tvRole = view.findViewById(R.id.tvRole);

        //Kh???i t???o c?? s??? d??? li???u
        daoAccount = new DaoAccount(getActivity());
        //M??? c?? s??? d??? li???u
        daoAccount.open();
        //L???y ra id c???a t??i kho???n ????ng nh???p
        SharedPreferences preferences = getActivity().getSharedPreferences("getIdUser", Context.MODE_PRIVATE);
        int idUser = preferences.getInt("idUser", -1);
        //l???y ra ?????i t?????ng c?? idUser
        DtoAccount getDtoAccount = daoAccount.getDtoAccount(idUser);
        tvRole.setText("T??i kho???n l?? : "+getDtoAccount.getRole());

        if(getDtoAccount.getRole().equals("User")){
            btnManagerCategories.setVisibility(View.GONE);
            btnManaerService.setVisibility(View.GONE);
            btnManagerRooms.setVisibility(View.GONE);
            btnManagerDoctor.setVisibility(View.GONE);
            btnManagerTimeWork.setVisibility(View.GONE);
            btnManagerTimeWorkDetail.setVisibility(View.GONE);
            btnFile.setVisibility(View.VISIBLE);
        }
        else if(getDtoAccount.getRole().equals("Doctor")){
            btnManagerCategories.setVisibility(View.GONE);
            btnManaerService.setVisibility(View.GONE);
            btnManagerRooms.setVisibility(View.GONE);
            btnManagerDoctor.setVisibility(View.GONE);
            btnManagerTimeWork.setVisibility(View.GONE);
            btnManagerTimeWorkDetail.setVisibility(View.GONE);
            btnFile.setVisibility(View.GONE);
        }
        else if(getDtoAccount.getRole().equals("Admin")){
            btnManagerCategories.setVisibility(View.VISIBLE);
            btnManaerService.setVisibility(View.VISIBLE);
            btnManagerRooms.setVisibility(View.VISIBLE);
            btnManagerDoctor.setVisibility(View.VISIBLE);
            btnManagerTimeWork.setVisibility(View.VISIBLE);
            btnManagerTimeWorkDetail.setVisibility(View.VISIBLE);
            btnFile.setVisibility(View.GONE);
        }
        //B???t s??? ki???n b???m v??o
        btnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FileActivity.class);
                startActivity(intent);
            }
        });

        //B???t s??? ki???n b???m v??o btnManagerTimeWorkDetail
        btnManagerTimeWorkDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ManagerTimeWorkDetailActivity.class);
                startActivity(intent);
            }
        });

        //B???t s??? ki???n b???m v??o qu???n l?? time work
        btnManagerTimeWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ManagerTimeWorkActivity.class);
                startActivity(intent);
            }
        });
        //B???t s??? ki???n b???m v??o qu???n l?? b??c s??
        btnManagerDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ManagerDoctorActivity.class);
                startActivity(intent);
            }
        });
        //B???t s??? ki???n b???m v??o qu???n l?? d???ch v??? kh??m
        btnManaerService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ManagerServiceActivity.class);
                startActivity(intent);
            }
        });
        //B???t s??? ki???n b???m v??a qu???n l?? ph??ng kh??m
        btnManagerRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ManagerRoomsActivity.class);
                startActivity(intent);
            }
        });
        //B???t s??? ki???n b???m v??o qu???n l?? lo???i kh??m
        btnManagerCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ManagerCategoriesActivity.class);
                startActivity(intent);
            }
        });
        //B???t s??? ki???n b???n v??o imgUser
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

        if (getDtoAccount.getImg() != null) {
            //??p ki???u d??? li???u t??? string sang uri
            Uri uri = Uri.parse(getDtoAccount.getImg().trim());
            imgUser.setImageURI(uri);
        }
        tvFullNameUser.setText(getDtoAccount.getFullName());

    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences preferences = getActivity().getSharedPreferences("getIdUser", Context.MODE_PRIVATE);
        int idUser = preferences.getInt("idUser", -1);
        //l???y ra ?????i t?????ng c?? idUser
        DtoAccount getDtoAccount = daoAccount.getDtoAccount(idUser);
        if (getDtoAccount.getImg() != null) {
            //??p ki???u d??? li???u t??? string sang uri
            Uri uri = Uri.parse(getDtoAccount.getImg().trim());
            imgUser.setImageURI(uri);
        }
    }
}
