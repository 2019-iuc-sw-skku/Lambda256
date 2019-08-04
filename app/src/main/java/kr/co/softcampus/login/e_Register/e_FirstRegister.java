package kr.co.softcampus.login.e_Register;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kr.co.softcampus.login.Connection.ConType;
import kr.co.softcampus.login.Connection.ConnectionClass;
import kr.co.softcampus.login.Connection.Constant;
import kr.co.softcampus.login.Connection.Server;
import kr.co.softcampus.login.R;

public class e_FirstRegister extends Activity {

    // ID

    EditText EditText_Email;
    EditText EditText_password;

    TextView Email_recheck_TextView;
    TextView code_recheck_TextView;


    Button Button_verify;
    Button Check_Button;

    ImageView LogoImage;

    PopupWindow mPopupWindow;

    Boolean canGo;


    // variables
    String Email;
    int verify_code;

    // flag
    boolean verification_code_flag = false;


    public static boolean checkEmail(String email) {
        String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        boolean isNormal = m.matches();
        return isNormal;
    }

    public void show_msg() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        imm.hideSoftInputFromWindow(EditText_Email.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(EditText_password.getWindowToken(), 0);


        // Alert을 이용해 종료시키기
        AlertDialog.Builder dialog = new AlertDialog.Builder(e_FirstRegister.this);
        dialog.setTitle("")
                .setMessage("인증 요청 메시지를 보낼까요?")
                .setPositiveButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(e_FirstRegister.this, "취소", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(e_FirstRegister.this, e_Sendemail1popup.class);
                        startActivityForResult(intent, 1);
                        // BackEnd 인증요청 팝업창 띄움
/*                        View popupLayout = getLayoutInflater().inflate(R.layout.popup_email, null);
                        mPopupWindow = new PopupWindow(popupLayout, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);//getApplicationContext().getResources().getDisplayMetrics().widthPixels * 2 / 3, getApplicationContext().getResources().getDisplayMetrics().heightPixels * 2 / 3);
                        //popupLayout 에서 (LinearLayout 을 사용) 레이아웃이 둘러싸고 있는 컨텐츠의 크기 만큼 팝업 크기를 지정
                        mPopupWindow.showAtLocation(popupLayout, Gravity.CENTER, 0, 0);

                        mPopupWindow.setOutsideTouchable(false);
                        mPopupWindow.setTouchable(true);
                        mPopupWindow.setFocusable(false);

                        // 외부 영역 선택히 PopUp 종료

//                        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
                        mPopupWindow.update();



                        Button cancel = (Button) popupLayout.findViewById(R.id.button2);
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "Verification code가 전송되었습니다.", Toast.LENGTH_SHORT).show();
                                mPopupWindow.dismiss();
                            }
                        });*/


                    }
                }).create().show();


    }

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstregister);
        canGo = false;

        // ID
        EditText_Email = findViewById(R.id.EditText_Email);
        EditText_password = findViewById(R.id.EditText_verify_code);

        Email_recheck_TextView = findViewById(R.id.Email_recheck_TextView);
        code_recheck_TextView = findViewById(R.id.code_recheck_TextView);

        Button_verify = findViewById(R.id.verify_button);
        Check_Button = findViewById(R.id.Check_Button);

        LogoImage = findViewById(R.id.LogoImage);


        //
        LogoImage.setImageResource(R.drawable.logo2);


        // 인증요청 버튼을 누르면
        Button_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Email = EditText_Email.getText().toString();

                if (checkEmail(Email) == false) {
                    Email_recheck_TextView.setText("이메일을 다시 확인해주세요");
                } else {
                    Email_recheck_TextView.setText("");

                    AsyncTask<String, Void, Boolean> asyncTask = new AsyncTask<String, Void, Boolean>() {
                        @Override
                        protected Boolean doInBackground(String... strings) {
                            ConnectionClass cc = new ConnectionClass();

                            JSONObject result;
                            try {
                                result = cc.MyConnection(Server.SERVER, Constant.DUPCHECK, ConType.TYPE_POST, new JSONObject().put("email", Email));
                                return result.getBoolean("result");
                            } catch (Exception e) {
                                e.printStackTrace();
                                return false;
                            }
                        }
                    };

                    asyncTask.execute();
                    Boolean flag = false;
                    try {
                        flag = asyncTask.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (flag) {
                        canGo = true;
                        show_msg();
                    } else {
                        Toast.makeText(e_FirstRegister.this, "이미 등록된 사용자입니다.", Toast.LENGTH_SHORT).show();
                    }


                    // BackEnd 에 인증요청하기

                }

            }
        });

        // 확인 버튼 누르면
        Check_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (canGo) {
                    Intent intent = new Intent(e_FirstRegister.this, e_SecondRegister.class);
                    intent.putExtra("email", EditText_Email.getText().toString());
                    startActivityForResult(intent, 1);
                } else {
                    // 인증번호 입력받기
                    String input_verify_code;
                    input_verify_code = EditText_password.getText().toString();


                    // BackEnd 에서 인증코드 확인할 것
                /*
                    인증코드가 맞으면
                    verification_code_flag = true;
                    추가하기
                 */


                    // 아래 코드는 임시 코드 ( 디버깅을 위한 )

                    if (input_verify_code.equals("0000"))
                        verification_code_flag = true;


                    if (verification_code_flag == false)
                        code_recheck_TextView.setText("인증번호를 다시 확인해주세요");
                    else {
                        Intent intent = new Intent(e_FirstRegister.this, e_EndRegister.class);
                        startActivityForResult(intent, 1);
                    }
                }


            }
        });


    }
}