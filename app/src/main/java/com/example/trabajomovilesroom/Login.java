package com.example.trabajomovilesroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.trabajomovilesroom.db.AppDatabase;
import com.example.trabajomovilesroom.db.UserDao;

import java.util.List;

public class Login extends AppCompatActivity {
    private EditText nombre, password;
    private TextView error;
    private Button aceptar;

    AppDatabase db;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        db = AppDatabase.getDatabase(this);
        userDao = db.userDao();

        nombre = findViewById(R.id.txtName);
        password = findViewById(R.id.txtPassword);

        error = findViewById(R.id.lbError);

        error.setText("");

        aceptar = findViewById(R.id.btAceptar);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //realizar peticion a base de datos con user y pass
                List list;
                list = userDao.verify(nombre.getText().toString(), password.getText().toString());
                if(list.isEmpty()){
                    error.setText("Usuario o contraseña incorrectos");
                }else{
                    //Entrar al juego con un bundle que le diga que es ese usuario
                    error.setText("Ha funcionado");
                    Intent i = new Intent(view.getContext(), Juego.class);
                    i.putExtra("usuario", nombre.getText().toString());
                    startActivity(i);
                }
            }
        });
    }
}
