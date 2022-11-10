package poly.edu.vn.dat_lich_kham_benh_online;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.SpinnerCategoriesAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoCategories;
import poly.edu.vn.dat_lich_kham_benh_online.DAO.DaoService;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoCategories;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoService;

public class UpdateServiceActivity extends AppCompatActivity {
    private DaoService daoService;
    private DaoCategories daoCategories;
    private ImageView imgUpdateService;
    private EditText edUpdateNameService, edUpdatePriceService;
    private Spinner spUpdateCategories;
    private Button btnUpdateSaveService;
    private String uriImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_service);
        init();
        //Khởi tạo
        daoService = new DaoService(this);
        //Mở cơ sở dữ liệu
        daoService.open();

        //Khởi tạo
        daoCategories = new DaoCategories(this);
        //Mở cơ sở dữ liệu
        daoCategories.open();

        Intent intent = getIntent();
        int idService = intent.getIntExtra("getIdService", -1);

        //Lấy ra đối tượng có idService truyền từ adapter qua
        DtoService dtoService = daoService.getDtoSeriveById(idService);

        //Lấy ra danh sách loại khám
        ArrayList<DtoCategories> listCategories = daoCategories.selectAll();
        //Gắn dữ liệu cho sp
        SpinnerCategoriesAdapter spinnerCategoriesAdapter = new SpinnerCategoriesAdapter(listCategories,this);
        spUpdateCategories.setAdapter(spinnerCategoriesAdapter);

        //Gắn dữ liệu theo đối tượng đã lấy
        edUpdateNameService.setText(dtoService.getName());
        edUpdatePriceService.setText(dtoService.getPrice()+"");
        for(int i=0;i<listCategories.size();i++){
            DtoCategories dtoCategories = listCategories.get(i);
            if(dtoCategories.getId() == dtoService.getCategories_id()){
                spUpdateCategories.setSelection(i);
                spUpdateCategories.setSelected(true);
            }
        }
        Uri uri = Uri.parse(dtoService.getImg());
        imgUpdateService.setImageURI(uri);


        //Bắt sự kiện cho imgUpdateService mở ra thư viện
        imgUpdateService.setOnClickListener(view->{
            Intent intentGrallary = new Intent(Intent.ACTION_PICK);
            intentGrallary.setType("image/*");
            startActivityForResult(intentGrallary,1);
        });
        //Bắt sự kiện cho nút save
        btnUpdateSaveService.setOnClickListener(view -> {
            dtoService.setImg(uriImg);
            dtoService.setName(edUpdateNameService.getText().toString());
            dtoService.setPrice(Float.parseFloat(edUpdatePriceService.getText().toString()));
            DtoCategories dtoCategories = (DtoCategories) spUpdateCategories.getSelectedItem();
            dtoService.setCategories_id(dtoCategories.getId());

            int res = daoService.updateRow(dtoService);
            if(res>0){
                Toast.makeText(this, "Sửa dịch vụ thành công", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Sửa dịch vụ không thành công", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void init() {
        imgUpdateService = findViewById(R.id.imgUpdateService);
        edUpdateNameService = findViewById(R.id.edUpdateNameService);
        edUpdatePriceService = findViewById(R.id.edUpdatePriceService);
        spUpdateCategories = findViewById(R.id.spUpdateCategories);
        btnUpdateSaveService = findViewById(R.id.btnUpdateSaveService);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1){
            Uri uri = data.getData();
            //Lấy ra uri của ảnh
            uriImg = uri+"";
            //Gắn ảnh vào imgEditUser
            imgUpdateService.setImageURI(uri);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}