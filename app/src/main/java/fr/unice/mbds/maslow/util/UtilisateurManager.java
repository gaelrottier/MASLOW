package fr.unice.mbds.maslow.util;

import android.content.Context;
import android.content.SharedPreferences;

import fr.unice.mbds.maslow.entities.Utilisateur;

/**
 * Created by Gael on 22/03/2016.
 */
public class UtilisateurManager {

    public static int getId(Context context) {
        return getSharedPreferences(context).getInt("id", 0);
    }

    public static void saveUtilisateur(Context context, Utilisateur utilisateur) {
        SharedPreferences.Editor editor = edit(context);

        editor.putInt("id", utilisateur.getId());
        editor.putString("nom", utilisateur.getNom());
        editor.putString("prenom", utilisateur.getPrenom());
        editor.putString("identifiant", utilisateur.getIdentifiant());
        editor.putString("token", utilisateur.getToken());

        close(editor);
    }

    public static void setId(Context context, int id) {
        SharedPreferences.Editor editor = edit(context);

        editor.putInt("id", id);

        close(editor);
    }

    public static String getNom(Context context) {
        return getSharedPreferences(context).getString("nom", "");
    }

    public static void setNom(Context context, String nom) {
        SharedPreferences.Editor editor = edit(context);

        editor.putString("nom", nom);

        close(editor);
    }

    public static String getPrenom(Context context) {
        return getSharedPreferences(context).getString("prenom", "");
    }

    public static void setPrenom(Context context, String prenom) {
        SharedPreferences.Editor editor = edit(context);

        editor.putString("prenom", prenom);

        close(editor);
    }

    public static String getIdentifiant(Context context) {
        return getSharedPreferences(context).getString("identifiant", "");
    }

    public static void setIdentifiant(Context context, String identifiant) {
        SharedPreferences.Editor editor = edit(context);

        editor.putString("identifiant", identifiant);

        close(editor);
    }

    public static String getToken(Context context) {
        return getSharedPreferences(context).getString("token", "");
    }

    public static void setToken(Context context, String token) {
        SharedPreferences.Editor editor = edit(context);

        editor.putString("token", token);

        close(editor);
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("utilisateur", Context.MODE_PRIVATE);

        return sharedPreferences;
    }

    private static SharedPreferences.Editor edit(Context context) {
        return getSharedPreferences(context).edit();
    }

    public static void close(SharedPreferences.Editor editor) {
        editor.apply();
    }
}
