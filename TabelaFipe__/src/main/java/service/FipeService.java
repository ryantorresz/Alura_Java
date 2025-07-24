package service;

import lombok.RequiredArgsConstructor;
import model.Veiculo;
import org.springframework.stereotype.Service;
import repository.FipeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FipeService {
    private final FipeRepository fipeRepository;

    public List<Veiculo> consultarValoresPorModelo(String tipoVeiculo, String codigoMarca, String codigoModelo) {
        return fipeRepository.getAnos(tipoVeiculo, codigoMarca, codigoModelo)
                .stream()
                .map(ano -> fipeRepository.getValor(tipoVeiculo, codigoMarca, codigoModelo, ano.getCodigo()))
                .collect(Collectors.toList());
    }
}