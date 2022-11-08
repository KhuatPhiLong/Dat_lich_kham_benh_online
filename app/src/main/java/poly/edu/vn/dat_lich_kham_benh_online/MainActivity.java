package poly.edu.vn.dat_lich_kham_benh_online;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import poly.edu.vn.dat_lich_kham_benh_online.FRAGMENT.FragmentHome;
import poly.edu.vn.dat_lich_kham_benh_online.FRAGMENT.FragmentNotify;
import poly.edu.vn.dat_lich_kham_benh_online.FRAGMENT.FragmentOrders;
import poly.edu.vn.dat_lich_kham_benh_online.FRAGMENT.FragmentUser;

public class MainActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    private BottomNavigationView naviBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getFragment(new FragmentHome());
        naviBottom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.naviBottomHome:
                        getFragment(new FragmentHome());
                        break;
                    case R.id.naviBottomOrders:
                        getFragment(new FragmentOrders());
                        break;
                    case R.id.naviBottomNotify:
                        getFragment(new FragmentNotify());
                        break;
                    case R.id.naviBottomUser:
                        getFragment(new FragmentUser());
                        break;

                }
                return true;
            }
        });

    }
    public void getFragment(Fragment fg){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frameLayout,fg).commit();
    }
    public void init(){
        frameLayout = findViewById(R.id.frameLayout);
        naviBottom = findViewById(R.id.naviBottom);
    }
}