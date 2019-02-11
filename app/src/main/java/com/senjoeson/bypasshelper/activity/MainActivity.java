package com.senjoeson.bypasshelper.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.senjoeson.bypasshelper.R;
import com.senjoeson.bypasshelper.adapter.SmsModelAdapter;
import com.senjoeson.bypasshelper.base.DbUtils;
import com.senjoeson.bypasshelper.model.SmsModel;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    public RecyclerView mRecyclerView;
    public SmsModelAdapter mSmsModelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        mRecyclerView = findViewById(R.id.recyclerview);
        setSupportActionBar(toolbar);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSmsModelAdapter = new SmsModelAdapter();
        mRecyclerView.setAdapter(mSmsModelAdapter);

        //动态获取权限了
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(Manifest.permission.RECEIVE_SMS, Manifest.permission.CALL_PHONE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            Toast.makeText(MainActivity.this, "为了程序的稳定运行,请同意此次权限申请", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });


        getAllSmsModel();
        fab.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            getAllSmsModel();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     *  显示当前的任务
     */
    private void getAllSmsModel() {
        Observable.create(new ObservableOnSubscribe<List<SmsModel>>() {
            @Override
            public void subscribe(ObservableEmitter<List<SmsModel>> emitter) throws Exception {
                List<SmsModel> allSmsModel = DbUtils.getSmsModelDao().getAllSmsModel();
                emitter.onNext(allSmsModel);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<SmsModel>>() {
                    @Override
                    public void accept(List<SmsModel> smsModels) throws Exception {
                        mSmsModelAdapter.setDatas(smsModels);
                        mSmsModelAdapter.notifyDataSetChanged();
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Intent intent = new Intent(MainActivity.this, AddSmsModelActivity.class);
                startActivity(intent);
                break;
        }
    }


}
