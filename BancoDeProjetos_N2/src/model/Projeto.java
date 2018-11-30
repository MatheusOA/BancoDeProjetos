
package model;

import java.util.Date;

public class Projeto {
    private int id;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private int clienteIntegrador;
    private int clienteFinal;
    private float custoEstimado;
    private int andamento;
    private int hardwareID;
    private String nome;
    private String nomeIntegrador;
    private String nomeFinal;
    private String nomeHardware;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    

    public String getNomeIntegrador() {
        return nomeIntegrador;
    }

    public void setNomeIntegrador(String nomeIntegrador) {
        this.nomeIntegrador = nomeIntegrador;
    }

    public String getNomeFinal() {
        return nomeFinal;
    }

    public void setNomeFinal(String nomeFinal) {
        this.nomeFinal = nomeFinal;
    }

    public String getNomeHardware() {
        return nomeHardware;
    }

    public void setNomeHardware(String nomeHardware) {
        this.nomeHardware = nomeHardware;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public int getClienteIntegrador() {
        return clienteIntegrador;
    }

    public void setClienteIntegrador(int clienteIntegrador) {
        this.clienteIntegrador = clienteIntegrador;
    }

    public int getClienteFinal() {
        return clienteFinal;
    }

    public void setClienteFinal(int clienteFinal) {
        this.clienteFinal = clienteFinal;
    }

    public float getCustoEstimado() {
        return custoEstimado;
    }

    public void setCustoEstimado(float custoEstimado) {
        this.custoEstimado = custoEstimado;
    }

    public int getAndamento() {
        return andamento;
    }

    public void setAndamento(int andamento) {
        this.andamento = andamento;
    }

    public int getHardwareID() {
        return hardwareID;
    }

    public void setHardwareID(int hardware) {
        this.hardwareID = hardware;
    }
    
    
    
}
