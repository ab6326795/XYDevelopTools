package com.pwdgame.developtools;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class AlertMsgActivity extends Activity{
	
	private TextView mContentTextView;
	private ImageButton mCloseBtn;
	
       @Override
       public void onCreate(Bundle savedInstanceState){
    	   super.onCreate(savedInstanceState);
    	   setContentView(R.layout.activity_alertmsg);
    	   
    	   mContentTextView=(TextView) findViewById( R.id.activity_alertmsg_content_tv);
    	   mCloseBtn=(ImageButton) findViewById(R.id.titlebar_dialog_close_btn);
    	   
    	   
    	   String data=getIntent().getStringExtra("_data");
    	   if(TextUtils.isEmpty(data)){
    		   Toast.makeText(getApplicationContext(), "无效的数据", Toast.LENGTH_SHORT).show();
    		   finish();
    		   return;
    	   }
    	   mContentTextView.setText(data);
    	   
    	   mCloseBtn.setOnClickListener(new View.OnClickListener() {
			
				@Override
				public void onClick(View arg0) {
					finish();
				}
    	   });
    	   
       }
}
