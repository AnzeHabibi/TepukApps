package com.example.tepukapps.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tepukapps.R;
import com.example.tepukapps.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;


public class HomeFragment extends Fragment {
    private View view;
    private int[] mImages = new int[]{
            R.drawable.test1 ,R.drawable.test2,R.drawable.test3
    };
    private String[] mImagesTitle = new String[]{
            "test 1","test 2", "test 3"
    };
    private CarouselView carouselView;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);
            }
        });
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getContext(), mImagesTitle[position], Toast.LENGTH_SHORT).show();
            }
        });
        tabLayout = getActivity().findViewById(R.id.tabLayout);
        viewPager = getActivity().findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.AddFragment(new OrganikFragment(),"Organik");
        adapter.AddFragment(new AnorganikFragment(),"Anorganik");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return  view;
    }
}
