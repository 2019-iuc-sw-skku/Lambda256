package kr.co.softcampus.login.m_getskkoin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import kr.co.softcampus.login.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import kr.co.softcampus.login.g_MainScreen;
import kr.co.softcampus.login.h_mypage.h_mypage1;
import kr.co.softcampus.login.i_send.i_sendfirst;
import kr.co.softcampus.login.j_giftcon.j_giftmain;
import kr.co.softcampus.login.k_infomain;
import me.relex.circleindicator.CircleIndicator;

public class m_getskkoin3 extends Activity {

    ImageView homebutton;
    ImageView bell;

    ImageButton Mybutton;
    ImageButton Send;
    ImageButton Purchase;
    ImageButton Inform;

    View bot;
    View top;
    TextView screentext;

    PagerAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getskkoin3);

        bot = findViewById(R.id.getskkoin3Bot);
        top=findViewById(R.id.getskkoin3top1);

        screentext=top.findViewById(R.id.screentext);
        screentext.setText("스코인 이용방법");


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


        ViewPager vpPager = findViewById(R.id.vpPager);
        adapter = new MyPagerAdapter(this);
        vpPager.setAdapter(adapter);

        CircleIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(vpPager);

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(m_getskkoin3.this, g_MainScreen.class);
                startActivityForResult(intent, 1);
            }
        });

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(m_getskkoin3.this, h_mypage1.class);
                startActivityForResult(intent, 1);
            }
        });

        Mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(m_getskkoin3.this, h_mypage1.class);
                startActivityForResult(intent, 1);
            }
        });

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(m_getskkoin3.this, i_sendfirst.class);
                startActivityForResult(intent, 1);
            }
        });

        Purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(m_getskkoin3.this, j_giftmain.class);
                startActivityForResult(intent, 1);
            }
        });

        Inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(m_getskkoin3.this, k_infomain.class);
                startActivityForResult(intent, 1);
            }
        });

    }


    public class MyPagerAdapter extends PagerAdapter {
        private int[] images = {R.drawable.popup1,
                R.drawable.popup2,
                R.drawable.popup3,
                R.drawable.popup4};
        private LayoutInflater inflater;
        private Context context;
        public MyPagerAdapter(Context context){
            this.context = context;
        }
        @Override
        public int getCount() {
            return images.length;
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            inflater = (LayoutInflater)context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.slider, container, false);
            ImageView imageView = v.findViewById(R.id.imageView);
            imageView.setImageResource(images[position]);
            container.addView(v);
            return v;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.invalidate();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        overridePendingTransition(0, 0);

    }
}
