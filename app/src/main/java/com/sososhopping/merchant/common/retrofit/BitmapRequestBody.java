package com.sososhopping.merchant.common.retrofit;

import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

public class BitmapRequestBody extends RequestBody {

    Bitmap bitmap;

    public BitmapRequestBody(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return MediaType.parse("image/jpeg");
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 99, sink.outputStream());
    }
}
