package xyz.controlepaneel.recipe.recipecreator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddRecipe extends AppCompatActivity {

    private static final String TAG = "AddRecipe";

    DatabaseRecipes mDatabaseRecipes;
    private Button button_add;
    private Button button_back;
    private EditText AddRecipeName;
    private EditText AddRecipe;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        AddRecipeName = (EditText) findViewById(R.id.AddRecipeName);
        AddRecipe = (EditText) findViewById(R.id.AddRecipe);
        button_add = (Button) findViewById(R.id.button_add);
        button_back = (Button) findViewById(R.id.button_back);
        mDatabaseRecipes = new DatabaseRecipes(this);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rName = AddRecipeName.getText().toString();
                String rRecipe = AddRecipe.getText().toString();
                if (AddRecipe.length() != 0 && AddRecipeName.length() != 0) {
                    AddData(rName, rRecipe);
                    AddRecipe.setText("");
                    AddRecipeName.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastMessage("Back to Recipes");
                Intent int1 = new Intent(AddRecipe.this, Recipes.class);
                startActivity(int1);
            }
        });
    }
    public void AddData(String rName, String rRecipe) {
        boolean insertData = mDatabaseRecipes.addData(rName, rRecipe);

        if (insertData) {
            Intent int1 = new Intent(AddRecipe.this, Recipes.class);
            toastMessage("Recipe added, back to recipes");
            startActivity(int1);
        } else {
            toastMessage("Something went wrong");
        }
    }



    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}