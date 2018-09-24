package xyz.controlepaneel.recipe.recipecreator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by User on 2/28/2017.
 */

public class EditIngredients extends AppCompatActivity {

    private static final String TAG = "EditIngredients";

    private Button button_edit,button_delete,button_back;
    private EditText editIngredient;

    DatabaseHelper mDatabaseHelper;

    private String selectedName;
    private int selectedID;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ingredient);
        button_edit = (Button) findViewById(R.id.button_edit);
        button_delete = (Button) findViewById(R.id.button_delete);
        button_back = (Button) findViewById(R.id.button_back);
        editIngredient = (EditText) findViewById(R.id.editIngredient);
        mDatabaseHelper = new DatabaseHelper(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");

        //set the text to show the current selected name
        editIngredient.setText(selectedName);

        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editIngredient.getText().toString();
                if(!item.equals("")){
                    mDatabaseHelper.updateName(item,selectedID,selectedName);
                    Intent int1 = new Intent(EditIngredients.this, Ingredients.class);
                    startActivity(int1);
                }else{
                    toastMessage("You must enter a name");
                }
            }
        });

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedID,selectedName);
                editIngredient.setText("");
                toastMessage("removed from database");
                Intent int1 = new Intent(EditIngredients.this, Ingredients.class);
                startActivity(int1);
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 = new Intent(EditIngredients.this, Ingredients.class);
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


