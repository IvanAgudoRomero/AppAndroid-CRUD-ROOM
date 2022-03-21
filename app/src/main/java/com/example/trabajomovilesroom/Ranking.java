package com.example.trabajomovilesroom;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabajomovilesroom.db.AppDatabase;
import com.example.trabajomovilesroom.db.User;
import com.example.trabajomovilesroom.db.UserDao;

import java.util.List;

public class Ranking extends AppCompatActivity {
    private EditText ranking;

    private AppDatabase db;
    private UserDao userDao;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking);

        db = AppDatabase.getDatabase(this);
        userDao = db.userDao();

        ranking = findViewById(R.id.txtRanking);

        List<User> lista = userDao.getAll();
        String valores = "";

        for (int i = 0; i < lista.size(); i++) {
            valores+=""+lista.get(i).getId()+"   "+lista.get(i).getNombre()+"   "+lista.get(i).getPuntos()+"\n";
        }

        ranking.setText(valores);
    }
}
