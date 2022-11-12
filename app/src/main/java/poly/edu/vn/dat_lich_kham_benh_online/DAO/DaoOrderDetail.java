package poly.edu.vn.dat_lich_kham_benh_online.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoOrderDetail;
import poly.edu.vn.dat_lich_kham_benh_online.SQL.MyDbhelper;

public class DaoOrderDetail {
    SQLiteDatabase db;
    MyDbhelper dbhelper;

    public DaoOrderDetail(Context context){
        dbhelper = new MyDbhelper(context);
    }
    public void open(){
        db = dbhelper.getWritableDatabase();
    }

    public long insertRow(DtoOrderDetail dtoOrderDetail){
        ContentValues val = new ContentValues();
        val.put(DtoOrderDetail.colOrderId,dtoOrderDetail.getOrder_id());
        val.put(DtoOrderDetail.colorderDoctorId,dtoOrderDetail.getOrderDoctor_id());

        long res = db.insert(DtoOrderDetail.nameTable,null,val);
        return res;
    }
    public ArrayList<DtoOrderDetail> selectAll(){
        ArrayList<DtoOrderDetail> list = new ArrayList<>();
        Cursor cs  =db.query(DtoOrderDetail.nameTable,null,null,null,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                DtoOrderDetail dtoOrderDetail = new DtoOrderDetail();
                dtoOrderDetail.setOrder_id(cs.getInt(0));
                dtoOrderDetail.setOrderDoctor_id(cs.getInt(1));

                list.add(dtoOrderDetail);
                cs.moveToNext();
            }
        }
        return list;
    }

    public ArrayList<DtoOrderDetail> selectAllByIdFile(int idFile){
        ArrayList<DtoOrderDetail> list = new ArrayList<>();
        String[] whereArgs = new String[]{idFile+""};
        String select = "select order_id from tbOrderDetail inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id where tbOrderDoctor.file_id = ? group by order_id";
        Cursor cs  =db.rawQuery(select,whereArgs);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                DtoOrderDetail dtoOrderDetail = new DtoOrderDetail();
                dtoOrderDetail.setOrder_id(cs.getInt(0));
                list.add(dtoOrderDetail);
                cs.moveToNext();
            }
        }
        return list;
    }

}
