package com.example.root.google.Utility;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Util {

    /*
    * @author programmer
    * @get Indonesian format currency
    * */
    public static String indonesiaFormat(String nominal) {
        Double format_nominal = Double.parseDouble(nominal);
        DecimalFormat mataUangIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp.");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        mataUangIndonesia.setDecimalFormatSymbols(formatRp);
        return mataUangIndonesia.format(format_nominal);
    }
}
