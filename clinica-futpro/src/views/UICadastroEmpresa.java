package views;

import conexoes.MySQL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.CadastroEmpresa;

public class UICadastroEmpresa extends javax.swing.JFrame {

    MySQL conectar = new MySQL();
    CadastroEmpresa novaEmpresa = new CadastroEmpresa();

    private void cadastraEmpresa(CadastroEmpresa novaEmpresa) {
        this.conectar.conectaBanco();

        novaEmpresa.setNome(txtNomeCadastro.getText());
        novaEmpresa.setRazao(txtRazaoCadastro.getText());
        novaEmpresa.setCnpj(txtCnpjCadastro.getText());
        novaEmpresa.setEndereco(txtEnderecoCadastro.getText());

        try {
            this.conectar.insertSQL(
                    "INSERT INTO tb_empresas VALUES ("
                    + "'" + novaEmpresa.getId() + "',"
                    + "'" + novaEmpresa.getNome() + "',"
                    + "'" + novaEmpresa.getRazao() + "',"
                    + "'" + novaEmpresa.getCnpj() + "',"
                    + "'" + novaEmpresa.getEndereco() + "'"
                    + ");");
            JOptionPane.showMessageDialog(null, "[OK]: Empresa cadastrada com sucesso!");
        } catch (Exception e) {
            System.out.println("[ERRO]: Não foi possível cadastrar nova empresa! " + e.getMessage());
        } finally {
            this.conectar.fechaBanco();
        }
    }

    private void buscaEmpresa(CadastroEmpresa novaEmpresa) {
        this.conectar.conectaBanco();

        String consultaCnpj = this.txtCnpjConsulta.getText();

        try {
            this.conectar.executarSQL(
                    "SELECT " + "nome," + "razao," + "endereco" + " FROM " + "tb_empresas"
                    + " WHERE" + " cnpj = '" + consultaCnpj + "'" + ";"
            );
            while (this.conectar.getResultSet().next()) {
                novaEmpresa.setNome(this.conectar.getResultSet().getString(1));
                novaEmpresa.setRazao(this.conectar.getResultSet().getString(2));
                novaEmpresa.setEndereco(this.conectar.getResultSet().getString(3));
            }

            if (novaEmpresa.getNome() == null) {
                JOptionPane.showMessageDialog(null, "[ERRO]: Empresa não localizada!");
                txtCnpjConsulta.setText("");
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar empresa!" + e.getMessage());
            JOptionPane.showMessageDialog(null, "[ERRO]: Erro ao buscar empresa!");
        } finally {
            txtConsultaNome.setText(novaEmpresa.getNome());
            txtConsultaRazao.setText(novaEmpresa.getRazao());
            txtConsultaEndereco.setText(novaEmpresa.getEndereco());

            this.conectar.fechaBanco();
        }
    }

    public void atualizaEmpresa(CadastroEmpresa novaEmpresa) {
        this.conectar.conectaBanco();

        String consultaCnpj = this.txtCnpjConsulta.getText();

        try {
            this.conectar.updateSQL(
                    "UPDATE tb_empresas SET nome="
                    + "'" + txtConsultaNome.getText() + "',"
                    + " razao =" + "'" + txtConsultaRazao.getText() + "', "
                    + " endereco =" + "'" + txtConsultaEndereco.getText() + "'"
                    + " WHERE" + " cnpj = '" + consultaCnpj + "'" + ";"
            );

            if (novaEmpresa.getNome() == null) {
                JOptionPane.showMessageDialog(null, "[ERRO]: Campos para busca de empresa vazios!");
            } else {
                JOptionPane.showMessageDialog(null, "[OK]: Empresa atualizada com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Não foi possível atualizar a empresa!" + e.getMessage());
            JOptionPane.showMessageDialog(null, "[ERRO]: Erro ao atualizar empresa!");
        } finally {
            this.conectar.fechaBanco();
        }
    }

    public void removeEmpresa(CadastroEmpresa novaEmpresa) {
        this.conectar.conectaBanco();

        String consultaCnpj = this.txtCnpjConsulta.getText();

        try {
            this.conectar.updateSQL(
                    "DELETE FROM tb_empresas WHERE cnpj = '" + consultaCnpj + "';"
            );

            if (novaEmpresa.getNome() == null) {
                JOptionPane.showMessageDialog(null, "[ERRO]: Campos para deleção de empresa vazios!");
            } else {
                JOptionPane.showMessageDialog(null, "[OK]: Empresa deletada com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Não foi possível deletar a empresa!" + e.getMessage());
            JOptionPane.showMessageDialog(null, "[ERRO]: Erro ao deletar empresa!");
        } finally {
            txtConsultaNome.setText("");
            txtConsultaRazao.setText("");
            txtConsultaEndereco.setText("");

            this.conectar.fechaBanco();
        }
    }

    public void LimpaCampos() {
        txtNomeCadastro.setText("");
        txtRazaoCadastro.setText("");
        txtCnpjCadastro.setText("");
        txtEnderecoCadastro.setText("");
    }

    public void LimpaCamposConsulta() {
        txtCnpjConsulta.setText("");
        txtConsultaNome.setText("");
        txtConsultaRazao.setText("");
        txtConsultaEndereco.setText("");
    }

    public UICadastroEmpresa() {
        initComponents();
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
        txtRazaoCadastro = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtNomeCadastro = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtEnderecoCadastro = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtCnpjCadastro = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtEmailCadastro1 = new javax.swing.JPanel();
        btnBusca = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtConsultaRazao = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtConsultaNome = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtCnpjConsulta = new javax.swing.JTextField();
        btnAtualiza = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnLimpa = new javax.swing.JButton();
        txtConsultaEndereco = new javax.swing.JTextField();

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
        jLabel7.setText("CADASTRAR EMPRESAS");

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(41, 144, 181));
        jLabel8.setText("Cnpj");

        txtRazaoCadastro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtRazaoCadastro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));
        txtRazaoCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRazaoCadastroActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(41, 144, 181));
        jLabel1.setText("Nome Institucional");

        txtNomeCadastro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNomeCadastro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(41, 144, 181));
        jLabel3.setText("Razão Social");

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(41, 144, 181));
        jLabel9.setText("Endereço");

        txtEnderecoCadastro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEnderecoCadastro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));
        txtEnderecoCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnderecoCadastroActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(41, 144, 141));
        jLabel15.setText("Empresa já cadastrada? Solicite Serviço");
        jLabel15.setToolTipText("");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        txtCnpjCadastro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCnpjCadastro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));
        txtCnpjCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCnpjCadastroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout txtEmailCadastroLayout = new javax.swing.GroupLayout(txtEmailCadastro);
        txtEmailCadastro.setLayout(txtEmailCadastroLayout);
        txtEmailCadastroLayout.setHorizontalGroup(
            txtEmailCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtEmailCadastroLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(txtEmailCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(txtEmailCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel8)
                        .addComponent(jLabel3)
                        .addComponent(jLabel9)
                        .addComponent(txtNomeCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                        .addComponent(txtRazaoCadastro)
                        .addComponent(txtEnderecoCadastro)
                        .addComponent(txtCnpjCadastro)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtEmailCadastroLayout.createSequentialGroup()
                .addContainerGap(118, Short.MAX_VALUE)
                .addGroup(txtEmailCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtEmailCadastroLayout.createSequentialGroup()
                        .addComponent(btnCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtEmailCadastroLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(95, 95, 95))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtEmailCadastroLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(132, 132, 132))))
        );
        txtEmailCadastroLayout.setVerticalGroup(
            txtEmailCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtEmailCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(96, 96, 96)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRazaoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCnpjCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEnderecoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addComponent(btnCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addGap(43, 43, 43)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtEmailCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtEmailCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jLabel10.setText("BUSCAR EMPRESA");

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(41, 144, 181));
        jLabel11.setText("Endereço");

        txtConsultaRazao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtConsultaRazao.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));
        txtConsultaRazao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConsultaRazaoActionPerformed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(41, 144, 181));
        jLabel12.setText("Nome Institucional");

        txtConsultaNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtConsultaNome.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(41, 144, 181));
        jLabel13.setText("Razão Social");

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(41, 144, 181));
        jLabel14.setText("Cnpj");

        txtCnpjConsulta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCnpjConsulta.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));
        txtCnpjConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCnpjConsultaActionPerformed(evt);
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

        txtConsultaEndereco.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtConsultaEndereco.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));
        txtConsultaEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConsultaEnderecoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout txtEmailCadastro1Layout = new javax.swing.GroupLayout(txtEmailCadastro1);
        txtEmailCadastro1.setLayout(txtEmailCadastro1Layout);
        txtEmailCadastro1Layout.setHorizontalGroup(
            txtEmailCadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtEmailCadastro1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(txtEmailCadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtualiza, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(104, 104, 104))
            .addGroup(txtEmailCadastro1Layout.createSequentialGroup()
                .addGroup(txtEmailCadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(txtEmailCadastro1Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jLabel10))
                    .addGroup(txtEmailCadastro1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(txtEmailCadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(txtEmailCadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel11)
                                .addComponent(jLabel13)
                                .addComponent(jLabel14)
                                .addGroup(txtEmailCadastro1Layout.createSequentialGroup()
                                    .addComponent(btnBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                    .addComponent(btnLimpa, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtConsultaNome)
                                .addComponent(txtConsultaRazao)
                                .addComponent(txtCnpjConsulta)
                                .addComponent(txtConsultaEndereco)))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        txtEmailCadastro1Layout.setVerticalGroup(
            txtEmailCadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtEmailCadastro1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(15, 15, 15)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCnpjConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(txtEmailCadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBusca, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(btnLimpa, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
                .addGap(55, 55, 55)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConsultaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConsultaRazao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConsultaEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(btnAtualiza, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addGap(22, 22, 22)
                .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
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
                .addContainerGap(42, Short.MAX_VALUE))
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
                .addGap(32, 32, 32)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(272, Short.MAX_VALUE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
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
                .addContainerGap(123, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroActionPerformed
        btnCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastraEmpresa(novaEmpresa);
                LimpaCampos();
            }
        });
    }//GEN-LAST:event_btnCadastroActionPerformed

    private void txtRazaoCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRazaoCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazaoCadastroActionPerformed

    private void txtEnderecoCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnderecoCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnderecoCadastroActionPerformed

    private void btnBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaActionPerformed
        btnBusca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscaEmpresa(novaEmpresa);
                LimpaCampos();
            }
        });
    }//GEN-LAST:event_btnBuscaActionPerformed

    private void txtConsultaRazaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConsultaRazaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConsultaRazaoActionPerformed

    private void txtCnpjConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCnpjConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCnpjConsultaActionPerformed

    private void btnAtualizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizaActionPerformed
        btnAtualiza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizaEmpresa(novaEmpresa);
                LimpaCamposConsulta();
            }
        });
    }//GEN-LAST:event_btnAtualizaActionPerformed

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        UICadastroServico telaCadastroServico = new UICadastroServico();
        this.dispose();
        telaCadastroServico.setVisible(true);
    }//GEN-LAST:event_jLabel15MouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEmpresa(novaEmpresa);
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

    private void txtCnpjCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCnpjCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCnpjCadastroActionPerformed

    private void txtConsultaEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConsultaEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConsultaEnderecoActionPerformed

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
            java.util.logging.Logger.getLogger(UICadastroEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UICadastroEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UICadastroEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UICadastroEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UICadastroEmpresa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualiza;
    private javax.swing.JButton btnBusca;
    private javax.swing.JButton btnCadastro;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLimpa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JTextField txtCnpjCadastro;
    private javax.swing.JTextField txtCnpjConsulta;
    private javax.swing.JTextField txtConsultaEndereco;
    private javax.swing.JTextField txtConsultaNome;
    private javax.swing.JTextField txtConsultaRazao;
    private javax.swing.JPanel txtEmailCadastro;
    private javax.swing.JPanel txtEmailCadastro1;
    private javax.swing.JTextField txtEnderecoCadastro;
    private javax.swing.JTextField txtNomeCadastro;
    private javax.swing.JTextField txtRazaoCadastro;
    // End of variables declaration//GEN-END:variables
}
