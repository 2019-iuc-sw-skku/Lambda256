package kr.co.softcampus.login.c_Login;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import kr.co.softcampus.login.R;

/**
 * @name Loginmailerrorpopup
 * @descriptions 로그인 실패시 팝업 (by 이메일)
 * @author 배수영
 */

// This activity is for the popup content.
public class c_Loginmailerrorpopup extends Activity {

    ImageView warningImage;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        warningImage = findViewById(R.id.errorImage);

        // Remove Title Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_loginemailerror);
        // Drawing error image
        warningImage=findViewById(R.id.errorImage);
        warningImage.setImageResource(R.drawable.error);
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
