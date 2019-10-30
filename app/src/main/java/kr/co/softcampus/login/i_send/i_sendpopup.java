package kr.co.softcampus.login.i_send;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import kr.co.softcampus.login.R;

public class i_sendpopup extends Activity {


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_email);
    }

    public void mOnClose1(View v){ //취소
        Intent intent=new Intent(i_sendpopup.this, i_sendfirst.class);
        startActivityForResult(intent, 1);
    }
    public void mOnClose2(View v){ //완료
        finish();
        Intent intent=new Intent(i_sendpopup.this, i_sendfinish.class);
        startActivityForResult(intent, 1);
    }

    public boolean onTouchEvent(MotionEvent event){
        return event.getAction() != MotionEvent.ACTION_OUTSIDE;
    }

    public void onBackPressed(){
        return;
    }
}
