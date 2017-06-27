
package com.example.ayou.jk_takeout.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;



public class imageload extends ImageLoader{
    @Override
    public void displayImage(Context context, Object o, ImageView imageView) {
        Picasso.with(context)
               .load((String)o)
               .into(imageView);
    }
}
