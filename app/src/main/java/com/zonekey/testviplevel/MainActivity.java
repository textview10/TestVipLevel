package com.zonekey.testviplevel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.zonekey.testviplevel.view.VipLevelView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private VipLevelView vipLevelView;
    private AppCompatButton btn_test0,btn_test1,btn_test2,btn_test3,btn_test4,btn_move_level;
    private int movelevel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialView();
    }

    private void initialView() {
        vipLevelView = (VipLevelView) findViewById(R.id.vip_level_view);
        btn_test0 = (AppCompatButton) findViewById(R.id.btn_test0);
        btn_test1 = (AppCompatButton) findViewById(R.id.btn_test1);
        btn_test2 = (AppCompatButton) findViewById(R.id.btn_test2);
        btn_test3 = (AppCompatButton) findViewById(R.id.btn_test3);
        btn_test4 = (AppCompatButton) findViewById(R.id.btn_test4);
        btn_move_level = (AppCompatButton) findViewById(R.id.btn_test_move_level);
        btn_test0.setOnClickListener(this);
        btn_test1.setOnClickListener(this);
        btn_test2.setOnClickListener(this);
        btn_test3.setOnClickListener(this);
        btn_test4.setOnClickListener(this);
        btn_move_level.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_test0:
                vipLevelView.setVipLevel(0);
                break;
            case R.id.btn_test1:
                vipLevelView.setVipLevel(1);
                break;
            case R.id.btn_test2:
                vipLevelView.setVipLevel(2);
                break;
            case R.id.btn_test3:
                vipLevelView.setVipLevel(3);
                break;
            case R.id.btn_test4:
                vipLevelView.setVipLevel(4);
                break;
            case R.id.btn_test_move_level:
                vipLevelView.setVipLevel(movelevel);
                movelevel++;
                break;
        }
    }
}
