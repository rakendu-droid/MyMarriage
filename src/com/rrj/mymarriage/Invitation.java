package com.rrj.mymarriage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Invitation extends FragmentActivity implements TabListener {
	

	ActionBar actionBar;
	ViewPager viewPager;
	Context ctx;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.invitation);
		actionBar = getActionBar();

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// Creation of first Tab
		

		viewPager = (ViewPager) findViewById(R.id.swiper);
		ActionBar.Tab tabInvite = actionBar.newTab();
		tabInvite.setText("Invitation");
		tabInvite.setTabListener(this);

		// Creation of second Tab
		ActionBar.Tab tabProgram = actionBar.newTab();
		tabProgram.setText("Programme");
		tabProgram.setTabListener(this);

		actionBar.addTab(tabInvite);
		actionBar.addTab(tabProgram);
		viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));// constructor
																			// adapter
																			// requires
																			// fragment
																			// manger

		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int pageNo) {
				// TODO Auto-generated method stub
				// page selected by user is 1 then tab selected should be number
				// 1 is what it does here
				actionBar.setSelectedNavigationItem(pageNo);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				if (arg0 == ViewPager.SCROLL_STATE_IDLE) {
					// Log.d("idle", "is now idle");
				} else if (arg0 == ViewPager.SCROLL_STATE_DRAGGING) {
					// Log.d("drag", "dragging now");
				} else if (arg0 == ViewPager.SCROLL_STATE_SETTLING) {
					// Log.d("settle", "Settling now");
				}
			}
		});
		
		
	}
//	public boolean onPrepareOptionsMenu(Menu menu){
//		MenuItem item = menu.findItem(R.id.menu);
//		if(android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH)
//			item.setVisible(true);
//		else
//			item.setVisible(false);
//		return true;
//	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuItem item = menu.findItem(R.id.action_settings);
//		Log.d("Tag", "Hello"+item);
//		if(android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.HONEYCOMB_MR2)
//			item.setVisible(true);
//		else
//			item.setVisible(false);

		return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		addToCalendar();
		Toast.makeText(getApplicationContext(), "Event added to calendar", Toast.LENGTH_LONG).show();
		return true;
	}
	public void addToCalendar() {
		// TODO Auto-generated method stub
			Log.d("Tag", "Add to cal");
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy HH.mm");
			String strDate = "09/03/2014 09.30";
			try {
				Date date = newFormat.parse(strDate);
				cal.setTime(date);
				long startMilis = cal.getTimeInMillis();
				String strEndDate = "09/03/2014 12.30";
				Date endDate = newFormat.parse(strEndDate);
				cal.setTime(endDate);
				//cal.add(Calendar.DATE, 1);
				//Log.d("Tag","End date    "+endDate);	
				long endMilis = cal.getTimeInMillis();
				MyCalendar myCal = new MyCalendar(getApplicationContext());
				myCal.AddEvent(startMilis, endMilis, "Rakendu's Wedding", "Rakendu's Wedding.");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	class MyAdapter extends FragmentPagerAdapter {

		public MyAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			Fragment fragment = null;
			// if at position 0 then return fragment 1
			if (arg0 == 0) {
				fragment = new FragmentInvite();
			}
			// if at position 1 then return fragment 2
			else if (arg0 == 1) {
				fragment = new FragmentProgram();
			}

			return fragment;
		}

		@Override
		// tells viewPager how many pages are there
		public int getCount() {
			// TODO Auto-generated method stub
			return 2;
		}

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		// particular tab changes ,changing the view page
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
}
