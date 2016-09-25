package cn.edu.bistu.cs.se.handle26;

import android.os.Message;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;





public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView=(TextView) findViewById(R.id.textcontent);
        final Button btnStart=(Button)findViewById(R.id.btnStart);
        final android.os.Handler handler =new android.os.Handler() {
            @Override
            public void handleMessage(Message message) {
                    textView.setText(message.arg1 + "");
            }

            };
        final Runnable myWorker=new Runnable() {
            @Override
            public void run() {
                int progress=0;
                while(progress<=100){
                    Message message=new Message();
                    message.arg1=progress;
                    handler.sendMessage(message);
                    progress+=10;
                    try{
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                Message message=handler.obtainMessage();
                message.arg1=-1;
                handler.sendMessage(message);
            }
        };

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread workThread=new Thread(null,myWorker,"workThread");
                workThread.start();
            }
        });
    }
}