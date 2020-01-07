package kr.co.softcampus.login.i_send;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import kr.co.softcampus.login.R;

/**
 * @name sendcheck
 * @descriptions 송금여부확인
 */

public class i_sendcheck extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_sendcheck);
    }

    public void mOnClose1(View v){
        finish();
        Intent intent=new Intent(i_sendcheck.this, i_sendfirst.class);
        startActivityForResult(intent, 1);
    }

    public void mOnClose2(View v){
        finish();
        Intent intent=new Intent(i_sendcheck.this, i_sendfinish.class);
        startActivityForResult(intent, 1);
    }

    public boolean onTouchEvent(MotionEvent event){
        return event.getAction() != MotionEvent.ACTION_OUTSIDE;
    }

    public void onBackPressed(){
        return;
    }
}
