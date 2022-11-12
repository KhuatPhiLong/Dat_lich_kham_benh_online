package poly.edu.vn.dat_lich_kham_benh_online;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.CategoriesAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoCategories;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoCategories;

public class ManagerCategoriesActivity extends AppCompatActivity {
    private RecyclerView rvManagerCategories;
    private TextView tvAddCategories;
    private DaoCategories daoCategories;
    private ArrayList<DtoCategories> listCategories;
    private CategoriesAdapter categoriesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_categories);
        init();
        //Khởi tạo
        daoCategories = new DaoCategories(this);
        //Mở cơ sở dữ liệu
        daoCategories.open();
        //Lấy ra danh sách categories
        listCategories = daoCategories.selectAll();
        //Gắn dữ liệu cho apdter
        categoriesAdapter = new CategoriesAdapter(listCategories,daoCategories);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        //Gắn dữ liệu vào rv
        rvManagerCategories.setLayoutManager(manager);
        rvManagerCategories.setAdapter(categoriesAdapter);
        
        tvAddCategories.setOnClickListener(view->{
            Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_add_categories);
            Window window = dialog.getWindow();
            if(window == null){
                return;
            }
            else{
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            EditText edNameCategories = dialog.findViewById(R.id.edNameCategories);
            Button btnSaveCategories = dialog.findViewById(R.id.btnSaveCategories);
            //Bắt sự kiện cho nút save room
            btnSaveCategories.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DtoCategories dtoCategories = new DtoCategories();
                    dtoCategories.setName(edNameCategories.getText().toString());

                    long res = daoCategories.insertRow(dtoCategories);
                    if(res>0){
                        listCategories.clear();
                        listCategories.addAll(daoCategories.selectAll());
                        categoriesAdapter.notifyDataSetChanged();
                        Toast.makeText(ManagerCategoriesActivity.this, "Thêm loại khám thành công", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(ManagerCategoriesActivity.this, "Thêm loại khám thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            dialog.show();
        });
    }
    public void init(){
        tvAddCategories = findViewById(R.id.tvAddCategories);
        rvManagerCategories = findViewById(R.id.rvManagerCategories);
    }

    @Override
    protected void onResume() {
        super.onResume();
        categoriesAdapter = new CategoriesAdapter(listCategories,daoCategories);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        //Gắn dữ liệu vào rv
        rvManagerCategories.setLayoutManager(manager);
        rvManagerCategories.setAdapter(categoriesAdapter);
    }
}