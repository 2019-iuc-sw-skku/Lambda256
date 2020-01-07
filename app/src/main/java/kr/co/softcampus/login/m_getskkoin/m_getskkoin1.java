package kr.co.softcampus.login.m_getskkoin;

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


/**
 * @name getskkoin1
 * @descriptions 스코인 얻는 법 가이드창
 * @author 배수영
 */

public class m_getskkoin1 extends Activity {
    ImageView homebutton;
    ImageView bell;

    Button getskkoin2_Button;
    Button getskkoin3_Button;

    ImageButton Mybutton;
    ImageButton Send;
    ImageButton Purchase;
    ImageButton Inform;

    View bot;
    View top;
    TextView screentext;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getskkoin1);

        bot = findViewById(R.id.getskkoin1Bot);
        top=findViewById(R.id.getskkoin1top1);

        screentext=top.findViewById(R.id.screentext);
        screentext.setText("스코인 얻기");


        homebutton=top.findViewById(R.id.homebutton);
        homebutton.setImageResource(R.drawable.home);

        bell = top.findViewById(R.id.bell);
        bell.setImageResource(R.drawable.bell);


        Mybutton = bot.findViewById(R.id.Mybutton2);
        Mybutton.setImageResource(R.drawable.mypage);

        Send=bot.findViewById(R.id.Send2);
        Send.setImageResource(R.drawable.send);

        Purchase=bot.findViewById(R.id.Purchase2);
        Purchase.setImageResource(R.drawable.giftcon);

        Inform=bot.findViewById(R.id.Inform2);
        Inform.setImageResource(R.drawable.info);

        getskkoin2_Button =findViewById(R.id.Button11);
        getskkoin3_Button =findViewById(R.id.Button12);



        getskkoin2_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(m_getskkoin1.this, m_getskkoin2.class);
                startActivityForResult(intent, 1);
            }
        });

        getskkoin3_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(m_getskkoin1.this, m_getskkoin3.class);
                startActivityForResult(intent, 1);
            }
        });

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                finish();

                Intent intent = new Intent(m_getskkoin1.this, g_MainScreen.class);
                startActivityForResult(intent, 1);
            }
        });

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(m_getskkoin1.this, h_mypage1.class);
                startActivityForResult(intent, 1);
            }
        });

        Mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();// 현재 activity 종료
                finish();

                Intent intent = new Intent(m_getskkoin1.this, h_mypage1.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
            }
        });

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();// 현재 activity 종료
                finish();

                Intent intent = new Intent(m_getskkoin1.this, i_sendfirst.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
            }
        });

        Purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();// 현재 activity 종료
                finish();

                Intent intent = new Intent(m_getskkoin1.this, j_giftmain.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
            }
        });

        Inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();// 현재 activity 종료
                finish();

                Intent intent = new Intent(m_getskkoin1.this, j_giftmain.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);

            }
        });



    }

    @Override
    protected void onPause() {
        super.onPause();

        overridePendingTransition(0, 0);

    }

    @Override
    public void onBackPressed() {

        finish();// 현재 activity 종료

    //    Intent intent = new Intent(m_getskkoin1.this, k_infomain.class);
     //   intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
     //   startActivityForResult(intent, 1);

    }
}
