package poly.edu.vn.dat_lich_kham_benh_online.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

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
        val.put(DtoFile.colCountry,dtoFile.getCountry());
        long res = db.insert(DtoFile.nameTable,null,val);
        return res;
    }
    public int deleteRow(DtoFile dtoFile){
        String[] check = new String[]{dtoFile.getId()+""};
        int res = db.delete(DtoFile.nameTable,"id = ?",check);
        return res;
    }
    public int updateRow(DtoFile dtoFile){
        ContentValues val = new ContentValues();
        val.put(DtoFile.colUserId,dtoFile.getUser_id());
        val.put(DtoFile.colBirthday,dtoFile.getBirthday());
        val.put(DtoFile.colCccd,dtoFile.getCccd());
        val.put(DtoFile.colBhyt,dtoFile.getBhyt());
        val.put(DtoFile.colJob,dtoFile.getJob());
        val.put(DtoFile.colEmail,dtoFile.getEmail());
        val.put(DtoFile.colAddress,dtoFile.getAddress());
        val.put(DtoFile.colCountry,dtoFile.getCountry());
        String[] check = new String[]{dtoFile.getId()+""};
        int res = db.update(DtoFile.nameTable,val,"id = ?",check);
        return res;
    }
    public ArrayList<DtoFile> selectAll(){
        ArrayList<DtoFile> listFile = new ArrayList<>();
        Cursor cs = db.query(DtoFile.nameTable,null,null,null,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                DtoFile dtoFile = new DtoFile();
                dtoFile.setId(cs.getInt(0));
                dtoFile.setUser_id(cs.getInt(1));
                dtoFile.setBirthday(cs.getString(2));
                dtoFile.setCccd(cs.getString(3));
                dtoFile.setCountry(cs.getString(4));
                dtoFile.setBhyt(cs.getString(5));
                dtoFile.setJob(cs.getString(6));
                dtoFile.setEmail(cs.getString(7));
                dtoFile.setCountry(cs.getString(8));

                listFile.add(dtoFile);
                cs.moveToNext();
            }
        }
        return listFile;
    }
    public DtoFile dtoFile(int idFile) {
        DtoFile dtoFile = new DtoFile();
        String where = "id = ?";
        String[] whereArgs = {idFile + ""};
        Cursor cs = db.query(DtoFile.nameTable, null, where, whereArgs, null, null, null);
        if (cs.moveToFirst()) {
            dtoFile.setId(cs.getInt(0));
            dtoFile.setUser_id(cs.getInt(1));
            dtoFile.setBirthday(cs.getString(2));
            dtoFile.setCccd(cs.getString(3));
            dtoFile.setCountry(cs.getString(4));
            dtoFile.setBhyt(cs.getString(5));
            dtoFile.setJob(cs.getString(6));
            dtoFile.setEmail(cs.getString(7));
            dtoFile.setCountry(cs.getString(8));
        }
        return dtoFile;
    }

    public boolean checkFileByIdAcount(int idAccount){
        String[] selectArgs = new String[]{idAccount+""};
        String select = "SELECT tbFile.id,tbFile.user_id,tbFile.birthday,tbFile.cccd,tbFile.country,tbFile.bhyt,tbFile.job,tbFile.email,tbFile.address from tbFile INNER JOIN  tbAccount on tbAccount.id = tbFile.user_id where tbAccount.id = ?";
        Cursor cs = db.rawQuery(select,selectArgs);
        if(cs.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }
    public DtoFile getDtoFileByIdAccount(int idAccount){
        DtoFile dtoFile = new DtoFile();
        String[] selectArgs = new String[]{idAccount+""};
        String select = "SELECT tbFile.id,tbFile.user_id,tbFile.birthday,tbFile.cccd,tbFile.country,tbFile.bhyt,tbFile.job,tbFile.email,tbFile.address from tbFile INNER JOIN  tbAccount on tbAccount.id = tbFile.user_id where tbAccount.id = ?";
        Cursor cs = db.rawQuery(select,selectArgs);
        if (cs.moveToFirst()) {
            dtoFile.setId(cs.getInt(0));
            dtoFile.setUser_id(cs.getInt(1));
            dtoFile.setBirthday(cs.getString(2));
            dtoFile.setCccd(cs.getString(3));
            dtoFile.setCountry(cs.getString(4));
            dtoFile.setBhyt(cs.getString(5));
            dtoFile.setJob(cs.getString(6));
            dtoFile.setEmail(cs.getString(7));
            dtoFile.setAddress(cs.getString(8));
        }
        return dtoFile;
    }
}
