package fr.unice.mbds.maslow.util;

import fr.unice.mbds.maslow.entities.Utilisateur;

/**
 * Created by Nicolas on 09/12/2015.
 */
public class ApiUrlService {

    public static String WEBSOCKET_BASE_URL = "ws://134.59.152.114:3000/websocket/";
    public static String WEBSOCKET_USERNAME = "mbds-maslow@harmolabs.com";
    public static String WEBSOCKET_PASSWORD = "maslowmaslow";

    public static String SERVER_BASE_URL = "http://192.168.0.170:8080/";

    public static String UTILISATEUR_URL = SERVER_BASE_URL + "u/";

    public static String WATCHLIST_URL = SERVER_BASE_URL + "w/";
    private static String APPAREIL_URL = "a/";
    private static String EVENEMENT_URL = "e/";

    public static String PROCEDURAL_URL = SERVER_BASE_URL + "p/";
    private static String OPERATION_URL = "o/";

    private static String TOKEN = "?token=" + Utilisateur.token;

    /**
     * @return http://url:8080/w/idWatchlist/
     */
    public static String getWatchlistUrl(int idWatchlist) {
        return WATCHLIST_URL + idWatchlist + "/" + TOKEN;
    }

    /**
     * @return http://url:8080/w/idWatchlist/a/
     */
    public static String getAppareilUrl(int idWatchlist) {
        return getWatchlistUrl(idWatchlist) + APPAREIL_URL + TOKEN;
    }

    /**
     * @return http://url:8080/w/idWatchlist/a/idAppareil/
     */
    public static String getAppareilUrl(int idWatchlist, int idAppareil) {
        return getAppareilUrl(idWatchlist) + idAppareil + "/" + TOKEN;
    }

    /**
     * @return http://url:8080/w/idWatchlist/a/idAppareil/e/
     */
    public static String getEvenementUrl(int idWatchlist, int idAppareil) {
        return getAppareilUrl(idWatchlist, idAppareil) + EVENEMENT_URL + TOKEN;
    }

    /**
     * @return http://url:8080/w/idWatchlist/a/idAppareil/e/idEvenement/
     */
    public static String getEvenementUrl(int idWatchlist, int idAppareil, int idEvenement) {
        return getEvenementUrl(idWatchlist, idAppareil) + idEvenement + "/" + TOKEN;
    }

    /**
     * @return http://url:8080/p/idProcedural/
     */
    public static String getProceduralUrl(int idProcedural) {
        return PROCEDURAL_URL + idProcedural + "/" + TOKEN;
    }

    /**
     * @return http://url:8080/p/idProcedural/o/idOperation/
     */
    public static String getOperationUrl(int idProcedural, int idOperation) {
        return getProceduralUrl(idProcedural) + OPERATION_URL + "/" + TOKEN;
    }
}
