package silvanet.com.mx.imagine_space.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import silvanet.com.mx.imagine_space.R;
import silvanet.com.mx.imagine_space.View_List_Recycled;

/**
 * Created by LuisAlfredoSilva on 13/08/2016.
 */
public class LoginFacebook extends AppCompatActivity implements FacebookCallback<LoginResult> {
    @BindView(R.id.fb_login_button)
    LoginButton login;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_facebook);
        ButterKnife.bind(this);

        callbackManager = CallbackManager.Factory.create();
        login.registerCallback(callbackManager,this);

        if(AccessToken.getCurrentAccessToken()!=null){
            //*Snackbar.make(findViewById(android.R.id.content),"Login IF",Snackbar.LENGTH_SHORT).show();
            startActivity(new Intent(this, View_List_Recycled.class));
        }
    }

    @Override
    public void onSuccess(LoginResult loginResult){
        //**Snackbar.make(findViewById(android.R.id.content),"Login",Snackbar.LENGTH_SHORT).show();
        startActivity(new Intent(this, View_List_Recycled.class));
    }

    @Override
    public void onCancel() {
        Snackbar.make(findViewById(android.R.id.content),"Cancel Login",Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void onError(FacebookException error) {
        Snackbar.make(findViewById(android.R.id.content),error.getLocalizedMessage(),Snackbar.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
