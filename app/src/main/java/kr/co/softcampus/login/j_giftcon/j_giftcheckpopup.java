package kr.co.softcampus.login.j_giftcon;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import kr.co.softcampus.login.Connection.ConType;
import kr.co.softcampus.login.Connection.ConnectionClass;
import kr.co.softcampus.login.Connection.Constant;
import kr.co.softcampus.login.Connection.Server;
import kr.co.softcampus.login.R;

/**
 * @name giftcheckpopup
 * @descriptions 구매의사 확인
 */

public class j_giftcheckpopup extends Activity {

    Intent get;
    ImageView imageView6;
    Button button_cancel;
    Button button_purchase;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_giftcheck);

        get = getIntent();

        button_cancel = findViewById(R.id.button_cancel);
        button_purchase = findViewById(R.id.button_purchase);

        imageView6=findViewById(R.id.errorImage);
        imageView6.setImageResource(R.drawable.insert_coin);

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(j_giftcheckpopup.this, j_giftmain.class);
                startActivityForResult(intent, -1);
            }
        });

        button_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(j_giftcheckpopup.this, j_giftfailpopup.class);

                // 해당하는 금액만큼 토큰 송금
                AsyncTask<String, Void, String> asyncTask = new AsyncTask<String, Void, String>() {
                    @Override
                    protected String doInBackground(String... longs) {
                        ConnectionClass cc = new ConnectionClass();
                        JSONObject result;
                        try {
                            result = cc.MyConnection(Server.LUNI, Constant.LUNIPURCHASE, ConType.TYPE_POST,
                                    new JSONObject().put("from", Constant.WADDRESS).put("inputs",new JSONObject().put("valueAmount", longs[0])));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            return null;
                        }

                        try {
                            Log.e("MYFIRSTRESULT", result.toString());
                            return result.getJSONObject("data").getString("txId");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }
                };

                Log.e("MY COST", Long.toString(get.getLongExtra("cost", 0) * Constant.TOKEN_UNIT));
                asyncTask.execute(get.getLongExtra("cost", 0) + "000000000000000000");
                String tx = "";

                // 송금한 transaction id를 이용하여 기프티콘 교환 신청
                try {
                    tx = asyncTask.get(10, TimeUnit.SECONDS);
                    //Log.e("IHIHIHIHIHIHIHIHHIIHIH", tx);
                    JSONObject tmp;

                    do {
                        AsyncTask<JSONObject, Void, JSONObject> checkasyncTask = new AsyncTask<JSONObject, Void, JSONObject>() {
                            @Override
                            protected JSONObject doInBackground(JSONObject... jsonObjects) {
                                ConnectionClass cc = new ConnectionClass();
                                JSONObject result = null;
                                result = cc.MyConnection(Server.LUNI, Constant.HISTORY, ConType.TYPE_GET, jsonObjects[0]);

                                return result;
                            }
                        };

                        Handler handler = new Handler();

                        // transaction 반영시간 고려
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                Log.d("j_giftcheckpopup","Waiting");
                            }
                        }, 2000);

                        checkasyncTask.execute(new JSONObject().put("txid", tx));
                        tmp = checkasyncTask.get();
                    } while(tmp.getJSONObject("data").getJSONObject("history").getString("txStatus").equalsIgnoreCase("WAIT"));


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(tx.equals("")) {
                    startActivityForResult(intent, -1);
                }

                AsyncTask<JSONObject, Void, JSONObject> lasyncTask = new AsyncTask<JSONObject, Void, JSONObject>() {
                    @Override
                    protected JSONObject doInBackground(JSONObject... jsonObjects) {
                        ConnectionClass cc = new ConnectionClass();
                        JSONObject result = null;
                        result = cc.MyConnection(Server.SERVER, Constant.PURCHASE, ConType.TYPE_POST, jsonObjects[0]);

                        return result;
                    }
                };
                JSONObject result = null;
                try {
                    Log.e("txid", tx);
                    lasyncTask.execute(new JSONObject().put("txhash", tx).put("name", get.getStringExtra("name")).put("category1", get.getStringExtra("c1")).put("category2",get.getStringExtra("c2")));
                    result = lasyncTask.get(10, TimeUnit.SECONDS);
                } catch (Exception e) {
                    e.printStackTrace();
                    startActivityForResult(intent, -2);
                }
                try {
                    if (result.getBoolean("result")) {
                        intent = new Intent(j_giftcheckpopup.this, j_giftcontentpopup.class);
                        intent.putExtra("image", result.getJSONObject("data").getString("image"));
                        intent.putExtra("c1", get.getStringExtra("c1"));
                        intent.putExtra("c2", get.getStringExtra("c2"));
                        Log.e("gifticon", result.getJSONObject("data").getString("image"));
                        startActivityForResult(intent, 1);
                        finish();
                    } else{
                        Log.e("MYRESULT", result.toString());
                        startActivityForResult(intent, -4);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    startActivityForResult(intent, -3);
                }
                //기프티콘 구매
            }
        });
    }

    public boolean onTouchEvent(MotionEvent event){
        return event.getAction() != MotionEvent.ACTION_OUTSIDE;
    }

    public void onBackPressed(){
        return;
    }
}
