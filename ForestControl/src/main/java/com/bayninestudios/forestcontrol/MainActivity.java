package com.bayninestudios.forestcontrol;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        View view = this.findViewById(R.layout.activity_main);
//        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void playSound(View view) {
        Log.d("debug", " " + (String)view.getTag());
//        new SendSound().execute("1");
    }

    private class SendSound extends AsyncTask<String, Void, Integer> {

        protected Integer doInBackground(String... commands) {
            try {
                Socket socket = new Socket("192.168.1.187",50007);
                String str = commands[0];
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())), true);
                out.println(str);
                out.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
}
