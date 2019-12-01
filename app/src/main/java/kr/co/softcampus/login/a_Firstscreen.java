package kr.co.softcampus.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class a_Firstscreen extends Activity {

    TextView textView11;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstscreen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(a_Firstscreen.this, b_LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);




    }
}