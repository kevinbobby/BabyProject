package com.zhubibo.baby;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.zhubibo.baby.module.SettingActivity;
import com.zhubibo.baby.util.DateUtil;
import com.zhubibo.baby.util.PreferenceUtil;

import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        SeekBar.OnSeekBarChangeListener,
        MyVolumeReceiver.VolumeChangedActionCallback {

    private SeekBar ringBar, alarmBar, musicBar, callBar;
    private TextView nameTv, dayTv, tv1, tv2, tv3, navHeaderNameTv;
    private ImageView birthdayIv;
    private AudioManager audioManager;

    private static final String dateFormat = "yyyy-MM-dd";
    private String birthdayStr = "2017-07-07";
    private Date birthday;
    private String babyName;

    private MyVolumeReceiver mVolumeReceiver;

    public static String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TAG = getClass().getSimpleName();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "后续开发中，敬请期待", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navHeaderNameTv = (TextView) navigationView.getHeaderView(0).findViewById(R.id.navHeaderNameTv);
        System.out.println(navHeaderNameTv != null);

        // 音量部分
        mVolumeReceiver = new MyVolumeReceiver();
        mVolumeReceiver.setCallback(this);

        ringBar = (SeekBar) findViewById(R.id.ringBar);
        alarmBar = (SeekBar) findViewById(R.id.alarmBar);
        musicBar = (SeekBar) findViewById(R.id.musicBar);
        callBar = (SeekBar) findViewById(R.id.callBar);

        // 获取当前音量
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        updateVolume();

        // 倒计时部分
        init();

        nameTv = (TextView) findViewById(R.id.nameTv);
        dayTv = (TextView) findViewById(R.id.dayTv);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        birthdayIv = (ImageView) findViewById(R.id.birthdayIv);

        updateDayArea();
    }

    private void init() {
        birthdayStr = (String) PreferenceUtil.getValue(PreferenceUtil.BIRTHDAY, birthdayStr);
        birthday = DateUtil.getDate(birthdayStr, dateFormat);
        babyName = (String) PreferenceUtil.getValue(PreferenceUtil.BABY_NAME, getString(R.string.app_name));
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter() ;
        filter.addAction("android.media.VOLUME_CHANGED_ACTION") ;
        registerReceiver(mVolumeReceiver, filter) ;
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mVolumeReceiver);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_setting) {
            Intent settingIntent = new Intent(this, SettingActivity.class);
            settingIntent.putExtra("birthday", birthday);
            settingIntent.putExtra("baby_name", babyName);
            startActivityForResult(settingIntent, Constants.REQUEST_CODE_MAIN_TO_SETTING);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_CODE_MAIN_TO_SETTING) {
            if (resultCode == RESULT_OK) {
                birthday = (Date) data.getSerializableExtra("birthday");
                babyName = data.getStringExtra("baby_name");

                PreferenceUtil.saveValue(PreferenceUtil.BIRTHDAY, DateUtil.getDateStr(birthday, dateFormat));
                PreferenceUtil.saveValue(PreferenceUtil.BABY_NAME, babyName);
                updateDayArea();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            switch (seekBar.getId()) {
                case R.id.ringBar:
                    audioManager.setStreamVolume(AudioManager.STREAM_RING, progress, AudioManager.FLAG_PLAY_SOUND);
                    break;
                case R.id.alarmBar:
                    audioManager.setStreamVolume(AudioManager.STREAM_ALARM, progress, AudioManager.FLAG_PLAY_SOUND);
                    break;
                case R.id.musicBar:
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_PLAY_SOUND);
                    break;
                case R.id.callBar:
                    if (progress < 1) {
                        progress = 1;
                        seekBar.setProgress(progress);
                    }
                    audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, progress, AudioManager.FLAG_PLAY_SOUND);
                    break;
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    /**
     * 更新声音
     */
    @Override
    public void updateVolume() {
        // 手机铃声
        int ringValue = audioManager.getStreamVolume(AudioManager.STREAM_RING);
        int ringMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
        // 闹钟
        int alarmValue = audioManager.getStreamVolume(AudioManager.STREAM_ALARM);
        int alarmMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM);
        // 媒体音量
        int musicValue = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int musicMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        // 通话
        int callValue = audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
        int callMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);

        ringBar.setMax(ringMax);
        ringBar.setProgress(ringValue);
        ringBar.setOnSeekBarChangeListener(this);

        alarmBar.setMax(alarmMax);
        alarmBar.setProgress(alarmValue);
        alarmBar.setOnSeekBarChangeListener(this);

        musicBar.setMax(musicMax);
        musicBar.setProgress(musicValue);
        musicBar.setOnSeekBarChangeListener(this);

        callBar.setMax(callMax);
        callBar.setProgress(callValue);
        callBar.setOnSeekBarChangeListener(this);
    }

    /**
     * 更新日期
     */
    private void updateDayArea() {
        nameTv.setText(babyName);
        navHeaderNameTv.setText(babyName);

        int diffDay = DateUtil.calcDiffDay(birthday);

        if (diffDay <= 0) {
            boolean isBirthday = DateUtil.isBirthday(birthday);
            int age = DateUtil.getBirthday(birthday);

            if (isBirthday && age > 0) {
                tv1.setText("");
                tv2.setText("今天");
                dayTv.setText(age + "");
                tv3.setText("岁生日啦!");
                birthdayIv.setVisibility(View.VISIBLE);
            } else {
                diffDay = Math.abs(diffDay);
                diffDay = diffDay + 1;
                tv1.setText("");
                tv2.setText("今天已经");
                dayTv.setText(diffDay + "");
            }
        } else {
            dayTv.setText(diffDay + "");
        }
    }
}
