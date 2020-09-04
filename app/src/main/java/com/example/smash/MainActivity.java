package com.example.smash;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

//import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends ListActivity {
    String tests[] = {
            "LifeCycleTest",
            "SingleTouchTest",
//            "MultitouchTest",
            "KeyTest",
            "AssetTest",
            "ExternalStorageTest",
            "SoundPoolTest",
            "MediaPlayerTest",
            "FullScreenTest",
            "RenderViewTest",
            "ShapeTest",
            "BitmapTest",
            "FontTest",
            "SurfaceTest"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        setListAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                this.tests
                ));


    }

    @Override
    protected void onListItemClick(
            ListView l,
            View v,
            int position,
            long id) {
        super.onListItemClick(l, v, position, id);

        String testName = tests[position];
        try {
            Class clazz = Class.forName("com.example.smash."+
                    testName);
            Intent intent = new Intent(this, clazz);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
