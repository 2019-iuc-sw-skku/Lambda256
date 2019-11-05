package kr.co.softcampus.login.h_mypage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import kr.co.softcampus.login.Connection.Constant;
import kr.co.softcampus.login.R;

public class listAdapter extends BaseAdapter {
    Context context;
    ArrayList<list_item> list_itemArrayList;

    TextView date1;
    TextView content1;
    TextView plus1;


    public listAdapter(Context context, ArrayList<list_item> list_itemArrayList) {
        this.context = context;
        this.list_itemArrayList = list_itemArrayList;
    }


    @Override
    //리스트 뷰가 몇 개의 아이템을 가지고 있는지 알려주는 함수
    public int getCount() {
        return this.list_itemArrayList.size();
    }

    @Override
    public Object getItem(int position) {

        return list_itemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //+이면 mypage1_plus
        //-이면 mypage1_minus
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.mypage1_plusactivity,null);
            date1= convertView.findViewById(R.id.date1);
            content1= convertView.findViewById(R.id.content1);
            plus1= convertView.findViewById(R.id.plus1);

        }

        date1.setText(list_itemArrayList.get(position).getWrite_date());

        content1.setText(list_itemArrayList.get(position).getTx().substring(0,4) +
                list_itemArrayList.get(position).getTx().substring(41));//list_itemArrayList.get(position).getNote());
        plus1.setText(Double.toString(list_itemArrayList.get(position).getAmount()));

        if(list_itemArrayList.get(position).getIsSucceed().equals("SUCCEED")){
            plus1.setTextColor(context.getResources().getColor(R.color.DarkGreen));
        } else{
            plus1.setTextColor(context.getResources().getColor(R.color.Red));
        }
        return convertView;
    }
}
