package com.example.mode03;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_pic;
    private Button bnt_az;
    private Button bnt_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        et_pic = (EditText) findViewById(R.id.et_pic);
        bnt_az = (Button) findViewById(R.id.bnt_az);
        bnt_no = (Button) findViewById(R.id.bnt_no);
        Intent intent = getIntent();
        String pic = intent.getStringExtra("pic");
        et_pic.setText(pic);
        bnt_az.setOnClickListener(this);
        bnt_no.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bnt_az:
                String s = et_pic.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse("file://"+s),"application/vnd.android.package-archive");
                startActivity(intent);


                break;
            case R.id.bnt_no:
                finish();
                break;
        }
    }


}
