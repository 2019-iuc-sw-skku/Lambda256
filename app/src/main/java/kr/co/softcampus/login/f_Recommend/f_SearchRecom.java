package kr.co.softcampus.login.f_Recommend;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import kr.co.softcampus.login.R;

public class f_SearchRecom extends Activity {

    ImageView imageView;


    Button Button_check;
    Button button_cancel;


    EditText editText_address_recommender;

    Boolean flag;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);


        // findViewById
        Button_check = findViewById(R.id.button_confirm);
        button_cancel = findViewById(R.id.button_cancel);
        imageView = findViewById(R.id.imageView);
        editText_address_recommender = findViewById(R.id.address_recommender);


        // Initialize the View
        imageView.setImageResource(R.drawable.logo2);
        flag = false;


        Button_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 추천인 주소 입력받음
                String address = editText_address_recommender.getText().toString();

                // 디버깅용 임시 코드
                if (address.equals("temp")) {

                    flag = true;

                }


                /*
                    성공적으로 추천인 코드를 입력받은 경우
                    ->   flag = true;

                 */


                if (flag == true) {

                    Toast.makeText(f_SearchRecom.this, "추천인에게 코인이 지급되었습니다.", Toast.LENGTH_LONG).show();

                    finish(); // 이전 화면으로 돌아감

                } else {

                    Toast.makeText(f_SearchRecom.this, "추천인 코드를 다시 한 번 확인해주세요.", Toast.LENGTH_SHORT).show();

                }

            }
        });


        // 취소버튼 누르면
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder dialog = new AlertDialog.Builder(f_SearchRecom.this);
                dialog.setTitle("추천인을 입력하지 않으시겠습니까?")
                        .setMessage("추천인 입력은 회원가입시 1회만 가능합니다.")

                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                finish(); // 현재 액티비티 종료

                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create().show();


            }
        });


    }

    // 뒤로가기 버튼
    /*
        1. 뒤로가기 버튼을 누르면?
        2. 추천인 입력 안할거냐고 물어본다

     */
    @Override
    public void onBackPressed() {

        Toast.makeText(this, "추천인을 입력하지 않으셨습니다.", Toast.LENGTH_SHORT).show();
        
        super.onBackPressed();

    }

}