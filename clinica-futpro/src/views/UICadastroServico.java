package views;

import conexoes.MySQL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.CadastroServico;

public class UICadastroServico extends javax.swing.JFrame {

    MySQL conectar = new MySQL();
    CadastroServico novoServico = new CadastroServico();

    // ComboBox Empresas
    private void listaEmpresas() {
        this.conectar.conectaBanco();

        String sql = "SELECT tb_empresas.nome from tb_empresas;";

        try {
            this.conectar.executarSQL(sql);

            while (this.conectar.getResultSet().next()) {
                cbxEmpresaCadastro.addItem(this.conectar.getResultSet().getString(1));
            }
        } catch (Exception e) {
            System.out.println("[ERRO]: Não foi possível importar nova empresa! " + e.getMessage());
        } finally {
            this.conectar.fechaBanco();
        }
    }

    // ComboBox Prestadores
    private void listaPrestadores() {
        this.conectar.conectaBanco();

        String sql = "SELECT tb_prestadores.nome from tb_prestadores;";

        try {
            this.conectar.executarSQL(sql);

            while (this.conectar.getResultSet().next()) {
                cbxPrestadorCadastro.addItem(this.conectar.getResultSet().getString(1));
            }
        } catch (Exception e) {
            System.out.println("[ERRO]: Não foi possível importar novo prestador! " + e.getMessage());
        } finally {
            this.conectar.fechaBanco();
        }
    }

    private void cadastraServico(CadastroServico novoServico) {
        this.conectar.conectaBanco();

        novoServico.setHash(txtCodigoCadastro.getText());
        novoServico.setEmpresa((String) cbxEmpresaCadastro.getSelectedItem());
        novoServico.setPrestador((String) cbxPrestadorCadastro.getSelectedItem());
        novoServico.setTipo((String) cbxServicoCadastro.getSelectedItem());
        novoServico.setQtd_horas(Double.parseDouble(txtHorasCadastro.getText()));
        novoServico.setDescricao(txtDescricaoCadastro.getText());

        try {
            if (!novoServico.getHash().isBlank() && !novoServico.getDescricao().isBlank()) {
                this.conectar.insertSQL(
                        "INSERT INTO tb_servicos VALUES ("
                        + "'" + novoServico.getId() + "',"
                        + "'" + novoServico.getHash() + "',"
                        + "'" + novoServico.getEmpresa() + "',"
                        + "'" + novoServico.getPrestador() + "',"
                        + "'" + novoServico.getTipo() + "',"
                        + "'" + novoServico.getQtd_horas() + "',"
                        + "'" + novoServico.getDescricao() + "'"
                        + ");");
                JOptionPane.showMessageDialog(null, "[OK]: Serviço cadastrado com sucesso!");
            }

            if (novoServico.getHash().isBlank() || novoServico.getDescricao().isBlank()) {
                JOptionPane.showMessageDialog(null, "[ERRO]: Campos para cadastro de serviço vazios!");
            }
        } catch (Exception e) {
            System.out.println("[ERRO]: Não foi possível cadastrar novo servico! " + e.getMessage());
        } finally {
            this.conectar.fechaBanco();
        }
    }

    private void buscaServico(CadastroServico novoServico) {
        this.conectar.conectaBanco();

        String consultaHash = this.txtHashConsulta.getText();

        try {
            if (!consultaHash.isEmpty()) {
                this.conectar.executarSQL(
                        "SELECT " + "tipo," + "empresa," + "prestador," + "qtd_horas," + "descricao" + " FROM " + "tb_servicos"
                        + " WHERE" + " hash = '" + consultaHash + "'" + ";"
                );
                while (this.conectar.getResultSet().next()) {
                    novoServico.setTipo(this.conectar.getResultSet().getString(1));
                    novoServico.setEmpresa(this.conectar.getResultSet().getString(2));
                    novoServico.setPrestador(this.conectar.getResultSet().getString(3));
                    novoServico.setQtd_horas(this.conectar.getResultSet().getDouble(4));
                    novoServico.setDescricao(this.conectar.getResultSet().getString(5));
                }

                txtEmpresaConsulta.setText(novoServico.getEmpresa());
                txtPrestadorConsulta.setText(novoServico.getPrestador());
                txtHorasConsulta.setText(Double.toString(novoServico.getQtd_horas()));
                txtDescricaoConsulta.setText(novoServico.getDescricao());
            } else {
                JOptionPane.showMessageDialog(null, "[ERRO]: Campo de consulta de HASH vazio!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar servico! " + e.getMessage());
            JOptionPane.showMessageDialog(null, "[ERRO]: Erro ao buscar serviço!");
        } finally {
            this.conectar.fechaBanco();
        }
    }

    public void atualizaServico(CadastroServico novoServico) {
        this.conectar.conectaBanco();

        String consultaHash = this.txtHashConsulta.getText();

        try {
            this.conectar.updateSQL(
                    "UPDATE tb_servicos SET tipo="
                    + "'" + cbxServicoConsulta.getSelectedItem() + "',"
                    + " empresa =" + "'" + txtEmpresaConsulta.getText() + "',"
                    + " prestador =" + "'" + txtPrestadorConsulta.getText() + "',"
                    + " qtd_horas =" + "'" + txtHorasConsulta.getText() + "',"
                    + " descricao =" + "'" + txtDescricaoConsulta.getText() + "'"
                    + " WHERE" + " hash = '" + consultaHash + "'" + ";"
            );

            if (txtEmpresaConsulta.getText().isBlank() || txtPrestadorConsulta.getText().isBlank() || txtHorasConsulta.getText().isBlank() || txtDescricaoConsulta.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "[ERRO]: Campos para atualização de serviço vazios!");
            } else {
                JOptionPane.showMessageDialog(null, "[OK]: Serviço atualizado com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Não foi possível atualizar a servico! " + e.getMessage());
            JOptionPane.showMessageDialog(null, "[ERRO]: Erro ao atualizar serviço!");
        } finally {
            this.conectar.fechaBanco();
        }
    }

    public void removeServico(CadastroServico novoServico) {
        this.conectar.conectaBanco();

        String consultaHash = this.txtHashConsulta.getText();

        try {
            this.conectar.updateSQL(
                    "DELETE FROM tb_servicos WHERE hash = '" + consultaHash + "';"
            );
        } catch (Exception e) {
            System.out.println("Não foi possível deletar o servico! " + e.getMessage());
            JOptionPane.showMessageDialog(null, "[ERRO]: Erro ao deletar serviço!");
        } finally {
            this.conectar.fechaBanco();
        }
    }

    public void LimpaCampos() {
        txtCodigoCadastro.setText("");

        cbxEmpresaCadastro.setSelectedIndex(0);
        cbxPrestadorCadastro.setSelectedIndex(0);
        cbxServicoCadastro.setSelectedIndex(0);

        txtHorasCadastro.setText("");
        txtDescricaoCadastro.setText("");
    }

    public void LimpaCamposConsulta() {
        txtHashConsulta.setText("");

        cbxServicoConsulta.setSelectedIndex(0);

        txtEmpresaConsulta.setText("");
        txtPrestadorConsulta.setText("");
        txtHorasConsulta.setText("");
        txtDescricaoConsulta.setText("");
    }

    public UICadastroServico() {
        initComponents();
        listaEmpresas();
        listaPrestadores();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel1 = new keeptoo.KGradientPanel();
        jPanel1 = new javax.swing.JPanel();
        txtEmailCadastro = new javax.swing.JPanel();
        btnCadastro = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtHorasCadastro = new javax.swing.JTextField();
        txtDescricaoCadastro = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtCodigoCadastro = new javax.swing.JTextField();
        cbxEmpresaCadastro = new javax.swing.JComboBox<>();
        cbxPrestadorCadastro = new javax.swing.JComboBox<>();
        cbxServicoCadastro = new javax.swing.JComboBox<>();
        btnLimpa1 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtEmailCadastro1 = new javax.swing.JPanel();
        btnBusca = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtHashConsulta = new javax.swing.JTextField();
        btnAtualiza = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnLimpa = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtDescricaoConsulta = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtHorasConsulta = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cbxServicoConsulta = new javax.swing.JComboBox<>();
        txtEmpresaConsulta = new javax.swing.JTextField();
        txtPrestadorConsulta = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        kGradientPanel1.setkEndColor(new java.awt.Color(12, 91, 60));
        kGradientPanel1.setkGradientFocus(600);
        kGradientPanel1.setkStartColor(new java.awt.Color(41, 144, 181));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtEmailCadastro.setBackground(new java.awt.Color(255, 255, 255));
        txtEmailCadastro.setForeground(new java.awt.Color(242, 104, 53));

        btnCadastro.setBackground(new java.awt.Color(41, 144, 181));
        btnCadastro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCadastro.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/adicionar-usuario (1).png"))); // NOI18N
        btnCadastro.setText("INSCREVER-SE");
        btnCadastro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(41, 144, 181));
        jLabel7.setText("CADASTRAR SERVIÇOS");

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(41, 144, 181));
        jLabel8.setText("Tipo de Serviço");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(41, 144, 181));
        jLabel1.setText("Código Hash");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(41, 144, 181));
        jLabel3.setText("Prestador");

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(41, 144, 181));
        jLabel9.setText("Descrição");

        txtHorasCadastro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHorasCadastro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));
        txtHorasCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHorasCadastroActionPerformed(evt);
            }
        });

        txtDescricaoCadastro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDescricaoCadastro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));
        txtDescricaoCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescricaoCadastroActionPerformed(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(41, 144, 181));
        jLabel17.setText("Quantidade de Horas");

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(41, 144, 181));
        jLabel19.setText("Empresa");

        txtCodigoCadastro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCodigoCadastro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));
        txtCodigoCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoCadastroActionPerformed(evt);
            }
        });

        cbxEmpresaCadastro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxEmpresaCadastro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empresas Cadastradas" }));
        cbxEmpresaCadastro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));

        cbxPrestadorCadastro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxPrestadorCadastro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Prestadores Cadastrados" }));
        cbxPrestadorCadastro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));

        cbxServicoCadastro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxServicoCadastro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Serviços Disponíveis", "Massagista", "Nutricionista", "Fisioterapeuta" }));
        cbxServicoCadastro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));

        btnLimpa1.setBackground(new java.awt.Color(41, 144, 181));
        btnLimpa1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimpa1.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/limpar-limpo.png"))); // NOI18N
        btnLimpa1.setText("LIMPAR CAMPOS");
        btnLimpa1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnLimpa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpa1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout txtEmailCadastroLayout = new javax.swing.GroupLayout(txtEmailCadastro);
        txtEmailCadastro.setLayout(txtEmailCadastroLayout);
        txtEmailCadastroLayout.setHorizontalGroup(
            txtEmailCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtEmailCadastroLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(txtEmailCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxEmpresaCadastro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9)
                    .addComponent(jLabel17)
                    .addComponent(txtDescricaoCadastro)
                    .addComponent(txtCodigoCadastro)
                    .addComponent(cbxPrestadorCadastro, 0, 347, Short.MAX_VALUE)
                    .addComponent(cbxServicoCadastro, 0, 347, Short.MAX_VALUE)
                    .addComponent(txtHorasCadastro))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtEmailCadastroLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(txtEmailCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtEmailCadastroLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(92, 92, 92))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtEmailCadastroLayout.createSequentialGroup()
                        .addGroup(txtEmailCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnLimpa1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73))))
        );
        txtEmailCadastroLayout.setVerticalGroup(
            txtEmailCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtEmailCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxEmpresaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxPrestadorCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxServicoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHorasCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescricaoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(btnCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLimpa1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(41, 144, 141));
        jLabel23.setText("Cadastrar Novo Prestador? Clique aqui");
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(41, 144, 141));
        jLabel11.setText("Cadastrar Nova Empresa? Clique aqui");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtEmailCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel11)))
                .addGap(96, 96, 96))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtEmailCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/Futpro_logo2__3_-removebg-preview (1).png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 255));
        jLabel4.setText("PÁGINA DE CADASTRO");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/image-removebg-preview (2).png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("<html> A melhor clínica esportiva para <br> o seu ATLETA !");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtEmailCadastro1.setBackground(new java.awt.Color(255, 255, 255));
        txtEmailCadastro1.setForeground(new java.awt.Color(242, 104, 53));

        btnBusca.setBackground(new java.awt.Color(41, 144, 181));
        btnBusca.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBusca.setForeground(new java.awt.Color(255, 255, 255));
        btnBusca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/binoculars.png"))); // NOI18N
        btnBusca.setText("BUSCAR");
        btnBusca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(41, 144, 181));
        jLabel10.setText("BUSCAR SERVIÇO");

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(41, 144, 181));
        jLabel13.setText("Tipo");

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(41, 144, 181));
        jLabel14.setText("Código Hash");

        txtHashConsulta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHashConsulta.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));
        txtHashConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHashConsultaActionPerformed(evt);
            }
        });

        btnAtualiza.setBackground(new java.awt.Color(41, 144, 181));
        btnAtualiza.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAtualiza.setForeground(new java.awt.Color(255, 255, 255));
        btnAtualiza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/updated.png"))); // NOI18N
        btnAtualiza.setText("ATUALIZAR");
        btnAtualiza.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnAtualiza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizaActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(41, 144, 181));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/delete.png"))); // NOI18N
        btnDelete.setText("DELETAR");
        btnDelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnLimpa.setBackground(new java.awt.Color(41, 144, 181));
        btnLimpa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimpa.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/limpar-limpo.png"))); // NOI18N
        btnLimpa.setText("LIMPAR");
        btnLimpa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnLimpa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpaActionPerformed(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(41, 144, 181));
        jLabel15.setText("Descrição");

        txtDescricaoConsulta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDescricaoConsulta.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));
        txtDescricaoConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescricaoConsultaActionPerformed(evt);
            }
        });

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(41, 144, 181));
        jLabel20.setText("Empresa");

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(41, 144, 181));
        jLabel12.setText("Prestador");

        txtHorasConsulta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHorasConsulta.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));
        txtHorasConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHorasConsultaActionPerformed(evt);
            }
        });

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(41, 144, 181));
        jLabel16.setText("Quantidade de Horas");

        cbxServicoConsulta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxServicoConsulta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Serviços Disponíveis", "Massagista", "Nutricionista", "Fisioterapeuta" }));
        cbxServicoConsulta.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));

        txtEmpresaConsulta.setEditable(false);
        txtEmpresaConsulta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEmpresaConsulta.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));
        txtEmpresaConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpresaConsultaActionPerformed(evt);
            }
        });

        txtPrestadorConsulta.setEditable(false);
        txtPrestadorConsulta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPrestadorConsulta.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));
        txtPrestadorConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrestadorConsultaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout txtEmailCadastro1Layout = new javax.swing.GroupLayout(txtEmailCadastro1);
        txtEmailCadastro1.setLayout(txtEmailCadastro1Layout);
        txtEmailCadastro1Layout.setHorizontalGroup(
            txtEmailCadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtEmailCadastro1Layout.createSequentialGroup()
                .addGroup(txtEmailCadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(txtEmailCadastro1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(txtEmailCadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtHashConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(txtEmailCadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(txtEmailCadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel13)
                                    .addComponent(txtDescricaoConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel12)
                                    .addComponent(txtHorasConsulta)
                                    .addComponent(txtEmpresaConsulta)
                                    .addComponent(txtPrestadorConsulta))
                                .addGroup(txtEmailCadastro1Layout.createSequentialGroup()
                                    .addComponent(btnBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(45, 45, 45)
                                    .addComponent(btnLimpa, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbxServicoConsulta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(txtEmailCadastro1Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel10))
                    .addGroup(txtEmailCadastro1Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addGroup(txtEmailCadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAtualiza, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        txtEmailCadastro1Layout.setVerticalGroup(
            txtEmailCadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtEmailCadastro1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHashConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(txtEmailCadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpa, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmpresaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPrestadorConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(cbxServicoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHorasConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescricaoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnAtualiza, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtEmailCadastro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtEmailCadastro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(55, 55, 55)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(240, Short.MAX_VALUE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroActionPerformed
        btnCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastraServico(novoServico);
            }
        });
    }//GEN-LAST:event_btnCadastroActionPerformed

    private void txtHorasCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHorasCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHorasCadastroActionPerformed

    private void btnBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaActionPerformed
        btnBusca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscaServico(novoServico);
            }
        });
    }//GEN-LAST:event_btnBuscaActionPerformed

    private void txtHashConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHashConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHashConsultaActionPerformed

    private void btnAtualizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizaActionPerformed
        btnAtualiza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizaServico(novoServico);
            }
        });
    }//GEN-LAST:event_btnAtualizaActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeServico(novoServico);
                LimpaCamposConsulta();
            }
        });
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnLimpaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpaActionPerformed
        btnLimpa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LimpaCamposConsulta();
            }
        });
    }//GEN-LAST:event_btnLimpaActionPerformed

    private void txtDescricaoCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescricaoCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescricaoCadastroActionPerformed

    private void txtCodigoCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoCadastroActionPerformed

    private void txtDescricaoConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescricaoConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescricaoConsultaActionPerformed

    private void txtHorasConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHorasConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHorasConsultaActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        UICadastroEmpresa telaCadastroEmpresa = new UICadastroEmpresa();
        this.dispose();
        telaCadastroEmpresa.setVisible(true);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        UICadastroPrestador telaCadastroPrestador = new UICadastroPrestador();
        this.dispose();
        telaCadastroPrestador.setVisible(true);
    }//GEN-LAST:event_jLabel23MouseClicked

    private void btnLimpa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpa1ActionPerformed
        btnLimpa1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LimpaCampos();
            }
        });
    }//GEN-LAST:event_btnLimpa1ActionPerformed

    private void txtEmpresaConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpresaConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpresaConsultaActionPerformed

    private void txtPrestadorConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrestadorConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrestadorConsultaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UICadastroServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UICadastroServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UICadastroServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UICadastroServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UICadastroServico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualiza;
    private javax.swing.JButton btnBusca;
    private javax.swing.JButton btnCadastro;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLimpa;
    private javax.swing.JButton btnLimpa1;
    private javax.swing.JComboBox<String> cbxEmpresaCadastro;
    private javax.swing.JComboBox<String> cbxPrestadorCadastro;
    private javax.swing.JComboBox<String> cbxServicoCadastro;
    private javax.swing.JComboBox<String> cbxServicoConsulta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JTextField txtCodigoCadastro;
    private javax.swing.JTextField txtDescricaoCadastro;
    private javax.swing.JTextField txtDescricaoConsulta;
    private javax.swing.JPanel txtEmailCadastro;
    private javax.swing.JPanel txtEmailCadastro1;
    private javax.swing.JTextField txtEmpresaConsulta;
    private javax.swing.JTextField txtHashConsulta;
    private javax.swing.JTextField txtHorasCadastro;
    private javax.swing.JTextField txtHorasConsulta;
    private javax.swing.JTextField txtPrestadorConsulta;
    // End of variables declaration//GEN-END:variables
}
