package poly.edu.vn.dat_lich_kham_benh_online;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.CategoriesAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.ServiceAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.SpinnerCategoriesAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoCategories;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoService;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoCategories;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoService;

public class AddSerivceActivity extends AppCompatActivity {
    private String uriImg;
    private ImageView imgService;
    private EditText edNameService,edPriceService;
    private Spinner spCategories;
    private Button btnSaveService;
    private DaoCategories daoCategories;
    private ArrayList<DtoCategories> listCategories;
    private SpinnerCategoriesAdapter spCategoriesAdapter;
    private DaoService daoService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_serivce);
        init();

        //Khởi tạo
        daoCategories = new DaoCategories(this);
        //Mở cơ sở dữ liệu
        daoCategories.open();

        //Khởi tạo
        daoService = new DaoService(this);
        //Mở cơ sở dữ liệu
        daoService.open();
        
        //Lấy ra danh sách categories
        listCategories = daoCategories.selectAll();
        //Khởi tạo adpater categories
        spCategoriesAdapter = new SpinnerCategoriesAdapter(listCategories,this);
        //Gắn adapter vòa sp
        spCategories.setAdapter(spCategoriesAdapter);

        //Bắt sự kiện cho imgService để mở thư viện lấy ảnh
        imgService.setOnClickListener(view->{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (AddSerivceActivity.this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                    requestPermissions(permissions, 1);
                }
                else {
                    Intent intentGrallary = new Intent(Intent.ACTION_PICK);
                    intentGrallary.setType("image/*");
                    startActivityForResult(intentGrallary,1);
                }
            }
        });
        //Lấy ra danh sách
        ArrayList<DtoService> listServie = daoService.selectAll();
        ServiceAdapter serviceAdapter = new ServiceAdapter(listServie,this);

        //Bắt sự kiện cho nút
        btnSaveService.setOnClickListener(view ->{
            if(uriImg == null){
                Toast.makeText(this, "Bạn chưa chọn ảnh", Toast.LENGTH_SHORT).show();
                return;
            }
            DtoService dtoService = new DtoService();
            dtoService.setImg(uriImg);
            dtoService.setName(edNameService.getText().toString());
            dtoService.setPrice(Float.parseFloat(edPriceService.getText().toString()));
            DtoCategories dtoCategories = (DtoCategories) spCategories.getSelectedItem();
            dtoService.setCategories_id(dtoCategories.getId());
            long res = daoService.insertRow(dtoService);
            if(res>0){
                listServie.clear();
                listServie.addAll(daoService.selectAll());
                serviceAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Thêm dịch vụ khám thành công", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Thêm dịch vụ khám không thành công", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1){
            if(data!=null){
                Uri uri = data.getData();
                //Lấy ra uri của ảnh
                uriImg = uri+"";
                //Gắn ảnh vào imgEditUser
                imgService.setImageURI(uri);
            }
        }
    }
    public void init(){
        imgService = findViewById(R.id.imgService);
        edNameService = findViewById(R.id.edNameService);
        edPriceService = findViewById(R.id.edPriceService);
        spCategories = findViewById(R.id.spCategories);
        btnSaveService = findViewById(R.id.btnSaveService);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}