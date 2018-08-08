package com.example.baris.whatis.user_interface.history;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.novoda.accessibility.AccessibilityServices;
import com.example.baris.whatis.R;
import com.example.baris.whatis.data.database.HistoryDB;
import com.example.baris.whatis.MainActivity;
import com.example.baris.whatis.user_interface.more.MoreActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity {

    private HistoryAdapter historyAdapter;
    private Parcelable parcelable;
    private HistoryViewModel historyViewModel;
    private List<HistoryDB> words = new ArrayList<>();
    private static final String KEY_RECYCLERVIEW_STATE = "key_recyclerview_state";


    @BindView(R.id.navigation_view_history)
    BottomNavigationView navigation_view_history;
    @BindView(R.id.history_rv)
    RecyclerView history_rv;
    @BindView(R.id.message_constraint_layout)
    ConstraintLayout message_constraint_layout;
    @BindView(R.id.message_tv)
    TextView message_tv;
    @BindView(R.id.clear_FAB)
    FloatingActionButton clear_FAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        setSupportActionBar(findViewById(R.id.toolbar_history));
        Objects.requireNonNull(getSupportActionBar()).setTitle("  "+ getString(R.string.history));
        getSupportActionBar().setLogo(R.drawable.ic_action_history);

        historyAdapter = new HistoryAdapter(words);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        history_rv.setLayoutManager(linearLayoutManager);
        history_rv.setItemAnimator(new DefaultItemAnimator());
        history_rv.setAdapter(historyAdapter);

        history_rv.addOnItemTouchListener(new HistoryTouchListener(getApplicationContext(), history_rv, new HistoryItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(HistoryActivity.this, MainActivity.class);
                intent.putExtra(MainActivity.NEW_SEARCH_HISTORY_ENTRY, historyAdapter.getItem(position).getWord());
                startActivity(intent);
                overridePendingTransition(R.anim.activity_close_enter, R.anim.activity_close_exit);
            }
        }));

        Menu menu = navigation_view_history.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        navigation_view_history.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_search:
                                Intent intent1 = new Intent(HistoryActivity.this, MainActivity.class);
                                startActivity(intent1);
                                overridePendingTransition(R.anim.activity_close_enter, R.anim.activity_close_exit);
                                break;
                            case R.id.navigation_history:

                                break;
                            case R.id.navigation_more:
                                Intent intent = new Intent(HistoryActivity.this, MoreActivity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.activity_open_enter, R.anim.activity_open_exit);
                                break;
                        }
                        return false;
                    }
                });

        clear_FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDltDialog();
            }
        });

        AccessibilityServices accessibilityServices = AccessibilityServices.newInstance(this);
        if (savedInstanceState != null) {
            parcelable = savedInstanceState.getParcelable(KEY_RECYCLERVIEW_STATE);
        }
        historyViewModel = ViewModelProviders.of(this,
                new HistoryViewModelFactory(getApplication(), HistoryDB.DictionaryDatabase.getInstance(getApplication())))
                .get(HistoryViewModel.class);

        historyViewModel.getHistoryWords().observe(this, searchHistoryEntries -> {
            assert searchHistoryEntries != null;
            if (searchHistoryEntries.size() != 0) {
                showHistory(searchHistoryEntries);
            } else {
                showEmpty();
            }
        });

    }

    private void showHistory(List<HistoryDB> seekHistoryWords) {
        message_constraint_layout.setVisibility(View.GONE);
        history_rv.setVisibility(View.VISIBLE);
        historyAdapter = new HistoryAdapter(seekHistoryWords);
        history_rv.setAdapter(historyAdapter);
        if (parcelable != null) {
            history_rv.getLayoutManager().onRestoreInstanceState(parcelable);
            parcelable = null;
        }
    }

    public void showDltDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.are_you_sure));
        builder.setMessage(getString(R.string.all_history_will_be_deleted));

        builder.setPositiveButton(R.string.ok, (dialog, id) -> {
            historyViewModel.deleteAllHistory();
            dialog.dismiss();
        });
        builder.setNegativeButton(R.string.cancel, (dialog, id) -> dialog.dismiss());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showEmpty() {
        message_tv.setText(getString(R.string.no_history_record));
        message_constraint_layout.setVisibility(View.VISIBLE);
        history_rv.setVisibility(View.GONE);
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Parcelable state = history_rv.getLayoutManager().onSaveInstanceState();
        bundle.putParcelable(KEY_RECYCLERVIEW_STATE, state);
    }

}
