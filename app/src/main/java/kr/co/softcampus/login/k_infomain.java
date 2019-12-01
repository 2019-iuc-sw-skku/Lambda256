package kr.co.softcampus.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import kr.co.softcampus.login.Connection.ConType;
import kr.co.softcampus.login.Connection.ConnectionClass;
import kr.co.softcampus.login.Connection.Constant;
import kr.co.softcampus.login.Connection.Server;
import kr.co.softcampus.login.f_Recommend.f_SearchRecom;
import kr.co.softcampus.login.h_mypage.h_mypage1;
import kr.co.softcampus.login.i_send.i_sendfirst;
import kr.co.softcampus.login.j_giftcon.j_giftmain;
import kr.co.softcampus.login.l_setting.l_settingfirst;
import kr.co.softcampus.login.m_getskkoin.m_getskkoin1;
import kr.co.softcampus.login.n_center.n_customer_center_first;

public class k_infomain extends Activity {
    ImageView homebutton;
    ImageView bell;
    Button editbutton1; //내 정보 수정
    ImageView imageView12;

    ImageView imageView13;
    ImageView imageView14;
    ImageView imageView15;
    ImageView imageView16;

    Button setting_Button;
    Button getskkoin_Button;
    Button customer_center_Button;
    Button recommend_Button;

    ImageButton Mybutton;
    ImageButton Send;
    ImageButton Purchase;
    ImageButton Inform;

    View bot;
    View top;
    TextView screentext, nickname;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        bot = findViewById(R.id.infoBot);
        top = findViewById(R.id.infotop1);

        nickname = findViewById(R.id.textView14);

        screentext = top.findViewById(R.id.screentext);
        screentext.setText("안내");

        imageView12 = findViewById(R.id.imageView12);
        imageView12.setImageResource(R.drawable.picture5);


        imageView13 = findViewById(R.id.imageView13);
        imageView13.setImageResource(R.drawable.settings);

        imageView14 = findViewById(R.id.imageView14);
        imageView14.setImageResource(R.drawable.favorites);

        imageView15 = findViewById(R.id.imageView15);
        imageView15.setImageResource(R.drawable.chat);

        imageView16 = findViewById(R.id.imageView16);
        imageView16.setImageResource(R.drawable.users);


        homebutton = top.findViewById(R.id.homebutton);
        homebutton.setImageResource(R.drawable.home);

        bell = top.findViewById(R.id.bell);
        bell.setImageResource(R.drawable.bell);


        Mybutton = bot.findViewById(R.id.Mybutton2);
        Mybutton.setImageResource(R.drawable.mypage);

        Send = bot.findViewById(R.id.Send2);
        Send.setImageResource(R.drawable.send);

        Purchase = bot.findViewById(R.id.Purchase2);
        Purchase.setImageResource(R.drawable.giftcon);

        Inform = bot.findViewById(R.id.Inform2);
        Inform.setImageResource(R.drawable.info);

        editbutton1 = findViewById(R.id.editbutton1);

        setting_Button = findViewById(R.id.setting);
        getskkoin_Button = findViewById(R.id.getskkoin);
        customer_center_Button = findViewById(R.id.center);
        recommend_Button = findViewById(R.id.commend);


        // 닉네임 받아오는 부분
        AsyncTask<String, Void, String> nickAsycnTask = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                ConnectionClass cc = new ConnectionClass();
                try {
                    JSONObject result = cc.MyConnection(Server.SERVER, Constant.NICKNAME, ConType.TYPE_POST, new JSONObject().put("email", Constant.EMAIL));
                    return result.getJSONObject("data").getString("nickname");
                } catch (Exception e) {
                    e.printStackTrace();
                }


                return "Anonymous";
            }
        };

        nickAsycnTask.execute();
        try {

            nickname.setText(nickAsycnTask.get(10, TimeUnit.SECONDS));

            
        } catch (Exception e) {
            nickname.setText("Anonymous");
        }

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                finish();// 현재 activity 종료

                Intent intent = new Intent(k_infomain.this, g_MainScreen.class);
                startActivityForResult(intent, 1);
            }
        });

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "기능 구현중입니다.", Toast.LENGTH_SHORT).show();

            }
        });

        editbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                finish();// 현재 activity 종료

                Intent intent = new Intent(k_infomain.this, h_mypage2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
                
                 */

                Toast.makeText(k_infomain.this, "기능 수정중 ...", Toast.LENGTH_SHORT).show();
            }
        }); //회원정보 수정

        setting_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

           //     finish();// 현재 activity 종료

                Intent intent = new Intent(k_infomain.this, l_settingfirst.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
            }
        });

        getskkoin_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             //   finish();// 현재 activity 종료

                Intent intent = new Intent(k_infomain.this, m_getskkoin1.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
            }
        });

        customer_center_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(k_infomain.this, n_customer_center_first.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
            }
        });

        recommend_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(k_infomain.this, f_SearchRecom.class);
                startActivityForResult(intent, 1);
            }
        });


        Mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                finish();// 현재 activity 종료

                Intent intent = new Intent(k_infomain.this, h_mypage1.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
            }
        });

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();// 현재 activity 종료

                Intent intent = new Intent(k_infomain.this, i_sendfirst.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
            }
        });

        Purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();// 현재 activity 종료

                Intent intent = new Intent(k_infomain.this, j_giftmain.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
            }
        });

        Inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    // 액티비티 종료시 애니메이션  제거
    @Override
    protected void onPause() {
        super.onPause();

        overridePendingTransition(0, 0);

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("로그아웃 하시겠습니까?").setMessage("");

        builder.setPositiveButton("로그아웃", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                Toast.makeText(getApplicationContext(), "로그아웃 되었습니다", Toast.LENGTH_SHORT).show();
                finishAffinity();
                Intent intent = new Intent( k_infomain.this, b_LoginActivity.class);
                startActivity(intent);
                System.exit(0);
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }




}
