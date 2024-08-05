package com.repository.fixer.service;

import com.repository.fixer.model.ErrorDetails;
import com.repository.fixer.model.RatesResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class RatesService {

    public RatesResponse prepareRates(String accessKey){
        RatesResponse response = new RatesResponse();
        if(accessKey == null){
            response.setSuccess(false);
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setCode(101);
            errorDetails.setType("missing_access_key");
            errorDetails.setInfo("You have not supplied an API Access Key.");
            response.setError(errorDetails);
        } else if (!accessKey.equals("123456789")) {
            response.setSuccess(false);
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setCode(101);
            errorDetails.setType("invalid_access_key");
            errorDetails.setInfo("You have not supplied a valid API Access Key.");
            response.setError(errorDetails);
        } else {
            response.setSuccess(true);
            response.setTimestamp(Instant.now().getEpochSecond());
            response.setBase("EUR");
            response.setDate(getCurrentDate());

            Map<String, Double> rates = new HashMap<>();
            rates.put("AED", 3.989798);
            rates.put("AFN", 76.845301);
            rates.put("ALL", 100.616684);
            rates.put("AMD", 421.900738);
            rates.put("ANG", 1.957499);
            rates.put("AOA", 947.904349);
            rates.put("ARS", 1008.8068);
            rates.put("AUD", 1.655446);
            rates.put("AWG", 1.955257);
            rates.put("AZN", 1.854716);
            rates.put("BAM", 1.956708);
            rates.put("BBD", 2.193018);
            rates.put("BDT", 127.619213);
            rates.put("BGN", 1.956169);
            rates.put("BHD", 0.409382);
            rates.put("BIF", 3129.022995);
            rates.put("BMD", 1.086254);
            rates.put("BND", 1.45955);
            rates.put("BOB", 7.505552);
            rates.put("BRL", 6.144071);
            rates.put("BSD", 1.086099);
            rates.put("BTC", 1.6129563e-5); // 0.000015905178
            rates.put("BTN", 90.931953);
            rates.put("BWP", 14.716828);
            rates.put("BYN", 3.554384);
            rates.put("BYR", 21290.578376);
            rates.put("BZD", 2.189275);
            rates.put("CAD", 1.501991);
            rates.put("CDF", 3112.117798);
            rates.put("CHF", 0.959309);
            rates.put("CLF", 0.037304);
            rates.put("CLP", 1029.345593);
            rates.put("CNY", 7.875774);
            rates.put("CNH", 7.886264);
            rates.put("COP", 4376.81065);
            rates.put("CRC", 574.263803);
            rates.put("CUC", 1.086254);
            rates.put("CUP", 28.785731);
            rates.put("CVE", 110.3172);
            rates.put("CZK", 25.343354);
            rates.put("DJF", 193.0493);
            rates.put("DKK", 7.462142);
            rates.put("DOP", 64.289533);
            rates.put("DZD", 145.968039);
            rates.put("EGP", 52.475435);
            rates.put("ERN", 16.29381);
            rates.put("ETB", 62.791278);
            rates.put("EUR", 1.0);
            rates.put("FJD", 2.453794);
            rates.put("FKP", 0.837024);
            rates.put("GBP", 0.84384);
            rates.put("GEL", 2.930401);
            rates.put("GGP", 0.837024);
            rates.put("GHS", 16.834966);
            rates.put("GIP", 0.837024);
            rates.put("GMD", 73.59391);
            rates.put("GNF", 9360.429212);
            rates.put("GTQ", 8.417867);
            rates.put("GYD", 227.197506);
            rates.put("HKD", 8.481259);
            rates.put("HNL", 26.891477);
            rates.put("HRK", 7.503789);
            rates.put("HTG", 143.365859);
            rates.put("HUF", 390.808662);
            rates.put("IDR", 17678.620892);
            rates.put("ILS", 3.974804);
            rates.put("IMP", 0.837024);
            rates.put("INR", 90.952482);
            rates.put("IQD", 1422.853624);
            rates.put("IRR", 45736.724787);
            rates.put("ISK", 149.902733);
            rates.put("JEP", 0.837024);
            rates.put("JMD", 169.888825);
            rates.put("JOD", 0.769829);
            rates.put("JPY", 167.030018);
            rates.put("KES", 142.570944);
            rates.put("KGS", 91.289437);
            rates.put("KHR", 4455.108089);
            rates.put("KMF", 493.403702);
            rates.put("KPW", 977.628979);
            rates.put("KRW", 1502.913911);
            rates.put("KWD", 0.332231);
            rates.put("KYD", 0.905128);
            rates.put("KZT", 514.496347);
            rates.put("LAK", 24088.733141);
            rates.put("LBP", 97263.723706);
            rates.put("LKR", 329.101181);
            rates.put("LRD", 212.234334);
            rates.put("LSL", 19.827974);
            rates.put("LTL", 3.207426);
            rates.put("LVL", 0.657065);
            rates.put("LYD", 5.248484);
            rates.put("MAD", 10.698062);
            rates.put("MDL", 19.279211);
            rates.put("MGA", 4943.202527);
            rates.put("MKD", 61.648604);
            rates.put("MMK", 3528.110616);
            rates.put("MNT", 3747.576672);
            rates.put("MOP", 8.733513);
            rates.put("MRU", 43.021961);
            rates.put("MUR", 50.858446);
            rates.put("MVR", 16.673634);
            rates.put("MWK", 1883.339153);
            rates.put("MXN", 20.035633);
            rates.put("MYR", 5.059231);
            rates.put("MZN", 69.411621);
            rates.put("NAD", 19.8277);
            rates.put("NGN", 1733.661519);
            rates.put("NIO", 39.979102);
            rates.put("NOK", 11.951672);
            rates.put("NPR", 145.491165);
            rates.put("NZD", 1.842309);
            rates.put("OMR", 0.418156);
            rates.put("PAB", 1.086084);
            rates.put("PEN", 4.082551);
            rates.put("PGK", 4.260977);
            rates.put("PHP", 63.558881);
            rates.put("PKR", 302.295467);
            rates.put("PLN", 4.273629);
            rates.put("PYG", 8224.81614);
            rates.put("QAR", 3.961975);
            rates.put("RON", 4.970377);
            rates.put("RSD", 117.147078);
            rates.put("RUB", 93.473484);
            rates.put("RWF", 1428.188945);
            rates.put("SAR", 4.075185);
            rates.put("SBD", 9.20703);
            rates.put("SCR", 14.792012);
            rates.put("SDG", 636.545236);
            rates.put("SEK", 11.741318);
            rates.put("SGD", 1.45778);
            rates.put("SHP", 0.837024);
            rates.put("SLE", 24.817974);
            rates.put("SLL", 22778.207208);
            rates.put("SOS", 620.682269);
            rates.put("SRD", 31.50242);
            rates.put("STD", 22483.264636);
            rates.put("SVC", 9.503497);
            rates.put("SYP", 2729.246161);
            rates.put("SZL", 19.825107);
            rates.put("THB", 39.017892);
            rates.put("TJS", 11.513154);
            rates.put("TMT", 3.856202);
            rates.put("TND", 3.369763);
            rates.put("TOP", 2.590828);
            rates.put("TRY", 35.798321);
            rates.put("TTD", 7.372387);
            rates.put("TWD", 35.632718);
            rates.put("TZS", 2932.885896);
            rates.put("UAH", 44.59199);
            rates.put("UGX", 4050.879522);
            rates.put("USD", 1.086254);
            rates.put("UYU", 43.730693);
            rates.put("UZS", 13722.366887);
            rates.put("VEF", 3935012.176293);
            rates.put("VES", 39.684984);
            rates.put("VND", 27498.519979);
            rates.put("VUV", 128.962288);
            rates.put("WST", 3.045431);
            rates.put("XAF", 656.249403);
            rates.put("XAG", 0.038944);
            rates.put("XAU", 0.000455);
            rates.put("XCD", 2.935656);
            rates.put("XDR", 0.819165);
            rates.put("XOF", 656.267536);
            rates.put("XPF", 119.331742);
            rates.put("YER", 271.943143);
            rates.put("ZAR", 19.857337);
            rates.put("ZMK", 9777.58732);
            rates.put("ZMW", 28.375427);
            rates.put("ZWL", 349.773344);
            response.setRates(rates);
        }
        return response;
    }

    private String getCurrentDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.now().format(formatter);
    }
}
