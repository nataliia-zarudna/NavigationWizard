package com.zarudna.navigationwizard.ui.urlfunction;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.zarudna.navigationwizard.R;

/**
 * Created by nsirobaba on 4/27/18.
 */

public class UrlFunctionFragment extends Fragment {

    private static final String URL_ARG = "url_arg";

    private String mUrl;

    public static UrlFunctionFragment newInstance(String text) {
        UrlFunctionFragment instance = new UrlFunctionFragment();

        Bundle args = new Bundle();
        args.putString(URL_ARG, text);
        instance.setArguments(args);

        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUrl = (getArguments() != null) ? getArguments().getString(URL_ARG, "") : "";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_url, container, false);
        WebView webView = fragmentView.findViewById(R.id.web_view);
        webView.loadUrl(mUrl);

        return fragmentView;
    }
}
