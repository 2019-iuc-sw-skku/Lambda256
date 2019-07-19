package kr.co.softcampus.login.d_SearchPW;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kr.co.softcampus.login.R;

public class d_SearchPW extends Activity {
    Button Check;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchpw);

        Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(d_SearchPW.this, d_Sendemailpopup.class);
                startActivityForResult(intent, 1);
            }
        });
    }
}
