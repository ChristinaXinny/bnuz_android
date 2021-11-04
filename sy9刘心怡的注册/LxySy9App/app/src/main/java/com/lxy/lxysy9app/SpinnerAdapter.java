package com.lxy.lxysy9app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SpinnerAdapter extends BaseAdapter {

    private Context context;
    private String[] array;
    private int layoutId;


    public SpinnerAdapter(Context context, String[] array, int layoutId) {
        this.context = context;
        this.array = array;
        this.layoutId = layoutId;
    }


    @Override
    public int getCount() {
        return array.length;
    }


    @Override
    public Object getItem(int position) {
        return array[position];
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView != null ? convertView : View.inflate(context, layoutId, null);
        TextView txt_name = (TextView) item.findViewById(R.id.txt_name);
        txt_name.setText(array[position]);
        return item;
    }
}
