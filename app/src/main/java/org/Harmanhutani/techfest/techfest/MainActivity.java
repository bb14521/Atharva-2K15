package org.Harmanhutani.techfest.techfest;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Stack;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, OnMapReadyCallback{

    ImageView tf_logo;

    ArrayList<EventSummary> events = new ArrayList<EventSummary>();
    Stack<Fragment> fragStack = new Stack<Fragment>();

    HashMap<String, Integer[]> layout_desc = new HashMap<String, Integer[]>();

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    SupportMapFragment mapFrag;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private int mActionBarColor = R.color.actionbar_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        ((DrawerLayout) findViewById(R.id.drawer_layout)).openDrawer(findViewById(R.id.navigation_drawer));

        tf_logo = new ImageView(this);

     //   Toast.makeText(getApplicationContext(),
      //          "Enable GPS and Data for Best UX", Toast.LENGTH_LONG).show();

        Picasso.with(this).load(R.drawable.tf_logo).fit().centerCrop().into((tf_logo));

        events = getIntent().getParcelableArrayListExtra("events");






        layout_desc.put("Carnival", new Integer[]{R.layout.details_carnival, R.drawable.vortex, R.color.actionbar_competitions});





        layout_desc.put("Our Sponsors", new Integer[]{R.layout.fragment_ssponserss, R.drawable.geo, R.color.actionbar_competitions});


        layout_desc.put("Technoholix", new Integer[]{R.layout.details_technoholix, R.drawable.et, R.color.actionbar_technoholix});
        layout_desc.put("Lectures", new Integer[]{R.layout.details_lectures, R.drawable.lectures, R.color.actionbar_lectures});
        layout_desc.put("Lan Gaming", new Integer[]{R.layout.details_exhibitions, R.drawable.finalssss, R.color.actionbar_exhibitions});
        layout_desc.put("Model Exhibition", new Integer[]{R.layout.details_ozone, R.drawable.ozone, R.color.actionbar_ozone});


        layout_desc.put("GNE's Cube Challenge", new Integer[]{R.layout.details_game_changer, R.drawable.cube, R.color.actionbar_ideate});


        layout_desc.put("Robo Soccer", new Integer[]{R.layout.details_parishram, R.drawable.soccer1, R.color.actionbar_ideate});
        layout_desc.put("Car Maze Racing", new Integer[]{R.layout.details_ujjwal, R.drawable.race, R.color.actionbar_ideate});

        layout_desc.put("Machine O Founics", new Integer[]{R.layout.details_irc_workshop, R.drawable.gg, R.color.actionbar_workshops
        });
        layout_desc.put("Bridge Modelling", new Integer[]{R.layout.details_augmented_reality, R.drawable.popsic6, R.color.actionbar_workshops});
        layout_desc.put("RCC", new Integer[]{R.layout.details_zero_energy_buildings, R.drawable.rcc, R.color.actionbar_workshops});
        layout_desc.put("Water Rock-It", new Integer[]{R.layout.details_arduped, R.drawable.water, R.color.actionbar_workshops});
        layout_desc.put("DAM-It", new Integer[]{R.layout.details_cloud_computing, R.drawable.cloud_computing, R.color.actionbar_workshops});
        layout_desc.put("Robomania", new Integer[]{R.layout.details_gyrocopter, R.drawable.mania, R.color.actionbar_workshops});
        layout_desc.put("Electroclique", new Integer[]{R.layout.details_hacktricks_level_1, R.drawable.hacktricks, R.color.actionbar_workshops});
        layout_desc.put("Hacktricks Level 2", new Integer[]{R.layout.details_hacktricks_level_2, R.drawable.hacktricks, R.color.actionbar_workshops});
        layout_desc.put("Dev Cops", new Integer[]{R.layout.details_web_development, R.drawable.dev, R.color.actionbar_workshops});
        layout_desc.put("Geomorphs", new Integer[]{R.layout.details_swarm_robotics, R.drawable.geo, R.color.actionbar_workshops});
        layout_desc.put("Auto Spark ||", new Integer[]{R.layout.details_psoc, R.drawable.haryy, R.color.actionbar_workshops});
        layout_desc.put("Digital Drawing", new Integer[]{R.layout.details_propeller_clock, R.drawable.dg, R.color.actionbar_workshops});
        layout_desc.put("Drafting Mania", new Integer[]{R.layout.details_unmanned_vehicle, R.drawable.rr, R.color.actionbar_workshops});
        layout_desc.put("Hackathon", new Integer[]{R.layout.details_robotic_navigation, R.drawable.hack, R.color.actionbar_workshops});
        layout_desc.put("Electrocle3rique", new Integer[]{R.layout.details_sixth_sense_robotics, R.drawable.et, R.color.actionbar_workshops});

        layout_desc.put("Decathlon", new Integer[]{R.layout.details_dcathlon,R.drawable.qq,R.color.actionbar_lectures});
        layout_desc.put("Game of Thrones", new Integer[]{R.layout.details_got,R.drawable.game,R.color.actionbar_lectures});
        layout_desc.put("CSI 3.0", new Integer[]{R.layout.details_csi,R.drawable.csi,R.color.actionbar_lectures});
        layout_desc.put("Mock Parliament", new Integer[]{R.layout.details_mock,R.drawable.mock,R.color.actionbar_lectures});
        layout_desc.put("Placenario 2.0", new Integer[]{R.layout.details_place,R.drawable.training_placement,R.color.actionbar_lectures});
        layout_desc.put("Snazzy Fiesta", new Integer[]{R.layout.details_snazy,R.drawable.hh,R.color.actionbar_lectures});
        layout_desc.put("Electroclique", new Integer[]{R.layout.details_dcathlon,R.drawable.qq,R.color.actionbar_lectures});


        layout_desc.put("Photography workshop by Nikon", new Integer[] {R.layout.details_camera, R.drawable.log3, R.color.actionbar_technoholix});
        layout_desc.put("Ethical Hacking", new Integer[] {R.layout.details_hack, R.drawable.et, R.color.actionbar_technoholix});
        layout_desc.put("Workshop by UltraTech Cement", new Integer[] {R.layout.details_cement, R.drawable.au, R.color.actionbar_technoholix});
        layout_desc.put("Home Security System", new Integer[] {R.layout.details_home, R.drawable.qwww, R.color.actionbar_technoholix});
        layout_desc.put("Image Processing", new Integer[] {R.layout.details_image, R.drawable.im, R.color.actionbar_technoholix});
        layout_desc.put("Civil Services Preparation", new Integer[] {R.layout.details_upsc, R.drawable.upsc, R.color.actionbar_technoholix});
        addLayoutIDs();

        restoreActionBar();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
     //   mapFrag=(SupportMapFragment) fragmentManager.findFragmentById(R.id.map_fragment);
        Fragment frag = null;
        boolean show=false;

       position++;

        switch (position) {

            case 8:
                frag = EventListFragment.newInstance(EventListFragment.TYPE_LIST, getString(R.string.title_technoholix),R.color.actionbar_technoholix, R.layout.details_technoholix, filterEvents(getString(R.string.title_technoholix)));
                break;
            case 31:
              //  frag = EventListFragment.newInstance(EventListFragment.TYPE_LIST_GROUP, getString(R.string.title_initiatives), R.color.actionbar_initiatives, R.layout.fragment_initiatives, filterEvents(getString(R.string.title_initiatives)));
                break;
            case 5:
                frag = EventListFragment.newInstance(EventListFragment.TYPE_LIST, getString(R.string.title_lectures),R.color.actionbar_lectures, R.layout.details_lectures, filterEvents(getString(R.string.title_lectures)));
                break;
            case 3:
                frag = EventListFragment.newInstance(EventListFragment.TYPE_LIST, getString(R.string.title_competitions),R.color.actionbar_lectures, R.layout.details_carnival, filterEvents(getString(R.string.title_competitions)));
                break;
            case 7:
                for (EventSummary es : events)
                    if (es.title.equals(getString(R.string.title_exhibitions))) {
                        frag = EventDetailsFragment.newInstance(es);
                    }
                break;
            case 9:
                for (EventSummary es : events)
                    if (es.title.equals(getString(R.string.title_ozone))) {
                        frag = EventDetailsFragment.newInstance(es);
                    }
                break;
            case 6:
               /* for (EventSummary es : events)
                    if (es.title.equals(getString(R.string.title_conference))) {
                        frag = EventDetailsFragment.newInstance(es);
                    }*/
                frag=EventListFragment.newInstance(EventListFragment.TYPE_LIST_GROUP, "Our Sponsers", R.color.actionbar_home, R.layout.fragment_ssponserss,null);
                break;
            case 2:
                frag = EventListFragment.newInstance(EventListFragment.TYPE_LIST, getString(R.string.title_ideate), R.color.actionbar_ideate, R.layout.details_ideate, filterEvents(getString(R.string.title_ideate)));
                break;
            case 4:
                frag = EventListFragment.newInstance(EventListFragment.TYPE_LIST, getString(R.string.title_workshops), R.color.actionbar_workshops, R.layout.details_workshops, filterEvents(getString(R.string.title_workshops)));
                break;
            case 10:
                //registration
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://docs.google.com/forms/d/1na6WGrtm8ne-XE40Gh0uUn-APzeQgJhWNgwWadHkyEM/viewform?usp=send_form"));
                startActivity(i);
                return;
            case 11:
                Intent in = new Intent(Intent.ACTION_VIEW);
                in.setData(Uri.parse("https://www.google.com/maps/d/edit?mid=zpO2xvnvoSdI.k_f1Ri_IP9JY&usp=sharing"));
               startActivity(in);

                return;



            case 12:
                frag=EventListFragment.newInstance(EventListFragment.TYPE_LIST_GROUP, "Big View", R.color.actionbar_home, R.layout.fragment_ourpics,null);
                break;
            case 13:
                frag=EventListFragment.newInstance(EventListFragment.TYPE_LIST_GROUP, getString(R.string.title_developers), R.color.actionbar_developers, R.layout.fragment_developers,null);
                break;
            default:
                frag = EventListFragment.newInstance(EventListFragment.TYPE_LIST_GROUP, getString(R.string.title_home), R.color.actionbar_home, R.layout.fragment_main, null);

               // mapFrag.getMapAsync(this);

                show=true;
                break;
        }

        fragStack.push(frag);

        fragmentManager.beginTransaction()
                .replace(R.id.container, frag)
                .commit();

    }

    public ArrayList<EventSummary> filterEvents(String title) {
        ArrayList<EventSummary> temp = new ArrayList<EventSummary>();

        for (EventSummary es : events) {
            String[] categories = es.category.split("\\+");
            for (String cat : categories) {
                if (cat.equals(title)) {
                    temp.add(es);
                    break;
                }
            }
        }

        return temp;
    }

    public void loadDetails(View v) {
        EventDetailsFragment eventDetails = EventDetailsFragment.newInstance(events.get(v.getId()));

        fragStack.push(eventDetails);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, eventDetails)
                .commit();
    }

    public void loadList(View v) {
        String title = (String) v.getTag();

        EventListFragment eventList = EventListFragment.newInstance(
                EventListFragment.TYPE_LIST,
                title,
                layout_desc.get(title)[2],
                layout_desc.get(title)[0],
                filterEvents(title));

        fragStack.push(eventList);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, eventList)
                .commit();
    }

    public SupportMapFragment loadMapFragment(){
        SupportMapFragment mapFrag = SupportMapFragment.newInstance();



       mapFrag.getMapAsync(this);


       return mapFrag;
    }

    public void addLayoutIDs() {
        for (EventSummary es : events) {
            Integer[] temp_desc = layout_desc.get(es.title);
            if (temp_desc != null) {
                es.description_layout = temp_desc[0];
                es.image_id = temp_desc[1];
                es.actionbar_color = temp_desc[2];
            } else {
               // es.description_layout = R.layout.details_robowars;
            }
        }
    }


    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public void setReminder(View v) {
        EventSummary es = events.get((Integer) v.getTag());

        String beginTime=es.time.split("-")[0];
        int beginHours = Integer.valueOf(beginTime.split(":")[0]);
        int beginMinutes = Integer.valueOf(beginTime.split(":")[1]);

        String endTime;

        try{endTime=es.time.split("-")[1];}
        catch(ArrayIndexOutOfBoundsException e){
            endTime=beginTime;
        }

        int endHours = Integer.valueOf(endTime.split(":")[0]);
        int endMinutes = Integer.valueOf(endTime.split(":")[1]);

        int year = Integer.valueOf(es.date.split("/")[2]);
        int month = Integer.valueOf(es.date.split("/")[1]) - 1;
        int day = Integer.valueOf(es.date.split("/")[0].split("\\+")[0]);



        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, beginHours, beginMinutes);

        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", cal.getTimeInMillis());
        intent.putExtra("allDay", false);

        cal.set(year, month, day, endHours, endMinutes);

        intent.putExtra("endTime", cal.getTimeInMillis());
        intent.putExtra("title", es.title + " @ " + es.venue);
        intent.putExtra("description", es.description);
        startActivity(intent);
    }

    public String getLatLangString(String place) {
        if (place.equals(""))
            return "19.130739,72.917208";
        else if (place.equals("SAC"))
            return "19.135369,72.913769";
        else if (place.equals("Footer Field"))
            return "19.134354,72.912133";
        else if (place.equals("VMCC"))
            return "19.132506,72.917260";
        else if (place.equals("FCK"))
            return "19.130484,72.915719";
        else if (place.equals("H8 Road"))
            return "19.133731,72.911319";
        else if (place.equals("KV Grounds"))
            return "19.129144,72.918190";
        else if (place.equals("SOM"))
            return "19.1317237,72.9157796";
        else if (place.equals("Convocation Hall"))
            return "19.1319587,72.914763";
        else if (place.equals("Open Air Theatre"))
            return "19.135369,72.913769";
        else if (place.equals("Swimming Pool"))
            return "19.1351579,72.9126434";
        else if (place.equals("LT Back Lawns"))
            return "19.1329083,72.9156243";
        else if (place.equals("LA Foyer"))
            return "19.1307588,72.9170093";
        else if (place.equals("GG"))
            return "19.1315482,72.9162513";
        else if (place.equals("IC"))
            return "19.13159,72.9157591";
        else if (place.equals("MB Foyer"))
            return "19.1324573,72.9153494";
        else if (place.equals("ME"))
            return "19.1333328,72.9164112";
        else if (place.equals("LA"))
            return "19.1307588,72.9170093";
        else if (place.equals("LT"))
            return "19.1323033,72.9156914";
        else
            return "19.1279852,72.914763";
        //Done Till Here adding Geo-Coordinates
        // To Add OAT, Swimming Pool, LT Back Lawns, LA Foyer, GG, IC, MB Foyer, ME, LA, LT
    }

    public LatLng getLatLng(String place){
        String str=getLatLangString(place);

        return new LatLng(Float.valueOf(str.split(",")[0]),Float.valueOf(str.split(",")[1]));
    }

    public void getDirections(View v) {
       // EventSummary es = events.get((Integer) v.getTag());

        Intent in = new Intent(Intent.ACTION_VIEW);
        in.setData(Uri.parse("https://www.google.com/maps/d/edit?mid=zpO2xvnvoSdI.k_f1Ri_IP9JY&usp=sharing"));
        startActivity(in);
        return;


    }

    public void onSectionAttached(String title, int actionbar_color) {
        mTitle = title;
        mActionBarColor = actionbar_color;

        restoreActionBar();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();

        if(actionBar==null) return;

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(mActionBarColor)));
    }

    @Override
    public void onBackPressed() {
        boolean showMap=false;

        if (mNavigationDrawerFragment.isDrawerOpen()) {
            mNavigationDrawerFragment.closeDrawer();
        } else {
            fragStack.pop();

            if (fragStack.empty())
                super.onBackPressed();
            else {
                Fragment currFrag = fragStack.peek();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, currFrag)
                        .commit();
                if(currFrag instanceof EventFragment){
                    if(((EventFragment)currFrag).getTitle().equals("Home"))
                        showMap=true;
                    onSectionAttached(((EventFragment) currFrag).getTitle(), ((EventFragment) currFrag).getActionBarColor());
                } else if(currFrag instanceof SupportMapFragment) {
                    ((SupportMapFragment) currFrag).getMapAsync(this);

                    onSectionAttached("Map", R.color.actionbar_home);
                }

            }
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        for(EventSummary es : events){

            map.addMarker(new MarkerOptions()
                    .position(getLatLng(es.venue))
                    .title(es.title)
                    .icon(BitmapDescriptorFactory.fromResource(getSuperIcon(es.actionbar_color))));
        }
        //Add Utility Icons Here
        //Food
        // Convo-side
        map.addMarker(new MarkerOptions()
                .position(getLatLng("19.1314256,72.9143114"))
                .title("Food Court Convo")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fastfood_map)));

        // SAC-side
        map.addMarker(new MarkerOptions()
                .position(getLatLng("19.1349975,72.9139439"))
                .title("Food Court SAC")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fastfood_map)));

        //Restaurant
        map.addMarker(new MarkerOptions()
                .position(getLatLng("19.1285848,72.9145769"))
                .title("Restaurant")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.restaurant_map)));

        map.setMyLocationEnabled(true);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.133709, 72.913284),15));
    }

    public int getSuperIcon(int abcolor){
        //28498a
        switch(abcolor){
            case R.color.actionbar_lectures:
                return R.drawable.lectures_map;
            case R.color.actionbar_exhibitions:
                return R.drawable.exhibition_map ;
            case R.color.actionbar_technoholix:
                return R.drawable.technoholix_map ;
            case R.color.actionbar_initiatives:
                return R.drawable.initiative_map ;
            case R.color.actionbar_conference:
                return R.drawable.conference_map ;
            case R.color.actionbar_competitions:
                return R.drawable.competitions_map;
            case R.color.actionbar_ozone:
                return R.drawable.ozone_map;
            case R.color.actionbar_workshops:
                return R.drawable.workshops_map ;
            case R.color.actionbar_ideate:
                return R.drawable.ideate_map;
            default:
                return R.drawable.icon_wo_bg_1;
        }
    }

    public void fblink(View v){
        String name = (String)v.getTag();

       // String url = "fb://profile/";
        String url = "http://www.facebook.com/";
        try{
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url+name));
            startActivity(i);
        } catch (Exception e){
            url = "http://www.facebook.com/";

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url+name));
            startActivity(i);
        }
    }

    public void ghlink(View v){
        String name = (String)v.getTag();
        String url="http://www.github.com/";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url+name));
        startActivity(i);
    }

    public void registerLink(TextView tv){
        String url="http://www.foreseegame.com/PromotionalActivities.aspx?paramPro=IITBombay1";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void launchMarket(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id="+v.getTag()));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id="+v.getTag()));
            startActivity(intent);
        }
    }
}
