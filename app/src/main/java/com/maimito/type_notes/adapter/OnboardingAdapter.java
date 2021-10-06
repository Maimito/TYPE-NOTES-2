package com.maimito.type_notes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maimito.type_notes.R;
import com.maimito.type_notes.model.OnboardingList;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class OnboardingAdapter extends SliderViewAdapter<OnboardingAdapter.OnboardingVH> {
    private Context context;
    private List<OnboardingList> onboardingLists;

    public OnboardingAdapter(Context context, List<OnboardingList> onboardingLists){
        this.context = context;
        this.onboardingLists = onboardingLists;
    }

    @Override
    public OnboardingVH onCreateViewHolder(ViewGroup parent){
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.onboard_layout, parent, false);
        return new OnboardingVH(inflate);
    }

    @Override
    public void onBindViewHolder(OnboardingAdapter.OnboardingVH vh, int position){
        vh.title.setText(onboardingLists.get(position).getTitle());
        vh.desc.setText(onboardingLists.get(position).getDesc());
    }

    @Override
    public int getCount(){
        return onboardingLists.size();
    }

    class OnboardingVH extends SliderViewAdapter.ViewHolder{
        TextView title, desc;
        public OnboardingVH(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.onboard_title);
            desc = itemView.findViewById(R.id.onboard_desc);
        }
    }
}
