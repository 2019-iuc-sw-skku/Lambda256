package kr.co.softcampus.login.j_giftcon;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import kr.co.softcampus.login.Connection.ConType;
import kr.co.softcampus.login.Connection.ConnectionClass;
import kr.co.softcampus.login.Connection.Constant;
import kr.co.softcampus.login.Connection.Server;
import kr.co.softcampus.login.R;
import kr.co.softcampus.login.b_LoginActivity;
import kr.co.softcampus.login.g_MainScreen;
import kr.co.softcampus.login.h_mypage.h_mypage1;
import kr.co.softcampus.login.h_mypage.h_mypage2;
import kr.co.softcampus.login.i_send.i_sendfirst;
import kr.co.softcampus.login.i_send.sendlist_item;
import kr.co.softcampus.login.k_infomain;
import kr.co.softcampus.login.n_center.n_giftcon1;

public class j_giftmain extends Activity {
    ImageView homebutton;
    ImageView bell;

    ImageView cutlery;
    ImageView coffee_cup;
    ImageView chef_hat;

    ImageButton Mybutton;
    ImageButton Send;
    ImageButton Purchase;
    ImageButton Inform;

    ListView gift_listview;
    ArrayList<j_giftlist> giftlists;

    View bot;
    View top;
    TextView screentext;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giftmain);

        gift_listview = findViewById(R.id.gift_listview);
        giftlists = new ArrayList<>();

        AsyncTask<String, Void, JSONObject> asyncTask = new AsyncTask<String, Void, JSONObject>() {
            @Override
            protected JSONObject doInBackground(String... strings) {
                ConnectionClass cc = new ConnectionClass();
                JSONObject result;
                result = cc.MyConnection(Server.SERVER, Constant.GIFTLIST, ConType.TYPE_POST, new JSONObject());
                return result;
            }
        };

        asyncTask.execute();
        JSONArray results = null;
        JSONObject result = null;
        int jalen = 0;

        try {
            result = asyncTask.get(10, TimeUnit.SECONDS);
            Log.e("!!!!!!!!!!!!!!!!!!!!", result.toString());
            results = result.getJSONObject("data").getJSONArray("content");
            jalen = result.getJSONObject("data").getInt("length");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i =0; i<results.length(); i++){
            try {
                JSONObject tmp = results.getJSONObject(i);
                giftlists.add(new j_giftlist(tmp.getString("name"),
                        tmp.getString("category1"),
                        tmp.getString("category2"),
                        tmp.getInt("cost"),
                        tmp.getInt("count")));
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        gift_listview.setAdapter(new gifticonAdapter(getApplicationContext(), R.layout.item_gifticon, giftlists));

        bot = findViewById(R.id.giftmainbot);
        top=findViewById(R.id.giftmaintop);

        screentext=top.findViewById(R.id.screentext);
        screentext.setText("기프티콘 구매");

        cutlery=findViewById(R.id.cutlery);
        cutlery.setImageResource(R.drawable.cutlery);

        coffee_cup=findViewById(R.id.coffee_cup);
        coffee_cup.setImageResource(R.drawable.coffee_cup);

        chef_hat=findViewById(R.id.chef_hat);
        chef_hat.setImageResource(R.drawable.chef_hat);

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



        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 로딩중 팝업
                CheckTypesTask task = new CheckTypesTask();
                task.execute();

                finish();// 현재 activity 종료

                Intent intent = new Intent(j_giftmain.this, g_MainScreen.class);
                startActivityForResult(intent, 1);
            }
        });

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(j_giftmain.this, h_mypage1.class);
                //startActivityForResult(intent, 1);
            }
        });

        Mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 로딩중 팝업
                CheckTypesTask task = new CheckTypesTask();
                task.execute();

                finish();// 현재 activity 종료

                Intent intent = new Intent(j_giftmain.this, h_mypage1.class);
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
            }
        });

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();// 현재 activity 종료

                Intent intent = new Intent(j_giftmain.this, i_sendfirst.class);
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
            }
        });

        Purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();// 현재 activity 종료

                Intent intent = new Intent(j_giftmain.this, j_giftmain.class);
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

                Intent intent = new Intent(j_giftmain.this, k_infomain.class);
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
                Intent intent = new Intent( j_giftmain.this, b_LoginActivity.class);
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
                j_giftmain.this);

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
            Toast.makeText(j_giftmain.this, "로딩 완료", Toast.LENGTH_SHORT).show();
            super.onPostExecute(result);
        }
    }

}
