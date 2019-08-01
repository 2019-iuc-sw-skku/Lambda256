package kr.co.softcampus.login.j_giftcon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import kr.co.softcampus.login.R;
import kr.co.softcampus.login.g_MainScreen;
import kr.co.softcampus.login.h_mypage.h_mypage1;
import kr.co.softcampus.login.h_mypage.h_mypage2;
import kr.co.softcampus.login.i_send.i_sendfirst;
import kr.co.softcampus.login.k_infomain;
import kr.co.softcampus.login.n_center.n_giftcon1;

public class j_giftmain extends Activity {
    ImageView homebutton;
    ImageView bell;

    ImageView cutlery;
    ImageView coffee_cup;
    ImageView chef_hat;

    ImageButton Mybutton;
    ImageButton Send;
    ImageButton Purchase;
    ImageButton Inform;

    View bot;
    View top;
    TextView screentext;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giftmain);

        bot = findViewById(R.id.giftmainbot);
        top=findViewById(R.id.giftmaintop);

        screentext=top.findViewById(R.id.screentext);
        screentext.setText("기프티콘 구매");

        cutlery=findViewById(R.id.cutlery);
        cutlery.setImageResource(R.drawable.cutlery);

        coffee_cup=findViewById(R.id.coffee_cup);
        coffee_cup.setImageResource(R.drawable.coffee_cup);

        chef_hat=findViewById(R.id.chef_hat);
        chef_hat.setImageResource(R.drawable.chef_hat);

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
                Intent intent = new Intent(j_giftmain.this, g_MainScreen.class);
                startActivityForResult(intent, 1);
            }
        });

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(j_giftmain.this, h_mypage1.class);
                //startActivityForResult(intent, 1);
            }
        });

        Mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(j_giftmain.this, h_mypage1.class);
                startActivityForResult(intent, 1);
            }
        });

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(j_giftmain.this, i_sendfirst.class);
                startActivityForResult(intent, 1);
            }
        });

        Purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(j_giftmain.this, j_giftmain.class);
                startActivityForResult(intent, 1);
            }
        });

        Inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(j_giftmain.this, k_infomain.class);
                startActivityForResult(intent, 1);
            }
        });

    }

}
