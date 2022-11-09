package poly.edu.vn.dat_lich_kham_benh_online.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbhelper extends SQLiteOpenHelper {
    public static final String NAME ="Kham_benh_online";
    public static final int VERSION = 27;
    public MyDbhelper(Context context){
        super(context,NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlAccount = "CREATE TABLE  tbAccount  (id  INTEGER NOT NULL,userName  TEXT NOT NULL,passWord  TEXT NOT NULL,phone  TEXT NOT NULL,fullName  TEXT NOT NULL,gender  TEXT NOT NULL,role TEXT NOT NULL,img  TEXT ,PRIMARY KEY( ID  AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sqlAccount);
        //Tài khoản ADMIN
        String sqlAdmin = "INSERT INTO tbAccount VALUES(3,'Admin','Admin','012313','My Admin','Nam','Admin',null)";
        sqLiteDatabase.execSQL(sqlAdmin);

        String sqlDoctor = "CREATE TABLE tbDoctor (id INTEGER NOT NULL,user_id INTEGER NOT NULL REFERENCES tbAccount(id),birthday TEXT NOT NULL, service_id INTEGER NOT NULL REFERENCES tbServices(id),room_id INTEGER NOT NULL REFERENCES tbRooms(id),description TEXT, timework_id INTEGER NOT NULL REFERENCES tbTimeWork(id),PRIMARY KEY(id AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sqlDoctor);

        String sqlRooms = "CREATE TABLE tbRooms (id INTEGER NOT NULL,name TEXT NOT NULL,location TEXT,PRIMARY KEY(id AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sqlRooms);

        String sqlServices = "CREATE TABLE tbServices (id INTEGER NOT NULL,name TEXT NOT NULL,price float NOT NULL,categories_id INTEGER NOT NULL REFERENCES tbCategories(id),img TEXT ,PRIMARY KEY(id AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sqlServices);

        String sqlCategories = "CREATE TABLE tbCategories (id INTEGER NOT NULL,name INTEGER NOT NULL,PRIMARY KEY(id AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sqlCategories);

        String sqlFile = "CREATE TABLE tbFile (id INTEGER NOT NULL,user_id INTEGER REFERENCES tbUser(id),birthday TEXT NOT NULL,cccd TEXT NOT NULL,country TEXT NOT NULL,bhyt TEXT NOT NULL,job TEXT NOT NULL,email TEXT NOT NULL,address TEXT NOT NULL,PRIMARY KEY( id  AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sqlFile);

        String sqlTimeWork = "CREATE TABLE  tbTimeWork  (id  INTEGER NOT NULL,session  TEXT NOT NULL,PRIMARY KEY( id  AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sqlTimeWork);

        String sqlTimeWorkDetail = "CREATE TABLE  tbTimeWorkDetail  (id INTEGER NOT NULL,timework_id  INTEGER NOT NULL REFERENCES tbTimeWork(id),time TEXT NOT NULL,PRIMARY KEY( id  AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sqlTimeWorkDetail);

        String sqlOrders = "CREATE TABLE tbOrders (id INTEGER NOT NULL,file_id INTEGER NOT NULL REFERENCES tbFile(id),order_time TEXT NOT NULL,order_date TEXT NOT NULL,start_time TEXT NOT NULL,PRIMARY KEY(id AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sqlOrders);

        String sqlOrderDetail = "CREATE TABLE tbOrderDetail (order_id INTEGER NOT NULL REFERENCES tbOrders(id),doctor_id INTEGER NOT NULL REFERENCES tbDoctor(id));";
        sqLiteDatabase.execSQL(sqlOrderDetail);

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbAccount");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbDoctor");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbRooms");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbServices");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbCategories");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbTimeWork");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbTimeWorkDetail");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbOrders");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbOrderDetail");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbFile");
        onCreate(sqLiteDatabase);
    }
}
