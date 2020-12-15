package com.example.mode01;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mode01.Bean.FoodBean;
import com.example.mode01.Db.DbUtil;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {

    private Toolbar toolbar_main3;
    private EditText ed_name1;
    private RecyclerView recycler_main4;
    private ArrayList<String> list;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        initView();
    }

    private void initView() {
        toolbar_main3 = (Toolbar) findViewById(R.id.toolbar_main4);
        ed_name1 = (EditText) findViewById(R.id.ed_name1);
        recycler_main4 = (RecyclerView) findViewById(R.id.recycler_main4);
        toolbar_main3.setTitle("搜索");
        setSupportActionBar(toolbar_main3);
        recycler_main4.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        myAdapter = new MyAdapter(this, list);
        recycler_main4.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu3,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.si:
                submit();

                new Thread(){
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String s = ed_name1.getText().toString();
                                DbUtil dbUtil = new DbUtil();
                                List<FoodBean> query = dbUtil.query();
                                for (int i = 0; i <query.size() ; i++) {
                                    if(query.get(i).getDes().equals(s)){
                                        String des = query.get(i).getDes();
                                        Toast.makeText(Main4Activity.this,des,Toast.LENGTH_SHORT).show();
                                        list.add(des);
                                    }
                                    myAdapter.notifyDataSetChanged();
                                }
                            }
                        });
                    }
                }.start();


                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void submit() {
        // validate
        String name1 = ed_name1.getText().toString().trim();
        if (TextUtils.isEmpty(name1)) {
            Toast.makeText(this, "请输入.........", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
