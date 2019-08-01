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
import kr.co.softcampus.login.h_mypage.h_mypage1;
import kr.co.softcampus.login.j_giftcon.j_giftmain;

public class l_exitpopup extends Activity {
    Button on;
    Button off;

    ImageView imageView6;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_exit);

        off=findViewById(R.id.off);
        on=findViewById(R.id.on);

        imageView6=findViewById(R.id.imageView6);
        imageView6.setImageResource(R.drawable.broken_link);

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(l_exitpopup.this, l_settingfirst.class);
                startActivityForResult(intent, 1);
            }
        });

        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회원 탈퇴
                finish();
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