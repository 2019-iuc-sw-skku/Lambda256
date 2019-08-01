package kr.co.softcampus.login.n_center;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class n_centerfirst extends Activity {

    ImageView homebutton;
    ImageView bell;


    ImageView folder3;
    ImageView folder4;

    Button personinfo;
    Button skkoin;
    Button giftcon;
    Button send;
    Button call;
    Button talk;
    Button email;

    ImageButton Mybutton;
    ImageButton Send;
    ImageButton Purchase;
    ImageButton Inform;

    View bot;
    View top;
    TextView screentext;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_centerfirst);


        bot = findViewById(R.id.centerbot);
        top=findViewById(R.id.centertop1);

        screentext=top.findViewById(R.id.screentext);
        screentext.setText("고객센터");


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

        folder3=findViewById(R.id.folder3);
        folder3.setImageResource(R.drawable.folder_12);

        folder4=findViewById(R.id.folder4);
        folder4.setImageResource(R.drawable.folder_12);

        personinfo=findViewById(R.id.personinfo);
        skkoin=findViewById(R.id.skkoin);
        giftcon=findViewById(R.id.giftcon);
        send=findViewById(R.id.send);
        call=findViewById(R.id.call);
        talk=findViewById(R.id.talk);
        email=findViewById(R.id.email);


        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(n_centerfirst.this, g_MainScreen.class);
                startActivityForResult(intent, 1);
            }
        });

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(n_centerfirst.this, h_mypage1.class);
                //startActivityForResult(intent, 1);
            }
        });

        personinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1=new Intent(n_centerfirst.this, n_personinfo.class);
                startActivityForResult(intent1, 1);
            }
        }); //회원정보 수정

        skkoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(n_centerfirst.this, n_skkoin.class);
                startActivityForResult(intent1, 1);
            }
        }); //회원정보 수정
        giftcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(n_centerfirst.this, n_giftcon.class);
                startActivityForResult(intent, 1);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(n_centerfirst.this, n_send.class);
                startActivityForResult(intent, 1);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //전화번호 제공
            }
        });

        talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //카카오톡 정보 제공
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //이메일 정보 제공
            }
        });


        Mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(n_centerfirst.this, h_mypage1.class);
                startActivityForResult(intent, 1);
            }
        });

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(n_centerfirst.this, i_sendfirst.class);
                startActivityForResult(intent, 1);
            }
        });

        Purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(n_centerfirst.this, j_giftmain.class);
                startActivityForResult(intent, 1);
            }
        });

        Inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(n_centerfirst.this, k_infomain.class);
                startActivityForResult(intent, 1);
            }
        });

    }

}
