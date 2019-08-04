package kr.co.softcampus.login;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import kr.co.softcampus.login.Connection.ConType;
import kr.co.softcampus.login.Connection.ConnectionClass;
import kr.co.softcampus.login.Connection.Constant;
import kr.co.softcampus.login.Connection.Server;
import kr.co.softcampus.login.f_Recommend.f_SearchRecom;
import kr.co.softcampus.login.h_mypage.h_mypage1;
import kr.co.softcampus.login.h_mypage.h_mypage2;
import kr.co.softcampus.login.i_send.i_sendcheck;
import kr.co.softcampus.login.i_send.i_sendfirst;
import kr.co.softcampus.login.j_giftcon.j_giftmain;
import kr.co.softcampus.login.l_setting.l_settingfirst;
import kr.co.softcampus.login.m_getskkoin.m_getskkoin1;
import kr.co.softcampus.login.n_center.n_centerfirst;

public class k_infomain extends Activity {
    ImageView homebutton;
    ImageView bell;
    Button editbutton1; //내 정보 수정
    ImageView imageView12;

    ImageView imageView13;
    ImageView imageView14;
    ImageView imageView15;
    ImageView imageView16;

    Button setting;
    Button getskkoin;
    Button center;
    Button commend;

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
        top=findViewById(R.id.infotop1);

        nickname = findViewById(R.id.textView14);

        screentext=top.findViewById(R.id.screentext);
        screentext.setText("안내");

        imageView12=findViewById(R.id.imageView12);
        imageView12.setImageResource(R.drawable.picture5);


        imageView13=findViewById(R.id.imageView13);
        imageView13.setImageResource(R.drawable.settings);

        imageView14=findViewById(R.id.imageView14);
        imageView14.setImageResource(R.drawable.favorites);

        imageView15=findViewById(R.id.imageView15);
        imageView15.setImageResource(R.drawable.chat);

        imageView16=findViewById(R.id.imageView16);
        imageView16.setImageResource(R.drawable.users);


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

        editbutton1=findViewById(R.id.editbutton1);

        setting=findViewById(R.id.setting);
        getskkoin=findViewById(R.id.getskkoin);
        center=findViewById(R.id.center);
        commend=findViewById(R.id.commend);

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
        } catch (Exception e){
            nickname.setText("Anonymous");
        }

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(k_infomain.this, g_MainScreen.class);
                startActivityForResult(intent, 1);
            }
        });

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(k_infomain.this, h_mypage1.class);
                //startActivityForResult(intent, 1);
            }
        });

        editbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1=new Intent(k_infomain.this, h_mypage2.class);
                startActivityForResult(intent1, 1);
            }
        }); //회원정보 수정

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(k_infomain.this, l_settingfirst.class);
                startActivityForResult(intent, 1);
            }
        });

        getskkoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(k_infomain.this, m_getskkoin1.class);
                startActivityForResult(intent, 1);
            }
        });

        center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(k_infomain.this, n_centerfirst.class);
                startActivityForResult(intent, 1);
            }
        });

        commend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(k_infomain.this, f_SearchRecom.class);
                startActivityForResult(intent, 1);
            }
        });


        Mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(k_infomain.this, h_mypage1.class);
                startActivityForResult(intent, 1);
            }
        });

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(k_infomain.this, i_sendfirst.class);
                startActivityForResult(intent, 1);
            }
        });

        Purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(k_infomain.this, j_giftmain.class);
                startActivityForResult(intent, 1);
            }
        });

        Inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(k_infomain.this, k_infomain.class);
                startActivityForResult(intent, 1);
            }
        });

    }


}
