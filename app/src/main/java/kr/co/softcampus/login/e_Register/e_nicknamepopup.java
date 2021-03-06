package kr.co.softcampus.login.e_Register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import kr.co.softcampus.login.R;

/**
 * @name nicknamepopup
 * @descriptions 닉네임 중복될 경우 뜨는 팝업
 */

public class e_nicknamepopup extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_nickname);
    }

    public void mOnClose(View v){
        finish();
        Intent intent=new Intent(e_nicknamepopup.this, e_SecondRegister.class);
        startActivityForResult(intent, 1);
    }

    public boolean onTouchEvent(MotionEvent event){
        return event.getAction() != MotionEvent.ACTION_OUTSIDE;
    }

    public void onBackPressed(){
        return;
    }
}
