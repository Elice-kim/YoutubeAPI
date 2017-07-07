package com.wannaone.elice.youtubeapi.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wannaone.elice.youtubeapi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomImgFragment extends Fragment {

    private static String ARG_IMAGE_RESOURCE = "ARG_IMAGE_RESOURCE";
    private int imageRes;

    public static BottomImgFragment newInstance(int imageRes) {
        BottomImgFragment imageFragment = new BottomImgFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_IMAGE_RESOURCE, imageRes);
        imageFragment.setArguments(bundle);
        return imageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageRes = getArguments().getInt(ARG_IMAGE_RESOURCE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bottom_img, container, false);

        ImageView image = (ImageView) v.findViewById(R.id.image_icon);
        image.setImageResource(imageRes);
        return v;
    }

}
