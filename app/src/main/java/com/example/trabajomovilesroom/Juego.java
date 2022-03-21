package com.example.trabajomovilesroom;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabajomovilesroom.db.AppDatabase;
import com.example.trabajomovilesroom.db.User;
import com.example.trabajomovilesroom.db.UserDao;

public class Juego extends AppCompatActivity {
    private Button piedra, papel, tijera, salir;
    private TextView vs, resultado, player, cpu, victorias, empates, derrotas;
    private int contDerrotas=0, contVictorias=0, contEmpates=0, contPartidas=0;
    private ImageView IPlayer, ICPU;
    private TextView user;
    private String usuario;
    private AppDatabase db;
    private UserDao userDao;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        usuario = getIntent().getExtras().getString("usuario");

        user = findViewById(R.id.lbUser);

        user.setText(usuario);

        piedra = findViewById(R.id.piedra);
        papel = findViewById(R.id.papel);
        tijera = findViewById(R.id.tijera);

        salir = findViewById(R.id.bsSalir);

        vs = findViewById(R.id.vs);
        resultado = findViewById(R.id.resultado);
        player = findViewById(R.id.player);
        cpu = findViewById(R.id.cpu);
        victorias = findViewById(R.id.victorias);
        empates = findViewById(R.id.empates);
        derrotas = findViewById(R.id.derrotas);

        IPlayer = findViewById(R.id.iplayer);
        ICPU = findViewById(R.id.icpu);

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = AppDatabase.getDatabase(Juego.this);
                userDao = db.userDao();
                User user = new User();
                String username = getIntent().getExtras().getString("usuario");
                user = userDao.loadAllByNames(username).get(0);
                if(user.getPuntos()<contVictorias){
                    user.setPuntos(contVictorias);
                }
                userDao.update(user);
                Intent i = new Intent(view.getContext(), Ranking.class);
                startActivity(i);
            }
        });

        piedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pc = cpuChoice();
                String jugador = "piedra";
                String resul = comparator(jugador, pc);

                IPlayer.setImageResource(R.drawable.piedra);
              //  player.setText("piedra");
              //  cpu.setText(pc);

                switch (resul){
                    case "empate":
                        resultado.setText("empate");
                        resultado.setTextColor(Color.rgb(227,220,9));
                        break;
                    case "win":
                        resultado.setText("Has ganado");
                        resultado.setTextColor(Color.rgb(0,255,0));
                        break;
                    case "lose":
                        resultado.setText("Has perdido");
                        resultado.setTextColor(Color.rgb(255,0,0));
                        break;
                }
            }
        });

        papel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pc = cpuChoice();
                String jugador = "papel";
                String resul = comparator(jugador, pc);

                IPlayer.setImageResource(R.drawable.papel);
               // player.setText("papel");
               // cpu.setText(pc);

                switch (resul){
                    case "empate":
                        resultado.setText("empate");
                        resultado.setTextColor(Color.rgb(227,220,9));
                        break;
                    case "win":
                        resultado.setText("Has ganado");
                        resultado.setTextColor(Color.rgb(0,255,0));
                        break;
                    case "lose":
                        resultado.setText("Has perdido");
                        resultado.setTextColor(Color.rgb(255,0,0));
                        break;
                }
            }
        });

        tijera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pc = cpuChoice();
                String jugador = "tijera";
                String resul = comparator(jugador, pc);

                IPlayer.setImageResource(R.drawable.tijera);
               // player.setText("tijera");
            //    cpu.setText(pc);

                switch (resul){
                    case "empate":
                        resultado.setText("empate");
                        resultado.setTextColor(Color.rgb(227,220,9));
                        break;
                    case "win":
                        resultado.setText("Has ganado");
                        resultado.setTextColor(Color.rgb(0,255,0));
                        break;
                    case "lose":
                        resultado.setText("Has perdido");
                        resultado.setTextColor(Color.rgb(255,0,0));
                        break;
                }
            }
        });
    }

    public String comparator(String user, String cpu){
        if(user.equals(cpu)){
            contEmpates++;
            empates.setText("Empates: "+contEmpates);
            contPartidas++;
            return "empate";
        }else if(user=="piedra" && cpu=="tijera" || user=="papel" && cpu=="piedra" || user=="tijera" && cpu=="papel"){
            contVictorias++;
            victorias.setText("Victorias: "+contVictorias);
            contPartidas++;
            return "win";
        }else{
            contDerrotas++;
            derrotas.setText("Derrotas: "+contDerrotas);
            contPartidas++;
            return "lose";
        }
    }

    public String cpuChoice(){
        int choice = (int) Math.floor(Math.random()*3);

        switch (choice){
            case 0:
                ICPU.setImageResource(R.drawable.piedra);
                return "piedra";

            case 1:
                ICPU.setImageResource(R.drawable.papel);
                return "papel";

            case 2:
                ICPU.setImageResource(R.drawable.tijera);
                return "tijera";

        }
        return "piedra";
    }
}
