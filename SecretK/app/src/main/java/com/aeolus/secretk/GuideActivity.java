package com.aeolus.secretk;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity {

    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager guide_view_pager;
    private List<ImageView> pages;


    /**
     * 注册相关
    */
    private Button guide_btn;
    private EditText main_psd;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String Mainpassword;
    MD5 md5=new MD5();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        //初始化
        guide_view_pager = (ViewPager) findViewById(R.id.guide_view_pager);
        guide_btn = (Button) findViewById(R.id.guide_btn);
        main_psd = (EditText) findViewById(R.id.main_psd);


        //设置不可见
        guide_btn.setVisibility(View.GONE);
        main_psd.setVisibility(View.GONE);



        pages = new ArrayList<ImageView>();


        ImageView ivGuide1 = new ImageView(GuideActivity.this);
        ivGuide1.setBackgroundResource(R.drawable.guide_1);
        pages.add(ivGuide1);
        Log.e("111", "111111");

        ImageView ivGuide2 = new ImageView(GuideActivity.this);
        ivGuide2.setBackgroundResource(R.drawable.guide_2);
        pages.add(ivGuide2);
        Log.e("222", "222222");

        ImageView ivGuide3 = new ImageView(GuideActivity.this);
        ivGuide3.setBackgroundResource(R.drawable.guide_3);
        pages.add(ivGuide3);
        Log.e("3333", "3333");

        ImageView ivGuide4 = new ImageView(GuideActivity.this);
        ivGuide4.setBackgroundResource(R.drawable.guide_4);
        pages.add(ivGuide4);
        Log.e("444", "4444");

        ImageView ivGuide5 = new ImageView(GuideActivity.this);
        ivGuide5.setBackgroundResource(R.drawable.guide_5);
        pages.add(ivGuide5);

        ViewPagerAdapter adapter = new ViewPagerAdapter(pages);
        guide_view_pager.setAdapter(adapter);

        guide_view_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 4) {
                    guide_btn.setVisibility(View.VISIBLE);
                    main_psd.setVisibility(View.VISIBLE);


                } else {
                    guide_btn.setVisibility(View.GONE);
                    main_psd.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        pref = getSharedPreferences("mainpassword", MODE_WORLD_READABLE + MODE_WORLD_WRITEABLE);
        editor=pref.edit();


        guide_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Mainpassword = main_psd.getText().toString();
                editor.putString("mainpassword",md5.GetMD5Code(Mainpassword));
                //Toast.makeText(GuideActivity.this,md5.GetMD5Code(Mainpassword),Toast.LENGTH_SHORT).show();
                editor.commit();
                //Log.e("mainpsd", md5.GetMD5Code(Mainpassword));
                    startActivity(new Intent(GuideActivity.this, MainActivity.class));
                }

        });

    }






    class ViewPagerAdapter extends PagerAdapter{
        List<ImageView> pages = null;

        ViewPagerAdapter(List<ImageView> pages){this.pages = pages;}

        //获取Imageview 的数量
        @Override
        public int getCount() {
            return pages.size();
        }

        //判断是否是同一张图片
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return  object == view;
        }

        //pageadapter只缓存三张要显示的图片，如果滑动的图片超出了缓存范围，将图片销毁
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            ((ViewPager)container).removeView(pages.get(position));
        }

        //当要显示的图片进行缓存的时候，会调用此方法进行显示图片的初始化，我们将要显示的imageview加入到viewgroup中，然后作为返回值返回即可
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager)container).addView(pages.get(position));
            return pages.get(position);
        }
    }

}
