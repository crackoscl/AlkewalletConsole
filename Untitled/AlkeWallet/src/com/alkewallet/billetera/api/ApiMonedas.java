package com.alkewallet.billetera.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import com.alkewallet.billetera.Euro;
import com.alkewallet.billetera.IMoneda;
import com.alkewallet.billetera.Usd;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.HttpURLConnection;

public class ApiMonedas {
    private HashMap<String, IMoneda> monedas = new HashMap<>();

    public ApiMonedas() {
    }

    public ApiMonedas(HashMap<String, IMoneda> monedas) {
        this.monedas = monedas;
    }

    public void GetIndicadores() {

        try {
            URL url = new URL("https://mindicador.cl/api");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            int codRespuesta = conexion.getResponseCode();

            if (codRespuesta != 200) {
                throw new RuntimeException("ocurrio un error" + codRespuesta);
            } else {
                BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                String jsonString = response.toString();

                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
                JsonObject dolar = jsonObject.getAsJsonObject("dolar");
                JsonObject euro = jsonObject.getAsJsonObject("euro");
                monedas.put(dolar.get("codigo").getAsString(),
                        new Usd(dolar.get("codigo").getAsString(),"$","Dolares", dolar.get("valor").getAsDouble()));
                monedas.put(euro.get("codigo").getAsString(),
                        new Euro(euro.get("codigo").getAsString(),"€","Euros", euro.get("valor").getAsDouble()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, IMoneda> getMonedas() {
        return monedas;
    }
}
