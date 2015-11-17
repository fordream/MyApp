package com.aeolus.secretk.Log;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aeolus.secretk.MD5;
import com.aeolus.secretk.MainActivity;
import com.aeolus.secretk.R;

public class Login extends Activity {

    private Button enter_btn;
    private EditText psd_text;
    private SharedPreferences pref;
    MD5 md5 = new MD5();

    private String mainpsd;
    private String input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        enter_btn = (Button) findViewById(R.id.button);
        psd_text = (EditText) findViewById(R.id.psdText);
        pref= getSharedPreferences("mainpassword", MODE_WORLD_READABLE);

       // Log.e("okok","getget");

        mainpsd = pref.getString("mainpassword", "");
        //Log.e("get main psd",mainpsd);

        enter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = md5.GetMD5Code(psd_text.getText().toString());
                Log.e("input",input);
                //Toast.makeText(Login.this,input,Toast.LENGTH_SHORT).show();
                if(mainpsd.equals(input))
                {
                    startActivity(new Intent(Login.this, MainActivity.class));
                }
                else
                {
                    Toast.makeText(Login.this, "密码错误！", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
