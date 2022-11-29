package views;

import conexoes.MySQL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.CadastroPrestador;

public class UICadastroPrestador extends javax.swing.JFrame {

    MySQL conectar = new MySQL();
    CadastroPrestador novoPrestador = new CadastroPrestador();

    private void cadastraPrestador(CadastroPrestador novoPrestador) {
        this.conectar.conectaBanco();

        novoPrestador.setNome(txtNomeCadastroPrest.getText());
        novoPrestador.setEmail(txtemailCadastroPrest.getText());
        String password = String.valueOf(txtSenhaCadastroPrest.getPassword());
        novoPrestador.setSenha(password);
        novoPrestador.setCpf(txtCpfCadastroPrest.getText());
        novoPrestador.setProfissao((String) cbxProfissao.getSelectedItem());

        try {
            if (!novoPrestador.getNome().isBlank() && !novoPrestador.getEmail().isBlank() && !novoPrestador.getSenha().isBlank() && !novoPrestador.getCpf().isBlank()) {
                this.conectar.insertSQL(
                        "INSERT INTO tb_prestadores VALUES ("
                        + "'" + novoPrestador.getId() + "',"
                        + "'" + novoPrestador.getNome() + "',"
                        + "'" + novoPrestador.getEmail() + "',"
                        + "'" + novoPrestador.getSenha() + "',"
                        + "'" + novoPrestador.getCpf() + "',"
                        + "'" + novoPrestador.getProfissao() + "'"
                        + ");");
                JOptionPane.showMessageDialog(null, "[OK]: Prestador cadastrado com sucesso!");
            }

            if (novoPrestador.getNome().isBlank() || novoPrestador.getEmail().isBlank() || novoPrestador.getSenha().isBlank() || novoPrestador.getCpf().isBlank()) {
                JOptionPane.showMessageDialog(null, "[ERRO]: Campos para cadastro de prestador vazios!");
            }
        } catch (Exception e) {
            System.out.println("[ERRO]: Não foi possível cadastrar novo prestador! " + e.getMessage());
        } finally {
            this.conectar.fechaBanco();
        }
    }

    private void buscaPrestador(CadastroPrestador novoPrestador) {
        this.conectar.conectaBanco();

        String consultaCpf = this.txtCpfConsultaPrest.getText();

        try {
            if (!consultaCpf.isEmpty()) {
                this.conectar.executarSQL(
                        "SELECT " + "nome," + "email," + "senha," + "profissao" + " FROM " + "tb_prestadores"
                        + " WHERE" + " cpf = '" + consultaCpf + "'" + ";"
                );
                while (this.conectar.getResultSet().next()) {
                    novoPrestador.setNome(this.conectar.getResultSet().getString(1));
                    novoPrestador.setEmail(this.conectar.getResultSet().getString(2));
                    novoPrestador.setSenha(this.conectar.getResultSet().getString(3));
                    novoPrestador.setProfissao(this.conectar.getResultSet().getString(4));
                }

                txtConsultaNomePrest.setText(novoPrestador.getNome());
                txtConsultaEmailPrest.setText(novoPrestador.getEmail());
                txtConsultaSenhaPrest.setText(novoPrestador.getSenha());
                txtConsultaProfissao.setText(novoPrestador.getProfissao());
            } else {
                JOptionPane.showMessageDialog(null, "[ERRO]: Campo de consulta de CPF vazio!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar prestador! " + e.getMessage());
            JOptionPane.showMessageDialog(null, "[ERRO]: Erro ao buscar prestador!");
        } finally {
            this.conectar.fechaBanco();
        }
    }

    public void atualizaPrestador(CadastroPrestador novoPrestador) {
        this.conectar.conectaBanco();

        String consultaCpf = this.txtCpfConsultaPrest.getText();

        try {
            this.conectar.updateSQL(
                    "UPDATE tb_prestadores SET nome="
                    + "'" + txtConsultaNomePrest.getText() + "',"
                    + " email =" + "'" + txtConsultaEmailPrest.getText() + "', "
                    + " senha =" + "'" + String.valueOf(txtConsultaSenhaPrest.getPassword()) + "'"
                    + " WHERE" + " cpf = '" + consultaCpf + "'" + ";"
            );

            if (txtConsultaNomePrest.getText().isBlank() || txtConsultaEmailPrest.getText().isBlank() || txtConsultaProfissao.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "[ERRO]: Campos para atualização de prestador vazios!");
            } else {
                JOptionPane.showMessageDialog(null, "[OK]: Prestador atualizado com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Não foi possível atualizar o prestador! " + e.getMessage());
            JOptionPane.showMessageDialog(null, "[ERRO]: Erro ao atualizar prestador!");
        } finally {
            this.conectar.fechaBanco();
        }
    }

    public void removePrestador(CadastroPrestador novoPrestador) {
        this.conectar.conectaBanco();

        String consultaCpf = this.txtCpfConsultaPrest.getText();

        try {
            this.conectar.updateSQL(
                    "DELETE FROM tb_prestadores WHERE cpf = '" + consultaCpf + "';"
            );
        } catch (Exception e) {
            System.out.println("Não foi possível deletar o prestador! " + e.getMessage());
            JOptionPane.showMessageDialog(null, "[ERRO]: Erro ao deletar prestador!");
        } finally {
            txtConsultaNomePrest.setText("");
            txtConsultaEmailPrest.setText("");
            txtConsultaSenhaPrest.setText("");

            this.conectar.fechaBanco();
        }
    }

    public void LimpaCampos() {
        txtNomeCadastroPrest.setText("");
        txtemailCadastroPrest.setText("");
        txtSenhaCadastroPrest.setText("");
        txtCpfCadastroPrest.setText("");
    }

    public void LimpaCamposConsulta() {
        txtCpfConsultaPrest.setText("");
        txtConsultaNomePrest.setText("");
        txtConsultaEmailPrest.setText("");
        txtConsultaSenhaPrest.setText("");
        txtConsultaProfissao.setText("");
    }

    public UICadastroPrestador() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel1 = new keeptoo.KGradientPanel();
        jPanel1 = new javax.swing.JPanel();
        txtEmailCadastro = new javax.swing.JPanel();
        btnCadastroPrest = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtSenhaCadastroPrest = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        txtemailCadastroPrest = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtNomeCadastroPrest = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCpfCadastroPrest = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cbxProfissao = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        btnLimpaPrest1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtEmailCadastro1 = new javax.swing.JPanel();
        btnBuscaPrest = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtConsultaSenhaPrest = new javax.swing.JPasswordField();
        jLabel11 = new javax.swing.JLabel();
        txtConsultaEmailPrest = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtConsultaNomePrest = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtCpfConsultaPrest = new javax.swing.JTextField();
        btnAtualizaPrest = new javax.swing.JButton();
        btnDeletePrest = new javax.swing.JButton();
        btnLimpaPrest = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtConsultaProfissao = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        kGradientPanel1.setkEndColor(new java.awt.Color(12, 91, 60));
        kGradientPanel1.setkGradientFocus(600);
        kGradientPanel1.setkStartColor(new java.awt.Color(41, 144, 181));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtEmailCadastro.setBackground(new java.awt.Color(255, 255, 255));
        txtEmailCadastro.setForeground(new java.awt.Color(242, 104, 53));

        btnCadastroPrest.setBackground(new java.awt.Color(41, 144, 181));
        btnCadastroPrest.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCadastroPrest.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastroPrest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/adicionar-usuario (1).png"))); // NOI18N
        btnCadastroPrest.setText("INSCREVER-SE");
        btnCadastroPrest.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnCadastroPrest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroPrestActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(41, 144, 181));
        jLabel7.setText("CADASTRAR PRESTADORES");

        txtSenhaCadastroPrest.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtSenhaCadastroPrest.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));
        txtSenhaCadastroPrest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaCadastroPrestActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(41, 144, 181));
        jLabel8.setText("Senha");

        txtemailCadastroPrest.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtemailCadastroPrest.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));
        txtemailCadastroPrest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtemailCadastroPrestActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(41, 144, 181));
        jLabel1.setText("Nome completo");

        txtNomeCadastroPrest.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNomeCadastroPrest.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(41, 144, 181));
        jLabel3.setText("Email");

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(41, 144, 181));
        jLabel9.setText("Cpf");

        txtCpfCadastroPrest.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCpfCadastroPrest.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));
        txtCpfCadastroPrest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCpfCadastroPrestActionPerformed(evt);
            }
        });

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(41, 144, 181));
        jLabel16.setText("Profissão");
        jLabel16.setToolTipText("");

        cbxProfissao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxProfissao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Escolha sua profissão:", "Massagista", "Nutricionista", "Fisioterapeuta" }));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(41, 144, 141));
        jLabel15.setText("Prestador já cadastrado? Solicite Serviço");
        jLabel15.setToolTipText("");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        btnLimpaPrest1.setBackground(new java.awt.Color(41, 144, 181));
        btnLimpaPrest1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimpaPrest1.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpaPrest1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/limpar-limpo.png"))); // NOI18N
        btnLimpaPrest1.setText("LIMPAR CAMPOS");
        btnLimpaPrest1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnLimpaPrest1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpaPrest1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout txtEmailCadastroLayout = new javax.swing.GroupLayout(txtEmailCadastro);
        txtEmailCadastro.setLayout(txtEmailCadastroLayout);
        txtEmailCadastroLayout.setHorizontalGroup(
            txtEmailCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtEmailCadastroLayout.createSequentialGroup()
                .addGroup(txtEmailCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(txtEmailCadastroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cbxProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, txtEmailCadastroLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(txtEmailCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel1)
                            .addGroup(txtEmailCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel8)
                                .addComponent(jLabel3)
                                .addComponent(jLabel9)
                                .addComponent(txtNomeCadastroPrest, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                                .addComponent(txtemailCadastroPrest)
                                .addComponent(txtSenhaCadastroPrest)
                                .addComponent(txtCpfCadastroPrest)))))
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtEmailCadastroLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(txtEmailCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtEmailCadastroLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(101, 101, 101))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtEmailCadastroLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(71, 71, 71))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtEmailCadastroLayout.createSequentialGroup()
                        .addGroup(txtEmailCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnLimpaPrest1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCadastroPrest, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                        .addGap(92, 92, 92))))
        );
        txtEmailCadastroLayout.setVerticalGroup(
            txtEmailCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtEmailCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeCadastroPrest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtemailCadastroPrest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenhaCadastroPrest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCpfCadastroPrest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnCadastroPrest, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnLimpaPrest1, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/Futpro_logo2__3_-removebg-preview (1).png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 255));
        jLabel4.setText("PÁGINA DE CADASTRO");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/image-removebg-preview (2).png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("<html> A melhor clínica esportiva para  <br> o seu ATLETA !");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtEmailCadastro1.setBackground(new java.awt.Color(255, 255, 255));
        txtEmailCadastro1.setForeground(new java.awt.Color(242, 104, 53));

        btnBuscaPrest.setBackground(new java.awt.Color(41, 144, 181));
        btnBuscaPrest.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBuscaPrest.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscaPrest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/binoculars.png"))); // NOI18N
        btnBuscaPrest.setText("BUSCAR");
        btnBuscaPrest.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnBuscaPrest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaPrestActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(41, 144, 181));
        jLabel10.setText("BUSCAR PRESTADOR");

        txtConsultaSenhaPrest.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtConsultaSenhaPrest.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));
        txtConsultaSenhaPrest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConsultaSenhaPrestActionPerformed(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(41, 144, 181));
        jLabel11.setText("Senha");

        txtConsultaEmailPrest.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtConsultaEmailPrest.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));
        txtConsultaEmailPrest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConsultaEmailPrestActionPerformed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(41, 144, 181));
        jLabel12.setText("Nome completo");

        txtConsultaNomePrest.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtConsultaNomePrest.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(41, 144, 181));
        jLabel13.setText("Email");

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(41, 144, 181));
        jLabel14.setText("Cpf");

        txtCpfConsultaPrest.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCpfConsultaPrest.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));
        txtCpfConsultaPrest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCpfConsultaPrestActionPerformed(evt);
            }
        });

        btnAtualizaPrest.setBackground(new java.awt.Color(41, 144, 181));
        btnAtualizaPrest.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAtualizaPrest.setForeground(new java.awt.Color(255, 255, 255));
        btnAtualizaPrest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/updated.png"))); // NOI18N
        btnAtualizaPrest.setText("ATUALIZAR");
        btnAtualizaPrest.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnAtualizaPrest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizaPrestActionPerformed(evt);
            }
        });

        btnDeletePrest.setBackground(new java.awt.Color(41, 144, 181));
        btnDeletePrest.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDeletePrest.setForeground(new java.awt.Color(255, 255, 255));
        btnDeletePrest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/delete.png"))); // NOI18N
        btnDeletePrest.setText("DELETAR");
        btnDeletePrest.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnDeletePrest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletePrestActionPerformed(evt);
            }
        });

        btnLimpaPrest.setBackground(new java.awt.Color(41, 144, 181));
        btnLimpaPrest.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimpaPrest.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpaPrest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/limpar-limpo.png"))); // NOI18N
        btnLimpaPrest.setText("LIMPAR");
        btnLimpaPrest.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnLimpaPrest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpaPrestActionPerformed(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(41, 144, 181));
        jLabel17.setText("Profissão");

        txtConsultaProfissao.setEditable(false);
        txtConsultaProfissao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtConsultaProfissao.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(41, 144, 181)));
        txtConsultaProfissao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConsultaProfissaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout txtEmailCadastro1Layout = new javax.swing.GroupLayout(txtEmailCadastro1);
        txtEmailCadastro1.setLayout(txtEmailCadastro1Layout);
        txtEmailCadastro1Layout.setHorizontalGroup(
            txtEmailCadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtEmailCadastro1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(txtEmailCadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDeletePrest, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtualizaPrest, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(104, 104, 104))
            .addGroup(txtEmailCadastro1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(txtEmailCadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(txtEmailCadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel11)
                        .addComponent(jLabel13)
                        .addComponent(jLabel14)
                        .addGroup(txtEmailCadastro1Layout.createSequentialGroup()
                            .addComponent(btnBuscaPrest, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                            .addComponent(btnLimpaPrest, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtConsultaNomePrest)
                        .addComponent(txtConsultaEmailPrest)
                        .addComponent(txtConsultaSenhaPrest)
                        .addComponent(txtCpfConsultaPrest))
                    .addComponent(jLabel17)
                    .addComponent(txtConsultaProfissao, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtEmailCadastro1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(113, 113, 113))
        );
        txtEmailCadastro1Layout.setVerticalGroup(
            txtEmailCadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtEmailCadastro1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCpfConsultaPrest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(txtEmailCadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscaPrest, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(btnLimpaPrest, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConsultaNomePrest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConsultaEmailPrest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConsultaSenhaPrest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConsultaProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnAtualizaPrest, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addGap(22, 22, 22)
                .addComponent(btnDeletePrest, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
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
                .addContainerGap(46, Short.MAX_VALUE))
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
                .addContainerGap(248, Short.MAX_VALUE))
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

    private void btnCadastroPrestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroPrestActionPerformed
        btnCadastroPrest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastraPrestador(novoPrestador);
            }
        });
    }//GEN-LAST:event_btnCadastroPrestActionPerformed

    private void txtemailCadastroPrestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtemailCadastroPrestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtemailCadastroPrestActionPerformed

    private void txtSenhaCadastroPrestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaCadastroPrestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaCadastroPrestActionPerformed

    private void txtCpfCadastroPrestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCpfCadastroPrestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCpfCadastroPrestActionPerformed

    private void btnBuscaPrestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaPrestActionPerformed
        btnBuscaPrest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscaPrestador(novoPrestador);
            }
        });
    }//GEN-LAST:event_btnBuscaPrestActionPerformed

    private void txtConsultaSenhaPrestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConsultaSenhaPrestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConsultaSenhaPrestActionPerformed

    private void txtConsultaEmailPrestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConsultaEmailPrestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConsultaEmailPrestActionPerformed

    private void txtCpfConsultaPrestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCpfConsultaPrestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCpfConsultaPrestActionPerformed

    private void btnAtualizaPrestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizaPrestActionPerformed
        btnAtualizaPrest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizaPrestador(novoPrestador);
            }
        });
    }//GEN-LAST:event_btnAtualizaPrestActionPerformed

    private void btnDeletePrestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletePrestActionPerformed
        btnDeletePrest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removePrestador(novoPrestador);
                LimpaCamposConsulta();
            }
        });
    }//GEN-LAST:event_btnDeletePrestActionPerformed

    private void btnLimpaPrestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpaPrestActionPerformed
        btnLimpaPrest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LimpaCamposConsulta();
            }
        });
    }//GEN-LAST:event_btnLimpaPrestActionPerformed

    private void txtConsultaProfissaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConsultaProfissaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConsultaProfissaoActionPerformed

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        UICadastroServico telaCadastroServico = new UICadastroServico();
        this.dispose();
        telaCadastroServico.setVisible(true);
    }//GEN-LAST:event_jLabel15MouseClicked

    private void btnLimpaPrest1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpaPrest1ActionPerformed
        btnLimpaPrest1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LimpaCampos();
            }
        });
    }//GEN-LAST:event_btnLimpaPrest1ActionPerformed

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
            java.util.logging.Logger.getLogger(UICadastroPrestador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UICadastroPrestador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UICadastroPrestador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UICadastroPrestador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new UICadastroPrestador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizaPrest;
    private javax.swing.JButton btnBuscaPrest;
    private javax.swing.JButton btnCadastroPrest;
    private javax.swing.JButton btnDeletePrest;
    private javax.swing.JButton btnLimpaPrest;
    private javax.swing.JButton btnLimpaPrest1;
    private javax.swing.JComboBox<String> cbxProfissao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JTextField txtConsultaEmailPrest;
    private javax.swing.JTextField txtConsultaNomePrest;
    private javax.swing.JTextField txtConsultaProfissao;
    private javax.swing.JPasswordField txtConsultaSenhaPrest;
    private javax.swing.JTextField txtCpfCadastroPrest;
    private javax.swing.JTextField txtCpfConsultaPrest;
    private javax.swing.JPanel txtEmailCadastro;
    private javax.swing.JPanel txtEmailCadastro1;
    private javax.swing.JTextField txtNomeCadastroPrest;
    private javax.swing.JPasswordField txtSenhaCadastroPrest;
    private javax.swing.JTextField txtemailCadastroPrest;
    // End of variables declaration//GEN-END:variables
}
