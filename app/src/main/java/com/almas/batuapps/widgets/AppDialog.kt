package com.almas.batuapps.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.almas.batuapps.R

class AppDialog(context: Context) : Dialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_progress)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}