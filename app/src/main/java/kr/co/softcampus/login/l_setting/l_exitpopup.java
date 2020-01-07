package kr.co.softcampus.login.l_setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import kr.co.softcampus.login.R;
import kr.co.softcampus.login.b_LoginActivity;


/**
 * @unused
 * @name exitpopup
 * @descriptions 회원탈퇴여부 팝업
 * @author 배수영
 */

public class l_exitpopup extends Activity {
    Button on;
    Button off;

    ImageView imageView6;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_exit);

        off=findViewById(R.id.off);
        on=findViewById(R.id.on);

        imageView6=findViewById(R.id.errorImage);
        imageView6.setImageResource(R.drawable.broken_link);

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

               // Intent intent = new Intent(l_exitpopup.this, l_settingfirst.class);
              //  intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
              //  startActivityForResult(intent, 1);
            }
        });

        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회원 탈퇴 ( 로그인 화면으로 돌아감 )

                finishAffinity();
                Intent intent = new Intent(l_exitpopup.this, b_LoginActivity.class);
                startActivity(intent);
                System.exit(0);

                Toast.makeText(l_exitpopup.this, "회원 탈퇴가 완료되었습니다.", Toast.LENGTH_LONG).show();
            }
        });
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