package com.maimito.type_notes.adapter;

import android.content.Context;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maimito.type_notes.R;
import com.maimito.type_notes.model.SliderList;
import com.smarteist.autoimageslider.SliderViewAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SliderLoginAdapter extends SliderViewAdapter<SliderLoginAdapter.SliderAdapterVH> {
    private Context context;
    private List<SliderList> sliderLists;

    public SliderLoginAdapter(Context context, List<SliderList> sliderLists){
        this.context = context;
        this.sliderLists = sliderLists;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent){
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH vh, int position){
        vh.textView.setText(sliderLists.get(position).getTitle());
        Glide.with(context).load(sliderLists.get(position).getImage()).placeholder(R.drawable.placeholder)
                .into(vh.imageView);
    }

    @Override
    public int getCount(){
        return sliderLists.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder{
        View itemView;
        ImageView imageView;
        TextView textView;
        public SliderAdapterVH(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.slider_image);
            textView = itemView.findViewById(R.id.sliderlayout_text);
            this.itemView = itemView;
        }
    }
}
