package com.pm.primeerp.util;

import android.content.Context;
import android.graphics.Typeface;

public class TypeFactory {

    private Typeface regular;
    private Typeface bold;

    public TypeFactory(Context context) {
        String FONT_REGULAR = "fonts/sans_regular.ttf";
        regular = Typeface.createFromAsset(context.getAssets(), FONT_REGULAR);
        String FONT_BOLD = "fonts/sans_bold.ttf";
        bold = Typeface.createFromAsset(context.getAssets(), FONT_BOLD);
    }

    public Typeface getRegular() {
        return regular;
    }

    public Typeface getBold() {
        return bold;
    }
}
