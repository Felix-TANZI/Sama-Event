package javafxtest.event;

/*
   Projet : Sama-Event
   @Auteur : NZIKO Felix Andre
   Email : tanzifelix@gmail.com
   version : beta 1.0

   Instagram : felix.tanzi
   GitHub : Felix-TANZI
   Linkedin : Felix TANZI
*/

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class MainApplication extends Application {
    private static HikariDataSource dataSource;

//    initialisation du pool de connexion HikariCP

    public static void initConnectionPool() {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:mysql://127.0.0.1:3307/eventbd");
            config.setUsername("root");
            config.setPassword("felixtanzi12");
            config.setMaximumPoolSize(10);

            dataSource = new HikariDataSource(config);
            System.out.println("Pool de connexion initialise !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    Recuperation de la connexion depuis le pool

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

//    fermeture du pool de connexion

    public static void closePool() {
        if (dataSource != null && !dataSource.isClosed()){
            dataSource.close();
        }
    }

    @Override
    public void start(Stage primarystage) throws IOException {

//        initialisation du pool de connexion

        initConnectionPool();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ParticipantUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        primarystage.setTitle("Sama-Event");
        primarystage.setScene(scene);
        primarystage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}