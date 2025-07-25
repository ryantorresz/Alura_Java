package br.com.fipe.consulta.model;

import lombok.Data;
import java.util.List;

@Data
public class Modelo {
    private List<Marca> modelos;

    public List<Marca> getModelos() {
        return modelos;
    }

    public void setModelos(List<Marca> modelos) {
        this.modelos = modelos;
    }
}