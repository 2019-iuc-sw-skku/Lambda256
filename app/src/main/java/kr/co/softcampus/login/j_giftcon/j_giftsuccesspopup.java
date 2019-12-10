package kr.co.softcampus.login.j_giftcon;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import kr.co.softcampus.login.Connection.Constant;
import kr.co.softcampus.login.R;

public class j_giftsuccesspopup extends Activity {
    ImageView imageView6;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_giftsuccess);

        imageView6=findViewById(R.id.errorImage);
        imageView6.setImageResource(R.drawable.success);
        Constant.giftlists = null;
    }

    public void mOnClose(View v){
        finish();
//        Intent intent=new Intent(j_giftsuccesspopup.this, j_giftcontentpopup.class);
//        startActivityForResult(intent, 0);
    }

    public boolean onTouchEvent(MotionEvent event){
        return event.getAction() != MotionEvent.ACTION_OUTSIDE;
    }

    public void onBackPressed(){
        return;
    }
}
