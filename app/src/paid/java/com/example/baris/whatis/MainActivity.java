package com.example.baris.whatis;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baris.whatis.data.ManagerOfData;
import com.example.baris.whatis.data.models.LexicalEntry;
import com.example.baris.whatis.user_interface.history.HistoryActivity;
import com.example.baris.whatis.user_interface.more.MoreActivity;
import com.example.baris.whatis.user_interface.widget.HistoryWidgetProvider;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private Parcelable parcelable;
    private static final String KEY_RECYCLERVIEW_STATE = "key_recyclerview_state";
    public static final String NEW_SEARCH_HISTORY_ENTRY = "new_search_history_entry";
    private static final String TAG = MainActivity.class.getSimpleName();
    private String chosen_language;
    private String flag_name = "flag_es";
    private int resID;

    @BindView(R.id.entries_rv)
    RecyclerView entries_rv;

    @BindView(R.id.app_bar_layout)
    AppBarLayout app_bar_layout;

    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsing_toolbar_layout;

    @BindView(R.id.constraint_layout_et_ib_rv)
    ConstraintLayout constraint_layout_et_ib_rv;

    @BindView(R.id.navigation_view_main)
    BottomNavigationView navigation_view_main;

    @BindView(R.id.search_et)
    EditText search_et;

    @BindView(R.id.message_constraint_layout)
    ConstraintLayout message_constraint_layout;

    @BindView(R.id.message_tv)
    TextView message_tv;

    @BindView(R.id.load_pw)
    ProgressBar load_pw;

    @BindView(R.id.toolbar_iv)
    ImageView toolbar_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar((Toolbar)findViewById(R.id.main_toolbar));

        setupLanguage();

        Menu menu = navigation_view_main.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        navigation_view_main.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_search:

                                break;
                            case R.id.navigation_history:
                                Intent intent1 = new Intent(MainActivity.this, HistoryActivity.class);
                                startActivity(intent1);
                                overridePendingTransition(R.anim.activity_open_enter, R.anim.activity_open_exit);
                                break;
                            case R.id.navigation_more:
                                Intent intent = new Intent(MainActivity.this, MoreActivity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.activity_open_enter, R.anim.activity_open_exit);
                                break;
                        }
                        return false;
                    }
                });


        if (savedInstanceState != null) {
            parcelable = savedInstanceState.getParcelable(KEY_RECYCLERVIEW_STATE);
        }

        mainViewModel = ViewModelProviders.of(this,
                new MainViewModelFactory(getApplication(), ManagerOfData.getInstance(getApplication())))
                .get(MainViewModel.class);

        mainViewModel.getLexicalEntries().observe(this, lexicalEntries -> {
            if (lexicalEntries != null) showEntries(lexicalEntries);
            else {
                entries_rv.setVisibility(View.GONE);
            }
        });

        mainViewModel.getBool().observe(this, this::showLoadingIndicator);

        mainViewModel.getThrowableMutableLiveData().observe(this, throwable -> {
            Log.d(TAG, getString(R.string.error)+ throwable);
            if (throwable != null) showError(throwable);
            else message_constraint_layout.setVisibility(View.GONE);

        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        entries_rv.setLayoutManager(linearLayoutManager);
        if(getIntent().hasExtra(MainActivity.NEW_SEARCH_HISTORY_ENTRY)){
            String entry = Objects.requireNonNull(getIntent().getExtras()).getString(MainActivity.NEW_SEARCH_HISTORY_ENTRY);
            search_et.setText(entry);
            search();
        }
        if(getIntent().hasExtra(HistoryWidgetProvider.NEW_SEARCH_WORD)){
            String entry = Objects.requireNonNull(getIntent().getExtras()).getString(HistoryWidgetProvider.NEW_SEARCH_WORD);
            search_et.setText(entry);
            search();
        }
    }

    @OnClick(R.id.search_ib)
    public void search() {
        String word = search_et.getText().toString();
        if (TextUtils.isEmpty(word)) {
            Toast.makeText(this, getString(R.string.empty_search_text), Toast.LENGTH_SHORT).show();
        } else {
            mainViewModel.search(chosen_language,word);
            InputMethodManager inputMethodManager =(InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
            if(this.getCurrentFocus() != null) {
                assert inputMethodManager != null;
                inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    private void showEntries(List<LexicalEntry> lexicalEntryList) {
        entries_rv.setVisibility(View.VISIBLE);
        message_constraint_layout.setVisibility(View.GONE);
        MainAdapter adapter = new MainAdapter(lexicalEntryList);
        entries_rv.setAdapter(adapter);
        if (parcelable != null) {
            entries_rv.getLayoutManager().onRestoreInstanceState(parcelable);
            parcelable = null;
        }
    }

    private void showLoadingIndicator(Boolean bool) {
        if (!bool) {
            load_pw.setVisibility(View.GONE);
        } else {
            load_pw.setVisibility(View.VISIBLE);
        }
    }

    private void showError(Throwable throwable) {
        message_constraint_layout.setVisibility(View.VISIBLE);
        entries_rv.setVisibility(View.GONE);
        message_tv.setText(throwable.getMessage());
    }

    @Override
    public void onResume(){
        super.onResume();
        setupLanguage();
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Parcelable parcelable = entries_rv.getLayoutManager().onSaveInstanceState();
        bundle.putParcelable(KEY_RECYCLERVIEW_STATE, parcelable);
    }

    private void setupLanguage(){
        chosen_language=readFromFile(this);

        switch (chosen_language){
            case "es":
                flag_name = "flag_es";
                resID = this.getResources().getIdentifier(flag_name, "drawable", this.getPackageName());
                Picasso.with(this)
                        .load(resID)
                        .into(toolbar_iv);
                break;
            case "en":
                flag_name = "flag_en";
                resID = this.getResources().getIdentifier(flag_name, "drawable", this.getPackageName());
                Picasso.with(this)
                        .load(resID)
                        .into(toolbar_iv);
                break;
            case "lv":
                flag_name = "flag_lv";
                resID = this.getResources().getIdentifier(flag_name, "drawable", this.getPackageName());
                Picasso.with(this)
                        .load(resID)
                        .into(toolbar_iv);
                break;
            case "hi":
                flag_name = "flag_india";
                resID = this.getResources().getIdentifier(flag_name, "drawable", this.getPackageName());
                Picasso.with(this)
                        .load(resID)
                        .into(toolbar_iv);
                break;
            case "sw":
                flag_name = "flag_sw";
                resID = this.getResources().getIdentifier(flag_name, "drawable", this.getPackageName());
                Picasso.with(this)
                        .load(resID)
                        .into(toolbar_iv);
                break;
            case "ta":
                flag_name = "flag_india";
                resID = this.getResources().getIdentifier(flag_name, "drawable", this.getPackageName());
                Picasso.with(this)
                        .load(resID)
                        .into(toolbar_iv);
                break;
            case "gu":
                flag_name = "flag_india";
                resID = this.getResources().getIdentifier(flag_name, "drawable", this.getPackageName());
                Picasso.with(this)
                        .load(resID)
                        .into(toolbar_iv);
                break;
            default:
                break;
        }
    }

    private String readFromFile(Context context) {

        String ret = "es";

        try {
            InputStream inputStream = context.openFileInput("what_is.config");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            return ret;
        } catch (IOException e) {
            Log.e(getString(R.string.ioexception), getString(R.string.cannot_read_file) + e.toString());
        }
        return ret;
    }
}
