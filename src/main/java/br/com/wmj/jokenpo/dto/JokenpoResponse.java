package br.com.wmj.jokenpo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class JokenpoResponse {

    @NotNull
    @JsonProperty(value = "resultado")
    private String resultadoJogo;

    @NotNull
    private List<String> historico;

    public JokenpoResponse(String resultadoJogo, List<String> historico) {
        this.resultadoJogo = resultadoJogo;
        this.historico = historico;
    }

    public String getResultadoJogo() {
        return resultadoJogo;
    }

    public void setResultadoJogo(String resultadoJogo) {
        this.resultadoJogo = resultadoJogo;
    }

    public List<String> getHistorico() {
        return historico;
    }

    public void setHistorico(List<String> historico) {
        this.historico = historico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JokenpoResponse that = (JokenpoResponse) o;
        return Objects.equals(resultadoJogo, that.resultadoJogo) &&
                Objects.equals(historico, that.historico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultadoJogo, historico);
    }

    @Override
    public String toString() {
        return "JokenpoResponse{" +
                "resultadoJogo='" + resultadoJogo + '\'' +
                ", historico=" + historico +
                '}';
    }

}
