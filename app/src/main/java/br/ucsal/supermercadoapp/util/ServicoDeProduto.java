package br.ucsal.supermercadoapp.util;
import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import br.ucsal.supermercadoapp.model.ProdutoApi;

public class ServicoDeProduto {

	public static String webService = "https://api.cosmos.bluesoft.com.br/gtins/";
	public static int codigoSucesso = 200;
	
	public static ProdutoApi buscarProdutoPeloCodigoBarras(String cod) throws Exception{
		String url = webService+cod;
		
		try {
			URL resposta = new URL(url);
			HttpURLConnection conection = (HttpURLConnection) resposta.openConnection(); 
			//conection.setRequestProperty("X-Cosmos-Token", "WeCValNEiYNLPwrvUw38IQ");
			conection.setRequestProperty("X-Cosmos-Token", "3PO9QsWRsqVHkPzErV8uqQ");
			
			if (conection.getResponseCode() != codigoSucesso)
                throw new RuntimeException("HTTP error code : " + conection.getResponseCode());
			
			BufferedReader r = new BufferedReader(new InputStreamReader((conection.getInputStream())));
            String jsonEmString = Util.converteJsonEmString(r);

            Gson gson = new Gson();
            ProdutoApi produto = gson.fromJson(jsonEmString, ProdutoApi.class);
            
            return produto;

		} catch (MalformedURLException e) {
			e.printStackTrace();
            throw new Exception("ERRO: " + e);
		}
	}
}
