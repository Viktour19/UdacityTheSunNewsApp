package com.nectar.thesun.library;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class MyButton  extends Button{

	public MyButton(Context context) {
		super(context);
		if(isInEditMode())
    	{
    		return;
    	}
		// TODO Auto-generated constructor stub
	}

	public MyButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		if(isInEditMode())
    	{
    		return;
    	}
		// TODO Auto-generated constructor stub
	}

	public MyButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		if(isInEditMode())
    	{
    		return;
    	}
		// TODO Auto-generated constructor stub
	}

	//@Override
//	public void setTypeface(Typeface tf, int style) {
//		// TODO Auto-generated method stub
//		super.setTypeface(tf, style);
//		 if(style  == Typeface.BOLD)
//		    {
//		    	if(isInEditMode())
//		    	{
//		    		return;
//		    	}
//		    			 tf = Typeface.createFromAsset(getContext().getAssets(), "font/segoeuib.ttf");
//		    }
//		    else if(style  == Typeface.NORMAL)
//		    {
//		    	if(isInEditMode())
//		    	{
//		    		return;
//		    	}
//		    	tf = Typeface.createFromAsset(getContext().getAssets(), "font/segoeuil.ttf");
//		    }
//		    else if(style  == Typeface.ITALIC)
//		    {
//		    	if(isInEditMode())
//		    	{
//		    		return;
//		    	}
//		    	tf = Typeface.createFromAsset(getContext().getAssets(), "font/segoeuii.ttf");
//		    }
//		    			
//		    	
//				super.setTypeface(tf, 1);
//	}

	
}
