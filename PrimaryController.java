package com.mycompany.carrera;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;


public class PrimaryController {
     @FXML private Button btnIniciar;
     @FXML private Label lblLiebre;
      @FXML private Label lblTortuga;
     @FXML private ProgressBar barLiebre;
     @FXML private ProgressBar barTortuga;
    

    @FXML
    private void btnIniciar_click(){
        btnIniciar.setDisable(true);
        barLiebre.setProgress(0);
        barTortuga.setProgress(0);
        lblLiebre.setText("La Liebre: ¡Esta corriendo!");
        lblTortuga.setText("La Tortuga: ¡Esta corriendo!");
        
        Thread liebre = new Thread (() -> {
        try {
            for(int i = 1; i<= 100; i++){
               Thread.sleep(30);
               final double avance = i / 100.0;
              Platform.runLater(() -> barLiebre.setProgress(avance));
              
              if (i == 50){
                  Platform.runLater(() -> lblLiebre.setText("La Liebre: ¡Se durmio!"));
                  Thread.sleep(8000);
                  Platform.runLater(() -> lblLiebre.setText("La Liebre: ¡Se desperto!"));
              }
            }
            Platform.runLater(() -> lblLiebre.setText("La Liebre: ¡Llego a la meta!"));
        }catch (InterruptedException e){ 
        }
        });
        Thread tortuga = new Thread(()->{
            try {
                for(int i = 1; i<=100; i++){
                    Thread.sleep(100);
                    final double avance = i / 100.0;
                Platform.runLater(()-> barTortuga.setProgress(avance));
                }
                Platform.runLater(() -> lblTortuga.setText("La Tortuga: ¡Llego a la meta!"));
                }catch (InterruptedException e){
                   
                }
            });
        Thread fin = new Thread(()->{
            try{
                liebre.join();
                tortuga.join();
            }catch(InterruptedException e){
                return;
            }
            Platform.runLater(()-> btnIniciar.setDisable(false));
        });
        liebre.setDaemon(true);
        tortuga.setDaemon(true);
        fin.setDaemon(true);
        
        liebre.start();
        tortuga.start();
        fin.start();
        }
 
    }

