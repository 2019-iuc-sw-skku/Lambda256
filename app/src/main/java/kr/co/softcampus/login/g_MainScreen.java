package kr.co.softcampus.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import kr.co.softcampus.login.Connection.ConType;
import kr.co.softcampus.login.Connection.ConnectionClass;
import kr.co.softcampus.login.Connection.Constant;
import kr.co.softcampus.login.Connection.Server;
import kr.co.softcampus.login.h_mypage.h_mypage1;
import kr.co.softcampus.login.i_send.i_sendfirst;
import kr.co.softcampus.login.j_giftcon.j_giftmain;

public class g_MainScreen extends Activity {
    Context mContext;

    ImageView bell;

    ImageButton Mybutton;
    ImageButton Send;
    ImageButton Purchase;
    ImageButton Inform;

    ImageView imageView8;
    ImageView logo;

    TextView walletAddress;
    TextView token;
    TextView nickname;

    View bot;
    View top;

    String nick;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        mContext = this;

        imageView8 = findViewById(R.id.imageView8);
        imageView8.setImageResource(R.drawable.picture5);

        bell = findViewById(R.id.bell);
        bell.setImageResource(R.drawable.bell);

        logo = findViewById(R.id.logo);
        logo.setImageResource(R.drawable.logo);

        bot = findViewById(R.id.mainpageBot);

        top = findViewById(R.id.mainpagetop2);

        Mybutton = bot.findViewById(R.id.Mybutton2);
        Mybutton.setImageResource(R.drawable.mypage);

        Send=bot.findViewById(R.id.Send2);
        Send.setImageResource(R.drawable.send);

        Purchase=bot.findViewById(R.id.Purchase2);
        Purchase.setImageResource(R.drawable.giftcon);

        Inform=bot.findViewById(R.id.Inform2);
        Inform.setImageResource(R.drawable.info);

        walletAddress = findViewById(R.id.walletaddress);
        token = findViewById(R.id.textView16);
        nickname = findViewById(R.id.textView6);

   //     Toast.makeText(g_MainScreen.this, "로그인 하였습니다", Toast.LENGTH_LONG).show();

        // 현재 지갑에 있는 금액 확인 하는 코드
        AsyncTask<String, Void, String> asyncTask = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                ConnectionClass cc = new ConnectionClass();
                JSONObject result = cc.MyConnection(Server.LUNI, Constant.BALANCE, ConType.TYPE_GET, new JSONObject());
                try {
                    return new JSONObject(result.toString()).getJSONObject("data").getString("balance");
                } catch (Exception e) {
                    e.printStackTrace();
                    return "-1";
                }
            }
        };

        // 닉네임 받아오는 코드
        AsyncTask<String, Void, String> nickAsycnTask = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                ConnectionClass cc = new ConnectionClass();
                try {
                    JSONObject result = cc.MyConnection(Server.SERVER, Constant.NICKNAME, ConType.TYPE_POST, new JSONObject().put("email", Constant.EMAIL));
                    return result.getJSONObject("data").getString("nickname");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "Anonymous";
            }
        };

        asyncTask.execute();
        double curTokenDouble = 0;
        long curToken = 0;
        walletAddress.setText(Constant.WADDRESS);
        try {
            curTokenDouble = Double.parseDouble(asyncTask.get(10, TimeUnit.SECONDS))/Constant.TOKEN_UNIT;
            curToken = Math.round(curTokenDouble);
            token.setText(Long.toString(curToken));
        } catch (Exception e) {
            e.printStackTrace();
            token.setText("Connection Error");
        }

        if(Constant.NICK.isEmpty()) {
            nickAsycnTask.execute();
            try {
                Constant.NICK = nickAsycnTask.get(10, TimeUnit.SECONDS);
            } catch (Exception e) {
                nick = "Anonymous";
            }
        }
        nickname.setText(Constant.NICK);


        walletAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("WalletAddress", walletAddress.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(mContext, "지갑주소가 복사되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(mContext, "기능 구현중입니다.", Toast.LENGTH_SHORT).show();

            }
        });

        Mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();// 현재 activity 종료
                Intent intent = new Intent(g_MainScreen.this, h_mypage1.class);

                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거

                startActivityForResult(intent, 1);
            }
        });

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();// 현재 activity 종료
                Intent intent = new Intent(g_MainScreen.this, i_sendfirst.class);
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거

                startActivityForResult(intent, 1);
            }
        });

        Purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();// 현재 activity 종료

                Intent intent = new Intent(g_MainScreen.this, j_giftmain.class);
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
            }
        });

        Inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                finish();// 현재 activity 종료

                Intent intent = new Intent(g_MainScreen.this, k_infomain.class);
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
            }
        });







    }
    @Override
    protected void onPause() {
        super.onPause();

        overridePendingTransition(0, 0);

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("로그아웃 하시겠습니까?").setMessage("");

        builder.setPositiveButton("로그아웃", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                Toast.makeText(getApplicationContext(), "로그아웃 되었습니다", Toast.LENGTH_SHORT).show();
                finishAffinity();
                Intent intent = new Intent( g_MainScreen.this, b_LoginActivity.class);
                startActivity(intent);
                System.exit(0);
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }






}
