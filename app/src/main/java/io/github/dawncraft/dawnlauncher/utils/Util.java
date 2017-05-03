package io.github.dawncraft.dawnlauncher.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

public class Util
{
    public static void toast(Context ctx, String msg)
    {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context ctx, @StringRes int msgId)
    {
        Toast.makeText(ctx, ctx.getString(msgId), Toast.LENGTH_SHORT).show();
    }
}

    /*	boolean islight = false;
	Camera camera = Camera.open();

		Button button = (Button) findViewById(R.id.toggleButton1);
		button.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			if(!islight)
			{
				Parameters parameter = camera.getParameters();
				parameter.setFlashMode(Parameters.FLASH_MODE_TORCH);
				camera.setParameters(parameter);

				islight = true;
			}
			else
			{
			    Parameters parameter = camera.getParameters();
			    parameter.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
			    camera.setParameters(parameter);

			    islight = false;
			}
		}});

        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        viewpager.setAdapter(adapter);
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener());

    private class OnPageChangeListener implements ViewPager.OnPageChangeListener
    {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageSelected(int position) {}

        @Override
        public void onPageScrollStateChanged(int state) {}

    }
		        */
