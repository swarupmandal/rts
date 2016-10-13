package org.appsquad.sql;

public class DynamicPageCreationSql {
   public static final String FETCHALLPAGEDETAILSSQL = "select * from rts_page_menus where is_delete = 'N' ";
   public static final String COUNTWRTUSERIDANDMENUSIDSQL = "select count(*) from rts_page_user_mapper where user_serial_id = ? and menus_id = ? ";
   public static final String COUNTWRTUSERIDANDSQL = "select count(*) from rts_page_user_mapper where user_serial_id = ? ";
   public static final String DELETEAPPPAGEWRTUSERSERIALID = "delete from rts_page_user_mapper where user_serial_id = ? ";
   public static final String INSERTINTOPAGEUSERMAPPERTABLE = "insert into rts_page_user_mapper (user_serial_id,menus_id) values (?,?) ";
}
 