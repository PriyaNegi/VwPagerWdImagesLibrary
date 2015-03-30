package com.example.priya.vwpagerwdimageslib;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class GridVwActivity extends ActionBarActivity implements View.OnClickListener{
private ActionBar mActionBar;
    ImageView closeActivityImgVw;
    TextView tv_imagesCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_vw);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.setCustomView(R.layout.gridvw_custom_actionbar);
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        Toolbar parent =(Toolbar) mActionBar.getCustomView().getParent();//first get parent toolbar of current action bar
        parent.setContentInsetsAbsolute(0, 0);
        closeActivityImgVw=(ImageView) mActionBar.getCustomView().findViewById(R.id.closeImgVw);
        closeActivityImgVw.setOnClickListener(this);

        tv_imagesCount = (TextView) mActionBar.getCustomView().findViewById(R.id.images_count);

        tv_imagesCount.setText("Gallery");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_grid_vw, menu);
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

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.closeImgVw)
        {
           GridVwActivity.this.finish();
            overridePendingTransition(0,R.anim.mapview_slide_down);
        }
    }
}
