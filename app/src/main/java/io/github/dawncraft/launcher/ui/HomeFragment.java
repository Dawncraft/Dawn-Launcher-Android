package io.github.dawncraft.launcher.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import io.github.dawncraft.launcher.R;

public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener
{
    protected Context mActivity;
    SliderLayout sliderShow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Add slider
        sliderShow = (SliderLayout) view.findViewById(R.id.slider);
        TextSliderView textSliderView = new TextSliderView(mActivity);
        textSliderView.description("示范图片")
                .image("http://img.tuku.cn/file_thumb/201504/m2015041616571244.jpg")
                .setOnSliderClickListener(this);
        sliderShow.addSlider(textSliderView);

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

    @Override
    public void onSliderClick(BaseSliderView slider)
    {

    }
}
