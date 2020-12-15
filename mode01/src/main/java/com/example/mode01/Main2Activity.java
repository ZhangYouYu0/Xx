package com.example.mode01;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mode01.Bean.FoodBean;
import com.example.mode01.Db.DbUtil;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recycler_main;
    private ArrayList<FoodBean> strings;
    private MyAdapter1 myAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initData();
    }

    private void initData() {
        DbUtil dbUtil = new DbUtil();
        List<FoodBean> query = dbUtil.query();
        strings.addAll(query);
        myAdapter1.notifyDataSetChanged();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recycler_main = (RecyclerView) findViewById(R.id.recycler_main);
        recycler_main.setLayoutManager(new LinearLayoutManager(this));
        toolbar.setTitle("首页");
        setSupportActionBar(toolbar);
        strings = new ArrayList<>();
        myAdapter1 = new MyAdapter1(this,strings);
        recycler_main.setAdapter(myAdapter1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.y:
//                Toast.makeText(Main2Activity.this,"",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Main2Activity.this,Main4Activity.class));
                break;
            case R.id.e:
                startActivity(new Intent(Main2Activity.this,Main3Activity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
