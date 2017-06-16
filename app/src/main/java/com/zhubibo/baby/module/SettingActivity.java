package com.zhubibo.baby.module;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.zhubibo.baby.R;
import com.zhubibo.baby.util.DateUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by kevin on 2017/6/16.
 */

public class SettingActivity extends AppCompatActivity {

    private Date birthday;

    private Toolbar toolbar;
    private TextView settingNameTv, settingBirthdayTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("设置");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        birthday = (Date) getIntent().getSerializableExtra("birthday");

        // 获取UI
        settingNameTv = (TextView) findViewById(R.id.settingNameTv);
        settingBirthdayTv = (TextView) findViewById(R.id.settingBirthdayTv);
    }

    public void settingName(View view) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.set
    }

    @TargetApi(Build.VERSION_CODES.N)
    public void settingBirthday(View view) {
        final Calendar calendar = Calendar.getInstance();
        System.out.println(birthday);
        calendar.setTime(birthday);
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                birthday = calendar.getTime();
                settingBirthdayTv.setText(DateUtil.getDateStr(birthday, "yyyy-MM-dd"));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//        DatePicker datePicker = dialog.getDatePicker();
//        datePicker.setMinDate(DateUtil.getDate("1900-01-01", "yyyy-MM-dd").getTime());
//        calendar.setTime(new Date());
//        calendar.add(Calendar.YEAR, 1);
//        datePicker.setMaxDate(calendar.getTimeInMillis());
        dialog.show();
    }
}
