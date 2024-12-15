package com.example.runnablehandler;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    int number;
    Runnable runnable;
    Handler handler;
    Button button;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView = findViewById(R.id.textView);
        number = 0;
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

    }

    public void Start(View view) throws InterruptedException {

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                textView.setText("Time : " + number);
                number++;
                textView.setText("Time :" + number);
                handler.postDelayed(runnable,1000);
            }
        };

        handler.post(runnable);
        button.setEnabled(false);
        button2.setEnabled(true);

    }
    public void Stop(View view){

        handler.removeCallbacks(runnable);
        button2.setEnabled(false);
        button.setEnabled(true);

    }
    public void Reset(View view){
        number = 0;
        textView.setText("Time :" + number);
        button2.setEnabled(true);
    }
}
