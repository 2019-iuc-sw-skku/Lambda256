package kr.co.softcampus.login.e_Register;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import kr.co.softcampus.login.Connection.ConType;
import kr.co.softcampus.login.Connection.ConnectionClass;
import kr.co.softcampus.login.Connection.Constant;
import kr.co.softcampus.login.Connection.Server;
import kr.co.softcampus.login.R;

public class e_SecondRegister extends Activity {
    Button Check_Button;
    Intent get;
    EditText pw, nick;
    String nickname, password, email;

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondregister);


        get = getIntent();

        email = get.getStringExtra("email");
        pw = findViewById(R.id.EditText_password);
        nick = findViewById(R.id.EditText_nickname);
        Check_Button = findViewById(R.id.Check_Button);

        Check_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nickname = nick.getText().toString();
                password = pw.getText().toString();

                AsyncTask<String, Void, JSONObject> asyncTask = new AsyncTask<String, Void, JSONObject>() {
                    @Override
                    protected JSONObject doInBackground(String... strings) {
                        ConnectionClass cc = new ConnectionClass();
                        try {
                            JSONObject result = cc.MyConnection(Server.LUNI, Constant.WALLETGEN, ConType.TYPE_POST, new JSONObject().put("walletType", "LUNIVERSE").put("userKey", email + password));
                            return result;
                        } catch ( Exception e){
                            e.printStackTrace();
                        }
                        return null;
                    }
                };

                AsyncTask<String, Void, Boolean> asyncTask1 = new AsyncTask<String, Void, Boolean>() {
                    @Override
                    protected Boolean doInBackground(String... strings) {
                        ConnectionClass cc = new ConnectionClass();
                        try {
                            JSONObject result = cc.MyConnection(Server.SERVER, Constant.SIGNUP, ConType.TYPE_POST, new JSONObject().put("email", email).put("nickname", nickname).put("waddress", strings[0]));
                            Log.e("FINR", result.toString());
                            return result.getBoolean("result");
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        return false;
                    }
                };

                asyncTask.execute();

                JSONObject wreturn = null;
                Boolean wsuccess = false;
                Boolean ssuccess = false;
                try {
                    wreturn = asyncTask.get(10, TimeUnit.SECONDS);
                    wsuccess = wreturn.getBoolean("result");
                } catch (Exception e){
                    e.printStackTrace();
                }
                if(wsuccess){
                    try {
                        asyncTask1.execute(wreturn.getJSONObject("data").getString("address"));
                        ssuccess = asyncTask1.get(10, TimeUnit.SECONDS);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(e_SecondRegister.this, "지갑 주소를 받아오는 데에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(ssuccess){
                    Intent intent = new Intent(e_SecondRegister.this, kr.co.softcampus.login.f_Recommend.f_SearchRecom.class);
                    startActivityForResult(intent, 1);
                } else {
                    Toast.makeText(e_SecondRegister.this, "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
