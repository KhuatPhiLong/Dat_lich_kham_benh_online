package poly.edu.vn.dat_lich_kham_benh_online.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoCategories;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.SQL.MyDbhelper;

public class DaoCategories {
    SQLiteDatabase db;
    MyDbhelper dbhelper;
    public DaoCategories(Context context){
        dbhelper = new MyDbhelper(context);
    }
    public void open(){
        db = dbhelper.getWritableDatabase();
    }

    public long insertRow(DtoCategories dtoCategories){
        ContentValues val = new ContentValues();
        val.put(DtoCategories.colName,dtoCategories.getName());

        long res = db.insert(DtoCategories.nameTable,null,val);
        return res;
    }
    public int deleteRow(DtoCategories dtoCategories){
        String[] check = new String[]{dtoCategories.getId()+""};
        int res = db.delete(DtoCategories.nameTable,"id = ?",check);
        return res;
    }
    public int updateRow(DtoCategories dtoCategories){
        ContentValues val = new ContentValues();
        val.put(DtoCategories.colName,dtoCategories.getName());
        String[] check = new String[]{dtoCategories.getId()+""};
        int res = db.update(DtoCategories.nameTable,val,"id = ?",check);
        return res;
    }
    public ArrayList<DtoCategories> selectAll(){
        ArrayList<DtoCategories> listCategories = new ArrayList<>();
        Cursor cs = db.query(DtoCategories.nameTable,null,null,null,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                DtoCategories dtoCategories = new DtoCategories();
                dtoCategories.setId(cs.getInt(0));
                dtoCategories.setName(cs.getString(1));

                listCategories.add(dtoCategories);
                cs.moveToNext();
            }
        }
        return listCategories;
    }
    public DtoCategories getDtoCategories(int id){
        DtoCategories dtoCategories = new DtoCategories();
        String where = "id = ?";
        String[] whereArgs = {id+""};
        Cursor cs = db.query(DtoCategories.nameTable,null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
           dtoCategories.setId(cs.getInt(0));
           dtoCategories.setName(cs.getString(1));
            cs.moveToNext();
        }
        return dtoCategories;
    }
}
