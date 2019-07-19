package kr.co.softcampus.login.e_Register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import kr.co.softcampus.login.R;

public class e_Sendemail1popup extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_email);
    }

    public void mOnClose(View v){
        finish();
//        Intent intent=new Intent(e_Sendemail1popup.this, e_SecondRegister.class);
//        startActivityForResult(intent, 1);
    }

    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    public void onBackPressed(){
        return;
    }
}
