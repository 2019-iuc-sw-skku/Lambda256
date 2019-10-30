package kr.co.softcampus.login.j_giftcon;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import kr.co.softcampus.login.R;
import kr.co.softcampus.login.i_send.i_sendfinish;
import kr.co.softcampus.login.i_send.i_sendfirst;

public class j_giftcheckpopup extends Activity {

    ImageView imageView6;
    Button button_recommender;
    Button button_login;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_giftcheck);

        button_recommender=findViewById(R.id.button_recommender);
        button_login=findViewById(R.id.button_login);

        imageView6=findViewById(R.id.imageView6);
        imageView6.setImageResource(R.drawable.insert_coin);

        button_recommender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(j_giftcheckpopup.this, j_giftmain.class);
                startActivityForResult(intent, 1);
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(j_giftcheckpopup.this, j_giftsuccesspopup.class);
                startActivityForResult(intent, 1);
                //기프티콘 구매
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
