package com.wannaone.elice.youtubeapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wannaone.elice.youtubeapi.adpater.ViewSwapperAdapter;
import com.wannaone.elice.youtubeapi.bottomnavigation.AdaptableBottomNavigationView;
import com.wannaone.elice.youtubeapi.bottomnavigation.ViewSwapper;

public class MainActivity extends AppCompatActivity {

    private AdaptableBottomNavigationView bottomNavigationView;
    private ViewSwapperAdapter viewSwapperAdapter;
    private ViewSwapper viewSwapper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (AdaptableBottomNavigationView)
                findViewById(R.id.view_bottom_navigation);
        viewSwapper = (ViewSwapper) findViewById(R.id.view_swapper);
        viewSwapperAdapter = new ViewSwapperAdapter(getSupportFragmentManager());

        viewSwapper.setAdapter(viewSwapperAdapter);
        bottomNavigationView.setupWithViewSwapper(viewSwapper);
    }
}
