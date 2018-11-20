package com.example.dopaminastudios.myapplication.helper;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

public class InputValidation {

    private Context context;

    public InputValidation(Context context){
        this.context = context;
    }

    public boolean isInputEditTextFilled(TextView textView, String message){
        String value = textView.getText().toString().trim();
        if(value.isEmpty()){
            textView.setError(message);
            hideKeyboardFrom(textView);
            return false;
        }else {
            return true;
        }

    }

    private void hideKeyboardFrom(TextView textView) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(textView.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
