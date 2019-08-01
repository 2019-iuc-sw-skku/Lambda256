package kr.co.softcampus.login.Connection;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/*
 * Class name: ConnectionClass
 * Descriptions: This class is about all functions to connect servers.
 * 2019. 7. 30.
 * Made by Keonyoung Shim
 * Contact: +8210-9919-9004
 * Email: kyshim9004@gmail.com
 */

public class ConnectionClass {

    private String baseURL;
    private String url_string;
    private URL url;
    private HttpURLConnection con;


    /*  function name: setHeader
     *  Argument: Server type
     *  Operation: set http connection header via Server type
     *
     *  Comment: USE THIS FUNCTION
     */

    private void setHeader(Server to){
        con.setRequestProperty("Content-type", "application/json");

        switch (to){
            case SERVER:
                break;
            case LUNI:
                con.setRequestProperty("Authorization", Constant.getApikey());
                break;
        }
    }


    // Ignore it
/*    public void setUrl_string(String url_string) {
        this.url_string = url_string;
    }*/



    /* function name: MyConnection
     *  Argument: Server type, addurl, connection type, jsonObject to deliver
     *  Operation: connect and get return the result
     *  Return: JSONObject - resultcode, resultmessage
     *
     *  Comment: USE THIS FUNCTION
     */

    public JSONObject MyConnection(Server to, String addurl, ConType conType, JSONObject jsonObject) {

        switch (to) {
            case SERVER:
                baseURL = Constant.BASEURL;
                break;
            case LUNI:
                baseURL = Constant.LUNBASEURL;
                break;
        }

        InputStream is = null;  // 서버에서 읽어온 데이터 저장
        String result = "";     // 상태 반환할 값
        int resultCode = -1;    // 상태 코드


        /* url 값으로 서버에 연결 */
        try {
            if (addurl == null) {
                url_string = baseURL;
            } else {
                url_string = baseURL + addurl;
            }
            Log.e("URL", url_string);
            url = new URL(url_string);
            con = (HttpURLConnection) url.openConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        /* url 값으로 서버에 연결 */

        String json = "";
        switch (conType) {
            case TYPE_GET:
                try {
                    if(addurl == Constant.WALLETCHECK)
                        con = (HttpURLConnection) new URL("https://api.luniverse.io/tx/v1.0/wallets/bridge?walletType="+jsonObject.get("walletType")+"&userKey="+jsonObject.get("userKey")).openConnection();
                    /* 보낼 객체 JSON 화 */
//                    json = jsonObject.toString();
//                    Log.d("Conn_JsonObject", json);
                    /* 보낼 객체 JSON 화 */
                    con.setRequestProperty("Authorization", Constant.getApikey());

                    /* http 소켓 만들기 */
                    con.setRequestMethod("GET");
//                    setHeader(to);
//                    con.setDoOutput(true);
                    con.setDoInput(true);
                    /* http 소켓 만들기 */

                    /* 서버에 보내기 */
//                    DataOutputStream dos = new DataOutputStream(con.getOutputStream());
//                    dos.writeBytes(json);
//                    dos.flush();
                    /* 서버에 보내기 */
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //usage example
//                if (url.contains("blah blah")) {
//                    try {
//                        httpURLConnection.setDoInput(true);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    result = null;
//                }
                break;
            case TYPE_POST:
                try {
                    /* 보낼 객체 JSON 화 */
                    json = jsonObject.toString();
                    Log.d("Conn_JsonObject", json);
                    /* 보낼 객체 JSON 화 */

                    /* http 소켓 만들기 */
                    setHeader(to);
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    /* http 소켓 만들기 */

                    /* 서버에 보내기 */
                    OutputStream os = con.getOutputStream();
                    os.write(json.getBytes(StandardCharsets.UTF_8));
                    os.flush();
                    /* 서버에 보내기 */
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:

        }

        /* 결과값 받기 */
        switch (conType) {
            case TYPE_POST:
                try {
                    resultCode = con.getResponseCode();
                    result = con.getResponseMessage();
                    Log.e("RESULT", resultCode+", " +result);
                    is = con.getInputStream();
                    result = convertInputStreamToString(is);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case TYPE_GET:

            default:
                try {
                    Log.e("RESULT" , con.getResponseMessage() +  ", " +con.getResponseCode());
                    is = con.getInputStream();
                    result = convertInputStreamToString(is);
                    Log.e("REAL", result);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    is = con.getErrorStream();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }

        /* 결과값 받기 */
        JSONObject returnJson = null;
        con.disconnect();     // 서버 연결 해제
        try {
            returnJson = new JSONObject(result);
        } catch (Exception e){
            e.printStackTrace();
        }
//        try {
//            returnJson.put("resultCode", resultCode);
//            returnJson.put("result", result);
//        } catch (JSONException e){
//            e.printStackTrace();
//        }
        return returnJson;
    }

    /* 서버에서 받는 정보를 String 으로 변환 */
    private String convertInputStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        is.close();
        return sb.toString();
    }

}
