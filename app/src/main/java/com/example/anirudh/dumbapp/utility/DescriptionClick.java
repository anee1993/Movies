package com.example.anirudh.dumbapp.utility;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.example.anirudh.dumbapp.R;

/**
 * Created by Anirudh on 3/25/2018.
 */

public class DescriptionClick implements View.OnClickListener {
    private String description;
    private Context context;
    public DescriptionClick(String description, Context context){
        this.context = context;
        this.description = description;
    }
    @Override
    public void onClick(View view) {
        new AlertDialog.Builder(context)
                .setMessage(description)
                .setNeutralButton(context.getString(R.string.dismiss), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setCancelable(false)
                .create().show();
    }
}

