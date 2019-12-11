package kr.co.softcampus.login;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

public class a_Firstscreen extends Activity {

    TextView textView11;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstscreen);

        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(a_Firstscreen.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        if (checkSelfPermission(Manifest.permission.MEDIA_CONTENT_CONTROL) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(a_Firstscreen.this, new String[]{Manifest.permission.MEDIA_CONTENT_CONTROL}, 1);
        }


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