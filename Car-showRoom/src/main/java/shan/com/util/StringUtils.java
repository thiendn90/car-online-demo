package shan.com.util;

/**
 * Created by ThienDN on 8/28/2015.
 */
public class StringUtils {
    public static String getServerKey(String key){
        try{
            return Constants.customerConfig.getString(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public  static String getValueKey(String key){
        try{
            StringBuilder builder = new StringBuilder();
            builder.append(getServerKey("server_customer_config"));
            builder.append(Constants.urlBundle.getString(key));
            return builder.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
