package io.eberlein.apyide;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.fastjson.JSON;
import com.google.common.io.Files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utils {
    private static final String PYTHON_TEMPLATE_MAIN = "import logging\nlog = logging.getLogger(__name__)\n\n";

    private static final String KEY_SETTINGS = "apyide";
    private static final String KEY_SETTINGS_PATH = "path";
    private static final String KEY_SETTINGS_STYLES = "styles";
    private static final String PATH_PROJECTS = "/projects/";
    private static final String PATH_STYLES = "/styles/";

    private static File SDCARD(Context ctx){
        return ctx.getExternalFilesDir(null);
    }

    public static void replaceFragment(int rid, Fragment c, Fragment n) {
        FragmentTransaction ft = c.getFragmentManager().beginTransaction();
        ft.replace(rid, n, "");
        ft.commit();
    }

    private static SharedPreferences getPreferences(Context ctx){
        return ctx.getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE);
    }

    public static CodeStyles getStyles(Context ctx){
        SharedPreferences s = getPreferences(ctx);
        CodeStyles cs = JSON.parseObject(s.getString(KEY_SETTINGS_STYLES, "{}"), CodeStyles.class);
        if(cs == null) {
            cs = new CodeStyles();
            cs.add(new PythonDarkula());
        } else {
            if(cs.getStyles().size() == 0) cs.add(new PythonDarkula());
        }
        for(LanguageStyle l : cs.getStyles()){
            Log.d("Utils.getStyles", l.getName());
        }
        return cs;
    }

    public static File getAPyIDEPath(Context ctx){
        return new File(getPreferences(ctx).getString(KEY_SETTINGS_PATH, Environment.getExternalStorageDirectory().getPath() + "/apyide/"));
    }

    public static File getAPyIDEProjectsPath(Context ctx){
        return new File(getAPyIDEPath(ctx).getPath() + PATH_PROJECTS);
    }

    public static File getAPyIDECodestylesPath(Context ctx){
        return new File(getAPyIDEPath(ctx) + PATH_STYLES);
    }

    public static File getAPyIDEProjectPath(Context ctx, String name){
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

    public static boolean createProject(Context ctx, String name) {
        File p = getAPyIDEProjectPath(ctx, name);
        if (p.exists()) {
            Toast.makeText(ctx, "project '" + name + "' already exists", Toast.LENGTH_LONG).show();
            return false;
        } else {
            if (!p.mkdir()) {
                couldNotCreateToast(ctx, p.getPath());
                return false;
            }
            File pm = new File(p.getPath() + "/main.py");
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

    public static String readFile(File file){
        try {
            return new String(Files.toByteArray(file));
        } catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }

    public static CodeStyles getCodeStyles(Context ctx){
        return new CodeStyles(getAPyIDECodestylesPath(ctx));
    }

    public static String parseLastLog(Context ctx, String name){
        return ""; // todo
    }
}
