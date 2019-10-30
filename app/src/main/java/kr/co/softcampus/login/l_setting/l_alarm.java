package kr.co.softcampus.login.l_setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import kr.co.softcampus.login.R;
import kr.co.softcampus.login.g_MainScreen;
import kr.co.softcampus.login.h_mypage.h_mypage1;
import kr.co.softcampus.login.i_send.i_sendfirst;
import kr.co.softcampus.login.j_giftcon.j_giftmain;
import kr.co.softcampus.login.k_infomain;

public class l_alarm extends Activity {
    ImageView homebutton;
    ImageView bell;

    Switch onoffswitch;

    ImageButton Mybutton;
    ImageButton Send;
    ImageButton Purchase;
    ImageButton Inform;

  //  View bot;
    View top;
    TextView screentext;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_alarm);



//        screentext=top.findViewById(R.id.screentext);
//        screentext.setText("알람");

/*
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
*/
        onoffswitch=findViewById(R.id.onoffswitch);
/*
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

                Intent intent = new Intent(l_alarm.this, g_MainScreen.class);
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
            }
        });

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    Intent intent = new Intent(l_alarm.this, h_mypage1.class);
              //  startActivityForResult(intent, 1);
            }
        });
*/
        onoffswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onoffswitch.isChecked()) {
                    Intent intent1 = new Intent(l_alarm.this, l_alarmpopup.class);
                    intent1.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                    startActivityForResult(intent1, 1);
                }
            }
        });

/*
        Mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

                Intent intent = new Intent(l_alarm.this, h_mypage1.class);
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
            }
        });

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                Intent intent = new Intent(l_alarm.this, i_sendfirst.class);
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
            }
        });

        Purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(l_alarm.this, j_giftmain.class);
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
            }
        });

        Inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(l_alarm.this, k_infomain.class);
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
            }
        });

*/

    }

    public boolean onTouchEvent(MotionEvent event){
        return event.getAction() != MotionEvent.ACTION_OUTSIDE;
    }

    @Override
    public void onBackPressed() {

        finish();

        Intent intent = new Intent(l_alarm.this, l_settingfirst.class);
        intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
        startActivityForResult(intent, 1);


    }

    // 액티비티 종료시 애니메이션  제거
    @Override
    protected void onPause() {
        super.onPause();

        overridePendingTransition(0, 0);

    }
}
