package kr.co.softcampus.login.j_giftcon;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import kr.co.softcampus.login.R;

public class gifticonAdapter extends ArrayAdapter<j_giftlist> {
    @Override
    public void add(@Nullable j_giftlist object) {
        super.add(object);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public j_giftlist getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public gifticonAdapter(@NonNull Context context, int resource, @NonNull List<j_giftlist> objects) {
        super(context, resource, objects);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        j_giftlist giftlist = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_gifticon, parent, false);
        }

        Log.e("!!!!!!!!!!!!!", giftlist.getName());

        TextView name, category1, category2, cost, count;
        name = convertView.findViewById(R.id.name);
        category1 = convertView.findViewById(R.id.category1);
        category2 = convertView.findViewById(R.id.category2);
        cost = convertView.findViewById(R.id.cost);
        count = convertView.findViewById(R.id.count);


        name.setText(giftlist.getName());
        category1.setText(giftlist.getCategory1());
        category2.setText(giftlist.getCategory2());
        cost.setText(giftlist.getCost() +" SKK");
        count.setText(giftlist.getCount() + " 개");
        return convertView;
    }


}
