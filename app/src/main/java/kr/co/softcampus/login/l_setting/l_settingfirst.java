package kr.co.softcampus.login.l_setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import kr.co.softcampus.login.R;
import kr.co.softcampus.login.f_Recommend.f_SearchRecom;
import kr.co.softcampus.login.g_MainScreen;
import kr.co.softcampus.login.h_mypage.h_mypage1;
import kr.co.softcampus.login.h_mypage.h_mypage2;
import kr.co.softcampus.login.i_send.i_sendfirst;
import kr.co.softcampus.login.j_giftcon.j_giftmain;
import kr.co.softcampus.login.k_infomain;
import kr.co.softcampus.login.m_getskkoin.m_getskkoin1;
import kr.co.softcampus.login.n_center.n_centerfirst;

public class l_settingfirst extends Activity {
    ImageView homebutton;
    ImageView bell;


    ImageView folder1;
    ImageView folder2;

    Button alarm1;
    Button rule1;
    Button logout1;
    Button exit1;

    ImageButton Mybutton;
    ImageButton Send;
    ImageButton Purchase;
    ImageButton Inform;

    View bot;
    View top;
    TextView screentext;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_settingfirst);


        bot = findViewById(R.id.settingBot);
        top=findViewById(R.id.settingtop1);

        screentext=top.findViewById(R.id.screentext);
        screentext.setText("환경설정");


        homebutton=top.findViewById(R.id.homebutton);
        homebutton.setImageResource(R.drawable.home);

        bell = top.findViewById(R.id.bell);
        bell.setImageResource(R.drawable.bell);


        Mybutton = bot.findViewById(R.id.Mybutton2);
        Mybutton.setImageResource(R.drawable.picture1);

        Send=bot.findViewById(R.id.Send2);
        Send.setImageResource(R.drawable.picture2);

        Purchase=bot.findViewById(R.id.Purchase2);
        Purchase.setImageResource(R.drawable.picture3);

        Inform=bot.findViewById(R.id.Inform2);
        Inform.setImageResource(R.drawable.picture4);

        folder1=findViewById(R.id.folder1);
        folder1.setImageResource(R.drawable.folder_12);

        folder2=findViewById(R.id.folder2);
        folder2.setImageResource(R.drawable.folder_12);


        alarm1=findViewById(R.id.alarm1);
        rule1=findViewById(R.id.rule1);
        logout1=findViewById(R.id.logout1);
        exit1=findViewById(R.id.exit1);


        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(l_settingfirst.this, g_MainScreen.class);
                startActivityForResult(intent, 1);
            }
        });

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(l_settingfirst.this, h_mypage1.class);
                //startActivityForResult(intent, 1);
            }
        });

        alarm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1=new Intent(l_settingfirst.this, l_alarm.class);
                startActivityForResult(intent1, 1);
            }
        }); //회원정보 수정


        rule1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(l_settingfirst.this, l_rule.class);
                startActivityForResult(intent, 1);
            }
        });

        logout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(l_settingfirst.this, l_logoutpopup.class);
                startActivityForResult(intent, 1);
            }
        });

        exit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(l_settingfirst.this, l_exitpopup.class);
                startActivityForResult(intent, 1);
            }
        });


        Mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(l_settingfirst.this, h_mypage1.class);
                startActivityForResult(intent, 1);
            }
        });

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(l_settingfirst.this, i_sendfirst.class);
                startActivityForResult(intent, 1);
            }
        });

        Purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(l_settingfirst.this, j_giftmain.class);
                startActivityForResult(intent, 1);
            }
        });

        Inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(l_settingfirst.this, k_infomain.class);
                startActivityForResult(intent, 1);
            }
        });

    }

}
