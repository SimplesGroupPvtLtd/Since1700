package since.since1700.Login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import since.since1700.Adapter.LocationAdapter;
import since.since1700.Model.LocationModel;
import since.since1700.R;

/**
 * Created by Sandhiya on 9/19/2017.
 */

public class LocationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private LocationAdapter locationadapter;
    Context context;
    List<LocationModel> modellist=new ArrayList<LocationModel>();
    List<Button> color = new ArrayList<>();
    Button next;
    Button black,red,blue;
    public static final String mypreference = "mypref";
    SharedPreferences sharedpreferences;

    public static final String colorcode = "colorCode";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_location);
        sharedpreferences =  getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        mLayoutManager=new LinearLayoutManager(context);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(mLayoutManager);
        locationadapter = new LocationAdapter(context,modellist);
        recyclerView.setAdapter(locationadapter);

        next = (Button) findViewById(R.id.btn_next);

        black = (Button)findViewById(R.id.btn_black);
        red = (Button)findViewById(R.id.btn_red);
        blue = (Button)findViewById(R.id.btn_blue);

        color.add(black);
        color.add(red);
        color.add(blue);

        Log.e("BUTTONLIST", String.valueOf(color.get(0)));

        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                black.setBackgroundResource(R.mipmap.tickblue);
               if(modellist.get(0).isColorSelection()) {
                   black.setVisibility(View.VISIBLE);
                   black.setBackgroundResource(R.drawable.black);
                   modellist.get(0).setColorSelection(false);
               }else {
                   // Toast.makeText(getApplicationContext(), modellist.get(position).getProductname() + " selected!", Toast.LENGTH_SHORT).show();
                   black.setVisibility(View.VISIBLE);
                   black.setBackgroundResource(R.mipmap.tickblue);
                   modellist.get(0).setColorSelection(true);
               }


                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(colorcode, "blue");
                editor.commit();

            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                red.setBackgroundResource(R.mipmap.tickblue);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(colorcode, "blue");
                editor.commit();

            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                blue.setBackgroundResource(R.mipmap.tickblue);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(colorcode, "blue");
                editor.commit();

            }
        });



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String data = "";
                List<LocationModel> stList = ((LocationAdapter) locationadapter)
                        .getCountryist();

                for (int i = 0; i < stList.size(); i++) {
                    LocationModel country = stList.get(i);
                    if (country.isSelected() == true) {

                        data = data + "\n" + country.getLocation().toString();

                    }

                }


                Toast.makeText(getApplicationContext(),
                        "Selected items: \n" + data, Toast.LENGTH_LONG)
                        .show();
            }
        });

        final LocationModel comment = new LocationModel();
        final LocationModel comment1 = new LocationModel();
        final LocationModel comment2 = new LocationModel();
        final LocationModel comment3 = new LocationModel();
        final LocationModel comment4 = new LocationModel();
        final LocationModel comment5 = new LocationModel();

        comment.setLocation("NORTH AMERICA");
        comment1.setLocation("SOUTH AMERICA");
        comment2.setLocation("AFRICA");
        comment3.setLocation("EUROPE");
        comment4.setLocation("ASIA");
        comment5.setLocation("AUSTRALIA");


        modellist.add(comment);
        modellist.add(comment1);
        modellist.add(comment2);
        modellist.add(comment3);
        modellist.add(comment4);
        modellist.add(comment5);




    }

}
