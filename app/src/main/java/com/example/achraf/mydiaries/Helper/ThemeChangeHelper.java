package com.example.achraf.mydiaries.Helper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.achraf.mydiaries.DB.AccesDataOption;
import com.example.achraf.mydiaries.R;

public class ThemeChangeHelper {



    @SuppressLint("ResourceAsColor")
    public static void changeTheme(LinearLayout cadreGeneral, LinearLayout cadreText, Activity activity){

        Drawable shape1 = activity.getResources().getDrawable(R.drawable.top_diary_shape);
        Drawable shape2 = activity.getResources().getDrawable(R.drawable.show_cadre_diary);


        AccesDataOption accesDataOption = new AccesDataOption(activity);
        String theme = accesDataOption.getOption("theme");
        Log.d("dd",theme);


        int h = cadreGeneral.getHeight();
        ShapeDrawable mDrawable = new ShapeDrawable(new RectShape());
        mDrawable.getPaint().setShader(new LinearGradient(0, 0, 0, h,
                R.color.elementDiaryColorDescriptionPunkRock,
                R.color.elementDiaryColorSkyBlue,
                Shader.TileMode.MIRROR));

     /*

        if(theme.equals("lightHappyGreen")){
            shape1.setColorFilter(new
                    PorterDuffColorFilter(R.color.elementDiaryColorDescription,PorterDuff.Mode.MULTIPLY));
            shape2.setColorFilter(new
                    PorterDuffColorFilter(R.color.elementDiaryColor,PorterDuff.Mode.MULTIPLY));
        }
        if(theme.equals("punkRock")){
            shape1.setColorFilter(new
                    PorterDuffColorFilter(R.color.elementDiaryColorDescriptionPunkRock,PorterDuff.Mode.MULTIPLY));
            shape2.setColorFilter(new
                    PorterDuffColorFilter(R.color.elementDiaryColorPunkRock,PorterDuff.Mode.MULTIPLY));
        }
        if(theme.equals("classic")){
            shape1.setColorFilter(new
                    PorterDuffColorFilter(R.color.elementDiaryColorDescriptionClassic,PorterDuff.Mode.MULTIPLY));
            shape2.setColorFilter(new
                    PorterDuffColorFilter(R.color.elementDiaryColorClassic,PorterDuff.Mode.MULTIPLY));
        }
        if(theme.equals("skyBlue")){
            shape1.setColorFilter(new
                    PorterDuffColorFilter(R.color.elementDiaryColorDescriptionSkyBlue,PorterDuff.Mode.MULTIPLY));
            shape2.setColorFilter(new
                    PorterDuffColorFilter(R.color.elementDiaryColorSkyBlue,PorterDuff.Mode.MULTIPLY));
        }

        shape1.setColorFilter(new
                PorterDuffColorFilter(R.color.elementDiaryColorDescriptionClassic,PorterDuff.Mode.MULTIPLY));
        shape2.setColorFilter(new
                PorterDuffColorFilter(R.color.elementDiaryColorClassic,PorterDuff.Mode.MULTIPLY));

*/
        cadreGeneral.setBackgroundDrawable(
                mDrawable
        );
       cadreText.setBackgroundDrawable(
                mDrawable
        );

    }

}
