package com.zarudna.navigationwizard.ui.textfunction;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zarudna.navigationwizard.R;
import com.zarudna.navigationwizard.databinding.FragmentTextBinding;

/**
 * Fragment to display text from loaded menu
 */

public class TextFunctionFragment extends Fragment {

    private static final String TEXT_ARG = "text_arg";

    private TextFunctionViewModel mViewModel;

    public static TextFunctionFragment newInstance(String text) {
        TextFunctionFragment instance = new TextFunctionFragment();

        Bundle args = new Bundle();
        args.putString(TEXT_ARG, text);
        instance.setArguments(args);

        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(TextFunctionViewModel.class);

        String text = (getArguments() != null) ? getArguments().getString(TEXT_ARG, "") : "";
        mViewModel.setText(text);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentTextBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_text, container, false);
        dataBinding.setViewModel(mViewModel);

        return dataBinding.getRoot();
    }
}
