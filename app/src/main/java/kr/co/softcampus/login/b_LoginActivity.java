package kr.co.softcampus.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

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

    private SharedPreferences appData;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //설정값 불러오기
        appData = getSharedPreferences("appData", MODE_PRIVATE);
        load();

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPw = (EditText) findViewById(R.id.etPw);
        loginState = (CheckBox) findViewById(R.id.loginState);
        Login = (Button) findViewById(R.id.Login);
        SPW = (Button) findViewById(R.id.SPW);
        REGISTER = (Button) findViewById(R.id.REGISTER);
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
                if(etEmail.getText().toString().equals("")){
                    return_state = c_RETURN_STATE.EMAILFAIL;
                } else if(etPw.getText().toString().equals("")){
                    return_state = c_RETURN_STATE.PWFAIL;
                } else {
                    return_state = c_RETURN_STATE.SUCCESS;
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
                        Intent intent2 = new Intent(b_LoginActivity.this, g_MainScreen.class);
                        startActivity(intent2);
                        save();
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