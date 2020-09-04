package com.example.smash.framework.core;

import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FileIO {
    InputStream readAsset(String fileName) throws IOException;
    InputStream readFile(String fileName) throws IOException;
    OutputStream writeFile(String fileName) throws IOException;

    interface TouchHandlers extends View.OnTouchListener {

    }
}
