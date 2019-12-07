package kr.co.softcampus.login.j_giftcon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

import kr.co.softcampus.login.R;

public class j_giftcontentpopup extends Activity {
    TextView giftName;
    ImageView gift;

    Intent got, popupIntent;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_giftcontent);

        giftName = findViewById(R.id.gifticon_name);
        gift = findViewById(R.id.gifticon);
        got = getIntent();

        String img_string = got.getStringExtra("image");
        String c1 = got.getStringExtra("c1");
        String c2 = got.getStringExtra("c2");
        try {
            InputStream is = (InputStream) new URL(img_string).getContent();
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            gift.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        giftName.setText(c1 + " - " + c2);

        popupIntent = new Intent(j_giftcontentpopup.this, j_giftsuccesspopup.class);
        startActivityForResult(popupIntent, 1);

    }


    public boolean onTouchEvent(MotionEvent event){
        return event.getAction() != MotionEvent.ACTION_OUTSIDE;
    }

    public void onBackPressed(){
        Intent intent= new Intent(j_giftcontentpopup.this, j_giftmain.class);
        startActivityForResult(intent, 1);
        return;
    }
}
