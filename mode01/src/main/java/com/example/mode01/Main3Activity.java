package com.example.mode01;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mode01.Bean.FoodBean;
import com.example.mode01.Db.DbUtil;

public class Main3Activity extends AppCompatActivity {

    private Toolbar toolbar_main3;
    private EditText ed_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
    }

    private void initView() {
        toolbar_main3 = (Toolbar) findViewById(R.id.toolbar_main3);
        ed_name = (EditText) findViewById(R.id.ed_name);

        toolbar_main3.setTitle("编辑笔记");
        setSupportActionBar(toolbar_main3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.s:
                submit();
                DbUtil dbUtil = new DbUtil();
                String s = ed_name.getText().toString();
                FoodBean foodBean = new FoodBean();
                foodBean.setDes(s);
                long insert = dbUtil.insert(foodBean);
                if(insert>0){
                    Toast.makeText(Main3Activity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Main3Activity.this,Main2Activity.class));
                    finish();
                }else{
                    Toast.makeText(Main3Activity.this,"已经添加过类似的数据了",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void submit() {
        // validate
        String name = ed_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入.........", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
