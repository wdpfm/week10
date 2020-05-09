package com.wdpfm.week10;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tencent.mmkv.MMKV;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MMKV.initialize(this);
        MMKV kv = MMKV.defaultMMKV();
        String mUsername = kv.decodeString("username");
        String mPassword = kv.decodeString("password");
        username.setText(mUsername);
        password.setText(mPassword);
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        if (username.getText().toString().equals("")||password.getText().toString().equals("")){
            Toast.makeText(this, "账号或密码为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if(checkBox.isChecked()){
            MMKV kv = MMKV.defaultMMKV();
            kv.encode("username", username.getText().toString());
            kv.encode("password", password.getText().toString());
        }
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }
}
