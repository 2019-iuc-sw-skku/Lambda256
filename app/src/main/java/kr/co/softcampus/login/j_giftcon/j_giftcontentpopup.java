package kr.co.softcampus.login.j_giftcon;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

import kr.co.softcampus.login.Connection.Constant;
import kr.co.softcampus.login.R;


/**
 * @name giftcontentpopup
 * @descriptions 받은 상품권 확인 및 저장
 */

public class j_giftcontentpopup extends Activity {
    TextView giftName;
    ImageView gift;
    Button btn;
    Bitmap bitmap;

    Intent got, popupIntent;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_giftcontent);

        giftName = findViewById(R.id.gifticon_name);
        gift = findViewById(R.id.gifticon);
        btn = findViewById(R.id.imgDownload);
        got = getIntent();

        String img_string = got.getStringExtra("image");
        String c1 = got.getStringExtra("c1");
        String c2 = got.getStringExtra("c2");
        Log.e("IMAGESTRING", img_string);

        // 상품권 base64로 받아서 image화
        String base64Image = img_string.split(",")[1];
        try {
            byte[] imageBytes = base64Image.getBytes();
            InputStream stream = new ByteArrayInputStream(Base64.decode(imageBytes,Base64.DEFAULT));
        //            InputStream is = (InputStream) new URL(img_string).getContent();
            bitmap = BitmapFactory.decodeStream(stream);
            gift.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        giftName.setText(c1 + " - " + c2);

        // 상품권 저장 버튼
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = Environment.getExternalStorageDirectory().toString();
                Date time = Calendar.getInstance().getTime();
                String realPath = Constant.MYPATH + "/SKKOIN_GIFT_"+time.toString() + ".jpg";
                File SKKOINAppDir = new File(Constant.MYPATH);
                Log.e("PATH", Constant.MYPATH);

                if(!SKKOINAppDir.exists() && !SKKOINAppDir.isDirectory() && checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    SKKOINAppDir.mkdirs();
                }

                File file = new File(realPath);
                OutputStream out = null;
                try {
                    out = new FileOutputStream(file);
                    //File write logic here
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    out.flush();
                    out.close();
//                        sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
                    Log.e("PATH", realPath);
                    Toast.makeText(j_giftcontentpopup.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
//                    getApplicationContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Constant.MYPATH)));
                    Intent intent= new Intent(j_giftcontentpopup.this, j_giftmain.class);
                    startActivityForResult(intent, 1);
                    finish();
                } catch (FileNotFoundException e) {
                    Toast.makeText(j_giftcontentpopup.this, "어플리케이션 파일 접근 권한을 확인해주세요.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (IOException e){
                    Toast.makeText(j_giftcontentpopup.this, "어플리케이션 파일 접근 권한을 확인해주세요.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

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
