package kr.co.softcampus.login.Connection;

import android.os.AsyncTask;

import org.json.JSONObject;

public class ConnFunc {


    AsyncTask<JSONObject, Void, JSONObject> asyncTask;
    ConnectionClass conn;

    public ConnFunc () {
        conn = new ConnectionClass();
    }

    public JSONObject getJSON(final Server to, final String addurl, final ConType type, JSONObject param){
        asyncTask = new AsyncTask<JSONObject, Void, JSONObject>() {
            @Override
            protected JSONObject doInBackground(JSONObject... params) {
                JSONObject result = conn.MyConnection(to, addurl, type, params[0]);

                
                return null;
            }
        };

        if(param.length() == 0)
            asyncTask.execute();
        else
            asyncTask.execute(param);


        JSONObject result = null;
        try {
            result = asyncTask.get();
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

}
