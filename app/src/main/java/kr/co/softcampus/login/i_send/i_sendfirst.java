package kr.co.softcampus.login.i_send;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import kr.co.softcampus.login.R;
import kr.co.softcampus.login.g_MainScreen;
import kr.co.softcampus.login.h_mypage.h_mypage1;
import kr.co.softcampus.login.h_mypage.h_mypage2;
import kr.co.softcampus.login.h_mypage.listAdapter;
import kr.co.softcampus.login.h_mypage.list_item;
import kr.co.softcampus.login.j_giftcon.j_giftmain;
import kr.co.softcampus.login.k_infomain;

public class i_sendfirst extends Activity {
    ImageView homebutton;
    ImageView bell;

    ImageButton Mybutton;
    ImageButton Send;
    ImageButton Purchase;
    ImageButton Inform;
    Button conform;

    View bot;
    View top;
    TextView screentext;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        bot = findViewById(R.id.sendbot);
        top=findViewById(R.id.sendtop1);

        screentext=top.findViewById(R.id.screentext);
        screentext.setText("송금");

        homebutton=top.findViewById(R.id.homebutton);
        homebutton.setImageResource(R.drawable.home);

        bell = top.findViewById(R.id.bell);
        bell.setImageResource(R.drawable.bell);

        conform = findViewById(R.id.conform);
        conform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

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
                Intent intent=new Intent(i_sendfirst.this, g_MainScreen.class);
                startActivityForResult(intent, 1);
            }
        });

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent=new Intent(i_sendfirst.this, listAdapter.class);
                // startActivityForResult(intent, 1);
            }
        }); //알람 기능 구현

        Mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(i_sendfirst.this, h_mypage1.class);
                startActivityForResult(intent, 1);
            }
        });

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(i_sendfirst.this, i_sendfirst.class);
                startActivityForResult(intent, 1);
            }
        });

        Purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(i_sendfirst.this, j_giftmain.class);
                startActivityForResult(intent, 1);
            }
        });

        Inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(i_sendfirst.this, k_infomain.class);
                startActivityForResult(intent, 1);
            }
        });
    }
}
