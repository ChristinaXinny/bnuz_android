package com.lxy.lxy_sy7_booklist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {

    private int resourceId;

    //resource : book_item.xml
    public BookAdapter(@NonNull Context context, int resource, @NonNull List<Book> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Book book = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView imageView = view.findViewById(R.id.book_img);
        TextView  nameView = view.findViewById(R.id.name);
        TextView authorView  = view.findViewById(R.id.author);
        TextView pressView  = view.findViewById(R.id.press);
        System.out.println(position);
        if (position%2==0){
            view.setBackgroundColor(Color.parseColor("#ECEBFF"));
        }
        else {
            view.setBackgroundColor(Color.parseColor("#FFF2F8"));

        }
        imageView.setImageResource(book.getImg());
        nameView.setText(book.getName());
        authorView.setText(book.getAuthor());
        pressView.setText(book.getPress());


        return view;


    }
}
