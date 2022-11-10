package poly.edu.vn.dat_lich_kham_benh_online.FRAGMENT;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import poly.edu.vn.dat_lich_kham_benh_online.ListDoctorActivity;
import poly.edu.vn.dat_lich_kham_benh_online.R;

public class FragmentHome extends Fragment {
    private Button btnListDoctor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnListDoctor = view.findViewById(R.id.btnListDoctor);

        btnListDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ListDoctorActivity.class);
                startActivity(intent);
            }
        });

    }
}
