package com.aeolus.secretk;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainChange extends Activity {


    Button btn;
    EditText et;
    MD5 md5;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_change);
        ChangeMain();


    }

    private void ChangeMain(){

        btn = (Button) findViewById(R.id.ok_btn);
        et = (EditText) findViewById(R.id.change_main);
        btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String psd = et.getText().toString();
                    pref = getSharedPreferences("mainpassword", MODE_WORLD_READABLE + MODE_WORLD_WRITEABLE);
                    editor = pref.edit();
                    editor.putString("mainpassword", md5.GetMD5Code(psd));

                    editor.commit();

                    Toast.makeText(MainChange.this, "修改成功！", Toast.LENGTH_SHORT).show();
                }
            });

        Toast.makeText(MainChange.this, "Change your main password here!", Toast.LENGTH_SHORT).show();

    }

}
