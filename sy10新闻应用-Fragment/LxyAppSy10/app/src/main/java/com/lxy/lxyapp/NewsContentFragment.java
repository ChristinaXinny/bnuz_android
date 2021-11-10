package com.lxy.lxyapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewsContentFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.new_content_frag, container,false);
        return view;
    }

    public void refresh(String newTitle, String newsContent){
        View visibilityLayout = view.findViewById(R.id.visible_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView newTitleText = view.findViewById(R.id.news_title);
        TextView newContentText = view.findViewById(R.id.news_content);
        newTitleText.setText(newTitle);
        newContentText.setText(newsContent);
    }
}
