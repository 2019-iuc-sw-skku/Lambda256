package kr.co.softcampus.login.i_send;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kr.co.softcampus.login.R;

public class i_sendfinish extends Activity {
    Button conform;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendfinish);

        conform=findViewById(R.id.conform);

        conform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(i_sendfinish.this, i_sendfirst.class);
                startActivityForResult(intent, 1);
            }
        });
    }

}
