package kr.co.softcampus.login.j_giftcon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import kr.co.softcampus.login.R;


/**
 * @name giftfailpopup
 * @descriptions 상품권 교환 실패 메시지
 */

public class j_giftfailpopup extends Activity {

    ImageView imageView6;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_giftfail);

        imageView6=findViewById(R.id.errorImage);
        imageView6.setImageResource(R.drawable.error);
    }

    public void mOnClose(View v){
        Intent intent=new Intent(j_giftfailpopup.this, j_giftmain.class);
        startActivityForResult(intent, -1);
    }

    public boolean onTouchEvent(MotionEvent event){
        return event.getAction() != MotionEvent.ACTION_OUTSIDE;
    }

    public void onBackPressed(){
        return;
    }
}
