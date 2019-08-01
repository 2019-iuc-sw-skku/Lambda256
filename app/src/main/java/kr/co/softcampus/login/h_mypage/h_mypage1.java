package kr.co.softcampus.login.h_mypage;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import kr.co.softcampus.login.Connection.ConType;
import kr.co.softcampus.login.Connection.ConnectionClass;
import kr.co.softcampus.login.Connection.Constant;
import kr.co.softcampus.login.Connection.Server;
import kr.co.softcampus.login.R;
import kr.co.softcampus.login.g_MainScreen;
import kr.co.softcampus.login.i_send.i_sendfirst;
import kr.co.softcampus.login.j_giftcon.j_giftmain;
import kr.co.softcampus.login.k_infomain;

public class h_mypage1 extends Activity {
    ImageView homebutton;
    ImageView bell;

    ImageView imageView11;
    Button editbutton;
    ImageButton Mybutton;
    ImageButton Send;
    ImageButton Purchase;
    ImageButton Inform;

    ListView listView;
    listAdapter listAdapter;
    ArrayList<list_item> list_itemArrayList;
    View bot;
    View top;
    TextView screentext, walletAddress, token;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage1);

        bot = findViewById(R.id.mypage1Bot);
        top = findViewById(R.id.mypage1top1);

        screentext=top.findViewById(R.id.screentext);
        screentext.setText("마이페이지");

        imageView11=findViewById(R.id.imageView11);
        imageView11.setImageResource(R.drawable.picture5);

        homebutton=top.findViewById(R.id.homebutton);
        homebutton.setImageResource(R.drawable.home);

        bell = top.findViewById(R.id.bell);
        bell.setImageResource(R.drawable.bell);


        Mybutton = bot.findViewById(R.id.Mybutton2);
        Mybutton.setImageResource(R.drawable.picture1);

        Send=bot.findViewById(R.id.Send2);
        Send.setImageResource(R.drawable.picture2);

        Purchase=bot.findViewById(R.id.Purchase2);
        Purchase.setImageResource(R.drawable.picture3);

        Inform=bot.findViewById(R.id.Inform2);
        Inform.setImageResource(R.drawable.picture4);

        walletAddress = findViewById(R.id.textView19);
        token = findViewById(R.id.textView29);

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

        asyncTask.execute();
        walletAddress.setText(Constant.WADDRESS);
        try {
            token.setText(asyncTask.get(10, TimeUnit.SECONDS));
        } catch (Exception e){
            token.setText("Connection Error");
        }


        editbutton=findViewById(R.id.editbutton);

        listView= findViewById(R.id.my_listview);

        list_itemArrayList=new ArrayList<list_item>();

        list_itemArrayList.add(new list_item(new Date(System.currentTimeMillis()),"이성균님에게 송금","-7000"));
        list_itemArrayList.add(new list_item(new Date(System.currentTimeMillis()),"교내 봉사","+10000"));

        listAdapter=new listAdapter(h_mypage1.this, list_itemArrayList);
        listView.setAdapter(listAdapter);

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(h_mypage1.this, g_MainScreen.class);
                startActivityForResult(intent, 1);
            }
        });

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent=new Intent(h_mypage1.this, listAdapter.class);
               // startActivityForResult(intent, 1);
            }
        }); //알람 기능 구현

        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(h_mypage1.this, h_mypage2.class);
                startActivityForResult(intent, 1);
            }
        });

        Mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(h_mypage1.this, h_mypage1.class);
                startActivityForResult(intent, 1);
            }
        });

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(h_mypage1.this, i_sendfirst.class);
                startActivityForResult(intent, 1);
            }
        });

        Purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(h_mypage1.this, j_giftmain.class);
                startActivityForResult(intent, 1);
            }
        });

        Inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(h_mypage1.this, k_infomain.class);
                startActivityForResult(intent, 1);
            }
        });
    }
}
