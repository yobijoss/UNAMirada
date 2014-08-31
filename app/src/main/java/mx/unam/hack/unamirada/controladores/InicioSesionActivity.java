package mx.unam.hack.unamirada.controladores;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;

import mx.unam.hack.unamirada.R;
import mx.unam.hack.unamirada.util.Constantes;

public class InicioSesionActivity extends ActionBarActivity implements View.OnClickListener {

    private UiLifecycleHelper uiHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        uiHelper = new UiLifecycleHelper(this, callback);
        uiHelper.onCreate(savedInstanceState);

        ((Button)findViewById(R.id.btn_tmp_formulario)).setOnClickListener(this);
    }



    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (session != null && state.isOpened()) {
            Log.i("UNAMIRADA", "Logged in...");
            if(Constantes.getName(this).equals(""))
                makeARequest(session);
            //else
                //goToFormulario();
        } else if (state.isClosed()) {
            Log.i("UNAMIRADA", "Logged out...");
            Constantes.setName(this, "");
        }
    }

    private void goToFormulario() {

       Intent intent = new Intent(InicioSesionActivity.this,FormularioActivity.class);
       startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        uiHelper.onResume();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    private void makeARequest(final Session session) {

        Request request = Request.newMeRequest(session, new Request.GraphUserCallback() {
            @Override
            public void onCompleted(GraphUser user, Response response) {
                if(session == Session.getActiveSession()){
                    if(user!= null){
                        Log.d("UNAMIRADA","EL USUARIO ES : "+ user.getFirstName() + user.getMiddleName() +user.getLastName());
                        Constantes.setName(getApplicationContext(),user.getFirstName()+" " + user.getMiddleName()+" "  +user.getLastName());
                    }
                }
                if (response.getError() != null) {
                    Log.d("UNAMIRADA","EL Error ES : "+ response.getRawResponse());
                }
            }
        });
        request.executeAsync();
    }


    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_tmp_formulario){
            goToFormulario();
        }
    }
}
