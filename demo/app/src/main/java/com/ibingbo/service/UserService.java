package com.ibingbo.service;

import com.ibingbo.utils.Http;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangbingbing on 16/7/30.
 */
public class UserService {

    final String HOST = "http://10.0.2.2:8002";
    final String API_GET_USER_LIST = "/user/list";
    final String API_GET_USER = "/user/get";
    final String API_QUERY_USER = "/user/query";

    private JSONObject result;

    public JSONObject getUserById(int id){
        try {
            String url = HOST + API_GET_USER + "?id=" + id;
            String data = Http.get(url);
            result = new JSONObject(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public JSONObject getUserList(){
        try{
            String url = HOST+API_GET_USER_LIST;
            String data = Http.get(url);
            result=new JSONObject(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public Boolean hasUser(Map<String,String> params){
        boolean exists = false;
        try{
            if(params !=null){
                StringBuilder sb = new StringBuilder();
                for(Map.Entry<String,String> entry : params.entrySet()){
                    sb.append(entry.getKey() + "=" + URLEncoder.encode(entry.getValue(),"UTF-8") +"&");
                }
                String p = sb.toString();
                p = p.substring(0,p.length()-1);
                String url = HOST + API_QUERY_USER;
                String result = Http.post(url,p);
                JSONObject user = new JSONObject(result);
                JSONArray users = user.getJSONArray("data");
                if(users.length()>0){
                    exists =  true;
                }else {
                    exists =  false;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return exists;
    }
}