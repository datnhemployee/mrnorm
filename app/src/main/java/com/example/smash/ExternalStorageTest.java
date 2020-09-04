package com.example.smash;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

// Book) Page 122)
//      In theory, we could only include the code of
//   our application in the APK file and
//   would have to download all the asset files
//   from a server to the SD card the first time our
//   application were started. Many of the high-profile
//   games on Android do this.
public class ExternalStorageTest extends AppCompatActivity {
    final private int REQUEST_READ_EXTERNAL_STORAGE = 123;
    final private int REQUEST_WRITE_EXTERNAL_STORAGE = 123;

    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.textView = new TextView(this);
        setContentView(this.textView);
        boolean permission_read = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
        boolean permission_write = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
        Log.d("Permission test",permission_read + " " + permission_write);
        if (!permission_read || !permission_write) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_READ_EXTERNAL_STORAGE
            );

        } else {
            this.ReadExternalStorage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_READ_EXTERNAL_STORAGE:
                for (int i=0;i< permissions.length; i++) {
                    String permission = permissions[i];
                    int grantResult = grantResults[i];

                    if (permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)
                    || permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        if (grantResult == PackageManager.PERMISSION_GRANTED) {
                            this.ReadExternalStorage();
                        } else {
                            Toast.makeText(
                                    ExternalStorageTest.this,
                                    "Permission Denied",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void ReadExternalStorage() {
        String state = Environment.getExternalStorageState();
        // Always check whether the external storage device is mounted
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            this.textView.setText("No external storage mounted");
        } else {
            File externalDir = Environment.getExternalStorageDirectory();
            File pathFile = new File(externalDir.getAbsolutePath() + File.separator + "text.txt");
            try {
                String tempText = "This is my first test: ABD";
                this.writeTextFile(pathFile, tempText);
                String text = this.readTextFile(pathFile);
                this.textView.setText(text);
                if (!pathFile.delete()){
                    this.textView.setText("Couldn't remove temporary file");
                }
            } catch (IOException e) {
                this.textView.setText("Something went wrong! " + e.getMessage());
            }
        }
    }

    private  void writeTextFile(File file, String text) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(text);
        writer.close();
    }

    private  String readTextFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder text = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            text.append(line);
            text.append("\n");
        }
        reader.close();
        return text.toString();
    }
}
