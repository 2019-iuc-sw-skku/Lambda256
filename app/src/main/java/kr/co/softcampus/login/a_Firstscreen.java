package kr.co.softcampus.login;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

import androidx.core.app.ActivityCompat;

/**
 * @name Firstscreen
 * @descriptions 앱 실행 시 로딩창
 */

public class a_Firstscreen extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstscreen);

        /* 파일 쓰기 권한 체크 */
        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(a_Firstscreen.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        /* 갤러리 접근 권한 체크 */
        if (checkSelfPermission(Manifest.permission.MEDIA_CONTENT_CONTROL) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(a_Firstscreen.this, new String[]{Manifest.permission.MEDIA_CONTENT_CONTROL}, 1);
        }


        /* 2초간 기다림 */
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