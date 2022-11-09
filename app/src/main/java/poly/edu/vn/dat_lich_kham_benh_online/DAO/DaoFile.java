package poly.edu.vn.dat_lich_kham_benh_online.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoFile;
import poly.edu.vn.dat_lich_kham_benh_online.SQL.MyDbhelper;

public class DaoFile {
    SQLiteDatabase db;
    MyDbhelper dbhelper;

    public DaoFile(Context context){
        dbhelper = new MyDbhelper(context);
    }
    public void open(){
        db = dbhelper.getWritableDatabase();
    }

    public long insertRow(DtoFile dtoFile){
        ContentValues val = new ContentValues();
        val.put(DtoFile.colUserId,dtoFile.getUser_id());
        val.put(DtoFile.colBirthday,dtoFile.getBirthday());
        val.put(DtoFile.colCccd,dtoFile.getCccd());
        val.put(DtoFile.colBhyt,dtoFile.getBhyt());
        val.put(DtoFile.colJob,dtoFile.getJob());
        val.put(DtoFile.colEmail,dtoFile.getEmail());
        val.put(DtoFile.colAddress,dtoFile.getAddress());

        long res = db.insert(DtoFile.nameTable,null,val);
        return res;
    }
    public int deleteRow(DtoFile dtoFile){
        String[] check = new String[]{dtoFile.getId()+""};
        int res = db.delete(DtoFile.nameTable,"id = ?",check);
        return res;
    }



}
