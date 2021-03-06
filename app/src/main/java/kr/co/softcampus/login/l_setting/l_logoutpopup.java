package kr.co.softcampus.login.l_setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import kr.co.softcampus.login.R;
import kr.co.softcampus.login.b_LoginActivity;


/**
 * @name logoutpopup
 * @descriptions 로그아웃 확인 popup
 * @author 배수영, 정현영
 */

public class l_logoutpopup extends Activity {
    Button on;
    Button off;

    ImageView imageView24;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_logout);
        off=findViewById(R.id.off);
        on=findViewById(R.id.on);

        imageView24=findViewById(R.id.imageView24);
        imageView24.setImageResource(R.drawable.exit_1);

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

                //  Intent intent = new Intent(l_logoutpopup.this, l_settingfirst.class);
                //   intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                //   startActivityForResult(intent, 1);
            }
        });

        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //로그아웃

                finishAffinity();
                Intent intent = new Intent(l_logoutpopup.this, b_LoginActivity.class);
                startActivity(intent);
                System.exit(0);

            }
        });
    }

    public void mOnClose(View v){
        finish();
    }

    public boolean onTouchEvent(MotionEvent event){
        return event.getAction() != MotionEvent.ACTION_OUTSIDE;
    }

    public void onBackPressed(){
        return;
    }
}