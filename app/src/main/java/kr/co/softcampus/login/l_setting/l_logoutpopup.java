package kr.co.softcampus.login.l_setting;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.IntentCompat;

import kr.co.softcampus.login.R;
import kr.co.softcampus.login.b_LoginActivity;

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