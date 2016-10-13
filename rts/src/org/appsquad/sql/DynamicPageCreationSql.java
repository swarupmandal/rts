package org.appsquad.sql;

public class DynamicPageCreationSql {
   public static final String FETCHALLPAGEDETAILSSQL = "select * from rts_page_menus where is_delete = 'N' ORDER BY menus_id ";
   public static final String COUNTWRTUSERIDANDMENUSIDSQL = "select count(*) from rts_page_user_mapper where role_id = ? and menus_id = ? ";
   public static final String COUNTWRTUSERIDANDSQL = "select count(*) from rts_page_user_mapper where role_id = ? ";
   public static final String DELETEAPPPAGEWRTUSERSERIALID = "delete from rts_page_user_mapper where role_id = ? ";
   public static final String INSERTINTOPAGEUSERMAPPERTABLE = "insert into rts_page_user_mapper (role_id,menus_id) values (?,?) ";
}
 