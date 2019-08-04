package kr.co.softcampus.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import kr.co.softcampus.login.Connection.ConType;
import kr.co.softcampus.login.Connection.ConnectionClass;
import kr.co.softcampus.login.Connection.Constant;
import kr.co.softcampus.login.Connection.Server;
import kr.co.softcampus.login.c_Login.c_Loginmailerrorpopup;
import kr.co.softcampus.login.c_Login.c_Loginpwerrorpopup;
import kr.co.softcampus.login.c_Login.c_RETURN_STATE;
import kr.co.softcampus.login.d_SearchPW.d_SearchPW;
import kr.co.softcampus.login.e_Register.e_FirstRegister;

public class b_LoginActivity extends AppCompatActivity {

    //로그인 세션 유지
    private boolean saveLoginData;
    private String id;
    private String pwd;
    private c_RETURN_STATE return_state;


    EditText etEmail;
    EditText etPw;
    CheckBox loginState;
    Button Login;
    Button SPW;
    Button REGISTER;

    ConnectionClass conn;
    String pk;

    private SharedPreferences appData;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //설정값 불러오기
        appData = getSharedPreferences("appData", MODE_PRIVATE);
        load();

        etEmail = findViewById(R.id.address_recommender);
        etPw = findViewById(R.id.etPw);
        loginState = findViewById(R.id.loginState);
        Login = findViewById(R.id.Login);
        SPW = findViewById(R.id.SPW);
        REGISTER = findViewById(R.id.REGISTER);
        return_state = c_RETURN_STATE.EMPTY;

        //이전에 로그인 정보를 저장시킨 기록이 있다면
        if (saveLoginData) {
            etEmail.setText(id);
            etPw.setText(pwd);
            loginState.setChecked(saveLoginData);
        }

        //로그인 눌렀을 때
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // 로그인 요청 (현재 private key 를 email+password로 하고 있음)
                AsyncTask<String, Void, JSONObject> asyncTask = new AsyncTask<String, Void, JSONObject>() {
                    @Override
                    protected JSONObject doInBackground(String... strings) {
                        ConnectionClass cc = new ConnectionClass();
                        JSONObject result;
                        try {
                            result = cc.MyConnection(Server.LUNI, Constant.WALLETCHECK, ConType.TYPE_GET, new JSONObject().put("walletType", "LUNIVERSE").put("userKey", strings[0]));
                        } catch (JSONException e){
                            e.printStackTrace();
                            return null;
                        }

                        return result;
                    }
                };

                pk = etEmail.getText().toString() + etPw.getText().toString();
                asyncTask.execute(pk);
                Boolean results = false;
                JSONObject result = null;

                try {
                    result = asyncTask.get(10, TimeUnit.SECONDS);
                    results = result.getBoolean("result");
                } catch (Exception e) {
                    e.printStackTrace();
                }


                // 결과 - 이메일 혹은 비밀번호가 틀렸는지는 아직 체크하지 않음
                if(results) {
                    return_state = c_RETURN_STATE.SUCCESS;
                } else {
                    return_state = c_RETURN_STATE.EMAILFAIL;
                }



                switch (return_state) {
                    case EMAILFAIL:
                        //만약 이메일이 일치하지 않을 때
                        Intent intent = new Intent(b_LoginActivity.this, c_Loginmailerrorpopup.class);
                        startActivityForResult(intent, 1);
                        break;
                    case PWFAIL:
                        //만약 비밀번호가 일치하지 않을 때
                        Intent intent1 = new Intent(b_LoginActivity.this, c_Loginpwerrorpopup.class);
                        startActivityForResult(intent1, 1);
                        break;
                    case SUCCESS:
                        //일치하면
                        Constant.setPRIVATEKEY(pk);
                        try {
                            Constant.walletAddressUpdate(result.getJSONObject("data").getString("address"));
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        Constant.EMAIL = etEmail.getText().toString();
                        Intent intent2 = new Intent(b_LoginActivity.this, g_MainScreen.class);
                        startActivity(intent2);
                        save();
                        finish();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Error occured!", Toast.LENGTH_SHORT).show();
                }
            }
        });



        //비밀번호 화면으로
        SPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(b_LoginActivity.this, d_SearchPW.class);
                startActivity(intent2);
            }
        });

        //회원가입 창으로
        REGISTER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(b_LoginActivity.this, e_FirstRegister.class);
                startActivity(intent3);
            }
        });

    }

    private void load() {
        // SharedPreferences 객체.get타입( 저장된 이름, 기본값 )
        // 저장된 이름이 존재하지 않을 시 기본값
        saveLoginData = appData.getBoolean("SAVE_LOGIN_DATA", false);
        id = appData.getString("ID", "");
        pwd = appData.getString("PWD", "");
    }

    //설정값 저장
    private void save() {
        SharedPreferences.Editor editor = appData.edit();
        editor.putBoolean("SAVE_LOGIN_DATA", loginState.isChecked());
        editor.putString("ID", etEmail.getText().toString().trim());
        editor.putString("PWD", etPw.getText().toString().trim());

        // apply, commit 을 안하면 변경된 내용이 저장되지 않음
        editor.apply();
    }
}