package kr.co.softcampus.login.e_Register;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import kr.co.softcampus.login.Connection.Constant;
import kr.co.softcampus.login.R;
import kr.co.softcampus.login.b_LoginActivity;
import kr.co.softcampus.login.f_Recommend.f_SearchRecom;

public class e_EndRegister extends Activity {

    Button button_recommender;
    Button button_login;
    Boolean flag_recom_button;
    TextView address;
    Context mContext;
    Intent got;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endregister);

        mContext = this;
        button_recommender = findViewById(R.id.button_cancel);
        button_login = findViewById(R.id.button_purchase);
        address = findViewById(R.id.textView8);


        try{
            got = getIntent();
            flag_recom_button = got.getBooleanExtra("Success", false);
            Log.e("FLAGRB", Boolean.toString(flag_recom_button));
        } catch (Exception e){

            Log.e("ERR_FLAGRB", Boolean.toString(flag_recom_button));
            e.printStackTrace();
            flag_recom_button = false;
        }

        if(flag_recom_button){
            button_recommender.setVisibility(View.GONE);
        }

        address.setText(Constant.WADDRESS);

        // button_recommender 누르면

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("WalletAddress", address.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(mContext, "지갑주소가 복사되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        button_recommender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag_recom_button == false){
                    // 추천인 입력 화면으로 전환
                    Intent intent=new Intent(e_EndRegister.this, f_SearchRecom.class);
                    startActivityForResult(intent, 1);
                }

                else{
                    Toast.makeText(e_EndRegister.this, "추천인 입력은 1회만 가능합니다.", Toast.LENGTH_LONG).show();
                }
            }
        });


        // 로그인 버튼
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag_recom_button){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(e_EndRegister.this);
                    dialog.setTitle("")
                            .setMessage("로그인 화면으로 이동하시겠습니까?")

                            .setPositiveButton("예", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Toast.makeText(e_EndRegister.this, "발급된 지갑주소로 로그인 해주세요.", Toast.LENGTH_LONG).show();

                                    // 로그인 화면으로 전환
                                    Intent intent = new Intent(getApplicationContext(), b_LoginActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);

                                }
                            })
                            .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).create().show();
                }
                // 추천인을 이미 입력 했다면
                else{
                    AlertDialog.Builder dialog = new AlertDialog.Builder(e_EndRegister.this);
                    dialog.setTitle("추천인을 입력하지 않으시겠습니까?")
                            .setMessage("추천인 입력은 회원가입시 1회만 가능합니다.")

                            .setPositiveButton("예", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Toast.makeText(e_EndRegister.this, "발급된 지갑주소로 로그인 해주세요.", Toast.LENGTH_LONG).show();

                                    // 로그인 화면으로 전환
                                    Intent intent = new Intent(getApplicationContext(), b_LoginActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);

                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).create().show();
                }
            }
        });
    }
}
