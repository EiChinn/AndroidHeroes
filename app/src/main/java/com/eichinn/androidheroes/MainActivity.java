package com.eichinn.androidheroes;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.eichinn.androidheroes.view.CircleProgressView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CircleProgressView cpv = (CircleProgressView) findViewById(R.id.cpv);

        cpv.setCircleColor(Color.YELLOW);

        cpv.setCenterTestColor(Color.RED);
        cpv.setCenterTextSize(20);
        cpv.setCenterText("Java");

        cpv.setArcAngle(120);
        cpv.setArcColor(Color.GRAY);
        cpv.setArcWidth(20);
    }

}
