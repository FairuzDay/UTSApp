package com.unikom.ujiantengahsemester;

import android.content.Context;
import android.content.Intent;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unikom.ujiantengahsemester.adapters.ViewPagerAdapter;
/**tanggal : 21-05-2019
 *Nim     : 10116568
 *Nama    : Muh.Fairuz Hadi Day
 *Kelas    : IF-13
 */
public class OnBoardingActivity extends AppCompatActivity {

    private ViewPager mSlide;
    private LinearLayout mDotsLayout;

    private ViewPagerAdapter mAdapterSlide;
    private TextView[] mDots;

    private Button mBtnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        mSlide = (ViewPager) findViewById(R.id.view_pager_slide);
        mDotsLayout = (LinearLayout) findViewById(R.id.layout_dots);
        mBtnSkip = (Button) findViewById(R.id.btn_skip);

        mAdapterSlide = new ViewPagerAdapter(this);

        mSlide.setAdapter(mAdapterSlide);

        SharedPreferences sharedPref = OnBoardingActivity.this.getPreferences(Context.MODE_PRIVATE);
        int firstOpen = sharedPref.getInt("firstOpen", 0);

        if (firstOpen == 1){
            startActivity(new Intent(OnBoardingActivity.this, LoginActivity.class));
            finish();
        }


        mBtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = OnBoardingActivity.this.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("firstOpen", 1);
                editor.apply();
                editor.commit();
                startActivity(new Intent(OnBoardingActivity.this, LoginActivity.class));
                finish();
            }
        });
        addDots(0);
        mSlide.addOnPageChangeListener(pagerListener);
    }

    public void addDots(int position){
        mDotsLayout.removeAllViews();
        mDots = new TextView[3];
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setPadding(10,10,10,10);
            mDots[i].setTextColor(getResources().getColor(R.color.colorPrimary));

            mDotsLayout.addView(mDots[i]);
        }
        if (mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }
    ViewPager.OnPageChangeListener pagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDots(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
