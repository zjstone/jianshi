package com.howietian.jianshi.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 83624 on 2017/5/11.
 */

/**
 * SQLiteDatabase 的一个辅助类，对数据库进行管理
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "jianshi.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_USER = "users";
    public static final String TABLE_UNIT = "units";
    public static final String TABLE_SCADA = "scadas";
    public static final String TABLE_PLC = "plcs";
    public static final String TABLE_OTHER = "others";
    public static final String TAG = "Database";


    private static final String USER_CREATE_SQL = "create table "
            + TABLE_USER
            + " ("
            + "id integer primary key autoincrement,"
            + "name text not null,"
            + "id_num text not null,"
            + "phone text not null,"
            + "pwd text not null,"
            + "email text not null,"
            + "workspace text not null"
            + ");";
    /**
     * 感觉像日了狗
     * group 是关键字，不能做为字段名
     */
    private static final String UNIT_CREATE_SQL = "create table "
            + TABLE_UNIT
            + " ("
            + "id integer primary key autoincrement,"
            + "name text not null,"
            + "_group text not null,"
            + "brief text not null,"
            + "pName text not null,"
            + "position text not null,"
            + "title text not null,"
            + "education text not null,"
            + "phoneNum text not null"
            + ");";
    private static final String SACDA_CREATE_SQL = "create table "
            + TABLE_SCADA + "( id integer primary key autoincrement, "
            + "uid int  not null,"
            + "sunit varchar(15) ,"
            + "sname varchar(15),"
            + "sposition varchar(15),"
            + "sphone text,"
            + "st_os_type int,"
            + "st_os_version varchar(15),"
            + "st_os_isTrue int,"
            + "st_os_upTime varchar(15),"
            + "st_os_upType int,"
            + "st_os_upBrief text,"
            + "st_s_company int,"
            + "st_s_brand varchar(15),"
            + "st_s_version varchar(15),"
            + "st_s_isBorder int,"
            + "st_s_isTrue int,"
            + "st_s_upTime varchar(15),"
            + "st_s_upType int,"
            + "st_s_upBrief text,"
            + "st_v_isInstall int,"
            + "st_v_company int,"
            + "st_v_brand varchar(15),"
            + "st_v_version varchar(15),"
            + "se_h_company int,"
            + "se_h_brand varchar(15),"
            + "se_h_version varchar(15),"
            + "se_h_isBorder int,"
            + "se_h_buyTime varchar(15),"
            + "se_os_type int,"
            + "se_os_brand varchar(15),"
            + "se_os_isTrue int,"
            + "se_os_upTime varchar(15),"
            + "se_os_upType int,"
            + "se_os_upBrief text,"
            + "se_db_company int,"
            + "se_db_name int,"
            + "se_db_version varchar(15),"
            + "se_db_isBorder int,"
            + "se_db_isTrue int,"
            + "se_db_upTime varchar(15),"
            + "se_db_upType int ,"
            + "se_db_brief text,"
            + "se_v_isInstall int ,"
            + "se_v_company int,"
            + "se_v_brand varchar(15),"
            + "se_v_version varchar(15),"
            + "se_o_brief text,"
            + "sw_h_company int,"
            + "sw_h_name varchar(15),"
            + "sw_h_type varchar(15),"
            + "sw_h_isBorder int,"
            + "sw_s_protocol varchar(20),"
            + "net_hu_isConnect int,"
            + "net_hu_isDefence int,"
            + "net_vpn varchar(15),"
            + "net_firewall varchar(15),"
            + "net_gFirewall varchar(15),"
            + "net_ids varchar(15),"
            + "net_ips varchar(15),"
            + "net_other varchar(15),"
            + "net_office_isConnect int,"
            + "net_office_isDefence int,"
            + "net_office_firewall varchar(15),"
            + "net_office_otherway varchar(20),"
            + "net_office_software text,"
            + "net_p_isConnect int,"
            + "net_p_connectType int,"
            + "net_p_type varchar(15),"
            + "net_r_connectType int,"
            + "net_r_isConnect int,"
            + "net_r_type varchar(15),"
            + "net_o_brief text,"
            + "other_brief text"
            + ");";
    private static final String IPS_CREATE_SQL = "create table "
            + TABLE_PLC + " (id integer primary key autoincrement, "
            + "uid int not null,"
            + "sunit varchar(15),"
            + "sname varchar(15),"
            + "sposition varchar(15),"
            + "sphone text,"
            + "upOsType int ,"
            + "upOsBrand int,"
            + "upOsVersion varchar(15),"
            + "upOsIsTrue int,"
            + "upOsUpTime varchar(15),"
            + "upOsUpType int,"
            + "upOsBrief text,"
            + "upJCompany int,"
            + "upJBrand varchar(15),"
            + "upJVersion varchar(15),"
            + "upJIsBorder int,"
            + "upJIsTrue int,"
            + "upJUpTime varchar(15),"
            + "upJUpType int,"
            + "upJBrief text,"
            + "upZCompany int,"
            + "upZBrand varchar(15),"
            + "upZVersion varchar(15),"
            + "upZIsBorder int,"
            + "upZIsTrue int,"
            + "upZUpTime varchar(15),"
            + "upZUpType int,"
            + "upZBrief text,"
            + "upVIsInstall int,"
            + "upVCompany int,"
            + "upVBrand varchar(15),"
            + "upVVersion varchar(15),"
            + "upWIsInstall int,"
            + "upWCompany int,"
            + "upWBrand varchar(15),"
            + "upWVersion varchar(15),"
            + "upOBrief text,"
            + "downIOCompany int,"
            + "downIOName varchar(15),"
            + "downIOModle varchar(15),"
            + "seHCompany int,"
            + "seHBrand varchar(15),"
            + "seHVersion varchar(15),"
            + "seHIsBorder int,"
            + "seHBuyTime varchar(15),"
            + "seOsType int,"
            + "seOsBrand varchar(15),"
            + "seOsIsTrue int,"
            + "seOsUpType int,"
            + "seOsUpTime varchar(15),"
            + "seOsUpBrief text,"
            + "seDbCompany int,"
            + "seDbName varchar(15),"
            + "seDbVersion varchar(15),"
            + "seDbIsBorder int,"
            + "seDbIsTrue int,"
            + "seDbUpTime varchar(15),"
            + "seDbUpType int,"
            + "seDbBrief text,"
            + "seVIsInstall int,"
            + "seVCompany int,"
            + "seVBrand varchar(15),"
            + "seVVersion varchar(15),"
            + "seOBrief text,"
            + "swHCompany int,"
            + "swHName varchar(15),"
            + "swHType varchar(15),"
            + "swHIsBorder int,"
            + "swSProtocol text,"
            + "netHuIsConnect int,"
            + "netHuIsDefence int,"
            + "netVpn varchar(15),"
            + "netFireWall varchar(15),"
            + "netGFireWall varchar(15),"
            + "netIds varchar(15),"
            + "netIps varchar(15),"
            + "netOther varchar(15),"
            + "netOfficeIsConnect int,"
            + "netOfficeIsDefence int,"
            + "netOfficeFirewall varchar(15),"
            + "netOfficeOtherWay varchar(15),"
            + "netSIsConnect int,"
            + "netSIsDefence int,"
            + "netSVpn varchar(15),"
            + "netSFireWall varchar(15),"
            + "netSIds varchar(15),"
            + "netSIps varchar(15),"
            + "netSBrief text,"
            + "netPIsConnect int,"
            + "netPConnectType int,"
            + "netCloIsConnect int,"
            + "netCloConnectType int,"
            + "netCloBrand varchar(15),"
            + "netOBrief text,"
            + "otherBrief text"
            + ");";

    private static final String OTHER_CREATE_SQL = "create table "
            + TABLE_OTHER + " (id integer primary key autoincrement, "
            + "uid int not null,"
            + "sunit varchar(15),"
            + "sname varchar(15),"
            + "sposition varchar(15),"
            + "sphone text,"
            + "tHCompany int,"
            + "tHBrand varchar(15),"
            + "tHVersion varchar(15),"
            + "tHIsBorder int,"
            + "tHBuyTime varchar(15),"
            + "tOsType int,"
            + "tOsBrand varchar(15),"
            + "tOsIsTrue int,"
            + "tOsUpTime varchar(15),"
            + "tOsUpType int,"
            + "tOsUpBrief text,"
            + "tSCompany int,"
            + "tSBrand varchar(15),"
            + "tSVersion varchar(15),"
            + "tSIsBorder int,"
            + "tSIsTrue int,"
            + "tSUpTime varchar(15),"
            + "tSUpType int,"
            + "tSupBrief text,"
            + "seHCompany int,"
            + "seHBrand varchar(15),"
            + "seHVersion varchar(15),"
            + "seHIsBorder int,"
            + "seHBuyTime varchar(15),"
            + "seOsType int,"
            + "seOsBrand varchar(15),"
            + "seOsIsTrue int,"
            + "seOsUpType int,"
            + "seOsUpTime varchar(15),"
            + "seOsUpBrief text,"
            + "seSCompany int,"
            + "seSBrand varchar(15),"
            + "seSVersion varchar(15),"
            + "seSIsBorder int,"
            + "seSIsTrue int,"
            + "seSUpTime varchar(15),"
            + "seSUpType int,"
            + "seSUpBrief text,"
            + "outIsBorder int,"
            + "outIsDefence int,"
            + "outVpn varchar(15),"
            + "outFireWall varchar(15),"
            + "outIds varchar(15),"
            + "outIps varchar(15),"
            + "outOtherWay varchar(15),"
            + "outProtocol varchar(15),"
            + "outWireless int,"
            + "outIsWireless int,"
            + "outIsInnerZuNet int,"
            + "inIsDefence int,"
            + "inRouter varchar(15),"
            + "inSwitch varchar(15),"
            + "inIds varchar(15),"
            + "inOtherWay varchar(15)"
            + ");";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(USER_CREATE_SQL);
        sqLiteDatabase.execSQL(UNIT_CREATE_SQL);
        sqLiteDatabase.execSQL(SACDA_CREATE_SQL);
        sqLiteDatabase.execSQL(IPS_CREATE_SQL);
        sqLiteDatabase.execSQL(OTHER_CREATE_SQL);
        Log.e(TAG, "create");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.e(TAG, "upgrade");
    }
}
