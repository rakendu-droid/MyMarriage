package com.rrj.mymarriage;



import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	 Animation iconAnim;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btnInvite = (Button)findViewById(R.id.invitation);
		Button btnVenue = (Button)findViewById(R.id.venue);
		Button btnschedule = (Button)findViewById(R.id.menu);
		
		iconAnim=AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.iconanim);
		
		btnInvite.startAnimation(iconAnim);
		btnVenue.setAnimation(iconAnim);
		btnschedule.setAnimation(iconAnim);
		btnInvite.setOnClickListener(this);
		btnVenue.setOnClickListener(this);
		btnschedule.setOnClickListener(this);
		
		
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.credits, menu);
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuItem item = menu.findItem(R.id.credits);
		item.setTitle("My App");
//		Log.d("Tag", "Hello"+item);
//		if(android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.HONEYCOMB_MR2)
//			item.setVisible(true);
//		else
//			item.setVisible(false);

		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	Intent intent = new Intent(getApplicationContext(), WishMe.class);
	startActivity(intent);
	return false;
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		switch(v.getId()){
		case R.id.invitation : intent = new Intent(getApplicationContext(), Invitation.class);
		startActivity(intent);
		break;
		case R.id.venue : intent = new Intent(getApplicationContext(), Venue.class);
		startActivity(intent);
		break;
		case R.id.menu : intent = new Intent(getApplicationContext(), ContactMe.class);
		startActivity(intent);
		break;
		}
	
			
	}

}
