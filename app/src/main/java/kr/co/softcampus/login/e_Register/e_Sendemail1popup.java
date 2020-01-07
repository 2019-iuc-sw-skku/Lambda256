package kr.co.softcampus.login.e_Register;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import kr.co.softcampus.login.R;

/**
 * @name Sendemail1popup
 * @descriptions 이메일 전송했다는 알림 팝업
 */

public class e_Sendemail1popup extends Activity {

    ImageView imageView;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_email1);

        imageView = findViewById(R.id.imageView9);

        imageView.setImageResource(R.drawable.paper_plane_1);


    }

    public void mOnClose(View v){
        finish();
//        Intent intent=new Intent(e_Sendemail1popup.this, e_SecondRegister.class);
//        startActivityForResult(intent, 1);
    }

    public boolean onTouchEvent(MotionEvent event){
        return event.getAction() != MotionEvent.ACTION_OUTSIDE;
    }

    public void onBackPressed(){
        return;
    }
}
