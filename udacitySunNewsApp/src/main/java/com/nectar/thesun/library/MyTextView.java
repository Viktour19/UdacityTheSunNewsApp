package com.nectar.thesun.library;

import android.R;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends TextView {

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        
    	super(context, attrs, defStyle);
    	if(isInEditMode())
    	{
    		return;
    	}
        
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        
    	if(isInEditMode())
    	{
    		return;
    	}
        
    }

    public MyTextView(Context context) {
        super(context);
    	if(isInEditMode())
    	{
    		return;
    	}
        
    }
    
    
    @Override
	public void setTextColor(int color) {
		// TODO Auto-generated method stub
		super.setTextColor(color);
	}

//	@Override
//	public void setTypeface(Typeface tf, int style) {
//		// TODO Auto-generated method stub
//    if(style  == Typeface.BOLD)
//    {
//    	if(isInEditMode())
//    	{
//    		return;
//    	}
//    			 tf = Typeface.createFromAsset(getContext().getAssets(), "font/segoeuib.ttf");
////    }
//    else if(style  == Typeface.NORMAL)
//    {
//    	if(isInEditMode())
//    	{
//    		return;
//    	}
//    	tf = Typeface.createFromAsset(getContext().getAssets(), "font/segoeuil.ttf");
//    }
//    else if(style  == Typeface.ITALIC)
//    {
//    	if(isInEditMode())
//    	{
//    		return;
//    	}
//    	tf = Typeface.createFromAsset(getContext().getAssets(), "font/segoeuii.ttf");
//    }
//    			
//    	
//		super.setTypeface(tf, 1);
//	}


}