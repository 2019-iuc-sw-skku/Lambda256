package kr.co.softcampus.login.n_center;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import kr.co.softcampus.login.R;

/**
 * @name customer_center_first
 * @descriptions FAQ 화면
 * @author 배수영
 */

public class n_customer_center_first extends Activity {

    ImageView imageView_logo;

    ImageView folder3;
    ImageView folder4;

    Button personinfo;
    Button skkoin;
    Button giftcon;
    Button send;
    Button call;
    Button talk;
    Button email;

    ImageButton Mybutton;
    ImageButton Send;
    ImageButton Purchase;
    ImageButton Inform;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_customer_center_first);

        imageView_logo = findViewById(R.id.imageView17);
        imageView_logo.setImageResource(R.drawable.logo2);


        folder3=findViewById(R.id.folder3);
        folder3.setImageResource(R.drawable.folder_12);

        folder4=findViewById(R.id.folder4);
        folder4.setImageResource(R.drawable.folder_12);

        personinfo=findViewById(R.id.personinfo);
        skkoin=findViewById(R.id.skkoin);
        giftcon=findViewById(R.id.giftcon);
        send=findViewById(R.id.send);
        call=findViewById(R.id.call);
        talk=findViewById(R.id.talk);
        email=findViewById(R.id.email);


        personinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1=new Intent(n_customer_center_first.this, n_personinfo.class);
                startActivityForResult(intent1, 1);
            }
        }); //회원정보 수정

        skkoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(n_customer_center_first.this, n_skkoin.class);
                startActivityForResult(intent1, 1);
            }
        }); //회원정보 수정
        giftcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(n_customer_center_first.this, n_giftcon.class);
                startActivityForResult(intent, 1);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(n_customer_center_first.this, n_send.class);
                startActivityForResult(intent, 1);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //전화번호 제공
            }
        });

        talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //카카오톡 정보 제공
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //이메일 정보 제공
            }
        });


        /*
        Mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 로딩중 팝업
                CheckTypesTask task = new CheckTypesTask();
                task.execute();

                finish();

                Intent intent = new Intent(n_customer_center_first.this, h_mypage1.class);
                startActivityForResult(intent, 1);
            }
        });

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                Intent intent = new Intent(n_customer_center_first.this, i_sendfirst.class);
                startActivityForResult(intent, 1);
            }
        });

        Purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                Intent intent = new Intent(n_customer_center_first.this, j_giftmain.class);
                startActivityForResult(intent, 1);
            }
        });

        Inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 로딩중 팝업
                CheckTypesTask task = new CheckTypesTask();
                task.execute();

                finish();
                Intent intent = new Intent(n_customer_center_first.this, k_infomain.class);
                startActivityForResult(intent, 1);
            }
        });

         */



    }

    @Override
    protected void onPause() {
        super.onPause();

        overridePendingTransition(0, 0); // 액티비티 종료 시 애니메이션 제거

    }

    private class CheckTypesTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog asyncDialog = new ProgressDialog(
                getApplicationContext());

        @Override
        protected void onPreExecute() {
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("로딩중입니다..");

            // show dialog
            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            asyncDialog.dismiss();

            finish();
            Toast.makeText(getApplicationContext(), "로딩 완료", Toast.LENGTH_SHORT).show();
            super.onPostExecute(result);
        }
    }




}
