package com.sososhopping.merchant.common.bindingadapter;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class BitmapImageViewAdapter {

    @BindingAdapter({"imgBitmap"})
    public static void setBitmap(ImageView imageView, Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
