package fr.unice.mbds.maslow.view.activity;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.widget.TextView;

import com.luciom.opticallbs.LiFiMessage;
import com.luciom.opticallbs.SmartLightHandlerAbs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import fr.unice.mbds.maslow.view.activity.LifiActivity;
import fr.unice.mbds.maslow.view.activity.LoginActivity;

/**
 * Created by MBDS on 25/03/2016.
 */
public class SmartLightHandler  extends SmartLightHandlerAbs {
    // private TextView id_filtered, message;
    private String id_filtered,message;
    public static String id_filtered_data;
    private Activity lifi_activity;
    /*public SmartLightHandler(TextView id_filtered,TextView message ,LifiActivity lifi_activity) {
        super();
        this.id_filtered = id_filtered;
       this.message = message;
        this.lifi_activity = lifi_activity;
    }*/

    public SmartLightHandler(String id_filtered,String message ,Activity lifi_activity) {
        super();
        this.id_filtered = id_filtered;
        this.message = message;
        this.lifi_activity = lifi_activity;
    }



    //@Override
    public String handleMessageH(Message msg) {
        super.handleMessage(msg);
        id_filtered_data = "";
        String message_data = "";
        if(msg.what==MsgWhat.NEW_MESSAGE.value) {
            if(msg.obj instanceof LiFiMessage) {
                LiFiMessage lifiMsg = (LiFiMessage)msg.obj;
                SimpleDateFormat sdf = new SimpleDateFormat("[HH'h'mm'm'ss.SSS]\n",
                        Locale.getDefault());
                message_data = sdf.format(Calendar.getInstance().getTime())+"Last ID=";
                System.out.println("uid length"+lifiMsg.getUID().length);
                for(int i=0; i<lifiMsg.getUID().length; i++) {
                    message_data += String.format("%02X", lifiMsg.getUID()[i]);
                    System.out.println("uid"+lifiMsg.getUID()[i]);
                }
                message_data += System.getProperty("line.separator")+"Last DATA=";
                for(int i=0; i<lifiMsg.getUserData().length; i++) {
                    message_data += String.format("%02X", lifiMsg.getUserData()[i]);
                    System.out.println("lifiMsg.getUserData()" +lifiMsg.getUserData()[i]);
                }
                switch(lifiMsg.getUserDataStatus()) {
                    case NO_DATA:
                        message_data += " NO_DATA";
                        break;
                    case DATA_ERROR:
                        message_data += " DATA_ERROR";
                        break;
                    case CRC_NOK:
                        message_data += " CRC_NOK";
                        break;
                    case NO_CRC:
                        message_data += " (NO_CRC)";
                        break;
                    case CRC_OK:
                        message_data += " (CRC_OK)";
                        break;
                }
            }
        }
        else if(msg.what==MsgWhat.FILTERED_UID.value) {
            if(msg.obj instanceof byte[]) {
                id_filtered_data = "";
                byte[] filtered_id = (byte[])msg.obj;
                for(int i=0; i<filtered_id.length; i++) {
                    System.out.println("test id_filtered_data avant "+ id_filtered_data);
                    id_filtered_data += String.format("%02X", filtered_id[i]);
                    System.out.println("test id_filtered_data "+ id_filtered_data);

                }
                System.out.println("test else if");
            }
        }
        else if(msg.what==MsgWhat.DEAD.value) {
            id_filtered_data = "RECHERCHE EN COURS ...";
            message_data = ".";
            System.out.println("RECHERCHE EN COURS");
        }
        else if(msg.what==MsgWhat.START_LBS.value) {
            id_filtered_data = "LIFI ACTIVÉ";
            message_data = ".";
            System.out.println("LIFI ACTIVÉ");
        }
        else if(msg.what==MsgWhat.STOP_LBS.value) {
            id_filtered_data = "LIFI ARRÊTÉ";
            message_data = ".";
            System.out.println("LIFI ARRÊTÉ");
        }
        if(!id_filtered_data.equals("")) {
            //  id_filtered.setText(id_filtered_data);
            if(!id_filtered_data.equals("LIFI ACTIVÉ") && !id_filtered_data.equals("LIFI ARRÊTÉ") && !id_filtered_data.equals("RECHERCHE EN COURS ...")) {
                System.out.println("id_filtered_data" + id_filtered_data);

                /// il faut envoyer ce parametre a une autre classe pour l utiliser
            }
        }
        if(!message_data.equals("")) {
            // message.setText(message_data);
            System.out.println("message_data"+message_data);
        }

        return id_filtered_data;
    }


}
