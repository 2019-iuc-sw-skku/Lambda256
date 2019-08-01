package kr.co.softcampus.login.n_center;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import kr.co.softcampus.login.R;
import kr.co.softcampus.login.g_MainScreen;
import kr.co.softcampus.login.h_mypage.h_mypage1;
import kr.co.softcampus.login.i_send.i_sendfirst;
import kr.co.softcampus.login.j_giftcon.j_giftmain;
import kr.co.softcampus.login.k_infomain;
import kr.co.softcampus.login.l_setting.l_alarm;
import kr.co.softcampus.login.l_setting.l_exitpopup;
import kr.co.softcampus.login.l_setting.l_logoutpopup;
import kr.co.softcampus.login.l_setting.l_rule;
import kr.co.softcampus.login.l_setting.l_settingfirst;

public class n_personinfo extends Activity {

    ImageView homebutton;
    ImageView bell;

    TextView textView48;
    ImageView imageView25;
    ImageButton Mybutton;
    ImageButton Send;
    ImageButton Purchase;
    ImageButton Inform;

    View bot;
    View top;
    TextView screentext;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personinfo);


        bot = findViewById(R.id.personinfobot);
        top=findViewById(R.id.personinfotop1);

        screentext=top.findViewById(R.id.screentext);
        screentext.setText("개인정보");

        textView48=findViewById(R.id.textView48);

        imageView25=findViewById(R.id.imageView25);
        imageView25.setImageResource(R.drawable.q);

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



        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(n_personinfo.this, g_MainScreen.class);
                startActivityForResult(intent, 1);
            }
        });

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(n_personinfo.this, h_mypage1.class);
                //startActivityForResult(intent, 1);
            }
        });

        textView48.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(n_personinfo.this, n_personinfo1.class);
                startActivityForResult(intent, 1);
            }
        });

        Mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(n_personinfo.this, h_mypage1.class);
                startActivityForResult(intent, 1);
            }
        });

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(n_personinfo.this, i_sendfirst.class);
                startActivityForResult(intent, 1);
            }
        });

        Purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(n_personinfo.this, j_giftmain.class);
                startActivityForResult(intent, 1);
            }
        });

        Inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(n_personinfo.this, k_infomain.class);
                startActivityForResult(intent, 1);
            }
        });

    }
}