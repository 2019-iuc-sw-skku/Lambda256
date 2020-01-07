package kr.co.softcampus.login.l_setting;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import kr.co.softcampus.login.R;

/**
 * @name alarmpopup
 * @descriptions 알람 설정 팝업
 * @author 배수영
 */

public class l_alarmpopup extends Activity {

    Button off;
    Button on;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_alarm);

        off=findViewById(R.id.off);
        on=findViewById(R.id.on);

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //알람 끄기
                finish();
            }
        });

        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //알람 켜기
                finish();
            }
        });
    }




    public boolean onTouchEvent(MotionEvent event){
        return event.getAction() != MotionEvent.ACTION_OUTSIDE;
    }

    public void onBackPressed(){
        return;
    }
}
