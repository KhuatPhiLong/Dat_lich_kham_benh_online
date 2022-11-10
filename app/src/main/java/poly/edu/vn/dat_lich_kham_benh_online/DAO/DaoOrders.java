package poly.edu.vn.dat_lich_kham_benh_online.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoOrders;
import poly.edu.vn.dat_lich_kham_benh_online.SQL.MyDbhelper;

public class DaoOrders {
    SQLiteDatabase db;
    MyDbhelper dbhelper;
    public DaoOrders(Context context){
        dbhelper = new MyDbhelper(context);
    }
    public void open(){
        db = dbhelper.getWritableDatabase();
    }
    public long inserRow(DtoOrders dtoOrders){
        ContentValues val = new ContentValues();
        val.put(DtoOrders.colFileId,dtoOrders.getFile_id());
        val.put(DtoOrders.colOrderDate,dtoOrders.getOrder_date());
        val.put(DtoOrders.colOrderTime,dtoOrders.getOrder_time());

        long res =db.insert(DtoOrders.nameTable,null,val);
        return res;
    }
    public ArrayList<DtoOrders> selectAll(){
        ArrayList<DtoOrders> listOrders = new ArrayList<>();
        Cursor cs = db.query(DtoOrders.nameTable,null,null,null,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.moveToNext()){
                DtoOrders dtoOrders = new DtoOrders();
                dtoOrders.setId(cs.getInt(0));
                dtoOrders.setOrder_time(cs.getString(1));
                dtoOrders.setOrder_date(cs.getString(2));

                listOrders.add(dtoOrders);
                cs.moveToNext();
            }
        }
        return listOrders;
    }
}