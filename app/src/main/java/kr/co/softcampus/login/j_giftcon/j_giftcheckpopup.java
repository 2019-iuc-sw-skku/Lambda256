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
                AsyncTask<Double, Void, String> asyncTask = new AsyncTask<Double, Void, String>() {
                    @Override
                    protected String doInBackground(Double... doubles) {
                        ConnectionClass cc = new ConnectionClass();
                        JSONObject result;
                        try {
                            result = cc.MyConnection(Server.LUNI, Constant.LUNIPURCHASE, ConType.TYPE_POST,
                                    new JSONObject().put("from", Constant.WADDRESS).put("inputs",new JSONObject().put("valueAmount", doubles[0])));
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

                asyncTask.execute(get.getDoubleExtra("cost", 0.0) * Constant.TOKEN_UNIT);
                String tx = "";
                try {
                    tx = asyncTask.get(10, TimeUnit.SECONDS);
                    Log.e("IHIHIHIHIHIHIHIHHIIHIH", tx);
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
                        handler.postDelayed(new Runnable() {
                            public void run() {

                            }
                        }, 2000);
                        checkasyncTask.execute(new JSONObject().put("txid", tx));
                        tmp = checkasyncTask.get();
                        Log.e("IHIHIHIHIHIHIHIHHIIHIH", tmp.getJSONObject("data").getJSONObject("history").toString());
                    } while(tmp.getJSONObject("data").getJSONObject("history").getString("txStatus").equalsIgnoreCase("WAIT"));
                    //Log.e("IHIHIHIHIHIHIHIHHIIHIH", tmp.toString());
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
//                    lasyncTask.execute(new JSONObject().put("txhash", tx).put("name", get.getStringExtra("name")).put("category1", get.getStringExtra("c1")).put("category2",get.getStringExtra("c2")));
                    lasyncTask.execute(new JSONObject().put("txhash", tx).put("name", get.getStringExtra("name")).put("category1", get.getStringExtra("c1")).put("category2",get.getStringExtra("c2")));
                    result = lasyncTask.get(10, TimeUnit.SECONDS);
                } catch (Exception e) {
                    e.printStackTrace();
                    startActivityForResult(intent, -2);
                }
                try {
                    if (result.getBoolean("result")) {
                        intent = new Intent(j_giftcheckpopup.this, j_giftsuccesspopup.class);
                        intent.putExtra("image", result.getJSONObject("data").getString("image"));
                        startActivityForResult(intent, 1);
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
