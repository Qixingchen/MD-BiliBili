package me.qixingchen.mdbilibili.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import me.qixingchen.mdbilibili.app.BilibiliApplication;

/**
 * Created by shenvsv on 14/5/14.
 */
public class KeyBoardUtils {
    public static void showKeyBoard(final EditText editText) {
        if (editText == null) {
            return;
        }
        editText.requestFocus();
        editText.post(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getAppContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, InputMethodManager.RESULT_UNCHANGED_SHOWN);
            }
        });
    }

    public static void hideSoftInput(EditText editText) {
        if (editText == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) getAppContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    public static Context getAppContext(){
        return BilibiliApplication.getApplication();
    }
}
