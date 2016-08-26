package com.example.admin.primeornot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {

    private Button showHint;
    private TextView hintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        final Intent intent=getIntent();
        showHint=(Button)findViewById(R.id.hintButton);
        hintView=(TextView)findViewById(R.id.hintView);
        showHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hintView.setVisibility(View.VISIBLE);
                intent.putExtra("hintUsed",true);
                setResult(RESULT_OK,intent);
            }
        });
    }
}
