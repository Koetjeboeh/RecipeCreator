package xyz.controlepaneel.recipe.recipecreator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by User on 2/28/2017.
 */

public class EditRecipe extends AppCompatActivity {

    private static final String TAG = "EditRecipe";

    private Button button_edit,button_delete,button_back;
    public EditText editRecipe, editRecipeName;

    DatabaseRecipes mDatabaseRecipe;

    private String selectedName, selectedRecipe;
    private int selectedID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);
        button_edit = (Button) findViewById(R.id.button_edit);
        button_delete = (Button) findViewById(R.id.button_delete);
        button_back = (Button) findViewById(R.id.button_back);
        editRecipeName = (EditText) findViewById(R.id.editRecipeName);
        editRecipe = (EditText) findViewById(R.id.editRecipe);
        mDatabaseRecipe = new DatabaseRecipes(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");

        //now get the name we passed as an extra
        selectedRecipe = receivedIntent.getStringExtra("recipe");

        //set the text to show the current selected name
        editRecipeName.setText(selectedName);

        //set the text to show the current selected name
        editRecipe.setText(selectedRecipe);

        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editRecipeName.getText().toString();
                String item2 = editRecipe.getText().toString();
                if(!item.equals("") && !item2.equals("")){
                    mDatabaseRecipe.updateName(item,selectedID,selectedName);
                    mDatabaseRecipe.updateRecipe(item2,selectedID,selectedRecipe);
                    Intent int1 = new Intent(EditRecipe.this, Recipes.class);
                    startActivity(int1);
                }else{
                    toastMessage("You must enter a name");
                }
            }
        });

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseRecipe.deleteName(selectedID,selectedName);
                editRecipeName.setText("");
                toastMessage("removed from database");
                Intent int1 = new Intent(EditRecipe.this, Recipes.class);
                startActivity(int1);
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 = new Intent(EditRecipe.this, Recipes.class);
                startActivity(int1);
            }
        });

    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}


