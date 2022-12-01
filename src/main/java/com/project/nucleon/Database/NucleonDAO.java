package com.project.nucleon.Database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NucleonDAO {

    /**
     * Membres static (de classe)
     * Informations concernant la connexion "Nom serveur" "port" "nomBDD" "usernamePHPmyAdmin" "passwordPHPmyAdmin"
     */
    private static String nomServeur;
    private static String nomBdd;
    private static String nomUtilisateur;
    private static String motDePasse;
    private static String chaineConnexion;

    private Connection connexion;
    private static NucleonDAO nucleonDAO = null;


    /**
     * Constructeur privé ! pour construire un objet, il faut utiliser la
     * méthode publique statique getNucleonDao
     *
     */
    private NucleonDAO() {

        InputStream inputStream = NucleonDAO.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();

        if(inputStream==null){
            System.out.println("Impossible de trouver db.properties");
            return;
        }

        try {
            properties.load(inputStream);

            System.out.println(NucleonDAO.nomServeur);

            NucleonDAO.nomServeur     = properties.getProperty("db.host");
            NucleonDAO.nomBdd         = properties.getProperty("db.db");
            NucleonDAO.motDePasse     = properties.getProperty("db.pass");
            NucleonDAO.nomUtilisateur = properties.getProperty("db.user");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            //--- Définition de l'emplacement de la BDD

            NucleonDAO.chaineConnexion = "jdbc:mysql://" + NucleonDAO.nomServeur + "/" + NucleonDAO.nomBdd + "?useUnicode=true"
                    + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
                    + "serverTimezone=UTC";

            //Création de la connexion à la BDD
            this.connexion = (Connection) DriverManager.getConnection(NucleonDAO.chaineConnexion, NucleonDAO.nomUtilisateur, NucleonDAO.motDePasse);

        } catch (SQLException ex) {
            Logger.getLogger(NucleonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Permet d'obtenir l'objet instancié
     *
     * @return un Objet DaoSIO avec connexion active ... pour une certaine durée
     */
    public static NucleonDAO getInstance() {

        if (NucleonDAO.nucleonDAO == null) {
            NucleonDAO.nucleonDAO = new NucleonDAO();
        } else {
            if (!NucleonDAO.nucleonDAO.connexionActive()) {
                NucleonDAO.nucleonDAO = new NucleonDAO();
            }
        }
        return NucleonDAO.nucleonDAO;
    }

    public Boolean connexionActive() {
        Boolean connexionActive = true;
        try {
            if (this.connexion.isClosed()) {
                connexionActive = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NucleonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connexionActive;
    }

    /**
     *
     * @param sql, comportera un ordre selec
     * @return
     */
    public ResultSet requeteSelection(String sql) {
        try {
            Statement requete = new NucleonDAO().connexion.createStatement();
            return requete.executeQuery(sql);

        } catch (SQLException ex) {
            Logger.getLogger(NucleonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param sql, comportera un ordre insert, update, select, alter, etc.
     * @return le nombre de lignes impactées par la requête action
     *
     */
    public Integer requeteAction(String sql) {

        try {
            Statement requete = new NucleonDAO().connexion.createStatement();
            return requete.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(NucleonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
