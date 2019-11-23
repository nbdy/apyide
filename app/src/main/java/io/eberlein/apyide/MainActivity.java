package io.eberlein.apyide;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Gravity;
import android.view.Menu;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    // @BindView(R.id.nav_) ExpandableListView projects;

    private void isTermuxInstalled(){
        PackageManager pm = getPackageManager();
        try{
            pm.getPackageInfo("com.termux", 0);
        } catch (PackageManager.NameNotFoundException e){
            Toast.makeText(this, "termux not installed", Toast.LENGTH_LONG).show();
            askForTermuxDownload();
        }
    }

    private void askForTermuxDownload(){
        SharedPreferences s = Utils.getPreferences(this);
        if(s.getBoolean("askTermuxDownload", true)){
            downloadTermux();
        } else {
            s.edit().putBoolean("termuxEnabled", false).apply();
        }
    }

    private void downloadTermux(){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setMessage("download modified version of termux?");
        b.setTitle("download termux");
        b.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        b.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://build.eberlein.io:8080/job/termux/lastSuccessfulBuild/artifact/app/build/outputs/apk/release/app-release.apk"));
                startActivity(i);
            }
        });
        b.create();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 0){
            if(grantResults.length > 0 && grantResults[0] == 0){
                Utils.createDirectoryStructure(this);
            } else {
                Toast.makeText(this, "i need permissions to do stuff", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_new_project,
                R.id.nav_open_project,
                R.id.nav_settings
        ).setDrawerLayout(drawer).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setItemIconTintList(null);

        isTermuxInstalled();

        checkSelfPermission("com.termux.permission.TERMUX_SERVICE");
        checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        requestPermissions(new String[]{"com.termux.permission.TERMUX_SERVICE",
                "android.permission.WRITE_EXTERNAL_STORAGE"}, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);  // todo add / change for other fragments
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {}
}
