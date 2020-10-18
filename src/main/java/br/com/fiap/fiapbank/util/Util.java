package br.com.fiap.fiapbank.util;

import javax.persistence.ManyToMany;
import java.math.BigDecimal;

public class Util {

    public static String getNameFromLine(String line){
        return line.replaceAll("\\d", "");
    }

    public static String getRMFromLine(String line){

        String output = line.replaceAll("([A-Z])","");
        output = output.trim().substring(0,6);
        return output;
    }

    public static BigDecimal generateRandomValue(){
        return new BigDecimal(Math.random()*10.0);
    }

}
