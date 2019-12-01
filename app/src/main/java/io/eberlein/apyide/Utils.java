package io.eberlein.apyide;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utils {
    private static final String PYTHON_TEMPLATE_MAIN = "import logging\nlog = logging.getLogger(__name__)\n\n";

    private static final String KEY_SETTINGS = "apyide";
    private static final String KEY_SETTINGS_PATH = "path";
    private static final String PATH_PROJECTS = "/projects/";

    private static File SDCARD(Context ctx){
        return ctx.getExternalFilesDir(null);
    }

    public static SharedPreferences getPreferences(Context ctx){
        return ctx.getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE);
    }

    private static File getAPyIDEPath(Context ctx){
        return new File(getPreferences(ctx).getString(KEY_SETTINGS_PATH, Environment.getExternalStorageDirectory().getPath() + "/apyide/"));
    }

    private static File getAPyIDEProjectsPath(Context ctx){
        return new File(getAPyIDEPath(ctx).getPath() + PATH_PROJECTS);
    }

    static File getAPyIDEProjectPath(Context ctx, String name){
        return new File(getAPyIDEProjectsPath(ctx).getPath() + "/" + name);
    }

    private static void couldNotCreateToast(Context ctx, String what){
        Log.e("Utils.class", "could not create '" + what + "'");
        Toast.makeText(ctx, "could not create '" + what + "'", Toast.LENGTH_LONG).show();
    }

    static void createDirectoryStructure(Context ctx){
        Log.d("Utils.class", SDCARD(ctx).getPath());
        File f = getAPyIDEPath(ctx);
        if(!f.isDirectory() && !f.mkdirs()) couldNotCreateToast(ctx, f.getPath());
        File p = getAPyIDEProjectsPath(ctx);
        if(!p.isDirectory() && !p.mkdirs()) couldNotCreateToast(ctx, p.getPath());

    }

    private static boolean createProject(Context ctx, String name) {
        File p = getAPyIDEProjectPath(ctx, name);
        if (p.exists()) {
            Toast.makeText(ctx, "project '" + name + "' already exists", Toast.LENGTH_LONG).show();
            return false;
        } else {
            if (!p.mkdir()) {
                couldNotCreateToast(ctx, p.getPath());
                return false;
            }
            File pm = new File(p.getPath() + "/" + name +".py");
            try {
                if (!pm.createNewFile()) {
                    couldNotCreateToast(ctx, pm.getPath());
                    return false;
                }
                FileOutputStream fos = new FileOutputStream(pm);
                fos.write(PYTHON_TEMPLATE_MAIN.getBytes());
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }
    }

    public static Project getCreateProject(Context ctx, String name) {
        File f = Utils.getAPyIDEProjectPath(ctx, name);
        if (f.exists()) return new Project(f);
        else if(!createProject(ctx, name)) return null;
        return new Project(f);
    }

    public static Projects getProjects(Context ctx){
        return new Projects(getAPyIDEProjectsPath(ctx));
    }

    public static String parseLastLog(Context ctx, String name){
        return ""; // todo
    }
}
