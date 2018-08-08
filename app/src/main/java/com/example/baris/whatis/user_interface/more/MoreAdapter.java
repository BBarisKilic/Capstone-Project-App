package com.example.baris.whatis.user_interface.more;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baris.whatis.R;
import com.example.baris.whatis.data.models.MoreItems;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreAdapter extends RecyclerView.Adapter<MoreAdapter.MoreViewHolder>{

    private List<MoreItems> moreItems;

    MoreAdapter(List<MoreItems> moreItems) {
        this.moreItems = moreItems;
    }

    @Override
    public MoreViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_more, viewGroup, false);

        return new MoreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MoreViewHolder moreViewHolder, int position) {
        moreViewHolder.title.setText(moreItems.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return moreItems.size();
    }

    class MoreViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.more_tv)
        TextView title;

        MoreViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
