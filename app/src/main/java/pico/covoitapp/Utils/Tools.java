package pico.covoitapp.Utils;

import java.io.IOException;
import java.text.SimpleDateFormat;


import okhttp3.Request;
import okio.Buffer;
import pico.covoitapp.Model.Api.MCovoiturage;

public class Tools {

    public static final SimpleDateFormat simpleDate =  new SimpleDateFormat("dd-MM-yyyy HH:mm");
    // String DATE_FORMAT = "dd/MM/yyyy hh:SS";

    public static String bodyToString(final Request query){
        try {
            final Request copy = query.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }

    }
    private static String checkDateFormat(int value){
        String tmpD = String.valueOf( value);
        if(value <10)
            tmpD = "0"+tmpD;
        return tmpD;
    }
    public static String showHeure(int hours, int minute){
        return (checkDateFormat(hours) +":" + checkDateFormat(minute));
    }

    public static String showDate(int year,
                                  int monthOfYear, int dayOfMonth){
        return (checkDateFormat(dayOfMonth) + "/" + checkDateFormat(monthOfYear) + "/" + year);
    }

    public static  String showDateHeure(MCovoiturage c){
        return showDate(c.getAnnee(), c.getMois(), c.getJours()) +" "+ showHeure(c.getHeure(), c.getMinutes());
    }
}
