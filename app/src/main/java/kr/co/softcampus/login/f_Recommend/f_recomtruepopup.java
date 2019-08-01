package kr.co.softcampus.login.f_Recommend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import kr.co.softcampus.login.R;

public class f_recomtruepopup extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_recomtrue);
    }

    public void mOnClose(View v){
        finish();
        Intent intent=new Intent(f_recomtruepopup.this, f_SearchRecom.class);
        startActivityForResult(intent, 1);
    }

    public boolean onTouchEvent(MotionEvent event){
        return event.getAction() != MotionEvent.ACTION_OUTSIDE;
    }

    public void onBackPressed(){
        return;
    }
}