package fr.unice.mbds.maslow.util;

/**
 * Created by Nicolas on 09/12/2015.
 */
public class ApiUrlService {

    public static String WEBSOCKET_BASE_URL = "ws://134.59.152.114:3000/websocket/";
    public static String WEBSOCKET_USERNAME = "mbds-maslow@harmolabs.com";
    public static String WEBSOCKET_PASSWORD = "maslowmaslow";

    private static String TOKEN_BASE = "?token=";

    public static String SERVER_BASE_URL = "http://192.168.43.40:8080/";

    public static String UTILISATEUR_URL = SERVER_BASE_URL + "u/";
    public static String AUTH_URL = UTILISATEUR_URL + "auth/";

    private static String WATCHLIST_URL = "w/";
    public static String APPAREILS_URL = SERVER_BASE_URL + "a/";
    private static String APPAREIL_URL = "a/";
    private static String EVENEMENT_URL = "e/";

    public static String PROCEDURAL_URL = "p/";
    private static String OPERATION_URL = "o/";

    /**
     * @return http://url:8080/a/idUtilisateur/w/idWatchlist/
     */
    public static String getWatchlistUrl(int idUtilisateur, int idWatchlist) {
        return UTILISATEUR_URL + idUtilisateur + "/" + WATCHLIST_URL + idWatchlist + "/";
    }

    /**
     * @return http://url:8080/u/idUtilisateur/w/idWatchlist/a/
     */
    public static String getWatchlistAppareilUrl(int idUtilisateur, int idWatchlist) {
        return getWatchlistUrl(idUtilisateur, idWatchlist) + APPAREIL_URL;
    }

    /**
     * @return http://url:8080/u/idUtilisateur/w/idWatchlist/a/idAppareil/
     */
    public static String getWatchlistAppareilUrl(int idUtilisateur, int idWatchlist, int idAppareil) {
        return getWatchlistAppareilUrl(idUtilisateur, idWatchlist) + idAppareil + "/";
    }

    /**
     * @return http://url:8080/w/idWatchlist/a/idAppareil/e/
     */
    public static String getEvenementUrl(int idWatchlist, int idAppareil) {
        return getWatchlistAppareilUrl(idWatchlist, idAppareil) + EVENEMENT_URL;
    }

    /**
     * @return http://url:8080/w/idWatchlist/a/idAppareil/e/idEvenement/
     */
    public static String getEvenementUrl(int idWatchlist, int idAppareil, int idEvenement) {
        return getEvenementUrl(idWatchlist, idAppareil) + idEvenement + "/";
    }

    /**
     * @return http://url:8080/p/idProcedural/
     */
    public static String getProceduralUrl(int idUtilisateur, int idProcedural) {
        return UTILISATEUR_URL + idUtilisateur + "/" + PROCEDURAL_URL + idProcedural + "/";
    }

    /**
     * @return http://url:8080/p/idProcedural/o/idOperation/
     */
    public static String getOperationUrl(int idUtilisateur, int idProcedural, int idOperation) {
        return getProceduralUrl(idUtilisateur, idProcedural) + OPERATION_URL + "/";
    }

    public static String addToken(String url, String token) {
        return url + TOKEN_BASE + token;
    }
}
