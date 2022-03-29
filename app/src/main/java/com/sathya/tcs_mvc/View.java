package com.sathya.tcs_mvc;

import android.util.Log;


// NOTE View can also be Mainactivity.. since we have the UI there...
public class View {

    // We can port the handler( UI )  to this block
    public void printUserLoginDetails(String name, String age) {

        Log.d("tag"," Login Details  ");
        Log.d("tag"," Name : "+name);
        Log.d("tag"," Age  : "+age);

    }
}
