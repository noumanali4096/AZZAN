package com.example.nouman.azzan;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import static com.firebase.ui.auth.ui.email.RegisterEmailFragment.TAG;


public class MosqueFragmentSolution extends Fragment implements com.google.android.gms.location.LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
    MapView mMapView;
    private GoogleMap mgoogleMap;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;
    private Marker currentLocationMarker;
    private Location lastLocation;
    DatabaseReference databasePrayerTiming;

    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final float DEFAULT_ZOOM = 12f;
    private DatabaseReference moqueFirebase;
    PrayerTimmings prayerTimmings;

    private static final String FINE_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = android.Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    public static final int REQUEST_LOCATION_CODE = 99;

    private HashMap<String,Marker> markerList;
    private Marker mMarker;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        moqueFirebase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://azzan-f7f08.firebaseio.com/mosque");
        databasePrayerTiming = FirebaseDatabase.getInstance().getReferenceFromUrl
                ("https://azzan-f7f08.firebaseio.com/prayertiming");
        markerList = new HashMap<String, Marker>();
        getLocationPermission();
        View rootView = inflater.inflate(R.layout.fragment_mosque_fragment_solution, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mgoogleMap = mMap;

                // For showing a move to my location button
                if(!getLocationPermission())
                {
                    getActivity().recreate();
                }
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    buildGoogleApiClient();
                    mgoogleMap.setMyLocationEnabled(true);
                    getDeviceLocation();
                    moqueFirebase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for(DataSnapshot ds : dataSnapshot.getChildren())
                            {

                                Mosque obj = ds.getValue(Mosque.class);

                                if(mgoogleMap!=null) {

                                    LatLng latLng=new LatLng(obj.getLati(),obj.getLongitude());
                                    MarkerOptions options = new MarkerOptions()
                                            .position(latLng)
                                            .title(obj.getName());
                                    options.icon(BitmapDescriptorFactory.fromResource(R.drawable.mosque));
                                    mMarker =  mgoogleMap.addMarker(options);
                                    markerList.put(obj.getPhone(),mMarker);
                                }

                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }


               mgoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                   @Override
                   public boolean onMarkerClick(Marker marker) {

                       for (HashMap.Entry<String,Marker> entry : markerList.entrySet()) {
                           if(entry.getValue().equals(marker)){
                               mMarker = marker;
                               String phone = entry.getKey();
                               Query fireBaseQuery = databasePrayerTiming.orderByChild("phoneNumber").equalTo(phone);

                               fireBaseQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                                   @Override
                                   public void onDataChange(DataSnapshot dataSnapshot) {

                                       //if (dataSnapshot.exists()) {

                                       // dataSnapshot is the "issue" node with all children with id 0
                                       for (DataSnapshot mosqueadminSnapshot : dataSnapshot.getChildren()) {
                                           prayerTimmings =  mosqueadminSnapshot.getValue(PrayerTimmings.class);
                                           if(prayerTimmings!=null)
                                           {
                                               FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                               BottomSheetDialog.newInstance(prayerTimmings,mMarker.getTitle(),
                                                       currentLocationMarker.getPosition().latitude,currentLocationMarker.getPosition().longitude,
                                                       mMarker.getPosition().latitude,mMarker.getPosition().longitude).show(transaction, "bottomsheet");
                                           }
                                       }

                                   }

                                   @Override
                                   public void onCancelled(DatabaseError databaseError) {

                                   }
                               });

                           }
                       }
                       return false;
                   }
               });

            }

        });

        return rootView;
    }



    public boolean getLocationPermission(){
       if(ContextCompat.checkSelfPermission(getContext()
               , Manifest.permission.ACCESS_FINE_LOCATION )!= PackageManager.PERMISSION_GRANTED){
           if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)){
               ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_CODE);

           }
           else
           {
               ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_CODE);
           }
           return false;
       }
       else{
           return true;
       }

    }

    protected synchronized void buildGoogleApiClient() {
        client = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        client.connect();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionGranted = false;

        switch(requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length > 0){
                    for(int i = 0; i < grantResults.length; i++){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionGranted = true;
                    //initialize our map
                    //updateLocationUI();
                }
            }
        }
    }


    private void updateLocationUI() {
        if (mgoogleMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted ) {

                mgoogleMap.setMyLocationEnabled(true);
                mgoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mgoogleMap.setMyLocationEnabled(false);
                mgoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getDeviceLocation(){
       // Log.d(TAG, "getDeviceLocation: getting the devices current location");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.getContext());
        try{


                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                           // Log.d(TAG, "onComplete: found location!");
                            Location currentLocation = (Location) task.getResult();
                            lastLocation=currentLocation;


                            LatLng latLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                            MarkerOptions options = new MarkerOptions();
                            options.position(latLng);
                            //options.title("My Location");


                            //currentLocationMarker=mgoogleMap.addMarker(options);
                           // moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                  //  DEFAULT_ZOOM,"My Location");

                            mgoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                          //  mgoogleMap.animateCamera(CameraUpdateFactory.zoomBy(DEFAULT_ZOOM));

                        }else{
                          //  Log.d(TAG, "onComplete: current location is null");
                            //Toast.makeText(this.getContext(), "Map is Ready", Toast.LENGTH_SHORT).show();
                            // Toast.makeText(this.g, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        }catch (SecurityException e){
           // Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
        }
    }

    private void moveCamera(LatLng latLng, float zoom,String title){
       // Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mgoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onLocationChanged(Location location) {

        lastLocation = location;
        if (currentLocationMarker != null) {
            currentLocationMarker.remove();
        }
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        currentLocationMarker = mgoogleMap.addMarker(markerOptions);
        mgoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mgoogleMap.animateCamera(CameraUpdateFactory.zoomBy(DEFAULT_ZOOM));

        if (client != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(client, this);
        }
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}
