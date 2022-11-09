package poly.edu.vn.dat_lich_kham_benh_online.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import poly.edu.vn.dat_lich_kham_benh_online.DTO.DtoAccount;
import poly.edu.vn.dat_lich_kham_benh_online.SQL.MyDbhelper;

public class DaoAccount {
    SQLiteDatabase db;
    MyDbhelper dbhelper;
    SharedPreferences sharedPreferences;

    public DaoAccount(Context context){
        dbhelper = new MyDbhelper(context);
        sharedPreferences = context.getSharedPreferences("getIdUser",Context.MODE_PRIVATE);
    }
    public void open(){
        db = dbhelper.getWritableDatabase();
    }

    public long insertRow(DtoAccount objUser){
        ContentValues val = new ContentValues();
        val.put(DtoAccount.colUserName,objUser.getUserName());
        val.put(DtoAccount.colPassWord,objUser.getPassWord());
        val.put(DtoAccount.colPhone,objUser.getPhone());
        val.put(DtoAccount.colFullName, objUser.getFullName());
        val.put(DtoAccount.colGender, objUser.getGender());
        val.put(DtoAccount.colRole,objUser.getRole());
        val.put(DtoAccount.colImg, objUser.getImg());

        long res = db.insert(DtoAccount.nameTable,null,val);
        return res;
    }
    public int deleteRow(DtoAccount objUser){
        String[] check = new String[]{objUser.getId()+""};
        int res  =db.delete(DtoAccount.nameTable,"id = ?",check);
        return res;
    }
    public int updateRow(DtoAccount objUser){
        ContentValues val = new ContentValues();
        val.put(DtoAccount.colUserName,objUser.getUserName());
        val.put(DtoAccount.colPassWord,objUser.getPassWord());
        val.put(DtoAccount.colPhone,objUser.getPhone());
        val.put(DtoAccount.colFullName, objUser.getFullName());
        val.put(DtoAccount.colGender, objUser.getGender());
        val.put(DtoAccount.colRole,objUser.getRole());
        val.put(DtoAccount.colImg, objUser.getImg());
        String[] check = new String[]{objUser.getId()+""};

        int res  =db.update(DtoAccount.nameTable,val,"id = ?",check);
        return res;
    }
    public boolean checkLogin(String userName,String passWord){
        String where = "userName = ? and passWord = ?";
        String[] whereArgs ={userName.trim(),passWord.trim()};
        Cursor cs = db.query(DtoAccount.nameTable,null,where,whereArgs,null,null,null);
        if(cs.getCount()>0){
            cs.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("idUser",cs.getInt(0));
            editor.commit();
            return true;
        }
        else{
            return false;
        }
    }
    public DtoAccount getDtoAccount(int  idUser){
        DtoAccount dtoUser = new DtoAccount();
        String where = "id = ?";
        String[] whereArgs ={idUser+""};
        Cursor cs = db.query(DtoAccount.nameTable,null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
            dtoUser.setId(cs.getInt(0));
            dtoUser.setUserName(cs.getString(1));
            dtoUser.setPassWord(cs.getString(2));
            dtoUser.setPhone(cs.getString(3));
            dtoUser.setFullName(cs.getString(4));
            dtoUser.setGender(cs.getString(5));
            dtoUser.setRole(cs.getString(6));
            dtoUser.setImg(cs.getString(7));

            cs.moveToNext();
        }
        cs.close();
        return dtoUser;
    }
    public DtoAccount getDtoUserTopIdOne(){
        DtoAccount dtoUser = new DtoAccount();
        String orderBy = "id desc";
        Cursor cs = db.query(DtoAccount.nameTable,null,null,null,null,null,orderBy,1+"");
        if(cs.moveToFirst()){
            dtoUser.setId(cs.getInt(0));
            dtoUser.setUserName(cs.getString(1));
            dtoUser.setPassWord(cs.getString(2));
            dtoUser.setPhone(cs.getString(3));
            dtoUser.setFullName(cs.getString(4));
            dtoUser.setGender(cs.getString(5));
            dtoUser.setRole(cs.getString(6));
            dtoUser.setImg(cs.getString(7));

            cs.moveToNext();
        }
        cs.close();
        return dtoUser;
    }
}
