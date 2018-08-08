package com.example.baris.whatis;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baris.whatis.data.models.Entries;
import com.example.baris.whatis.data.models.Examples;
import com.example.baris.whatis.data.models.LexicalEntry;
import com.example.baris.whatis.data.models.Senses;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>
{
    private List<LexicalEntry> lexicalEntries;

    MainAdapter(List<LexicalEntry> lexicalEntries) {
        this.lexicalEntries = lexicalEntries;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new MainViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder mainViewHolder, int position) {
        final LexicalEntry item = lexicalEntries.get(position);
        mainViewHolder.bindItem(item);
    }

    @Override
    public int getItemCount() {
        return lexicalEntries.size();
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.lexical_category_tv)
        TextView lexical_category_tv;
        @BindView(R.id.definition_tv)
        TextView definition_tv;

        MainViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bindItem(LexicalEntry lexicalEntry) {
            lexical_category_tv.setText(lexicalEntry.getLexicalCategory());
            List<Entries> entries = lexicalEntry.getEntries();
            for (Entries entry : entries) {
                List<Senses> senses = entry.getSenses();
                for (int i = 0; i < senses.size(); i++) {
                    String string;
                    if(i==0){
                        string = "\n" +(i+1)+ "- ";
                    }else{
                        string = "\n\n\n" +(i+1)+ "- ";
                    }
                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
                    spannableStringBuilder.setSpan(null, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    definition_tv.append(spannableStringBuilder);
                    Senses sense = senses.get(i);
                    List<String> definitions = sense.getDefinitions();
                    if (definitions != null) {
                        for (int x = 0; x < definitions.size(); x++) {
                            definition_tv.append(definitions.get(x) + "\n");
                        }
                    }
                    List<String> crossReferenceMarkers = sense.getCrossReferenceMarkers();
                    if (crossReferenceMarkers != null) {
                        if(sense.getDomains()!=null && sense.getDomains().size()>0) {
                            String domains = TextUtils.join(",", sense.getDomains());
                            definition_tv.append(domains + ",");
                        }
                        for (int y = 0; y < crossReferenceMarkers.size(); y++) {
                            definition_tv.append(crossReferenceMarkers.get(y)+"\n");
                        }
                    }
                    List<Examples> examples = sense.getExamples();
                    if (examples != null) {
                        for (int z = 0; z < examples.size(); z++) {
                            String example;
                            if(z==0){
                                example = "\n"+ R.string.exempli_gratia + " " +examples.get(z).getText();
                            }else{
                                example = "\n\n"+ R.string.exempli_gratia + " " +examples.get(z).getText();
                            }
                            SpannableStringBuilder spannableStringBuilder_examples = new SpannableStringBuilder(example);
                            spannableStringBuilder_examples.setSpan(null, 0, example.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            definition_tv.append(spannableStringBuilder_examples);
                        }
                    }
                }
            }
        }
    }
}
