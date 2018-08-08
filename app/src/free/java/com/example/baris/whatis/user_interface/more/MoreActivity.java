package com.example.baris.whatis.user_interface.more;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.baris.whatis.R;
import com.example.baris.whatis.data.models.MoreItems;
import com.example.baris.whatis.user_interface.about.AboutActivity;
import com.example.baris.whatis.user_interface.history.HistoryActivity;
import com.example.baris.whatis.MainActivity;
import com.example.baris.whatis.user_interface.language.LanguageActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreActivity extends AppCompatActivity {

    @BindView(R.id.adView)
    AdView adView;
    @BindView(R.id.navigation_view_more)
    BottomNavigationView navigation_view_more;
    @BindView(R.id.more_rv)
    RecyclerView more_rv;

    private MoreAdapter moreAdapter;
    private List<MoreItems> moreList = new ArrayList<>();

    private FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);

        setSupportActionBar(findViewById(R.id.more_toolbar));
        Objects.requireNonNull(getSupportActionBar()).setTitle("  "+ getString(R.string.more));
        getSupportActionBar().setLogo(R.drawable.ic_action_menu);


        moreAdapter = new MoreAdapter(moreList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        more_rv.setLayoutManager(mLayoutManager);
        more_rv.setItemAnimator(new DefaultItemAnimator());
        more_rv.setAdapter(moreAdapter);

        more_rv.addOnItemTouchListener(new MoreTouchListener(getApplicationContext(), more_rv, new MoreItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                String CurrentTitle = moreList.get(position).getTitle();
                if(CurrentTitle.equals(getString(R.string.language))){
                    Intent intent = new Intent(MoreActivity.this, LanguageActivity.class);
                    firebaseAnalytics.logEvent("more_language", null);
                    startActivity(intent);
                    overridePendingTransition(R.anim.activity_open_enter, R.anim.activity_open_exit);
                }else if(CurrentTitle.equals(getString(R.string.about))){
                    Intent intent = new Intent(MoreActivity.this, AboutActivity.class);
                    firebaseAnalytics.logEvent("more_about", null);
                    startActivity(intent);
                    overridePendingTransition(R.anim.activity_open_enter, R.anim.activity_open_exit);
                }
            }
        }));

        prepareMoreData();

        Menu menu = navigation_view_more.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        navigation_view_more.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_search:
                                Intent intent = new Intent(MoreActivity.this, MainActivity.class);
                                firebaseAnalytics.logEvent("home_layout", null);
                                startActivity(intent);
                                overridePendingTransition(R.anim.activity_close_enter, R.anim.activity_close_exit);
                                break;
                            case R.id.navigation_history:
                                Intent intent1 = new Intent(MoreActivity.this, HistoryActivity.class);
                                firebaseAnalytics.logEvent("history_layout", null);
                                startActivity(intent1);
                                overridePendingTransition(R.anim.activity_close_enter, R.anim.activity_close_exit);
                                break;
                            case R.id.navigation_more:

                                break;
                        }
                        return false;
                    }
                });

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }


    private void prepareMoreData() {
        MoreItems moreItems = new MoreItems(getString(R.string.language));
        moreList.add(moreItems);

        moreItems = new MoreItems(getString(R.string.about));
        moreList.add(moreItems);

        moreAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onPause() {
        if (adView!= null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}
