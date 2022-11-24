package models;

public class CadastroServico {

    private int id;
    private String hash;
    private String empresa;
    private String prestador;
    private String tipo;
    private double qtd_horas;
    private String descricao;

    public CadastroServico() {
    }

    public CadastroServico(int id, String hash, String empresa, String prestador, String tipo, double qtd_horas, String descricao) {
        this.id = id;
        this.hash = hash;
        this.empresa = empresa;
        this.prestador = prestador;
        this.tipo = tipo;
        this.qtd_horas = qtd_horas;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getPrestador() {
        return prestador;
    }

    public void setPrestador(String prestador) {
        this.prestador = prestador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getQtd_horas() {
        return qtd_horas;
    }

    public void setQtd_horas(double qtd_horas) {
        this.qtd_horas = qtd_horas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
