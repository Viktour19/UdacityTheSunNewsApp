package com.nectar.thesun.library;


import android.app.ProgressDialog;
import android.content.Context;

public class ProgressDialogManager {
		
	private ProgressDialog PDialog;

	public void showProggressDialog(Context context,String message
		) {

		PDialog = new ProgressDialog(
				context);
		PDialog.setMessage(message);
		PDialog.setCancelable(false);
		PDialog.setIndeterminate(false);
		PDialog.show();
	}
	
	public void dismiss()
	{
		PDialog.dismiss();
	}

}
