package programa;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.awt.event.ItemEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import modelo.Pais;

public class ComboBox extends javax.swing.JFrame {

    public ComboBox() {
        initComponents();

        Pais pais = new Pais();
        DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel(pais.mostrarPaises());

        comboPaises.setModel(modeloCombo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        comboPaises = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEstados = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("País:");

        comboPaises.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboPaisesItemStateChanged(evt);
            }
        });
        comboPaises.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPaisesActionPerformed(evt);
            }
        });

        tablaEstados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID Estado", "Nombre Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaEstados);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText("Estados:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboPaises, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboPaises, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboPaisesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPaisesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboPaisesActionPerformed

    private void comboPaisesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboPaisesItemStateChanged

        //para estar seguro que algun pais de mi comboBox fue seleccionado: para eso es el if
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            //pero qué país fue seleccionado, para eso son las siguientes líneas
            Pais pais = (Pais) comboPaises.getSelectedItem();//te regreesa un objeto de tipo object y debemos pasarlo a objeto de tipo Pais

            DefaultTableModel modeloTabla = new DefaultTableModel();
            tablaEstados.setModel(modeloTabla);

            PreparedStatement ps = null;
            ResultSet rs = null;

            try {

                Conexion con = new Conexion();
                Connection conexion = con.getConnection();

                ps = conexion.prepareStatement("select idestado,nombreEstado from estados where idpais="+pais.getIdPais());
                rs = ps.executeQuery();

                modeloTabla.addColumn("ID Estado");
                modeloTabla.addColumn("Nombre Estado");

                //cuantas columnas hay exactamente en la tabla
                ResultSetMetaData rsMD = (ResultSetMetaData) rs.getMetaData();
                int cantidadColumnas = rsMD.getColumnCount();

                //estableciendo el ancho de cada una de las columnas 
                int anchos[] = {50,150};

                for (int i = 0; i < cantidadColumnas; i++) {
                    tablaEstados.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
                }

                while (rs.next()) {//mientras siga habiendo datos
                    Object fila[] = new Object[cantidadColumnas];//arreglo de cualquier tipo
                    for (int i = 0; i < cantidadColumnas; i++) {
                        fila[i] = rs.getObject(i + 1); //llenamos el arreglo
                    }
                    modeloTabla.addRow(fila);
                }

            } catch (Exception ex) {
                System.err.println("Error, " + ex);
            }
        }

    }//GEN-LAST:event_comboPaisesItemStateChanged

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
            java.util.logging.Logger.getLogger(ComboBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ComboBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ComboBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ComboBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ComboBox().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboPaises;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaEstados;
    // End of variables declaration//GEN-END:variables
}
