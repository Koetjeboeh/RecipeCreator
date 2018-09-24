package xyz.controlepaneel.recipe.recipecreator;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private final int SPLASH_DISPLAY_LENGHT = 1000;

    // ===========================================================
    // "Constructors"
    // ===========================================================

    /** Called when the activity is first created. */

    @Override

    public void onCreate(Bundle icicle) {

        super.onCreate(icicle);

        setContentView(R.layout.activity_main);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/

        new Handler().postDelayed(new Runnable(){

            @Override

            public void run() {

                /* Create an Intent that will start the Menu-Activity. */

                Intent mainIntent = new Intent(MainActivity.this,BasicScreen.class);

                MainActivity.this.startActivity(mainIntent);

                MainActivity.this.finish();

            }

        }, SPLASH_DISPLAY_LENGHT);

    }

}
