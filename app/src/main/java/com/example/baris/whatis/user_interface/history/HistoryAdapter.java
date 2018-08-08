package com.example.baris.whatis.user_interface.history;

import android.support.v7.widget.RecyclerView;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;

import com.example.baris.whatis.R;
import com.example.baris.whatis.data.database.HistoryDB;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<HistoryDB> words;

    public HistoryDB getItem(int position) {
        return words.get(position);
    }

    HistoryAdapter(List<HistoryDB> words) {
        this.words = words;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new HistoryViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder historyViewHolder, int pos) {
        final HistoryDB word = words.get(pos);
        historyViewHolder.history_tv.setText(word.getWord());
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.history_tv)
        TextView history_tv;

        HistoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
