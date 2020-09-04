package com.example.smash.framework.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.preference.PreferenceManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileIO implements com.example.smash.framework.core.FileIO {
    Context context;
    AssetManager assetManager;
    String externalStoragePath;

    public FileIO(Context context) {
        this.context = context;
        this.assetManager = context.getAssets();
        this.externalStoragePath = context.getExternalFilesDir(null)
                .getAbsolutePath() + File.separator;
    }

    @Override
    public InputStream readAsset(String fileName) throws IOException {
        return this.assetManager.open(fileName);
    }

    @Override
    public InputStream readFile(String fileName) throws IOException {
        return new FileInputStream(this.externalStoragePath + fileName);
    }

    @Override
    public OutputStream writeFile(String fileName) throws IOException {
        return new FileOutputStream(this.externalStoragePath + fileName);
    }

    public SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this.context);
    }
}
