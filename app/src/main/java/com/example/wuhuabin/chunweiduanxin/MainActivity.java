package com.example.wuhuabin.chunweiduanxin;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> text ;
    SmsReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initList();
        this.onAddBtnPress();

//        myReceiver = new SmsReceiver();
//        IntentFilter itFilter = new IntentFilter();
//        itFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        registerReceiver(myReceiver, itFilter);

    }

    public void onAddBtnPress(){
        Button addBtn = (Button) findViewById(R.id.add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = (EditText) findViewById(R.id.input);
                Log.i(input.getText().toString(),"input value");
                text.add(input.getText().toString());
                Log.i(text.toString(),"text");
            }
        });
    }


    public void initList(){
        text = new ArrayList<String>();
        DeletableAdapter adapter = new DeletableAdapter(this,text);
        ListView list_test = (ListView) findViewById(R.id.list);
        list_test.setAdapter(adapter);
    }

}
