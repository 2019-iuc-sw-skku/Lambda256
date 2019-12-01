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

    TextView textView48;
    ImageView imageView25;
    ImageButton Mybutton;
    ImageButton Send;
    ImageButton Purchase;
    ImageButton Inform;

    ImageView imageView_logo;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personinfo);


        imageView_logo = findViewById(R.id.imageview_logo);
        imageView_logo.setImageResource(R.drawable.logo2);

        textView48=findViewById(R.id.textView48);

        imageView25=findViewById(R.id.imageView25);
        imageView25.setImageResource(R.drawable.q);


        textView48.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(n_personinfo.this, n_personinfo1.class);
                startActivityForResult(intent, 1);
            }
        });



    }

    @Override
    protected void onPause() {
        super.onPause();

        overridePendingTransition(0, 0); // 액티비티 종료 시 애니메이션 제거

    }

}
