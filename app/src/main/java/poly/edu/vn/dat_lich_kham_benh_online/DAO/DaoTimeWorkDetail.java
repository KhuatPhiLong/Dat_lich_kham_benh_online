package poly.edu.vn.dat_lich_kham_benh_online.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.ADAPTER.DoctorAdapter;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoTimeWork;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoTimeWorkDetail;
import poly.edu.vn.dat_lich_kham_benh_online.SQL.MyDbhelper;

public class DaoTimeWorkDetail {
    SQLiteDatabase db;
    MyDbhelper dbhelper;

    public DaoTimeWorkDetail(Context context){
        dbhelper = new MyDbhelper(context);
    }
    public void open(){
        db = dbhelper.getWritableDatabase();
    }

    public long insertRow(DtoTimeWorkDetail dtoTimeWorkDetail){
        ContentValues val = new ContentValues();
        val.put(DtoTimeWorkDetail.colTime,dtoTimeWorkDetail.getTime());
        val.put(DtoTimeWorkDetail.colTimework_id,dtoTimeWorkDetail.getTimework_id());

        long res  =db.insert(DtoTimeWorkDetail.nameTable,null,val);
        return res;
    }
    public int deleteRow(DtoTimeWorkDetail dtoTimeWorkDetail){
        String[] check = new String[]{dtoTimeWorkDetail.getId()+""};
        int res = db.delete(DtoTimeWorkDetail.nameTable,"id = ?",check);
        return res;
    }
    public int updateRow(DtoTimeWorkDetail dtoTimeWorkDetail){
        ContentValues val = new ContentValues();
        val.put(DtoTimeWorkDetail.colTime,dtoTimeWorkDetail.getTime());
        val.put(DtoTimeWorkDetail.colTimework_id,dtoTimeWorkDetail.getTimework_id());
        String[] check = new String[]{dtoTimeWorkDetail.getId()+""};

        int res = db.update(DtoTimeWorkDetail.nameTable,val,"id = ?",check);
        return res;
    }

    public ArrayList<DtoTimeWorkDetail> selectAll(){
        ArrayList<DtoTimeWorkDetail> listTimeWorkDetail = new ArrayList<>();
        Cursor cs = db.query(DtoTimeWorkDetail.nameTable,null,null,null,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                DtoTimeWorkDetail dtoTimeWorkDetail = new DtoTimeWorkDetail();
                dtoTimeWorkDetail.setId(cs.getInt(0));
                dtoTimeWorkDetail.setTimework_id(cs.getInt(1));
                dtoTimeWorkDetail.setTime(cs.getString(2));
                listTimeWorkDetail.add(dtoTimeWorkDetail);
                cs.moveToNext();
            }
        }
        return listTimeWorkDetail;
    }

    public ArrayList<DtoTimeWorkDetail> selectTimeWorkDetailByTimeWorkId(int idTimeWork){
        ArrayList<DtoTimeWorkDetail> listTimeWorkDetail = new ArrayList<>();
        String where = "timework_id = ?";
        String[] whereArgs = new String[]{idTimeWork+""};
        Cursor cs = db.query(DtoTimeWorkDetail.nameTable,null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                DtoTimeWorkDetail dtoTimeWorkDetail = new DtoTimeWorkDetail();
                dtoTimeWorkDetail.setId(cs.getInt(0));
                dtoTimeWorkDetail.setTimework_id(cs.getInt(1));
                dtoTimeWorkDetail.setTime(cs.getString(2));

                listTimeWorkDetail.add(dtoTimeWorkDetail);
                cs.moveToNext();
            }
        }
        return listTimeWorkDetail;
    }
}
