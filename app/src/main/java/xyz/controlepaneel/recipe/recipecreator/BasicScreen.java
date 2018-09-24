package xyz.controlepaneel.recipe.recipecreator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BasicScreen extends AppCompatActivity {

    private static final String TAG = "BasicScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_screen);
        Log.d(TAG,"onCreate: Started.");
        Button button_ingredients = (Button) findViewById(R.id.button_ingredients);
        Button button_recipes = (Button) findViewById(R.id.button_recipes);

        button_ingredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Continue to ingredients");
                toastMessage("Continue to ingredients");
                Intent intent = new Intent(BasicScreen.this, Ingredients.class);
                startActivity(intent);
            }
        });

        button_recipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Continue to recipes");
                toastMessage("Continue to recipes");
                Intent intent = new Intent(BasicScreen.this, Recipes.class);
                startActivity(intent);
            }
        });
    }

    private void toastMessage(String message){
        Toast.makeText(BasicScreen.this, message, Toast.LENGTH_SHORT).show();
    }
}
