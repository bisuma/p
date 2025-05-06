/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package halamanAplikasi;

import javax.swing.JFrame;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ahmad Kurniawan
 */
public class MainView extends javax.swing.JFrame {

    PenerimaanBeasiswa koneksi = new PenerimaanBeasiswa();

    Map<String, Object> param = new HashMap<String, Object>();

    /**
     * Creates new form MainView
     */
    //Method Mengosongkan Form
    private void kosongkan_form() {
        fieldNama.setEditable(true);
        fieldNama.setText(null);
        fieldNim.setText(null);
        fieldAlamat.setText(null);
        fieldAyah.setText(null);
        fieldGaji.setText(null);
        fieldIpk.setText(null);

    }

    //Method Simpan/ tambah data
    private void simpan() {

        int gajiAyah;
        float ipk;

        gajiAyah = Integer.parseInt(fieldGaji.getText());
        ipk = Float.valueOf(fieldIpk.getText());

        if (gajiAyah == 4000000 && ipk == 3.5) {

            fieldUang.setText("Rp.5000.000");

        } else if (gajiAyah == 3000000 && ipk == 3.5) {

            fieldUang.setText("Rp.4000.000");

        } else if (gajiAyah == 2000000 && ipk == 3.0) {

            fieldUang.setText("Rp.3000.000");

        } else if (gajiAyah == 1000000 && ipk == 3.5) {

            fieldUang.setText("Rp.3.500.000");

        } else {

            fieldUang.setText("Rp.0");

        }

        try {
            String sql = "INSERT INTO siswa VALUES ('" + fieldNim.getText() + "','" + fieldNama.getText() + "','" + fieldFakultas.getSelectedItem() + "','" + fieldprodi.getSelectedItem() + "','" + fieldAlamat.getText() + "','" + fieldJk.getSelectedItem() + "','" + fieldAyah.getText() + "','" + fieldGaji.getText() + "','" + fieldIpk.getText() + "','" + fieldAgama.getSelectedItem() + "','" + fieldUang.getText() + "')";
            java.sql.Connection conn = (Connection) PenerimaanBeasiswa.ConfigDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(this, "Data Berhasil Di Simpan...");

            //method tampil data
            menampilkan_data();
            //method kosongkan form
            kosongkan_form();

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    //method Ubah data
    private void ubah() {
        try {
            String sql = "UPDATE siswa SET nim='" + fieldNim.getText() + "',nama='" + fieldNama.getText() + "',fakultas='" + fieldFakultas.getSelectedItem() + "',prodi='" + fieldprodi.getSelectedItem() + "',alamat='" + fieldAlamat.getText() + "',jenis_kelamin='" + fieldJk.getSelectedItem() + "',nama_ayah='" + fieldAyah.getText() + "',gaji_ayah='" + fieldGaji.getText() + "',ipk='" + fieldIpk.getText() + "',agama='" + fieldAgama.getSelectedItem() + "' WHERE nim ='" + fieldNim.getText() + "'";
            java.sql.Connection conn = (Connection) PenerimaanBeasiswa.ConfigDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(this, "Data Berhasil Di Ubah...");

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        //method tampil data
        menampilkan_data();
        //method kosongkan form
        kosongkan_form();
    }

    //Method Hapus Data
    private void hapus() {
        try {
            String sql = "DELETE FROM   siswa WHERE Nim='" + fieldNim.getText() + "'";
            java.sql.Connection conn = (Connection) PenerimaanBeasiswa.ConfigDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        menampilkan_data();
        kosongkan_form();
    }

    //Method Menampilkan data
    private void menampilkan_data() {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("No");
        model.addColumn("Nim");
        model.addColumn("Nama");
        model.addColumn("Prodi");
        model.addColumn("Alamat");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Nama Ayah");
        model.addColumn("Gaji Ayah");
        model.addColumn("IPK Mahasiswa");
        model.addColumn("Agama");

        try {

            int no = 1;
            String sql = "SELECT * FROM siswa";
            java.sql.Connection conn = (Connection) PenerimaanBeasiswa.ConfigDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                model.addRow(new Object[]{no++, res.getString(1), res.getString(2), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9), res.getString(10)});

            }

            tabelData.setModel(model);

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    //Method Menampilkan data penerima Beasiswa
    private void getDataPenerimaBeasiswa() {

        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("nim");
        model.addColumn("nama");
        model.addColumn("fakultas");
        model.addColumn("prodi");
        model.addColumn("alamat");
        model.addColumn("jenis_kelamin");
        model.addColumn("nama_ayah");
        model.addColumn("gaji_ayah");
        model.addColumn("ipk_mahasiswa");
        model.addColumn("agama");

        try {

            int no = 1;
            String sql = "SELECT * FROM siswa";
            java.sql.Connection conn = (Connection) PenerimaanBeasiswa.ConfigDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                model.addRow(new Object[]{no++, res.getString(1), res.getString(2), res.getString(4), res.getString(6), res.getString(9), res.getString(11)});

            }

            tabelBeasiswa.setModel(model);

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    //method cari data penerima beasiswa
    private void cariDataPenerimaBeasiswa() {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("nim");
        model.addColumn("nama");
        model.addColumn("fakultas");
        model.addColumn("prodi");
        model.addColumn("alamat");
        model.addColumn("jenis_kelamin");
        model.addColumn("nama_ayah");
        model.addColumn("gaji_ayah");
        model.addColumn("ipk_mahasiswa");
        model.addColumn("agama");

        String key = SearchData.getText();

        try {

            int no = 1;
            String sql = "SELECT * FROM siswa WHERE nama LIKE '%" + key + "%' OR alamat LIKE '%" + key + "%' OR nim LIKE '%" + key + "%' OR nim LIKE '%" + key + "%' ";
            java.sql.Connection conn = (Connection) PenerimaanBeasiswa.ConfigDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                model.addRow(new Object[]{no++, res.getString(1), res.getString(2), res.getString(4), res.getString(6), res.getString(9), res.getString(11)});

            }

            tabelBeasiswa.setModel(model);

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }

    }

    //method pencarian Data
    private void cariData() {

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nim");
        model.addColumn("Nama");
        model.addColumn("Prodi");
        model.addColumn("Alamat");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Nama Ayah");
        model.addColumn("Gaji Ayah");
        model.addColumn("IPK Mahasiswa");
        model.addColumn("Agama");

        String key = fieldCari.getText();

        try {

            int no = 1;
            String sql = "SELECT * FROM siswa WHERE nama LIKE '%" + key + "%' OR alamat LIKE '%" + key + "%' OR nim LIKE '%" + key + "%' ";
            java.sql.Connection conn = (Connection) PenerimaanBeasiswa.ConfigDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                model.addRow(new Object[]{no++, res.getString(1), res.getString(2), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9), res.getString(10)});

            }

            tabelData.setModel(model);

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }

    }

    public MainView() {
        initComponents();

        menampilkan_data();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable(false);

        fieldUang.setEditable(false);
        fieldUang.setVisible(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bodyPanel = new javax.swing.JPanel();
        menuPanel = new javax.swing.JPanel();
        btnHome = new javax.swing.JButton();
        btnSyarat = new javax.swing.JButton();
        btnDataPenerima = new javax.swing.JButton();
        btnAbout = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnDataCalon = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        homePanel = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        about = new javax.swing.JLabel();
        syaratnav = new javax.swing.JLabel();
        calonNav = new javax.swing.JLabel();
        penerimaNav = new javax.swing.JLabel();
        DataPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        fieldNim = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        fieldAyah = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        fieldGaji = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        fieldIpk = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelData = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        fieldNama = new javax.swing.JTextField();
        btnLaporan = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        fieldCari = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        fieldprodi = new javax.swing.JComboBox<>();
        fieldFakultas = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        fieldAlamat = new javax.swing.JTextArea();
        fieldAgama = new javax.swing.JComboBox<>();
        fieldJk = new javax.swing.JComboBox<>();
        fieldUang = new javax.swing.JTextField();
        btnCek = new javax.swing.JButton();
        SyaratPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        dataPenerima = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelBeasiswa = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        SearchData = new javax.swing.JTextField();
        btnPrintData = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistem informasi Penerimaan Beasiswa");

        bodyPanel.setBackground(new java.awt.Color(255, 255, 255));

        menuPanel.setBackground(new java.awt.Color(255, 255, 255));
        menuPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        btnHome.setBackground(new java.awt.Color(255, 255, 255));
        btnHome.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnHome.setForeground(new java.awt.Color(153, 153, 153));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon1.png"))); // NOI18N
        btnHome.setText("               HOME");
        btnHome.setBorder(null);
        btnHome.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnHome.setIconTextGap(10);
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        btnSyarat.setBackground(new java.awt.Color(255, 255, 255));
        btnSyarat.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnSyarat.setForeground(new java.awt.Color(153, 153, 153));
        btnSyarat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon3.png"))); // NOI18N
        btnSyarat.setText("            SYARAT");
        btnSyarat.setBorder(null);
        btnSyarat.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSyarat.setIconTextGap(10);
        btnSyarat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSyaratActionPerformed(evt);
            }
        });

        btnDataPenerima.setBackground(new java.awt.Color(255, 255, 255));
        btnDataPenerima.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnDataPenerima.setForeground(new java.awt.Color(153, 153, 153));
        btnDataPenerima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon2.png"))); // NOI18N
        btnDataPenerima.setText("DATA PENERIMA");
        btnDataPenerima.setBorder(null);
        btnDataPenerima.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDataPenerima.setIconTextGap(10);
        btnDataPenerima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataPenerimaActionPerformed(evt);
            }
        });

        btnAbout.setBackground(new java.awt.Color(255, 255, 255));
        btnAbout.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnAbout.setForeground(new java.awt.Color(153, 153, 153));
        btnAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon3.png"))); // NOI18N
        btnAbout.setText("               ABOUT");
        btnAbout.setBorder(null);
        btnAbout.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAbout.setIconTextGap(10);
        btnAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAboutActionPerformed(evt);
            }
        });

        btnLogout.setBackground(new java.awt.Color(255, 255, 255));
        btnLogout.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(153, 153, 153));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon4.png"))); // NOI18N
        btnLogout.setText("               KELUAR");
        btnLogout.setBorder(null);
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLogout.setIconTextGap(10);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Rajdhani", 1, 30)); // NOI18N
        jLabel2.setText("SIPB");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));

        btnDataCalon.setBackground(new java.awt.Color(255, 255, 255));
        btnDataCalon.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnDataCalon.setForeground(new java.awt.Color(153, 153, 153));
        btnDataCalon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon2.png"))); // NOI18N
        btnDataCalon.setText("DATA CALON");
        btnDataCalon.setBorder(null);
        btnDataCalon.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDataCalon.setIconTextGap(10);
        btnDataCalon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataCalonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnHome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSyarat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDataPenerima, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAbout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3)))
                .addContainerGap())
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(48, 48, 48))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuPanelLayout.createSequentialGroup()
                .addComponent(btnDataCalon, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addContainerGap())
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSyarat, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDataCalon, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDataPenerima, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAbout, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.setBackground(new java.awt.Color(26, 188, 156));
        mainPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        mainPanel.setLayout(new java.awt.CardLayout());

        homePanel.setBackground(new java.awt.Color(0, 0, 102));
        homePanel.setForeground(new java.awt.Color(102, 102, 102));

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/header1.png"))); // NOI18N

        about.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/nav4.png"))); // NOI18N
        about.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aboutMouseClicked(evt);
            }
        });

        syaratnav.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/nav1.png"))); // NOI18N
        syaratnav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                syaratnavMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                syaratnavMouseClicked(evt);
            }
        });

        calonNav.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/nav2.png"))); // NOI18N
        calonNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                calonNavMouseClicked(evt);
            }
        });

        penerimaNav.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/nav3.png"))); // NOI18N
        penerimaNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                penerimaNavMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout homePanelLayout = new javax.swing.GroupLayout(homePanel);
        homePanel.setLayout(homePanelLayout);
        homePanelLayout.setHorizontalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(homePanelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(syaratnav)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calonNav)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(penerimaNav)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(about))
                    .addGroup(homePanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 12, Short.MAX_VALUE))
        );
        homePanelLayout.setVerticalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel20)
                .addGap(84, 84, 84)
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calonNav)
                    .addComponent(syaratnav)
                    .addComponent(penerimaNav)
                    .addComponent(about))
                .addContainerGap(873, Short.MAX_VALUE))
        );

        mainPanel.add(homePanel, "card2");

        DataPanel.setBackground(new java.awt.Color(0, 0, 102));

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("NIM");

        fieldNim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldNimActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Fakultas");

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Prodi");

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Alamat");

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Jenis Kelamin");

        fieldAyah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldAyahActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Nama Ayah");

        fieldGaji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldGajiActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Gaji Ayah");

        fieldIpk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldIpkActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("IPK Mahasiswa");

        btnSimpan.setBackground(new java.awt.Color(0, 102, 51));
        btnSimpan.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setText("SIMPAN");
        btnSimpan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 51), 2));
        btnSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSimpan.setIconTextGap(10);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnUbah.setBackground(new java.awt.Color(0, 102, 51));
        btnUbah.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnUbah.setForeground(new java.awt.Color(255, 255, 255));
        btnUbah.setText("UBAH");
        btnUbah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 51), 2));
        btnUbah.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnUbah.setIconTextGap(10);
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setBackground(new java.awt.Color(0, 102, 51));
        btnHapus.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setText("HAPUS");
        btnHapus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 51), 2));
        btnHapus.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnHapus.setIconTextGap(10);
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Data Calon Penerima Beasiswa");

        jLabel17.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Input Data Calon Penerima Beasiswa");

        tabelData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelDataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelData);

        jLabel23.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Agama");

        jLabel24.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Nama");

        fieldNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldNamaActionPerformed(evt);
            }
        });

        btnLaporan.setBackground(new java.awt.Color(0, 102, 51));
        btnLaporan.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnLaporan.setForeground(new java.awt.Color(255, 255, 255));
        btnLaporan.setText("Cetak Laporan");
        btnLaporan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 51), 2));
        btnLaporan.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLaporan.setIconTextGap(10);
        btnLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaporanActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(0, 102, 51));
        btnReset.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("RESET");
        btnReset.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 51), 2));
        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnReset.setIconTextGap(10);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/header5.png"))); // NOI18N

        fieldCari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                fieldCariMouseReleased(evt);
            }
        });
        fieldCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldCariActionPerformed(evt);
            }
        });
        fieldCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldCariKeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Cari data");

        fieldprodi.setForeground(new java.awt.Color(255, 255, 255));
        fieldprodi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Teknologi Informasi", "Teknik Sipil", "Hukum", "Psikologi", "Sastra Jawa", "Bahasa indonesia", "Bahasa Inggris" }));
        fieldprodi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldprodiActionPerformed(evt);
            }
        });

        fieldFakultas.setForeground(new java.awt.Color(255, 255, 255));
        fieldFakultas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pendidikan", "Ilmu sosial", "Teknik" }));
        fieldFakultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldFakultasActionPerformed(evt);
            }
        });

        fieldAlamat.setColumns(20);
        fieldAlamat.setRows(5);
        jScrollPane2.setViewportView(fieldAlamat);

        fieldAgama.setForeground(new java.awt.Color(255, 255, 255));
        fieldAgama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Islam", "Kristen", "katholik", "Hindu", "Budha" }));
        fieldAgama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldAgamaActionPerformed(evt);
            }
        });

        fieldJk.setForeground(new java.awt.Color(255, 255, 255));
        fieldJk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-laki", "Perempuan" }));
        fieldJk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldJkActionPerformed(evt);
            }
        });

        fieldUang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldUangActionPerformed(evt);
            }
        });

        btnCek.setBackground(new java.awt.Color(0, 102, 51));
        btnCek.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnCek.setForeground(new java.awt.Color(255, 255, 255));
        btnCek.setText("Cek Penerima Beasiswa");
        btnCek.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 51), 2));
        btnCek.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCek.setIconTextGap(10);
        btnCek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DataPanelLayout = new javax.swing.GroupLayout(DataPanel);
        DataPanel.setLayout(DataPanelLayout);
        DataPanelLayout.setHorizontalGroup(
            DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addGroup(DataPanelLayout.createSequentialGroup()
                .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DataPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addGap(407, 407, 407))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DataPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DataPanelLayout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fieldCari)
                                .addGap(315, 315, 315)
                                .addComponent(btnLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(btnCek, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1)))
                    .addGroup(DataPanelLayout.createSequentialGroup()
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DataPanelLayout.createSequentialGroup()
                                .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(DataPanelLayout.createSequentialGroup()
                                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DataPanelLayout.createSequentialGroup()
                                                .addGap(151, 151, 151)
                                                .addComponent(fieldUang, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(DataPanelLayout.createSequentialGroup()
                                                .addGap(88, 88, 88)
                                                .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel24)
                                                    .addComponent(jLabel10)
                                                    .addComponent(jLabel9)
                                                    .addComponent(jLabel8)
                                                    .addComponent(jLabel11))
                                                .addGap(49, 49, 49)
                                                .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(fieldNama)
                                                    .addComponent(fieldprodi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(fieldFakultas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(fieldNim, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel23)))
                                    .addGroup(DataPanelLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel17)
                                        .addGap(7, 7, 7)))
                                .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fieldAyah)
                                    .addComponent(fieldGaji)
                                    .addComponent(fieldIpk)
                                    .addComponent(fieldAgama, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DataPanelLayout.createSequentialGroup()
                                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(fieldJk, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DataPanelLayout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel19)))
                        .addGap(54, 54, 54)))
                .addContainerGap())
        );
        DataPanelLayout.setVerticalGroup(
            DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addGap(27, 27, 27)
                .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(DataPanelLayout.createSequentialGroup()
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(fieldJk, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldAyah, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldGaji, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldIpk, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(fieldAgama, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(DataPanelLayout.createSequentialGroup()
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldNim, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldNama, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGap(18, 18, 18)
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(fieldFakultas, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(fieldprodi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DataPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11))
                            .addGroup(DataPanelLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(fieldUang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldCari, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(btnLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCek, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(427, Short.MAX_VALUE))
        );

        mainPanel.add(DataPanel, "card4");

        SyaratPanel.setBackground(new java.awt.Color(0, 0, 102));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/header2.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("DIN Alternate", 1, 17)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("A. Syarat dan ketentuan mahasiswa yang menerima beasiswa :");

        jLabel18.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("1. Harus mahasiswa aktif atau sedang menempuh jenjang perkuliahan");

        jLabel25.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("2. Mahasiswa belum menerima beasiswa");

        jLabel26.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("3. Mahasiswa minimal harus memiliki ipk minimal 2.5");

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setFont(new java.awt.Font("DIN Alternate", 1, 17)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("B. Kriteria dan jumlah uang beasiswa");

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("1. Beasiswa Penghargaan      : Rp. 5.000.0000");

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("2. Beasiswa Bantuan             : Rp. 4.000.0000");

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("3. Beasiswa Non akademik   : Rp. 3.000.000 ");

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("4. Beasiswa Prestasi              : Rp. 4.500.000");

        javax.swing.GroupLayout SyaratPanelLayout = new javax.swing.GroupLayout(SyaratPanel);
        SyaratPanel.setLayout(SyaratPanelLayout);
        SyaratPanelLayout.setHorizontalGroup(
            SyaratPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SyaratPanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(SyaratPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel30)
                    .addComponent(jLabel29)
                    .addComponent(jLabel28)
                    .addComponent(jLabel27)
                    .addComponent(jLabel26)
                    .addComponent(jLabel25)
                    .addComponent(jLabel18)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        SyaratPanelLayout.setVerticalGroup(
            SyaratPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SyaratPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(46, 46, 46)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(jLabel25)
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addGap(74, 74, 74)
                .addComponent(jLabel27)
                .addGap(43, 43, 43)
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addComponent(jLabel30)
                .addGap(18, 18, 18)
                .addComponent(jLabel31)
                .addContainerGap(650, Short.MAX_VALUE))
        );

        mainPanel.add(SyaratPanel, "card4");

        dataPenerima.setBackground(new java.awt.Color(0, 0, 102));

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/header4.png"))); // NOI18N

        tabelBeasiswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tabelBeasiswa);

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 25)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Data Penerima beasiswa");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cari Data");

        SearchData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchDataActionPerformed(evt);
            }
        });
        SearchData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchDataKeyReleased(evt);
            }
        });

        btnPrintData.setBackground(new java.awt.Color(0, 102, 51));
        btnPrintData.setForeground(new java.awt.Color(255, 255, 255));
        btnPrintData.setText("Cetak Laporan");
        btnPrintData.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 1, true));
        btnPrintData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintDataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dataPenerimaLayout = new javax.swing.GroupLayout(dataPenerima);
        dataPenerima.setLayout(dataPenerimaLayout);
        dataPenerimaLayout.setHorizontalGroup(
            dataPenerimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPenerimaLayout.createSequentialGroup()
                .addGroup(dataPenerimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dataPenerimaLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel22))
                    .addGroup(dataPenerimaLayout.createSequentialGroup()
                        .addGap(401, 401, 401)
                        .addComponent(jLabel4))
                    .addGroup(dataPenerimaLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(dataPenerimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 978, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(dataPenerimaLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SearchData, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPrintData, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        dataPenerimaLayout.setVerticalGroup(
            dataPenerimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPenerimaLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel22)
                .addGap(53, 53, 53)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(dataPenerimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(SearchData, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrintData, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(741, Short.MAX_VALUE))
        );

        mainPanel.add(dataPenerima, "card5");

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:

        // remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        // add panel
        mainPanel.add(homePanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnSyaratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSyaratActionPerformed
        // TODO add your handling code here:
        // remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        // add panel
        mainPanel.add(SyaratPanel);
        mainPanel.repaint();
        mainPanel.revalidate();

    }//GEN-LAST:event_btnSyaratActionPerformed

    private void btnDataPenerimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataPenerimaActionPerformed
        // TODO add your handling code here:

        // remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        // add panel
        mainPanel.add(dataPenerima);
        mainPanel.repaint();
        mainPanel.revalidate();

        getDataPenerimaBeasiswa();

    }//GEN-LAST:event_btnDataPenerimaActionPerformed

    private void btnAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAboutActionPerformed
        // TODO add your handling code here:
        AboutView n = new AboutView();
        n.setVisible(true);

    }//GEN-LAST:event_btnAboutActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:

        int dialogBtn = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Anda yakin akan keluar?", "PERINGATAN", dialogBtn);

        if (dialogResult == 0) {
            // true condition 
            System.exit(0);
        } else {
            // false condition 
        }

    }//GEN-LAST:event_btnLogoutActionPerformed

    private void fieldNimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldNimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldNimActionPerformed

    private void fieldAyahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldAyahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldAyahActionPerformed

    private void fieldGajiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldGajiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldGajiActionPerformed

    private void fieldIpkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldIpkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldIpkActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        simpan();
        //   dataBeasiswa();
        //dataPenerimaBeasiswa();
        kosongkan_form();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        ubah();
        kosongkan_form();
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        int dialogBtn = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Anda yakin akan menghapus data?", "PERINGATAN", dialogBtn);

        if (dialogResult == 0) {
            // true condition 
            hapus();
            JOptionPane.showMessageDialog(this, "Data Berhasil Di Hapus...");
        } else {
            // false condition 
            menampilkan_data();
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void fieldNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldNamaActionPerformed

    private void tabelDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelDataMouseClicked
        // TODO add your handling code here:
        int baris = tabelData.rowAtPoint(evt.getPoint());
        String nim = tabelData.getValueAt(baris, 1).toString();
        fieldNim.setText(nim);
        String nama = tabelData.getValueAt(baris, 2).toString();
        fieldNama.setText(nama);
//        String fakultas = tabelData.getValueAt(baris, 3).toString();
//        fieldFakultas.setSelectedItem(fakultas);
        String prodi = tabelData.getValueAt(baris, 3).toString();
        fieldprodi.setSelectedItem(prodi);
        String alamat = tabelData.getValueAt(baris, 4).toString();
        fieldAlamat.setText(alamat);
        String jenis_kelamin = tabelData.getValueAt(baris, 5).toString();
        fieldJk.setSelectedItem(jenis_kelamin);
        String nama_ayah = tabelData.getValueAt(baris, 6).toString();
        fieldAyah.setText(nama_ayah);
        String gaji_ayah = tabelData.getValueAt(baris, 7).toString();
        fieldGaji.setText(gaji_ayah);
        String ipk = tabelData.getValueAt(baris, 8).toString();
        fieldIpk.setText(ipk);
        String agama = tabelData.getValueAt(baris, 9).toString();
        fieldAgama.setSelectedItem(agama);

    }//GEN-LAST:event_tabelDataMouseClicked

    private void btnLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaporanActionPerformed

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beasiswa", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.io.File namaFile = new java.io.File("./src/laporan/dataMahasiswa.jasper");
        try {
            net.sf.jasperreports.engine.JasperReport jasper;
            jasper = (net.sf.jasperreports.engine.JasperReport) net.sf.jasperreports.engine.util.JRLoader.loadObject(namaFile.getPath());
            net.sf.jasperreports.engine.JasperPrint jp;
            jp = net.sf.jasperreports.engine.JasperFillManager.fillReport(jasper, null, conn);
            net.sf.jasperreports.view.JasperViewer.viewReport(jp, false);
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(rootPane, ex.getMessage());
//            System.out.println(ex);
        }

    }//GEN-LAST:event_btnLaporanActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        kosongkan_form();
    }//GEN-LAST:event_btnResetActionPerformed


    private void fieldCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldCariActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_fieldCariActionPerformed

    private void btnDataCalonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataCalonActionPerformed
        // TODO add your handling code here:
        // remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        // add panel
        mainPanel.add(DataPanel);
        mainPanel.repaint();
        mainPanel.revalidate();

        menampilkan_data();
    }//GEN-LAST:event_btnDataCalonActionPerformed

    private void syaratnavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_syaratnavMouseClicked
        // TODO add your handling code here:
        // remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        // add panel
        mainPanel.add(SyaratPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_syaratnavMouseClicked

    private void calonNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calonNavMouseClicked
        // TODO add your handling code here:
        // remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        // add panel
        mainPanel.add(DataPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_calonNavMouseClicked

    private void penerimaNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_penerimaNavMouseClicked
        // TODO add your handling code here:
        // remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        // add panel
        mainPanel.add(dataPenerima);
        mainPanel.repaint();
        mainPanel.revalidate();

        getDataPenerimaBeasiswa();
    }//GEN-LAST:event_penerimaNavMouseClicked

    private void aboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutMouseClicked
        // TODO add your handling code here:
        AboutView n = new AboutView();
        n.setVisible(true);
    }//GEN-LAST:event_aboutMouseClicked

    private void syaratnavMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_syaratnavMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_syaratnavMousePressed

    private void fieldprodiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldprodiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldprodiActionPerformed

    private void fieldFakultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldFakultasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldFakultasActionPerformed

    private void fieldAgamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldAgamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldAgamaActionPerformed

    private void fieldJkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldJkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldJkActionPerformed

    private void fieldCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldCariKeyReleased
        // TODO add your handling code here:
        cariData();
    }//GEN-LAST:event_fieldCariKeyReleased

    private void fieldCariMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fieldCariMouseReleased
        // TODO add your handling code here:
        cariData();
    }//GEN-LAST:event_fieldCariMouseReleased

    private void SearchDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchDataActionPerformed

    private void SearchDataKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchDataKeyReleased
        // TODO add your handling code here:
        cariDataPenerimaBeasiswa();

    }//GEN-LAST:event_SearchDataKeyReleased

    private void fieldUangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldUangActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_fieldUangActionPerformed

    private void btnCekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekActionPerformed
        // TODO add your handling code here:
        // remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        // add panel
        mainPanel.add(dataPenerima);
        mainPanel.repaint();
        mainPanel.revalidate();

        getDataPenerimaBeasiswa();
    }//GEN-LAST:event_btnCekActionPerformed

    private void btnPrintDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintDataActionPerformed
        // TODO add your handling code here:
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SI-penerimaanbeasiswa", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.io.File namaFile = new java.io.File("./src/laporan/penerimaBeasiswa.jasper");
        try {
            net.sf.jasperreports.engine.JasperReport jasper;
            jasper = (net.sf.jasperreports.engine.JasperReport) net.sf.jasperreports.engine.util.JRLoader.loadObject(namaFile.getPath());
            net.sf.jasperreports.engine.JasperPrint jp;
            jp = net.sf.jasperreports.engine.JasperFillManager.fillReport(jasper, null, conn);
            net.sf.jasperreports.view.JasperViewer.viewReport(jp, false);
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(rootPane, ex.getMessage());
//            System.out.println(ex);
        }
    }//GEN-LAST:event_btnPrintDataActionPerformed

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
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DataPanel;
    private javax.swing.JTextField SearchData;
    private javax.swing.JPanel SyaratPanel;
    private javax.swing.JLabel about;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JButton btnAbout;
    private javax.swing.JButton btnCek;
    private javax.swing.JButton btnDataCalon;
    private javax.swing.JButton btnDataPenerima;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLaporan;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPrintData;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnSyarat;
    private javax.swing.JButton btnUbah;
    private javax.swing.JLabel calonNav;
    private javax.swing.JPanel dataPenerima;
    private javax.swing.JComboBox<String> fieldAgama;
    private javax.swing.JTextArea fieldAlamat;
    private javax.swing.JTextField fieldAyah;
    private javax.swing.JTextField fieldCari;
    private javax.swing.JComboBox<String> fieldFakultas;
    private javax.swing.JTextField fieldGaji;
    private javax.swing.JTextField fieldIpk;
    private javax.swing.JComboBox<String> fieldJk;
    private javax.swing.JTextField fieldNama;
    private javax.swing.JTextField fieldNim;
    private javax.swing.JTextField fieldUang;
    private javax.swing.JComboBox<String> fieldprodi;
    private javax.swing.JPanel homePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JLabel penerimaNav;
    private javax.swing.JLabel syaratnav;
    private javax.swing.JTable tabelBeasiswa;
    private javax.swing.JTable tabelData;
    // End of variables declaration//GEN-END:variables

}
