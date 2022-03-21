package com.example.trabajomovilesroom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.trabajomovilesroom.db.AppDatabase;
import com.example.trabajomovilesroom.db.User;
import com.example.trabajomovilesroom.db.UserDao;

import java.util.List;

public class Register extends AppCompatActivity {
    private EditText name, password, checkPassword;
    private TextView error;
    private Button aceptar;
    private String userr, pass, chPass;

    private AppDatabase db;
    private UserDao userDao;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
/*
        db = Room.databaseBuilder(this, AppDatabase.class, "usuarios").allowMainThreadQueries().build();
        userDao = db.userDao();
        */

        db = AppDatabase.getDatabase(this);
        userDao = db.userDao();

        name = findViewById(R.id.txtName);
        password = findViewById(R.id.txtPassword);
        checkPassword = findViewById(R.id.txtCheckPassword);

        error = findViewById(R.id.lbError);

        error.setText("");

        aceptar = findViewById(R.id.btAceptar);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                error.setText("");
                if(name.getText().toString().equals("") || password.getText().toString().equals("") || checkPassword.getText().toString().equals("")){
                    error.setText("Han de estar todos los campos llenos");
                }else if(!password.getText().toString().equals(checkPassword.getText().toString())){
                    error.setText("Los campos no coinciden");
                }else{
                    user = new User();
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Register.this);
                    SharedPreferences.Editor editor = preferences.edit();

                    //List list = userDao.loadAllByNames(name.getText().toString());
                    int valorId = 0;
                    valorId = preferences.getInt("id",0);

                    //realizar peticion a base de datos con user y pass
                    user.setId(valorId);
                    valorId++;
                    editor.putInt("id",valorId);
                    editor.apply();
                    user.nombre=name.getText().toString();
                    user.password= password.getText().toString();
                    userDao.insert(user);
                    Intent i = new Intent(view.getContext(), Login.class);
                    startActivity(i);
                }
            }
        });
    }
}
