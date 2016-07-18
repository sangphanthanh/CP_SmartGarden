package com.sangpt.smartgarden.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sangpt.smartgarden.R;
import com.sangpt.smartgarden.model.responseModel.LoginResponseModel;
import com.sangpt.smartgarden.services.RestService;
import com.sangpt.smartgarden.utils.DataUtils;
import com.sangpt.smartgarden.utils.QuickSharePreference;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    ViewHolder viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String username = DataUtils.getINSTANCE(this).getmPreferences().getString(QuickSharePreference.SHARE_USERNAME,"");
//        if (username.length()>0){
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.right_in, R.anim.left_out);
//            finish();
//        }
        viewHolder = new ViewHolder();
        viewHolder.txtUsername = (EditText) findViewById(R.id.txt_login_username_email);
        viewHolder.txtPassword = (EditText) findViewById(R.id.txt_login_password);
        viewHolder.btnLogin = (Button) findViewById(R.id.btn_login);
        viewHolder.btnLogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        DataUtils.getAlphaAmination(v);
        int id = v.getId();
        switch (id) {
            case R.id.btn_login:
                String username = viewHolder.txtUsername.getText().toString();
                String password = viewHolder.txtPassword.getText().toString();
                View focus = null;
                if (username.trim().length() <= 0) {
                    viewHolder.txtUsername.setError("Xin hãy điền username!");
                    focus = viewHolder.txtUsername;
                }
                if (password.trim().length() <= 0) {
                    viewHolder.txtPassword.setError("Xin hãy điền mật khẩu!");
                    if (focus == null)
                        focus = viewHolder.txtPassword;
                }
                if (focus != null) {
                    focus.requestFocus();
                } else {
                    RestService restService = new RestService();
                    restService.getAccountService().checkLogin(username, password, new Callback<LoginResponseModel>() {
                        @Override
                        public void success(LoginResponseModel responseModel, Response response) {
                            if (responseModel != null) {
                                DataUtils.getINSTANCE(LoginActivity.this).getmPreferences().edit().putString(QuickSharePreference.SHARE_USERNAME,responseModel.getUsername()).commit();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Login fail", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Toast.makeText(LoginActivity.this, "Login fail", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                break;
        }
    }

    private final class ViewHolder {
        EditText txtUsername;
        EditText txtPassword;
        Button btnLogin;
    }
}
