package poly.edu.vn.dat_lich_kham_benh_online.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.SQL.MyDbhelper;

public class DaoDoctor {
    SQLiteDatabase db;
    MyDbhelper dbhelper;

    public DaoDoctor(Context context) {
        dbhelper = new MyDbhelper(context);
    }

    public void open() {
        db = dbhelper.getWritableDatabase();
    }

    public long insertRow(DtoDoctor dtoDoctor) {
        ContentValues val = new ContentValues();
        val.put(DtoDoctor.colUser_id, dtoDoctor.getUser_id());
        val.put(DtoDoctor.colBirthday, dtoDoctor.getBirthday());
        val.put(DtoDoctor.colService_id, dtoDoctor.getService_id());
        val.put(DtoDoctor.colRoom_id, dtoDoctor.getRoom_id());
        val.put(DtoDoctor.colDescription, dtoDoctor.getDescription());
        val.put(DtoDoctor.colTimeWork, dtoDoctor.getTimework_id());

        long res = db.insert(DtoDoctor.nameTable, null, val);
        return res;
    }

    public int deleteRow(DtoDoctor dtoDoctor) {
        String[] check = new String[]{dtoDoctor.getId() + ""};
        int res = db.delete(DtoDoctor.nameTable, "id = ?", check);
        return res;
    }

    public int updateRow(DtoDoctor dtoDoctor) {
        ContentValues val = new ContentValues();
        val.put(DtoDoctor.colUser_id, dtoDoctor.getUser_id());
        val.put(DtoDoctor.colBirthday, dtoDoctor.getBirthday());
        val.put(DtoDoctor.colService_id, dtoDoctor.getService_id());
        val.put(DtoDoctor.colRoom_id, dtoDoctor.getRoom_id());
        val.put(DtoDoctor.colDescription, dtoDoctor.getDescription());
        val.put(DtoDoctor.colTimeWork, dtoDoctor.getTimework_id());
        String[] check = new String[]{dtoDoctor.getId() + ""};

        int res = db.update(DtoDoctor.nameTable, val, "id = ?", check);
        return res;
    }
    public int updateDoctor(DtoDoctor dtoDoctor) {
        ContentValues val = new ContentValues();
        val.put(DtoDoctor.colBirthday, dtoDoctor.getBirthday());
        val.put(DtoDoctor.colService_id, dtoDoctor.getService_id());
        val.put(DtoDoctor.colRoom_id, dtoDoctor.getRoom_id());
        val.put(DtoDoctor.colDescription, dtoDoctor.getDescription());
        val.put(DtoDoctor.colTimeWork, dtoDoctor.getTimework_id());
        String[] check = new String[]{dtoDoctor.getId() + ""};

        int res = db.update(DtoDoctor.nameTable, val, "id = ?", check);
        return res;
    }
    public ArrayList<DtoDoctor> selectAll() {
        ArrayList<DtoDoctor> listDoctor = new ArrayList<>();
        Cursor cs = db.query(DtoDoctor.nameTable, null, null, null, null, null, null);
        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {
                DtoDoctor dtoDoctor = new DtoDoctor();
                dtoDoctor.setId(cs.getInt(0));
                dtoDoctor.setUser_id(cs.getInt(1));
                dtoDoctor.setBirthday(cs.getString(2));
                dtoDoctor.setService_id(cs.getInt(3));
                dtoDoctor.setRoom_id(cs.getInt(4));
                dtoDoctor.setDescription(cs.getString(5));
                dtoDoctor.setTimework_id(cs.getInt(6));
                listDoctor.add(dtoDoctor);
                cs.moveToNext();
            }
        }
        return listDoctor;
    }

    public DtoDoctor selectDtoDoctorById(int idDoctor) {
        DtoDoctor dtoDoctor = new DtoDoctor();
        String where = "id = ?";
        String[] whereArgs = {idDoctor+""};
        Cursor cs = db.query(DtoDoctor.nameTable, null, where, whereArgs, null, null, null);
        if (cs.moveToFirst()) {

                dtoDoctor.setId(cs.getInt(0));
                dtoDoctor.setUser_id(cs.getInt(1));
                dtoDoctor.setBirthday(cs.getString(2));
                dtoDoctor.setService_id(cs.getInt(3));
                dtoDoctor.setRoom_id(cs.getInt(4));
                dtoDoctor.setDescription(cs.getString(5));
                dtoDoctor.setTimework_id(cs.getInt(6));

        }
        return dtoDoctor;
    }
}
