package io.eberlein.apyide;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.fastjson.JSON;
import com.google.common.io.Files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import io.eberlein.apyide.codestyles.CodeStyles;
import io.eberlein.apyide.codestyles.CodeStyle;

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
        ft.replace(rid, n);
        ft.addToBackStack(n.getTag());
        ft.commit();
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

    private static File getAPyIDECodestylesPath(Context ctx){
        return new File(getAPyIDEPath(ctx) + PATH_STYLES);
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

    public static String readFile(File file){
        try {
            return new String(Files.toByteArray(file));
        } catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }

    public static String parseLastLog(Context ctx, String name){
        return ""; // todo
    }

    public static InputMethodManager getInputMethodManager(Context ctx){
        return (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    public static void hideKeyboard(Context ctx){
        InputMethodManager i = getInputMethodManager(ctx);
        if(i != null) i.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public static void showKeyboard(Context ctx){
        InputMethodManager i = getInputMethodManager(ctx);
        if(i != null) i.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
    }
}
