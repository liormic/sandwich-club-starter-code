package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private static final String TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = 0;
        if (intent != null) {
            position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        }

        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }
        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing Json" + e.getMessage());
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }


    private void populateUI(Sandwich sandwichIntilaized) {

        ImageView sandwichImage = findViewById(R.id.image_iv);
        TextView alsoKnownAs = findViewById(R.id.also_known_tv);
        TextView originTv = findViewById(R.id.origin_tv);
        TextView descriptionTv = findViewById(R.id.description_tv);
        TextView ingridentsTv = findViewById(R.id.ingredients_tv);

        Picasso.with(this)
                .load(sandwichIntilaized.getImage())
                .into(sandwichImage);

        setTitle(sandwichIntilaized.getMainName());
        //replaceAll taken from stackoverflow
        alsoKnownAs.setText(sandwichIntilaized.getAlsoKnownAs().toString().replaceAll("\\[|]", ""));
        originTv.setText(sandwichIntilaized.getPlaceOfOrigin());
        descriptionTv.setText(sandwichIntilaized.getDescription());
        //replaceAll taken from stackoverflow
        ingridentsTv.setText(sandwichIntilaized.getIngredients().toString().replaceAll("\\[|]", ""));
    }

}
