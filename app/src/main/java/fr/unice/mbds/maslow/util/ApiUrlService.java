package fr.unice.mbds.maslow.util;

/**
 * Created by Nicolas on 09/12/2015.
 */
public class ApiUrlService {

    public static String WEBSOCKET_BASE_URL = "ws://134.59.152.114:3000/websocket/";
    public static String WEBSOCKET_USERNAME = "mbds-maslow@harmolabs.com";
    public static String WEBSOCKET_PASSWORD = "maslowmaslow";


    public static String SERVER_BASE_URL = "http://localhost:8080/";

    public static String UTILISATEUR_URL = SERVER_BASE_URL + "u/";

    public static String WATCHLIST_URL = SERVER_BASE_URL + "w/";
//    A employer tel WATCHLIST_URL + idW + APPAREIL_URL
    public static String APPAREIL_URL = "/a/";
    public static String EVENEMENT_URL = "/e/";

    public static String PROCEDURAL_URL = SERVER_BASE_URL + "/p/";
    public static String OPERATION_URL = "/o/";
}
