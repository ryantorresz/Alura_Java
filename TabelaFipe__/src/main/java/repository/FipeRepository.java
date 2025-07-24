package repository;

import model.Marca;
import model.Modelo;
import model.Veiculo;
import model.AnoModelo;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Repository
public class FipeRepository {
    private final RestTemplate restTemplate;
    private final String BASE_URL = "https://parallelum.com.br/fipe/api/v1";

    public FipeRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Marca> getMarcas(String tipoVeiculo) {
        String url = BASE_URL + "/" + tipoVeiculo + "/marcas";
        Marca[] marcas = restTemplate.getForObject(url, Marca[].class);
        return Arrays.asList(marcas);
    }

    public List<Marca> getModelos(String tipoVeiculo, String codigoMarca) {
        String url = BASE_URL + "/" + tipoVeiculo + "/marcas/" + codigoMarca + "/modelos";
        Modelo modelos = restTemplate.getForObject(url, Modelo.class);
        return modelos.getModelos();
    }

    public List<AnoModelo> getAnos(String tipoVeiculo, String codigoMarca, String codigoModelo) {
        String url = BASE_URL + "/" + tipoVeiculo + "/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos";
        AnoModelo[] anos = restTemplate.getForObject(url, AnoModelo[].class);
        return Arrays.asList(anos);
    }

    public Veiculo getValor(String tipoVeiculo, String codigoMarca, String codigoModelo, String codigoAno) {
        String url = BASE_URL + "/" + tipoVeiculo + "/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos/" + codigoAno;
        return restTemplate.getForObject(url, Veiculo.class);
    }
}