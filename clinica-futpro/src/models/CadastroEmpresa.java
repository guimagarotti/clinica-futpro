package models;

public class CadastroEmpresa {

    private int id;
    private String nome;
    private String razao;
    private String cnpj;
    private String endereco;

    public CadastroEmpresa() {
    }

    public CadastroEmpresa(int id, String nome, String razao, String cnpj, String endereco) {
        this.id = id;
        this.nome = nome;
        this.razao = razao;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
