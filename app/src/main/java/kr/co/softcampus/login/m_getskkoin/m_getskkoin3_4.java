package kr.co.softcampus.login.m_getskkoin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import kr.co.softcampus.login.R;

public class m_getskkoin3_4 extends Fragment {
    private String title;
    private int page;

    public static m_getskkoin3_4 newInstance(int page, String title){
        m_getskkoin3_4 fragment=new m_getskkoin3_4();
        Bundle args=new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        page=getArguments().getInt("someInt", 0);
        title=getArguments().getString("someTitle");

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.activity_getskkoin3_4,container,false);
        ImageButton tvLabel= view.findViewById(R.id.vpPager);
        tvLabel.setImageResource(R.drawable.popup1);
        return view;
    }
}
