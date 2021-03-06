package hackhpi.de.graden;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import me.relex.circleindicator.CircleIndicator;

public class DetailActivity extends AppCompatActivity {

    public static String GARDEN_PARAMETER = "garden_parameter";

    private final Integer[] GardenImages = {R.drawable.scale1, R.drawable.scale2, R.drawable.scale3, R.drawable.scale4};
    private ArrayList<Integer> Images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Garden garden = (Garden) getIntent().getSerializableExtra(GARDEN_PARAMETER);

        if (garden == null) {
            throw new IllegalStateException("Activity should be started with parameter");
        }

        setTitle(garden.name);

        TextView owner = (TextView) findViewById(R.id.owner);
        owner.setText(garden.owner);

        ImageView picture = (ImageView) findViewById(R.id.ownerImage);
        picture.setImageDrawable(getResources().getDrawable(garden.imageID));

        ImageView headerPicture = (ImageView) findViewById(R.id.detail_header);
        headerPicture.setImageDrawable(getResources().getDrawable(garden.headerImage));

        TextView location = (TextView) findViewById(R.id.location);
        location.setText(garden.location);

        TextView gardenType = (TextView) findViewById(R.id.gardenType);
        gardenType.setText(garden.type);

        TextView size = (TextView) findViewById(R.id.size);
        size.setText(garden.size);

        TextView plants = (TextView) findViewById(R.id.listOfPlants);
        plants.setText(garden.listOfPlants);

        TextView animals = (TextView) findViewById(R.id.animals);
        animals.setText(garden.livestock);

        TextView people = (TextView) findViewById(R.id.people);
        people.setText(garden.numberOfMembers);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q=HPI-Potsdam"));
                startActivity(intent);
            }
        });

        init();
    }

    private void init() {
        Collections.addAll(Images, GardenImages);

        ViewPager mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new ImageAdapter(DetailActivity.this, Images));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
    }
}
