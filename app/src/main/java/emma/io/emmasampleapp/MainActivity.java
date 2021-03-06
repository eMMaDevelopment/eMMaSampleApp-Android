package emma.io.emmasampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ToggleButton;

import com.emma.android.eMMa;

import java.util.HashMap;
import java.util.Map;

import io.emma.android.EMMA;
import io.emma.android.model.EMMACampaign;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ToggleButton tButton = (ToggleButton) findViewById(R.id.toggleButton);

        Button button = (Button)findViewById(R.id.next_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String,String> map = new HashMap<>();
                map.put("GENDER",tButton.isChecked()? "Male" :"Woman");

                EMMA.getInstance().trackExtraUserInfo( map);

                Intent intent = new Intent(MainActivity.this,ThanksActivity.class);
                startActivity(intent);
            }
        });

        //Check for any StartView
        EMMA.getInstance().getInAppMessage(EMMACampaign.Type.STARTVIEW);

        //Check push received options
        EMMA.getInstance().checkForRichPushUrl();
    }

    //Used when app is open and it receives a new Push
    @Override
    public void onNewIntent(Intent intent){
        eMMa.onNewNotification(intent,true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
