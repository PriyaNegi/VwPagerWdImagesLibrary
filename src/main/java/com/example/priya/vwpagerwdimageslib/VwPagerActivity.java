package com.example.priya.vwpagerwdimageslib;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.priya.vwpagerwdimageslib.adapters.VwPagerAdapter;
import com.example.priya.vwpagerwdimageslib.interfaces.IPositionHandler;
import com.example.priya.vwpagerwdimageslib.interfaces.IViewAnimation;

import java.util.ArrayList;


public class VwPagerActivity extends ActionBarActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{
   ArrayList<String> imgUrlsList;
    int posSelected;
    private ActionBar mActionBar;
    ImageView iv_displayGridList,closeActivityImgVw;
    TextView imageLabel,tv_currentPage;
    ViewPager imagesPager;
    ParamsToDisplay paramsToDisplay;
    public static final String IMAGE_PARAMS = "imageParams";
  static  IPositionHandler posHandler;
    static IViewAnimation viewAnimation;
    public static Intent makeNewInstance(ParamsToDisplay params,Activity mContext)
    {
        if(mContext instanceof IPositionHandler)
        posHandler=(IPositionHandler)mContext;
        if(mContext instanceof IViewAnimation)
            viewAnimation=(IViewAnimation)mContext;
        Intent intent = new Intent(mContext,VwPagerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(IMAGE_PARAMS, params);
        intent.putExtras(bundle);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vw_pager);
        Animation anim= AnimationUtils.loadAnimation(VwPagerActivity.this, R.anim.translate_vwpager);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.setCustomView(R.layout.custom_actionbar_stocks);
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        Toolbar parent =(Toolbar) mActionBar.getCustomView().getParent();//first get parent toolbar of current action bar
        parent.setContentInsetsAbsolute(0,0);
        iv_displayGridList = (ImageView) mActionBar.getCustomView().findViewById(R.id.iv_displayGridList);
        iv_displayGridList.setOnClickListener(this);
        closeActivityImgVw=(ImageView) mActionBar.getCustomView().findViewById(R.id.closeImgVw);
        closeActivityImgVw.setOnClickListener(this);

        imageLabel = (TextView) mActionBar.getCustomView().findViewById(R.id.label);

        imageLabel.setText("Images");
         imagesPager = (ViewPager) findViewById(R.id.imagesPager);
        tv_currentPage= (TextView) findViewById(R.id.tv_currentPage);
        if(getIntent().getExtras()!=null)
        {
            Bundle b=getIntent().getExtras();

            paramsToDisplay=(ParamsToDisplay)b.getSerializable(IMAGE_PARAMS);
            imgUrlsList=paramsToDisplay.getImagesUrlList();
            posSelected=paramsToDisplay.getImgSelectedPosition();
        }

        VwPagerAdapter imagesAdapter = new VwPagerAdapter(this, imgUrlsList);
        imagesPager.setAdapter(imagesAdapter);
        imagesPager.setOffscreenPageLimit(2);
        imagesPager.setCurrentItem(posSelected);
        imagesPager.setOnPageChangeListener(this);
      if(viewAnimation!=null)
        viewAnimation.applyAnimation(imagesPager);
        Animation fade_in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);

        fade_in.setInterpolator(new AccelerateInterpolator());
        fade_in.setDuration(2000);

      mActionBar.getCustomView().startAnimation(fade_in);
        tv_currentPage.setText((imagesPager.getCurrentItem()+1)+" of "+imgUrlsList.size());

    }





    @Override
    public void onClick(View v) {
  if(v.getId()==R.id.closeImgVw) {
   /*   Intent returnIntent = new Intent();
      returnIntent.putExtra("posSelected", imagesPager.getCurrentItem());
      setResult(RESULT_OK, returnIntent);*/
      if(posHandler!=null)
      posHandler.positionHandlerCallBack(imagesPager.getCurrentItem());
     VwPagerActivity.this.finish();
  }

if(v.getId()==R.id.iv_displayGridList) {
    Intent intent = new Intent(VwPagerActivity.this, GridVwActivity.class);


    startActivity(intent);
    overridePendingTransition(R.anim.mapview_slide_up, 0);
}
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
      //  imageLabel.setText("Image "+position);
    }

    @Override
    public void onPageSelected(int position) {
       imageLabel.setText("Image "+(position+1));
        tv_currentPage.setText(position+1 +" of "+imgUrlsList.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onBackPressed() {

    }

    public void animationSet()
    {
        // Scaling
       Animation scale = new ScaleAnimation(0.5f, 1.5f, 0.5f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
// 1 second duration
        scale.setDuration(1000);
// Moving up
        Animation slideUp = new TranslateAnimation(100, 0, -100, 0);
// 1 second duration
        slideUp.setDuration(1000);
// Animation set to join both scaling and moving
        AnimationSet animSet = new AnimationSet(true);
        animSet.setFillEnabled(true);
        //animSet.addAnimation(scale);
        animSet.addAnimation(slideUp);
// Launching animation set
        imagesPager.startAnimation(animSet);
    }
}
