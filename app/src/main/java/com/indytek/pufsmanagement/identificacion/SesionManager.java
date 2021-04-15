package com.indytek.pufsmanagement.identificacion;

import android.content.Context;
import android.content.SharedPreferences;

import com.indytek.pufsmanagement.R;

public class SesionManager{
    Context context;

    public SesionManager(Context context) {
        this.context = context;
    }

    private SharedPreferences prefs = context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE);

    private String userToken="" ;

    public void saveAuthToken(String token){
        SharedPreferences.Editor editor= prefs.edit();
        editor.putString(userToken,token);
        editor.apply();
    }
    public String fetchAuthToken(){
        return prefs.getString(userToken, null);
    }
}
