package kr.co.softcampus.login.i_send;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import kr.co.softcampus.login.c_Login.c_RETURN_STATE;
import kr.co.softcampus.login.g_MainScreen;
import kr.co.softcampus.login.h_mypage.h_mypage1;
import kr.co.softcampus.login.j_giftcon.j_giftmain;
import kr.co.softcampus.login.k_infomain;

public class i_sendfirst extends Activity {
    ImageView homebutton;
    ImageView bell;

    ImageButton Mybutton;
    ImageButton Send;
    ImageButton Purchase;
    ImageButton Inform;
    Button confirm_Button;

    ListView sendlist;
    ArrayList<sendlist_item> list;
    EditText addr, amnt;

    View bot;
    View top;
    TextView screentext;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        addr = findViewById(R.id.address);
        amnt = findViewById(R.id.amount);
        list = new ArrayList<>();
//        sendlist = findViewById(R.id.sendList);
//        sendlist.setAdapter(new SendAdapter(getApplicationContext(), list));

        bot = findViewById(R.id.sendbot);
        top=findViewById(R.id.sendtop1);

        screentext=top.findViewById(R.id.screentext);
        screentext.setText("송금");

        homebutton=top.findViewById(R.id.homebutton);
        homebutton.setImageResource(R.drawable.home);

        bell = top.findViewById(R.id.bell);
        bell.setImageResource(R.drawable.bell);

        confirm_Button = findViewById(R.id.confirm_button);
        confirm_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!addr.getText().toString().isEmpty() && !amnt.getText().toString().isEmpty()) {
                    list.add(new sendlist_item(addr.getText().toString(), Integer.parseInt(amnt.getText().toString())));
                    AsyncTask<ArrayList<sendlist_item>, Void, JSONObject> sendAsyncTask = new AsyncTask<ArrayList<sendlist_item>, Void, JSONObject>() {
                        @Override
                        protected JSONObject doInBackground(ArrayList<sendlist_item>... arrayLists) {
                            ConnectionClass cc = new ConnectionClass();
                            JSONObject result;
                            try {
                                result = cc.MyConnection(Server.LUNI, Constant.TOKENSEND, ConType.TYPE_POST,
                                        new JSONObject().put("from", Constant.WADDRESS).put("inputs",
                                                new JSONObject().put("receiverAddress", arrayLists[0].get(0).getAddress()).put("valueAmount", arrayLists[0].get(0).getToken() * Constant.TOKEN_UNIT)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                                return null;
                            }

                            return result;
                        }
                    };

                    sendAsyncTask.execute(list);
                    Boolean results = false;
                    JSONObject result = null;

                    try {
                        result = sendAsyncTask.get(10, TimeUnit.SECONDS);
                        results = result.getBoolean("result");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.e("!!!!!!!!!!!!!", result.toString());
                    c_RETURN_STATE return_state;
                    // 결과 - 이메일 혹은 비밀번호가 틀렸는지는 아직 체크하지 않음
                    if (results) {
                        Toast.makeText(i_sendfirst.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(i_sendfirst.this, i_sendfinish.class);
                        intent.putExtra("address", list.get(0).getAddress());
                        intent.putExtra("amount", list.get(0).getToken());
                        list.clear();
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(i_sendfirst.this, "Fail", Toast.LENGTH_SHORT).show();
                        list.clear();
                    }

                }
                else {
                    Toast.makeText(i_sendfirst.this, "주소와 송금 금액을 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Mybutton = bot.findViewById(R.id.Mybutton2);
      //  Mybutton.setImageResource(R.drawable.mypage);

        Send=bot.findViewById(R.id.Send2);
      //  Send.setImageResource(R.drawable.send);

        Purchase=bot.findViewById(R.id.Purchase2);
       // Purchase.setImageResource(R.drawable.giftcon);

        Inform=bot.findViewById(R.id.Inform2);
    //    Inform.setImageResource(R.drawable.info);



        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                finish();// 현재 activity 종료

                Intent intent=new Intent(i_sendfirst.this, g_MainScreen.class);
                startActivityForResult(intent, 1);
            }
        });

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "기능 구현중입니다.", Toast.LENGTH_SHORT).show();

            }
        });

        Mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();// 현재 activity 종료

                Intent intent=new Intent(i_sendfirst.this, h_mypage1.class);
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
            }
        });

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(i_sendfirst.this, "받는 분의 지갑 주소와 송금할 스코인의 양을 입력해주세요", Toast.LENGTH_SHORT).show();
              
            }
        });

        Purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();// 현재 activity 종료

                Intent intent=new Intent(i_sendfirst.this, j_giftmain.class);
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION); // 팝업 애니메이션 제거
                startActivityForResult(intent, 1);
            }
        });

        Inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                finish();// 현재 activity 종료

                Intent intent=new Intent(i_sendfirst.this, k_infomain.class);
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
                Intent intent = new Intent( i_sendfirst.this, b_LoginActivity.class);

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
