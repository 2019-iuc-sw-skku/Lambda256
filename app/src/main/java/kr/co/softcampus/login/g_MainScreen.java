package kr.co.softcampus.login;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.icu.text.IDNA;
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
        Mybutton.setImageResource(R.drawable.picture1);
        Send = bot.findViewById(R.id.Send2);
        Send.setImageResource(R.drawable.picture2);
        Purchase = bot.findViewById(R.id.Purchase2);
        Purchase.setImageResource(R.drawable.picture3);
        Inform = bot.findViewById(R.id.Inform2);
        Inform.setImageResource(R.drawable.picture4);

        walletAddress = findViewById(R.id.walletaddress);
        token = findViewById(R.id.textView16);
        nickname = findViewById(R.id.textView6);


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
        walletAddress.setText(Constant.WADDRESS);
        try {
            token.setText(asyncTask.get(10, TimeUnit.SECONDS));
        } catch (Exception e) {
            token.setText("Connection Error");
        }

        nickAsycnTask.execute();
        try {
            nickname.setText(nickAsycnTask.get(10, TimeUnit.SECONDS));
        } catch (Exception e){
            nickname.setText("Anonymous");
        }

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
                Intent intent = new Intent(g_MainScreen.this, h_mypage1.class);
                startActivityForResult(intent, 1);
            }
        });

        Mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(g_MainScreen.this, h_mypage1.class);
                startActivityForResult(intent, 1);
            }
        });

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(g_MainScreen.this, i_sendfirst.class);
                startActivityForResult(intent, 1);
            }
        });

        Purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(g_MainScreen.this, j_giftmain.class);
                startActivityForResult(intent, 1);
            }
        });

        Inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(g_MainScreen.this, k_infomain.class);
                startActivityForResult(intent, 1);
            }
        });

    }
}
