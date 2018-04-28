package com.zarudna.navigationwizard.ui.imagefunction;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zarudna.navigationwizard.R;

/**
 * Fragment to display image from loaded menu
 */

public class ImageFunctionFragment extends Fragment {

    private static final String IMAGE_ARG = "image_arg";

    private String mImageUrl;

    public static ImageFunctionFragment newInstance(String text) {
        ImageFunctionFragment instance = new ImageFunctionFragment();

        Bundle args = new Bundle();
        args.putString(IMAGE_ARG, text);
        instance.setArguments(args);

        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mImageUrl = (getArguments() != null) ? getArguments().getString(IMAGE_ARG, "") : "";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_image, container, false);
        ImageView imageView = fragmentView.findViewById(R.id.image);

        Glide.with(this).load(mImageUrl).into(imageView);

        return fragmentView;
    }
}
