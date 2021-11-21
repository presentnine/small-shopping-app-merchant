package com.sososhopping.merchant.util.retrofit.request;

import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

public class BitmapRequestBody extends RequestBody {

    Bitmap data;

    public BitmapRequestBody(Bitmap bitmap) {
        this.data = bitmap;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return MediaType.parse("image/jpeg");
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        data.compress(Bitmap.CompressFormat.JPEG, 99, sink.outputStream());
    }
}
