package io.eberlein.apyide;

import android.app.DownloadManager;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.FragmentUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;

    private void isTermuxInstalled(){
        PackageManager pm = getPackageManager();
        try{
            pm.getPackageInfo(Static.TERMUX_PACKAGE, 0);
            Log.i("MainActivity.iTI", "found termux");
            requestPermissions(new String[]{Static.PERMISSION_TERMUX}, Static.PERMISSION_TERMUX_CODE);
        } catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
            Toast.makeText(this, "termux not installed", Toast.LENGTH_LONG).show();
            askForTermuxDownload();
        }
    }

    private void askForTermuxDownload(){
        SharedPreferences s = Utils.getPreferences(this);
        boolean askTermuxDownload = s.getBoolean(Static.ASK_TERMUX_DOWNLOAD, true);
        Log.d("MainActivity.afTD", String.valueOf(askTermuxDownload));
        if(s.getBoolean(Static.ASK_TERMUX_DOWNLOAD, true)) downloadTermux();
        else s.edit().putBoolean(Static.ENABLE_TERMUX, false).apply();
    }

    private void downloadTermux(){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setMessage("download modified version of termux?");
        b.setTitle("download termux");
        b.setNegativeButton("no", (dialog, which) -> {
            Utils.getPreferences(getApplicationContext()).edit().putBoolean(Static.ENABLE_TERMUX, false).apply();
            Toast.makeText(getApplicationContext(), "executing code won't be possible", Toast.LENGTH_LONG).show();
            dialog.dismiss();
        });
        b.setPositiveButton("yes", (dialog, which) -> {
            DownloadManager.Request r = new DownloadManager.Request(Uri.parse(Static.TERMUX_APK_URL));
            r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "termux.apk");
            r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            ((DownloadManager) getSystemService(DOWNLOAD_SERVICE)).enqueue(r);
        });
        b.create().show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        String lk = "MainActivity:RPR";
        if (requestCode == Static.PERMISSION_TERMUX_CODE) {
            if(grantResults.length > 0 && grantResults[0] == 0){
                Log.d(lk, "got termux permission");
                Utils.getPreferences(this).edit().putBoolean(Static.ENABLE_TERMUX, true).apply();
            } else {
                Log.d(lk, "termux permission denied");
                Utils.getPreferences(this).edit().putBoolean(Static.ENABLE_TERMUX, false).apply();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Paper.init(this);
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
        PermissionUtils.permission(PermissionConstants.STORAGE).callback(new PermissionUtils.SimpleCallback() {
            @Override
            public void onGranted() {
                Utils.createDirectoryStructure(getApplicationContext());
                isTermuxInstalled();
            }

            @Override
            public void onDenied() {
                Toast.makeText(getApplicationContext(), "cannot create directory structure without permission", Toast.LENGTH_LONG).show();
            }
        }).request();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if(fm.getBackStackEntryCount() > 0){
            FragmentUtils.pop(fm);
        } else {
            super.onBackPressed();
        }
    }
}
