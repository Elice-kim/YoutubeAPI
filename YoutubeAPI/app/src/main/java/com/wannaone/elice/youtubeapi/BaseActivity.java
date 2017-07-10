package com.wannaone.elice.youtubeapi;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    boolean dialogVisible;
    private AlertDialog loadingDialog;

    /** dialog */
    public void showLoading() {
        hideLoading();
        dialogVisible = true;

        buildNewLoadingDialog().show();
    }

    /** common progressbar hide */
    public void hideLoading() {
        if (isDialogShowing()) {
            dialogVisible = false;

            try {
                if (loadingDialog != null) {
                    loadingDialog.dismiss();
                }
            } catch (IllegalArgumentException ignored) { }
        }
    }

    /** common progressbar checking */
    boolean isDialogShowing() {
        return dialogVisible;
    }

    /** common progressbar show */
    AlertDialog buildNewLoadingDialog() {
        loadingDialog = new AlertDialog.Builder(this)
                .setView(R.layout.dialog_loading)
                .setCancelable(false)
                .create();

        loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return loadingDialog;
    }

}
