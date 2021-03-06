package com.ibingbo.service;

import com.ibingbo.models.User;
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

    private final String HOST = "http://10.0.2.2:8002";
    private final String API_GET_USER_LIST = "/user/list";
    private final String API_GET_USER = "/user/get";
    private final String API_QUERY_USER = "/user/query";
    private final String API_ADD_USER = "/user/add";
    private final String API_DELETE_USER = "/user/delete";
    private final String API_UPDATE_USER ="/user/update";


    private JSONObject result;

    public Boolean addUser(User user) {
        boolean success = true;
        try {
            StringBuilder sb = new StringBuilder();

            sb.append("name=" + URLEncoder.encode(user.getName(), "UTF-8") + "&").append("password=" + URLEncoder.encode(user.getPassword()) + "&").append("email=" + URLEncoder.encode(user.getEmail()));

            String p = sb.toString();

            String url = HOST + API_ADD_USER;
            String result = Http.post(url, p);
            JSONObject res = new JSONObject(result);

            if (res.getInt("errno") == 0 && res.getBoolean("data")) {
                success = true;
            } else {
                success = false;
            }
        } catch (Exception e) {
            success = false;
            e.printStackTrace();
        }
        return success;
    }

    public Boolean updateUser(User user) {
        boolean success = true;
        try {
            StringBuilder sb = new StringBuilder();

            sb.append("id="+user.getId()+"&name=" + URLEncoder.encode(user.getName(), "UTF-8") + "&").append("password=" + URLEncoder.encode(user.getPassword()) + "&").append("email=" + URLEncoder.encode(user.getEmail()));

            String p = sb.toString();

            String url = HOST + API_UPDATE_USER;
            String result = Http.post(url, p);
            JSONObject res = new JSONObject(result);

            if (res.getInt("errno") == 0 && res.getBoolean("data")) {
                success = true;
            } else {
                success = false;
            }
        } catch (Exception e) {
            success = false;
            e.printStackTrace();
        }
        return success;
    }

    public User getUserById(int id) {
        User user=null;
        try {
            String url = HOST + API_GET_USER + "?id=" + id;
            String data = Http.get(url);
            result = new JSONObject(data);
            user=new User(result.getJSONObject("data").getString("name"),result.getJSONObject("data").getString("password"),result.getJSONObject("data").getString("email"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public Boolean deleteUserById(int id) {
        boolean result = false;
        try {
            String url = HOST + API_DELETE_USER + "?id=" + id;
            String data = Http.get(url);
            JSONObject res = new JSONObject(data);
            if(res.getInt("errno") == 0 && res.getInt("data") == 1){
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public JSONObject getUserList() {
        try {
            String url = HOST + API_GET_USER_LIST;
            String data = Http.get(url);
            result = new JSONObject(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Boolean hasUser(Map<String, String> params) {
        boolean exists = false;
        try {
            if (params != null) {
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    sb.append(entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "UTF-8") + "&");
                }
                String p = sb.toString();
                p = p.substring(0, p.length() - 1);
                String url = HOST + API_QUERY_USER;
                String result = Http.post(url, p);
                JSONObject user = new JSONObject(result);
                JSONArray users = user.getJSONArray("data");
                if (users.length() > 0) {
                    exists = true;
                } else {
                    exists = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }
}
