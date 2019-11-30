package kr.co.softcampus.login.c_Login;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import kr.co.softcampus.login.R;

public class c_Loginpwerrorpopup extends Activity {

    ImageView imageView6;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Remove Title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_loginpwerror);

        // Drawing error image
        imageView6=findViewById(R.id.errorImage);
        imageView6.setImageResource(R.drawable.error);
    }

    //click
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