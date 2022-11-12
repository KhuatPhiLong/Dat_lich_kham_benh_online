package poly.edu.vn.dat_lich_kham_benh_online.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoService;
import poly.edu.vn.dat_lich_kham_benh_online.SQL.MyDbhelper;

public class DaoService {
    SQLiteDatabase db;
    MyDbhelper dbhelper;

    public DaoService(Context context){
        dbhelper = new MyDbhelper(context);
    }
    public void open(){
        db = dbhelper.getWritableDatabase();
    }
    public long insertRow(DtoService dtoService){
        ContentValues val = new ContentValues();
        val.put(DtoService.colName,dtoService.getName());
        val.put(DtoService.colPrice,dtoService.getPrice());
        val.put(DtoService.colImg,dtoService.getImg());
        val.put(DtoService.colCategories_id,dtoService.getCategories_id());

        long res = db.insert(DtoService.nameTable,null,val);
        return res;
    }
    public int deleteRow(DtoService dtoService){
        String[] check = new String[]{dtoService.getId()+""};
        int res = db.delete(DtoService.nameTable,"id = ?",check);
        return res;
    }
    public int updateRow(DtoService dtoService){
        ContentValues val = new ContentValues();
        val.put(DtoService.colName,dtoService.getName());
        val.put(DtoService.colPrice,dtoService.getPrice());
        val.put(DtoService.colImg,dtoService.getImg());
        val.put(DtoService.colCategories_id,dtoService.getCategories_id());
        String[] check = new String[]{dtoService.getId()+""};

        int res = db.update(DtoService.nameTable,val,"id = ?",check);
        return res;
    }
    public ArrayList<DtoService> selectAll(){
        ArrayList<DtoService> listService = new ArrayList<>();
        Cursor cs = db.query(DtoService.nameTable,null,null,null,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                DtoService dtoService = new DtoService();
                dtoService.setId(cs.getInt(0));
                dtoService.setName(cs.getString(1));
                dtoService.setPrice(cs.getFloat(2));
                dtoService.setCategories_id(cs.getInt(3));
                dtoService.setImg(cs.getString(4));
                listService.add(dtoService);
                cs.moveToNext();
            }
        }
        return listService;
    }
    public DtoService getDtoSeriveById (int idService){
        DtoService dtoService = new DtoService();
        String where = "id = ?";
        String[] whererArgs = {idService+""};
        String[] select = new String[]{"*"};
        Cursor cs  =db.query(DtoService.nameTable,select,where,whererArgs,null,null,null);
        if(cs.moveToFirst()){
            dtoService.setId(cs.getInt(0));
            dtoService.setName(cs.getString(1));
            dtoService.setPrice(cs.getFloat(2));
            dtoService.setCategories_id(cs.getInt(3));
            dtoService.setImg(cs.getString(4));
            cs.moveToNext();
        }
        return dtoService;
    }
    public DtoService getDtoSeriveById2 (int idService){
        DtoService dtoService = new DtoService();
        String select = "select id, name,price,categories_id,img from tbServices where id = "+idService;
        Cursor cs = db.rawQuery(select, null);
        if(cs.moveToFirst()){
            dtoService.setId(cs.getInt(0));
            dtoService.setName(cs.getString(1));
            dtoService.setPrice(cs.getFloat(2));
            dtoService.setCategories_id(cs.getInt(3));
            dtoService.setImg(cs.getString(4));
            cs.moveToNext();
        }
        return dtoService;
    }
}
