package poly.edu.vn.dat_lich_kham_benh_online.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoOrderDetail;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoOrderDoctor;
import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoOrders;
import poly.edu.vn.dat_lich_kham_benh_online.SQL.MyDbhelper;

public class DaoOrderDoctor {
    SQLiteDatabase db;
    MyDbhelper dbhelper;

    public DaoOrderDoctor(Context context) {
        dbhelper = new MyDbhelper(context);
    }

    public void open() {
        db = dbhelper.getWritableDatabase();
    }

    public long insertRow(DtoOrderDoctor dtoOrderDoctor) {
        ContentValues val = new ContentValues();
        val.put(DtoOrderDoctor.colDoctor_id, dtoOrderDoctor.getDoctor_id());
        val.put(DtoOrderDoctor.colTotal, dtoOrderDoctor.getTotal());
        val.put(DtoOrderDoctor.colStart_date, dtoOrderDoctor.getStart_date());
        val.put(DtoOrderDoctor.colFile_id, dtoOrderDoctor.getFile_id());
        val.put(DtoOrderDoctor.colStart_time, dtoOrderDoctor.getStart_time());

        long res = db.insert(DtoOrderDoctor.nameTable, null, val);
        return res;
    }

    public int deleteRow(DtoOrderDoctor dtoOrderDoctor) {
        String[] check = new String[]{dtoOrderDoctor.getId() + ""};
        int res = db.delete(DtoOrderDoctor.nameTable, "id = ?", check);
        return res;
    }

    public ArrayList<DtoOrderDoctor> selectAll() {
        ArrayList<DtoOrderDoctor> list = new ArrayList<>();
        Cursor cs = db.query(DtoOrderDoctor.nameTable, null, null, null, null, null, null);
        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {
                DtoOrderDoctor dtoOrderDoctor = new DtoOrderDoctor();
                dtoOrderDoctor.setId(cs.getInt(0));
                dtoOrderDoctor.setFile_id(cs.getInt(1));
                dtoOrderDoctor.setDoctor_id(cs.getInt(2));
                dtoOrderDoctor.setStart_time(cs.getString(3));
                dtoOrderDoctor.setStart_date(cs.getString(4));
                dtoOrderDoctor.setTotal(cs.getFloat(5));
                list.add(dtoOrderDoctor);
                cs.moveToNext();
            }
        }
        return list;
    }

    public DtoOrderDoctor getDtoOrderDoctor() {
        DtoOrderDoctor dtoOrderDoctor = new DtoOrderDoctor();
        String select = "SELECT * FROM tbOrderDoctor ORDER by id DESC LIMIT 1";
        Cursor cs = db.rawQuery(select, null);
        if (cs.moveToFirst()) {
            dtoOrderDoctor.setId(cs.getInt(0));
            dtoOrderDoctor.setFile_id(cs.getInt(1));
            dtoOrderDoctor.setDoctor_id(cs.getInt(2));
            dtoOrderDoctor.setStart_time(cs.getString(3));
            dtoOrderDoctor.setStart_date(cs.getString(4));
            dtoOrderDoctor.setTotal(cs.getFloat(5));
        }
        return dtoOrderDoctor;
    }

    public ArrayList<DtoOrderDoctor> getDtoOrderDoctorByIdOrderDetail(int idOrderDetail) {
        ArrayList<DtoOrderDoctor> list = new ArrayList<>();
        String[] whereArgs = new String[]{idOrderDetail + ""};
        String select = "select tbOrderDoctor.id,tbOrderDoctor.file_id,tbOrderDoctor.doctor_id,tbOrderDoctor.start_date,tbOrderDoctor.start_time,tbOrderDoctor.total  from tbOrderDoctor inner join tbOrderDetail on tbOrderDoctor.id = tbOrderDetail.orderDoctor_id where tbOrderDetail.order_id = ?";
        Cursor cs = db.rawQuery(select, whereArgs);
        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {
                DtoOrderDoctor dtoOrderDoctor = new DtoOrderDoctor();
                dtoOrderDoctor.setId(cs.getInt(0));
                dtoOrderDoctor.setFile_id(cs.getInt(1));
                dtoOrderDoctor.setDoctor_id(cs.getInt(2));
                dtoOrderDoctor.setStart_time(cs.getString(3));
                dtoOrderDoctor.setStart_date(cs.getString(4));
                dtoOrderDoctor.setTotal(cs.getFloat(5));

                list.add(dtoOrderDoctor);
                cs.moveToNext();
            }
        }
        return list;
    }
}
