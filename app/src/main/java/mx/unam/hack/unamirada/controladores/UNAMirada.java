package mx.unam.hack.unamirada.controladores;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

import mx.unam.hack.unamirada.objetos.Eventos;
import mx.unam.hack.unamirada.objetos.Registro;

/**
 * Created by jagspage2013 on 30/08/14.
 */


public class UNAMirada extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Registro.class);
        ParseObject.registerSubclass(Eventos.class);


        Parse.initialize(this, "XDqK2322q1D4uj3YpXyyZ87XyuC9gzLaW3mvwaZS", "mKpmTNyvGw7MkIucDEtb84nk5W227nAJYCdnprOz");
        ParseUser.enableAutomaticUser();

		/*
		 * For more information on app security and Parse ACL:
		 * https://www.parse.com/docs/android_guide#security-recommendations
		 */
        ParseACL defaultACL = new ParseACL();

		/*
		 * If you would like all objects to be private by default, remove this
		 * line
		 */
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);

    }
}
