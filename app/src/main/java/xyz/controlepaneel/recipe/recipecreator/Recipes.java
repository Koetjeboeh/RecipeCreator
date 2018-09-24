package xyz.controlepaneel.recipe.recipecreator;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by User on 2/28/2017.
 */

public class Recipes extends AppCompatActivity {

    private static final String TAG = "Recipes";

    DatabaseRecipes mDatabaseRecipes;
    ArrayList<RecipeHelper> recipeList;
    ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        recipeList = new ArrayList<>();
        mListView = (ListView) findViewById(R.id.listView);
        mDatabaseRecipes = new DatabaseRecipes(this);
        Cursor data = mDatabaseRecipes.getData();

        populateListView();

        Button button_add = (Button) findViewById(R.id.button_add);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Continue to adding Recipes");
                toastMessage("Continue to adding Recipes");
                Intent intent = new Intent(Recipes.this, AddRecipe.class);
                startActivity(intent);
            }
        });
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        Cursor data = mDatabaseRecipes.getData();
        ArrayList<String>  listData = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
        }
        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //  TextView name = (TextView) view.getTag(R.id.editRecipeName);
                //  TextView recipe = (TextView) view.getTag(R.id.editRecipe);
                  String name = recipeList.get(i).getRecipeName();
                  String recipe = recipeList.get(i).getRecipeName();
                //  String name = adapterView.getItemAtPosition().toString();
                //  String recipe = adapterView.getItemAtPosition(l).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + name);

                Cursor data = mDatabaseRecipes.getData(); //get the id associated with that name
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent(Recipes.this, EditRecipe.class);
                    editScreenIntent.putExtra("ID",itemID);
                    editScreenIntent.putExtra("name",name);
                    editScreenIntent.putExtra("recipe",recipe);
                    startActivity(editScreenIntent);
                }
                else{
                    toastMessage("No ID associated with that ingredient");
                }
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