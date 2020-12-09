package com.example.hw1.helpers;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class UIHelper {

    public static void convertIMG(Context context, int id, ImageView img) {
        Glide.with(context)
                .load(id)
                .into(img);
    }
}
