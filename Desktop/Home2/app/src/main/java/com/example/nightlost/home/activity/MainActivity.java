package com.example.nightlost.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.nightlost.home.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intview();
    }

    private void intview() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn1:
                Intent intent = new Intent(this,HealthySchemeActivity.class);
                startActivity(intent);
                break;
            case R.id.btn2:
                Intent intent1 = new Intent(this,PhysicalDataActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn3:
                Intent intent2 = new Intent(this,TabInquiryActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn4:
                Intent intent3 = new Intent(this,MainHomeActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn5:
                Intent intent4 = new Intent(this,UseHelpActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
