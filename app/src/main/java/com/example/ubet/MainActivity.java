package com.example.ubet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ubet.fragments.AccountFragment;
import com.example.ubet.fragments.MatchesFragment;
import com.example.ubet.models.Response;
import com.example.ubet.viewmodels.MainActivityViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private MainActivityViewModel viewModel;
    private Fragment matchesFrag;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new MainActivityViewModel();
        dl = (DrawerLayout)findViewById(R.id.activity_main);
        nv = (NavigationView)findViewById(R.id.nv);

        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch(id)
                {
                    case R.id.account:
                        AccountFragment accountFragment = new AccountFragment();
                        fragmentTransaction.replace(R.id.matchesFragment, accountFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.settings:
                        Toast.makeText(MainActivity.this, "Settings",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mycart:
                        Toast.makeText(MainActivity.this, "My Cart",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.matches:
                        MatchesFragment matchesFragment = new MatchesFragment();
                        fragmentTransaction.replace(R.id.matchesFragment, matchesFragment);
                        fragmentTransaction.commit();
                    default:
                        return true;
                }
                return true;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
