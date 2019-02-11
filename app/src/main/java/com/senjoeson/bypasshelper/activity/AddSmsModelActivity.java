package com.senjoeson.bypasshelper.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.senjoeson.bypasshelper.R;
import com.senjoeson.bypasshelper.base.DbUtils;
import com.senjoeson.bypasshelper.model.SmsModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <pre>
 *     author : sunqiao
 *     e-mail : sunqiao@kayak.com.cn
 *     time   : 2019/02/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class AddSmsModelActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText mNoticePerson;
    public EditText mNoticePhone;
    public EditText mComment;
    public Button mSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sms_model);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
            getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        }


        mNoticePerson = findViewById(R.id.notice_person);
        mNoticePhone = findViewById(R.id.notice_phone);
        mComment = findViewById(R.id.comment);
        mSubmit = findViewById(R.id.submit);


        mSubmit.setOnClickListener(this);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                insertSmsModel();
                break;
        }
    }


    private void insertSmsModel() {
        if (TextUtils.isEmpty(mNoticePerson.getText().toString().trim()) ||
                TextUtils.isEmpty(mNoticePhone.getText().toString().trim()) ||
                TextUtils.isEmpty(mNoticePerson.getText().toString().trim())) {
            Toast.makeText(this, "信息未填写完整", Toast.LENGTH_SHORT).show();
            return;
        }

        Disposable subscribe = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                SmsModel smsModel = new SmsModel();
                smsModel.setMsgContent("分流抢票成功");
                smsModel.setNoticeName(mNoticePerson.getText().toString().trim());
                smsModel.setNoticePhoneNumber(mNoticePhone.getText().toString().trim());
                smsModel.setMsgContent(mComment.getText().toString().trim());
                Long insert = DbUtils.getSmsModelDao().insert(smsModel);
                emitter.onNext(insert);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long result) throws Exception {
                        if (result > 0) {
                            Toast.makeText(AddSmsModelActivity.this, "插入成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(AddSmsModelActivity.this, "插入失败", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });


    }
}
