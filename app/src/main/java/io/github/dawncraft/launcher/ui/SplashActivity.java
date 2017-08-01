package io.github.dawncraft.launcher.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import io.github.dawncraft.launcher.R;

public class SplashActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Picasso.with(this).load("http://img3.duitang.com/uploads/item/201606/10/20160610112945_ECVXw.jpeg").into((ImageView) findViewById(R.id.splash_image));

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, 3000);
    }

    @Override
    public void onBackPressed() {}
}
