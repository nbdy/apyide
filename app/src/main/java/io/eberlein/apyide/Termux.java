package io.eberlein.apyide;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

// using https://github.com/smthnspcl/termux-app
// https://github.com/termux/termux-app/issues/804
// https://github.com/termux/termux-app/pull/1029

public class Termux {
    public static final String TERMUX_USR_BIN_DIR = "/data/data/com.termux/files/usr/";

    private static final String TERMUX = "com.termux";
    private static final String TERMUX_FILE = "com.termux.file";
    private static final String TERMUX_SERVICE = "com.termux.app.TermuxService";

    private static final String ACTION_EXECUTE = "com.termux.service_execute";
    private static final String EXTRA_ARGUMENTS = "com.termux.execute.arguments";
    private static final String EXTRA_CURRENT_WORKING_DIRECTORY = "com.termux.execute.cwd";

    private static final String EXTRA_EXECUTE_IN_BACKGROUND = "com.termux.execute.background";

    private Context ctx;

    public Termux(Context ctx){
        this.ctx = ctx;
    }

    public void run(String cmd, String[] args){
        run(this.ctx, cmd, args);
    }

    public static void run(Context ctx, String cmd, String[] args){
        Uri t = new Uri.Builder().path(cmd).build();
        Log.d(TERMUX, t.toString());
        Intent i = new Intent(ACTION_EXECUTE, t);
        i.setClassName(TERMUX, TERMUX_SERVICE);
        i.putExtra(EXTRA_ARGUMENTS, args);
        // i.putExtra(EXTRA_EXECUTE_IN_BACKGROUND, true);
        // https://developer.android.com/about/versions/oreo/background.html
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) ctx.startForegroundService(i);
        else ctx.startService(i);
    }
}
