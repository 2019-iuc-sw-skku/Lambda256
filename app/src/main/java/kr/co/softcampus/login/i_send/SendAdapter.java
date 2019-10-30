package kr.co.softcampus.login.i_send;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.co.softcampus.login.R;

public class SendAdapter extends ArrayAdapter<sendlist_item> {
    public SendAdapter(Context context, ArrayList<sendlist_item> sendlist_items){
        super(context, 0, sendlist_items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        sendlist_item sendlistItem = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.send_list,parent,false);
        }
        EditText address = (EditText) convertView.findViewById(R.id.address);
        EditText amount = (EditText) convertView.findViewById(R.id.amount);
//
//        address.setText(address.getText());
//        amount.setText(amount.getText());

        return convertView;
    }
}
