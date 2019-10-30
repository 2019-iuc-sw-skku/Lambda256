package kr.co.softcampus.login.i_send;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import kr.co.softcampus.login.Connection.ConType;
import kr.co.softcampus.login.Connection.ConnectionClass;
import kr.co.softcampus.login.Connection.Constant;
import kr.co.softcampus.login.Connection.Server;
import kr.co.softcampus.login.R;

public class i_sendfinish extends Activity {
    Button conform;
    TextView textView3, textView4, textView20;
    Intent got;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendfinish);

        got = getIntent();
        conform=findViewById(R.id.confirm_button);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView20 = findViewById(R.id.textView20);

        String addr = got.getStringExtra("address");
        AsyncTask<String, Void, String> asyncTask = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                ConnectionClass cc = new ConnectionClass();
                JSONObject result = cc.MyConnection(Server.LUNI, Constant.BALANCE, ConType.TYPE_GET, new JSONObject());
                try {
                    return new JSONObject(result.toString()).getJSONObject("data").getString("balance");
                } catch (Exception e) {
                    e.printStackTrace();
                    return "-1";
                }
            }
        };
        AsyncTask<String, Void, JSONObject> nickasyncTask = new AsyncTask<String, Void, JSONObject>() {
            @Override
            protected JSONObject doInBackground(String... strings) {
                ConnectionClass cc = new ConnectionClass();
                JSONObject result;
                try {
                    result = cc.MyConnection(Server.SERVER, Constant.NICKNAME, ConType.TYPE_POST,
                            new JSONObject().put("w_address", strings[0]));
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }

                return result;
            }
        };
        String balance = "-1";
        asyncTask.execute();
        try {
            balance = asyncTask.get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        nickasyncTask.execute(addr);
        String results = null;
        JSONObject result = null;

        try {
            result = nickasyncTask.get(10, TimeUnit.SECONDS);
            results = result.getJSONObject("data").getString("nickname");
        } catch (Exception e) {
            e.printStackTrace();
            results = "anonymous";
        }

        textView3.setText(results + " 님께");
        textView4.setText(got.getDoubleExtra("amount", -1) + " 스코인");
        textView20.setText(balance);

        conform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(i_sendfinish.this, i_sendfirst.class);
                startActivityForResult(intent, 1);
            }
        });
    }

}
