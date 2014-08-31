package mx.unam.hack.unamirada.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import mx.unam.hack.unamirada.util.sql.DBTransaction;
import mx.unam.hack.unamirada.util.sql.DictionaryHelper;

/**
 * Created by jagspage2013 on 31/08/14.
 */
public class AddListDialog extends DialogFragment{

    private AlertDialog alertDialog;
    private EditText et;

    public static AddListDialog newInstance() {
        AddListDialog fragment = new AddListDialog();
        return fragment;
    }
    DialogComunicate comunicate;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        comunicate = (DialogComunicate)activity;
    }

    public interface DialogComunicate{
        public void actualizar(ContentValues values);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        et = new EditText(getActivity());
        et.setInputType(InputType.TYPE_CLASS_TEXT);
        et.setHint("Nombre Lista");
        alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("Agrega una Lista")
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, null)
                .setView(et).create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button okBtn = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                okBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nombre = et.getText().toString();
                        if (nombre.length()==0) {
                            et.setError("Invalid nombre!");
                            return;
                        }

                            DBTransaction transaction = new DBTransaction(getActivity());
                            transaction.open();
                            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                            Date date = new Date();
                            ContentValues values = new ContentValues();
                            values.put(DictionaryHelper.COL_NOMBRE, nombre);
                            values.put(DictionaryHelper.COL_FECHA,dateFormat.format(date));
                            long id = transaction.insertLista(values);
                            transaction.close();
                            values.put(DictionaryHelper.COL_ID_LISTAS,id);
                            comunicate.actualizar(values);
                        alertDialog.dismiss();
                    }
                });
            }
        });
        return alertDialog;
    }
}
