package br.com.fipe.consulta.service.FipeService;
import br.com.fipe.consulta.model.Veiculo;
import br.com.fipe.consulta.repository.FipeRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Service
@RequiredArgsConstructor
public class ServicoFipe {
    private final FipeRepository fipeRepository;

    public List<Veiculo> consultarValoresPorModelo(String tipoVeiculo, String codigoMarca, String codigoModelo) {
        List<Veiculo> collect = fipeRepository.getAnos(tipoVeiculo, codigoMarca, codigoModelo)
                .stream()
                .map(ano -> fipeRepository.getValor(tipoVeiculo, codigoMarca, codigoModelo, ano.getCodigo()))
                .collect(Collectors.toList());
        return collect;
    }
}