package com.aeolus.secretk;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aeolus.secretk.R;

import java.util.zip.Inflater;

public class AddContent extends Activity{

    EditText editText;
    Button btn;
    String psd;
    String md5psd;
    String output="";
    private SharedPreferences pref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_content);

        editText = (EditText) findViewById(R.id.add_psd);
        btn = (Button) findViewById(R.id.add_btn);
        pref= getSharedPreferences("mainpassword", MODE_WORLD_READABLE);

        md5psd = pref.getString("mainpassword", "");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                psd = editText.getText().toString();
                try {
                    CryptLib _crypt = new CryptLib();

                    String plainText = psd;
                    String key = CryptLib.SHA256(md5psd, 32); //32 bytes = 256 bit
                    String iv = CryptLib.generateRandomIV(16); //16 bytes = 128 bit
                    output = _crypt.encrypt(plainText, key, iv); //encrypt
                    Toast.makeText(AddContent.this,output,Toast.LENGTH_SHORT).show();
                    System.out.println("encrypted text=" + output);
                    output = _crypt.decrypt(output, key,iv); //decrypt
                    System.out.println("decrypted text=" + output);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });

    }
}
