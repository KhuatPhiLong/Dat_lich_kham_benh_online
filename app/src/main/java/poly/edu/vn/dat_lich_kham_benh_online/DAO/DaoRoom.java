package poly.edu.vn.dat_lich_kham_benh_online.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoRoom;
import poly.edu.vn.dat_lich_kham_benh_online.SQL.MyDbhelper;

public class DaoRoom {
    SQLiteDatabase db;
    MyDbhelper dbhelper;

    public DaoRoom(Context context){
        dbhelper = new MyDbhelper(context);
    }
    public void open(){
        db  =dbhelper.getWritableDatabase();
    }
    public long insertRow(DtoRoom dtoRoom){
        ContentValues val = new ContentValues();
        val.put(DtoRoom.colName,dtoRoom.getName());
        val.put(DtoRoom.colLocation,dtoRoom.getLocation());

        long res = db.insert(DtoRoom.nameTable,null,val);
        return res;
    }
    public int delteRow(DtoRoom dtoRoom){
        String[] check = new String[]{dtoRoom.getId()+""};
        int res = db.delete(DtoRoom.nameTable,"id = ?",check);
        return res;
    }
    public int updateRow(DtoRoom dtoRoom){
        ContentValues val = new ContentValues();
        val.put(DtoRoom.colName,dtoRoom.getName());
        val.put(DtoRoom.colLocation,dtoRoom.getLocation());
        String[] check = new String[]{dtoRoom.getId()+""};

        int res = db.update(DtoRoom.nameTable,val,"id = ?",check);
        return res;
    }
    public ArrayList<DtoRoom> selectAll(){
        ArrayList<DtoRoom> list = new ArrayList<>();
        Cursor cs = db.query(DtoRoom.nameTable,null,null,null,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                DtoRoom dtoRoom = new DtoRoom();
                dtoRoom.setId(cs.getInt(0));
                dtoRoom.setName(cs.getString(1));
                dtoRoom.setLocation(cs.getString(2));

                list.add(dtoRoom);
                cs.moveToNext();
            }
        }
        return list;
    }
    public DtoRoom getDtoRoom(int id){
        DtoRoom dtoRoom = new DtoRoom();
        String where = "id = ?";
        String[] whereArgs = {id+""};
        Cursor cs = db.query(DtoRoom.nameTable,null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
            dtoRoom.setId(cs.getInt(0));
            dtoRoom.setName(cs.getString(1));
            dtoRoom.setLocation(cs.getString(2));
            cs.moveToNext();
        }
        return dtoRoom;
    }
}
