package chat.nupasd_ufpi.chat;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    Button b_IP, b_Mensagem, IniciaServer;
    EditText et_IP, et_Mensagem;
    TextView t_Mensagem;
    String IP, mensg, txt;
    final Handler hand = new Handler();


    Socket s;

    String ipDispositivoInicial = null;
    int ttl = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 23 && (ActivityCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.INTERNET, android.Manifest.permission.ACCESS_WIFI_STATE,
                    android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.ACCESS_NETWORK_STATE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.RECEIVE_BOOT_COMPLETED,
                    android.Manifest.permission.WAKE_LOCK
            }, 0);
        }

        b_IP = (Button) findViewById(R.id.btn_IP);
        b_Mensagem = (Button) findViewById(R.id.btn_Mensagem);
        et_IP = (EditText) findViewById(R.id.edt_IP);
        et_Mensagem = (EditText) findViewById(R.id.edt_Mensagem);
        t_Mensagem = (TextView) findViewById(R.id.txt_ExibirMensagem);
        IniciaServer = (Button) findViewById(R.id.btn_IniciarServer);


        IniciaServer.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startServerSocket();

            }
        });



        b_IP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IP = et_IP.getText().toString();
                Toast.makeText(MainActivity.this, "IP salvo: " + IP, Toast.LENGTH_SHORT).show();
            }
        });

/*
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                try {

                    ServerSocket ss = new ServerSocket(9002);

                    while (true) {
                        //Server is waiting for client here, if needed
                        Socket s = ss.accept();
                        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));

                        mensg = input.readLine();

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        updateUI(mensg);

                        s.close();
                    }
                    //ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        thread.start();
*/



        b_Mensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             enviarMSG();






            }
        });

    }






    private void startServerSocket() {


                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {

                        try {

                            ServerSocket ss = new ServerSocket(9002);

                            while (true) {
                                //Server is waiting for client here, if needed
                                Socket s = ss.accept();
                                BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));

                                mensg = input.readLine();


                                verificar(mensg);



                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                //updateUI(mensg);

                                s.close();
                            }
                            //ss.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Erro na criação do servidor!", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
                thread.start();








    }



    private void verificar(final String scan){


        hand.post(new Runnable() {
            @Override
            public void run() {
//
                try {
                    AssetManager assetManager = getResources().getAssets();
                    InputStream inputStream = null;

                    if (scan.equalsIgnoreCase("calypso")){
                        inputStream = assetManager.open("CALYPSO_-_A_LUA_ME_TRAIU.MID");

                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        String linha, exibicao = null;
                        while((linha = bufferedReader.readLine()) !=null){
                            updateUI(linha);
                            //Chamando mais informações
                        }

                        inputStream.close();



                    }else if (scan.equalsIgnoreCase("arroxa")) {
                        inputStream = assetManager.open("ARROXA_1.mid");


                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        String linha, exibicao = null;
                        while((linha = bufferedReader.readLine()) !=null){
                            updateUI(linha);
                            //Chamando mais informações
                        }

                        inputStream.close();


                    }else if (scan.equalsIgnoreCase("correndo atrás de mim")) {
                        inputStream = assetManager.open("CORRENDO_ATRAS_DE_MIM.mid");



                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        String linha, exibicao = null;
                        while((linha = bufferedReader.readLine()) !=null){
                            updateUI(linha);
                            //Chamando mais informações
                        }

                        inputStream.close();



                    } else if (scan.equalsIgnoreCase("Saudade da Minha Terra")) {
                        inputStream = assetManager.open("Saudade_da_Minha_Terra.mid");


                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        String linha, exibicao = null;
                        while((linha = bufferedReader.readLine()) !=null){
                            updateUI(linha);
                            //Chamando mais informações
                        }

                        inputStream.close();




                    }else{

                        //Avisão para quando a música não for encontrada

                        android.app.AlertDialog alert;
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Aviso");
                        builder.setMessage("A Música não está salva neste dispositivo!");
                        builder.setPositiveButton("Ok", null
                        );
                        alert = builder.create();
                        alert.show();

                        enviarMSG();


                    }




                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });





    }




    public void reencaminhar(){






    }



    public void enviarMSG(){
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {


                            //Adicionando o IP configurável aqui

                            Socket s = new Socket(IP, 9000);

                            OutputStream out = s.getOutputStream();

                            PrintWriter output = new PrintWriter(out);

                            WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                            String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());


                            output.println(et_Mensagem.getText().toString()+ "/" + ip + "/" + ttl);

                            output.flush();

                        } catch (IOException ver) {
                            Toast.makeText(MainActivity.this, "Erro no envio da mensagem", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                thread.start();


                //Adiciona mensagem do dispositivo
                String s = t_Mensagem.getText().toString();

                t_Mensagem.setText(s + "\n EU: " + et_Mensagem.getText().toString());



            }
        });


    }







    public void updateUI(final String str){
        hand.post(new Runnable() {
            @Override
            public void run() {

                String s = t_Mensagem.getText().toString();

                t_Mensagem.setText("Enviado por Client: " +str);
            }
        });
    }
}
