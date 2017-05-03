package io.github.dawncraft.dawnlauncher.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import io.github.dawncraft.dawnlauncher.R;

public class HomeFragment extends Fragment
{
    protected Context mActivity;
    SliderLayout sliderShow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Add slider
        sliderShow = (SliderLayout) view.findViewById(R.id.slider);
//        TextSliderView textSliderView = new TextSliderView(mActivity);
//        textSliderView.description("示范图片")
//                .image("http://img3.imgtn.bdimg.com/it/u=1798501903,126021128&fm=23&gp=0.jpg");
//        sliderShow.addSlider(textSliderView);

        return view;
    }

    @Override
    public void onAttach(Context context)
    {
        this.mActivity = context;
        super.onAttach(context);
    }

    @Override
    public void onDestroy()
    {
        sliderShow.stopAutoCycle();
        super.onDestroy();
    }
}
