package kr.co.softcampus.login.h_mypage;

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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import kr.co.softcampus.login.Connection.ConType;
import kr.co.softcampus.login.Connection.ConnectionClass;
import kr.co.softcampus.login.Connection.Constant;
import kr.co.softcampus.login.Connection.Server;
import kr.co.softcampus.login.R;
import kr.co.softcampus.login.b_LoginActivity;
import kr.co.softcampus.login.g_MainScreen;
import kr.co.softcampus.login.i_send.i_sendfirst;
import kr.co.softcampus.login.j_giftcon.j_giftmain;
import kr.co.softcampus.login.k_infomain;

public class h_mypage1 extends Activity {

    Context mContext;

    String nick;

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
    TextView screentext, walletAddress, token, nickname;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage1);

        mContext = this;

        bot = findViewById(R.id.mypage1Bot);
        top = findViewById(R.id.mypage1top1);

        nickname=findViewById(R.id.textView14);

        screentext=top.findViewById(R.id.screentext);
        screentext.setText("마이페이지");

        imageView11=findViewById(R.id.imageView11);
        imageView11.setImageResource(R.drawable.picture5);

        homebutton=top.findViewById(R.id.homebutton);
        homebutton.setImageResource(R.drawable.home);

        bell = top.findViewById(R.id.bell);
        bell.setImageResource(R.drawable.bell);


        Mybutton = bot.findViewById(R.id.Mybutton2);
        Mybutton.setImageResource(R.drawable.mypage);

        Send=bot.findViewById(R.id.Send2);
        Send.setImageResource(R.drawable.send);

        Purchase=bot.findViewById(R.id.Purchase2);
        Purchase.setImageResource(R.drawable.giftcon);

        Inform=bot.findViewById(R.id.Inform2);
        Inform.setImageResource(R.drawable.info);

        walletAddress = findViewById(R.id.textView19);
        token = findViewById(R.id.textView29);

        // 현재 잔고 가져오는 코드
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

        // 닉네임 가져오는 코드
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
            token.setText(Double.toString(Double.parseDouble(asyncTask.get(10, TimeUnit.SECONDS))/Constant.TOKEN_UNIT));
        } catch (Exception e){
            token.setText("Connection Error");
        }

        if(Constant.NICK.equals("")) {
            nickAsycnTask.execute();
            try {
                Constant.NICK = nickAsycnTask.get(10, TimeUnit.SECONDS);
            } catch (Exception e) {
                nickname.setText("Anonymous");
            }
        }
        nickname.setText(Constant.NICK);

        editbutton=findViewById(R.id.editbutton);

        listView= findViewById(R.id.my_listview);

        list_itemArrayList=new ArrayList<list_item>();
/*
        list_itemArrayList.add(new list_item(new Date(System.currentTimeMillis()),"이성균님에게 송금","-7000"));
        list_itemArrayList.add(new list_item(new Date(System.currentTimeMillis()),"교내 봉사","+10000"));
*/
        listAdapter=new listAdapter(h_mypage1.this, list_itemArrayList);
        listView.setAdapter(listAdapter);

        walletAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("WalletAddress", walletAddress.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(mContext, "지갑주소가 복사되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 로딩중 팝업
                CheckTypesTask task = new CheckTypesTask();
                task.execute();

                finish();// 현재 activity 종료

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

        // 프로필 수정 activity
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

                // 로딩중 팝업
                CheckTypesTask task = new CheckTypesTask();
                task.execute();

                finish();// 현재 activity 종료

                Intent intent=new Intent(h_mypage1.this, h_mypage1.class);
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
            }
        });

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();// 현재 activity 종료

                Intent intent=new Intent(h_mypage1.this, i_sendfirst.class);
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
            }
        });

        Purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();// 현재 activity 종료

                Intent intent=new Intent(h_mypage1.this, j_giftmain.class);
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
            }
        });

        Inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 로딩중 팝업
                CheckTypesTask task = new CheckTypesTask();
                task.execute();

                finish();// 현재 activity 종료

                Intent intent=new Intent(h_mypage1.this, k_infomain.class);
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
                Intent intent = new Intent( h_mypage1.this, b_LoginActivity.class);
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

    private class CheckTypesTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog asyncDialog = new ProgressDialog(
                h_mypage1.this);

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
            Toast.makeText(h_mypage1.this, "로딩 완료", Toast.LENGTH_SHORT).show();
            super.onPostExecute(result);
        }
    }



}
