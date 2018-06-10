package com.wfis.wfis_shop.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wfis.wfis_shop.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdaper extends PagerAdapter {

    private List<String> photos = new ArrayList<>();

    public void setPhotos(List<String> photos) {
        this.photos = photos;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_view_pager, container, false);
        ImageView imageView = view.findViewById(R.id.image);
            Glide.with(container.getContext())
                    .load(photos.get(position))
                    .into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
