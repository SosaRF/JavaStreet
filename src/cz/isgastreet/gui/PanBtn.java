/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.isgastreet.gui;

/**
 *
 * @author Marco Schulz
 */
public class PanBtn extends javax.swing.JPanel
{

    private Action buttonMethods = null;
    private ModusBottonLine modus = null;

    public PanBtn()
    {

        initComponents();

    }

    public PanBtn(Action buttonMethods)
    {
        this.buttonMethods = buttonMethods;
        initComponents();

    }

    public static interface Action
    {

        public void weiter();

        public void zuruck();

        public void auswahl();
    }

    public static enum ModusBottonLine
    {
        MODUS_BUND,
        MODUS_KREIS,
        MODUS_ORT,
        MODUS_AUSGABE,
        MODUS_LOADON,
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanelZuruck = new javax.swing.JPanel();
        jZuruckBtn = new javax.swing.JButton();
        jPanelAusgabe = new javax.swing.JPanel();
        jAuswahlBtn = new javax.swing.JButton();
        jPanelWeiter = new javax.swing.JPanel();
        jLoadingPan = new javax.swing.JPanel();
        jLoadingIcon = new javax.swing.JLabel();
        jLodingText = new javax.swing.JLabel();
        jWeiterBtn = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(600, 30));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 30));
        setLayout(new java.awt.GridLayout(1, 0));

        jPanelZuruck.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jZuruckBtn.setText("Zurück");
        jZuruckBtn.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jZuruckBtn.setMinimumSize(new java.awt.Dimension(80, 40));
        jZuruckBtn.setPreferredSize(null);
        jZuruckBtn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jZuruckBtnActionPerformed(evt);
            }
        });
        jPanelZuruck.add(jZuruckBtn);

        add(jPanelZuruck);

        jAuswahlBtn.setText("Alle auswählen");
        jAuswahlBtn.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jAuswahlBtn.setMinimumSize(new java.awt.Dimension(150, 40));
        jAuswahlBtn.setPreferredSize(null);
        jAuswahlBtn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jAuswahlBtnActionPerformed(evt);
            }
        });
        jPanelAusgabe.add(jAuswahlBtn);

        add(jPanelAusgabe);

        jPanelWeiter.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLoadingPan.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLoadingIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/loader.gif"))); // NOI18N
        jLoadingPan.add(jLoadingIcon);

        jLodingText.setText("Daten werden abgerufen...");
        jLoadingPan.add(jLodingText);

        jPanelWeiter.add(jLoadingPan);
        jLoadingPan.setVisible(false);

        jWeiterBtn.setText("Weiter");
        jWeiterBtn.setAlignmentX(0.5F);
        jWeiterBtn.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jWeiterBtn.setMinimumSize(new java.awt.Dimension(80, 40));
        jWeiterBtn.setPreferredSize(null);
        jWeiterBtn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jWeiterBtnActionPerformed(evt);
            }
        });
        jPanelWeiter.add(jWeiterBtn);

        add(jPanelWeiter);
    }// </editor-fold>//GEN-END:initComponents

    public void setButtonMethods(Action buttonMethods)
    {
        this.buttonMethods = buttonMethods;
    }

    public void setBottonLineModus(ModusBottonLine modus)
    {

        this.modus = modus;
        if (modus != null)
        {
            switch (modus)
            {

                case MODUS_BUND:
                    jWeiterBtn.setText("Weiter");
                    jWeiterBtn.setEnabled(true);
                    jAuswahlBtn.setEnabled(true);
                    jZuruckBtn.setVisible(false);
                    jAuswahlBtn.setVisible(true);
                    jAuswahlBtn.setText("Deutschland Ausgeben");
                    break;

                case MODUS_KREIS:
                    jLoadingPan.setVisible(false);
                    jWeiterBtn.setText("Weiter");
                    jAuswahlBtn.setText("Alle auswählen");
                    jZuruckBtn.setVisible(true);
                    jAuswahlBtn.setVisible(true);
                    jWeiterBtn.setEnabled(true);
                    jZuruckBtn.setEnabled(true);
                    jAuswahlBtn.setEnabled(true);
                    break;

                case MODUS_ORT:
                    jLoadingPan.setVisible(false);
                    jWeiterBtn.setText("Straßen ausgaben");
                    jAuswahlBtn.setVisible(true);
                    jWeiterBtn.setEnabled(true);
                    jZuruckBtn.setEnabled(true);
                    jAuswahlBtn.setEnabled(true);
                    break;

                case MODUS_AUSGABE:
                    jZuruckBtn.setVisible(true);
                    jLoadingPan.setVisible(false);
                    jWeiterBtn.setText("Als CSV Speichern");
                    jAuswahlBtn.setVisible(false);
                    jWeiterBtn.setEnabled(true);
                    jZuruckBtn.setEnabled(true);
                    break;

                case MODUS_LOADON:
                    jLoadingPan.setVisible(true);
                    jWeiterBtn.setEnabled(false);
                    jZuruckBtn.setEnabled(false);
                    jAuswahlBtn.setEnabled(false);
                    break;
                    
                default:
                    jWeiterBtn.setEnabled(false);
                    jZuruckBtn.setEnabled(false);
                    jAuswahlBtn.setEnabled(false);
                    break;
            }
        }
    }

    private void jWeiterBtnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jWeiterBtnActionPerformed
    {//GEN-HEADEREND:event_jWeiterBtnActionPerformed

        Thread thread = new Thread(() ->
        {
            if (this.buttonMethods != null)
            {
                this.buttonMethods.weiter();
            }
        });
        thread.start();

    }//GEN-LAST:event_jWeiterBtnActionPerformed

    private void jZuruckBtnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jZuruckBtnActionPerformed
    {//GEN-HEADEREND:event_jZuruckBtnActionPerformed
        if (this.buttonMethods != null)
        {
            this.buttonMethods.zuruck();
        }
    }//GEN-LAST:event_jZuruckBtnActionPerformed

    private void jAuswahlBtnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jAuswahlBtnActionPerformed
    {//GEN-HEADEREND:event_jAuswahlBtnActionPerformed
        Thread thread = new Thread(() ->
        {
            if (this.buttonMethods != null)
            {
                this.buttonMethods.auswahl();
            }
        });
        thread.start();
    }//GEN-LAST:event_jAuswahlBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAuswahlBtn;
    private javax.swing.JLabel jLoadingIcon;
    private javax.swing.JPanel jLoadingPan;
    private javax.swing.JLabel jLodingText;
    private javax.swing.JPanel jPanelAusgabe;
    private javax.swing.JPanel jPanelWeiter;
    private javax.swing.JPanel jPanelZuruck;
    private javax.swing.JButton jWeiterBtn;
    private javax.swing.JButton jZuruckBtn;
    // End of variables declaration//GEN-END:variables
}
