package poly.edu.vn.dat_lich_kham_benh_online.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoTimeWork;
import poly.edu.vn.dat_lich_kham_benh_online.SQL.MyDbhelper;

public class DaoTimeWork {
    SQLiteDatabase db;
    MyDbhelper dbhelper;

    public DaoTimeWork(Context context){
        dbhelper = new MyDbhelper(context);
    }
    public void open(){
        db = dbhelper.getWritableDatabase();
    }

    public long insertRow(DtoTimeWork dtoTimeWork){
        ContentValues val = new ContentValues();
        val.put(DtoTimeWork.colSession,dtoTimeWork.getSession());

        long res = db.insert(DtoTimeWork.nameTable,null,val);
        return res;
    }
    public int deleteRow(DtoTimeWork dtoTimeWork){
        String[] check = new String[]{dtoTimeWork.getId()+""};
        int res  =db.delete(DtoTimeWork.nameTable,"id = ?",check);
        return res;
    }
    public int updateRow(DtoTimeWork dtoTimeWork){
        ContentValues val = new ContentValues();
        val.put(DtoTimeWork.colSession,dtoTimeWork.getSession());
        String[] check = new String[]{dtoTimeWork.getId()+""};

        int res = db.update(DtoTimeWork.nameTable,val,"id = ?",check);
        return res;
    }

    public ArrayList<DtoTimeWork> selectAll(){
        ArrayList<DtoTimeWork> listTimeWork =new ArrayList<>();
        Cursor cs = db.query(DtoTimeWork.nameTable,null,null,null,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                DtoTimeWork dtoTimeWork = new DtoTimeWork();
                dtoTimeWork.setId(cs.getInt(0));
                dtoTimeWork.setSession(cs.getString(1));

                listTimeWork.add(dtoTimeWork);
                cs.moveToNext();
            }
        }
        return listTimeWork;
    }

    public DtoTimeWork getDtoTimeWork(int id){
        DtoTimeWork dtoTimeWork = new DtoTimeWork();
        String where = "id = ?";
        String[] whereArgs = {id+""};
        Cursor cs  =db.query(DtoTimeWork.nameTable,null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
            dtoTimeWork.setId(cs.getInt(0));
            dtoTimeWork.setSession(cs.getString(1));
        }
        return dtoTimeWork;
    }
}
