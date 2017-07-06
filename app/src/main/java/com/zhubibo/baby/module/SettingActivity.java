package com.zhubibo.baby.module;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhubibo.baby.R;
import com.zhubibo.baby.util.DateUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by kevin on 2017/6/16.
 */

public class SettingActivity extends AppCompatActivity {

    private Date birthday;
    private String babyName;
    private boolean showVolumeSetting;

    private Toolbar toolbar;
    private TextView settingNameTv, settingBirthdayTv;
    private SwitchCompat settingVolmeSwitch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("设置");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        birthday = (Date) getIntent().getSerializableExtra("birthday");
        babyName = getIntent().getStringExtra("baby_name");
        showVolumeSetting = getIntent().getBooleanExtra("show_volume_setting", true);

        // 获取UI
        settingVolmeSwitch = (SwitchCompat) findViewById(R.id.settingVolumeSwitch);
        settingVolmeSwitch.setChecked(showVolumeSetting);
        settingNameTv = (TextView) findViewById(R.id.settingNameTv);
        settingNameTv.setText(babyName);
        settingBirthdayTv = (TextView) findViewById(R.id.settingBirthdayTv);
        settingBirthdayTv.setText(DateUtil.getDateStr(birthday, "yyyy-MM-dd"));
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("birthday", birthday);
        intent.putExtra("baby_name", babyName);
        intent.putExtra("show_volume_setting", showVolumeSetting);
        setResult(RESULT_OK, intent);
        super.finish();
    }

    public void settingName(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("设置姓名");

        View rootView = View.inflate(builder.getContext(), R.layout.dialog_input, null);
        final EditText inputEdt = (EditText) rootView.findViewById(R.id.dialogInputEdt);
        inputEdt.setText(babyName);
        inputEdt.setSelection(babyName.length());

        builder.setView(rootView);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String inputStr = inputEdt.getText().toString();
                if (TextUtils.isEmpty(inputStr)) {
                    Toast.makeText(SettingActivity.this, "名称不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    babyName = inputStr;
                    settingNameTv.setText(babyName);
                    dialog.dismiss();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void settingBirthday(View view) {
        final Calendar calendar = Calendar.getInstance();
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
        dialog.setCanceledOnTouchOutside(true);
        DatePicker datePicker = dialog.getDatePicker();
        datePicker.setMinDate(DateUtil.getDate("1900-01-01", "yyyy-MM-dd").getTime());
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 1);
        datePicker.setMaxDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public void settingVolume(View view) {
        showVolumeSetting = settingVolmeSwitch.isChecked();
    }
}
