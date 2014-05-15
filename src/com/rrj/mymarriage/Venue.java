package com.rrj.mymarriage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class Venue extends FragmentActivity implements OnMyLocationChangeListener{

	final static LatLng VENUE_LATLNG = new LatLng(12.943426, 77.568156);

	 double lat,lng;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.venue);
		
		GoogleMap googleMap = null;
		// Get the location manager
	   
		//API Key = AIzaSyAfAkZZAhNQQIuUOGmL_44upjmLfZmV1bE
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
		
		if (googleMap != null) {
			googleMap.setMyLocationEnabled(true);
			LocationRequest mLocationRequest = LocationRequest.create();
	        // Use high accuracy
	        mLocationRequest.setPriority(
	                LocationRequest.PRIORITY_HIGH_ACCURACY);
	        // Set the update interval to 10 seconds
	        mLocationRequest.setInterval(0);
	        // Set the fastest update interval to 5 second
	        mLocationRequest.setFastestInterval(0);
			googleMap.setOnMyLocationChangeListener(this);
			//Toast.makeText(getApplicationContext(), "From OnCreate"+myLocation.getLatitude()+"Longg"+myLocation.getLongitude(), Toast.LENGTH_LONG).show();
			Marker marker = googleMap.addMarker(new MarkerOptions().position(
					VENUE_LATLNG).title("Darmastala Manjunatha Swamy Kalyana Mantapa"));
			// Move the camera instantly with a zoom of 10.
			googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
					VENUE_LATLNG, 10));

			// Zoom in, animating the camera.
			googleMap.animateCamera(CameraUpdateFactory.zoomTo(17), 2000, null);
		}

	}

	private void alertDialog() {
		// TODO Auto-generated method stub
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		

		// 2. Chain together various setter methods to set the dialog characteristics
		builder.setMessage("Turn on your device GPS?")
		       .setTitle("Locate Me");
		builder.setPositiveButton("Yes Please", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               // User clicked OK button
	        	   Intent gpsOptionsIntent = new Intent(  
	       			    android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);  
	       			startActivity(gpsOptionsIntent);
	           }
	       });
	builder.setNegativeButton("No Thank you", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               // User cancelled the dialog
	        	   dialog.dismiss();
	           }
	       });

		// 3. Get the AlertDialog from create()
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		getMenuInflater().inflate(R.menu.venue, menu);

		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items

		//lat= 12.9285212 lng=77.58343390
		
		LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE );
		 boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		 if(!statusOfGPS)
		 {
			 Log.d("Tag", "Venue  "+lat +" and   "+lng);
			 alertDialog();
		 }
		 else
		 {
		Intent intent = new Intent(
				android.content.Intent.ACTION_VIEW,
				Uri.parse("http://maps.google.com/maps?saddr="+lat+","+lng+"&daddr=12.943,77.568"));
		intent.setClassName("com.google.android.apps.maps",
				"com.google.android.maps.MapsActivity");
		startActivity(intent);
		 }
		return true;
	}

	@Override
	public void onMyLocationChange(Location location) {
		// TODO Auto-generated method stub
		lat = location.getLatitude();
		lng = location.getLongitude();
		//Toast.makeText(getApplicationContext(), "My venue"+location.getLatitude(), Toast.LENGTH_LONG).show();
	}
	
}
