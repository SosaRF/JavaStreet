package cz.isgastreet.gui;

import cz.isgastreet.core.ApiConnect;
import cz.isgastreet.core.NameIdObj;
import cz.isgastreet.core.SelectBehave;
import cz.isgastreet.core.StreetObj;
import cz.isgastreet.core.Tools;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Marco Schulz
 */
public class FrameStreet extends JFrame
{

    private boolean deu = false;
    private final DefaultListModel modelbund = new DefaultListModel();
    private final DefaultListModel modelkreis = new DefaultListModel();
    private final DefaultListModel modelplz = new DefaultListModel();
    private final DefaultListModel modelausgabe = new DefaultListModel();

    private final ApiConnect api = new ApiConnect();

    private static final ImageIcon LOGO = new ImageIcon(FrameStreet.class.getResource("/images/doc_small.png"));

    private int searchLevel = 4;

    public FrameStreet()
    {
        createBundeslander();
        initComponents();
        startSetup();
        listen();
        buttons();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        panMain = new javax.swing.JPanel();
        mainTabPane = new javax.swing.JTabbedPane();
        auswahlTab = new javax.swing.JPanel();
        auswahlTabPane = new javax.swing.JTabbedPane();
        bundPane = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jBundList = new javax.swing.JList<>();
        kreisPane = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jKreisList = new javax.swing.JList<>();
        plzPane = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPlzList = new javax.swing.JList<>();
        jLoading = new javax.swing.JProgressBar();
        ausgabeTab = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        ausgabeListe = new javax.swing.JList<>();
        panBtn = new cz.isgastreet.gui.PanBtn();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(60, 63, 65));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(LOGO.getImage());
        setMinimumSize(new java.awt.Dimension(640, 700));
        setName("mainFrame"); // NOI18N

        mainTabPane.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mainTabPane.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        mainTabPane.setMinimumSize(new java.awt.Dimension(164, 250));
        mainTabPane.setPreferredSize(new java.awt.Dimension(591, 529));

        auswahlTab.setLayout(new java.awt.BorderLayout());

        auswahlTabPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        bundPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Bundesland"));
        bundPane.setPreferredSize(new java.awt.Dimension(1032, 150));
        bundPane.setLayout(new java.awt.BorderLayout());

        jBundList.setModel(modelbund);
        jScrollPane3.setViewportView(jBundList);

        bundPane.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        auswahlTabPane.addTab("Bundesland", bundPane);

        kreisPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Landkreis"));
        kreisPane.setPreferredSize(new java.awt.Dimension(1032, 150));
        kreisPane.setLayout(new java.awt.BorderLayout());

        jKreisList.setModel(modelkreis);
        jScrollPane4.setViewportView(jKreisList);

        kreisPane.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        auswahlTabPane.addTab("Landkreis", kreisPane);

        plzPane.setBorder(javax.swing.BorderFactory.createTitledBorder("PLZ Bereiche"));
        plzPane.setPreferredSize(new java.awt.Dimension(1032, 150));
        plzPane.setLayout(new java.awt.BorderLayout());

        jPlzList.setModel(modelplz);
        jScrollPane5.setViewportView(jPlzList);

        plzPane.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        auswahlTabPane.addTab("PLZ / Ort", plzPane);

        auswahlTab.add(auswahlTabPane, java.awt.BorderLayout.CENTER);

        jLoading.setMaximum(61);
        auswahlTab.add(jLoading, java.awt.BorderLayout.PAGE_END);

        mainTabPane.addTab("Auswahl", auswahlTab);

        ausgabeTab.setBackground(new java.awt.Color(255, 255, 255));
        ausgabeTab.setLayout(new java.awt.BorderLayout());

        ausgabeListe.setModel(modelausgabe);
        scrollPane.setViewportView(ausgabeListe);

        ausgabeTab.add(scrollPane, java.awt.BorderLayout.CENTER);

        mainTabPane.addTab("Ausgabe", ausgabeTab);

        javax.swing.GroupLayout panMainLayout = new javax.swing.GroupLayout(panMain);
        panMain.setLayout(panMainLayout);
        panMainLayout.setHorizontalGroup(
            panMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainTabPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panMainLayout.createSequentialGroup()
                .addComponent(panBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 1036, Short.MAX_VALUE)
                .addContainerGap())
        );
        panMainLayout.setVerticalGroup(
            panMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panMainLayout.createSequentialGroup()
                .addComponent(mainTabPane, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mainTabPane.getAccessibleContext().setAccessibleName("");

        getContentPane().add(panMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Methode zur Steuerung der Button Funktionen
     */
    private void buttons()
    {
        panBtn.setBottonLineModus(PanBtn.ModusBottonLine.MODUS_BUND);
        panBtn.setButtonMethods(new PanBtn.Action()
        {
            @Override
            public void weiter()
            {
                switch (searchLevel)
                {
                    case 4:
                        if (jBundList.getSelectedIndices().length < 1)
                        {
                            Tools.fehlermeldung("Es muss mindestens ein Bundesland Ausgewählt werden.");
                            return;
                        }
                        panBtn.setBottonLineModus(PanBtn.ModusBottonLine.MODUS_LOADON);
                        weiterBtnFunc(6, 1, 0, api.getLvl4List(), api.getLvl6List(), modelkreis, jBundList, jBundList, bundPane);
                        panBtn.setBottonLineModus(PanBtn.ModusBottonLine.MODUS_KREIS);
                        break;

                    case 6:
                        if (jKreisList.getSelectedIndices().length < 1)
                        {
                            Tools.fehlermeldung("Es muss mindestens ein Landkreis Ausgewählt werden.");
                            return;
                        }
                        panBtn.setBottonLineModus(PanBtn.ModusBottonLine.MODUS_LOADON);
                        weiterBtnFunc(7, 2, 1, api.getLvl6List(), api.getPlzList(), modelplz, jKreisList, jKreisList, kreisPane);
                        panBtn.setBottonLineModus(PanBtn.ModusBottonLine.MODUS_ORT);
                        break;

                    case 7:
                        if (jPlzList.getSelectedIndices().length < 1)
                        {
                            Tools.fehlermeldung("Es muss mindestens ein Ort Ausgewählt werden.");
                            return;
                        }
                        panBtn.setBottonLineModus(PanBtn.ModusBottonLine.MODUS_LOADON);
                        ausgabeBtnFunc(10, 1, 0, api.getLvl8List(), api.getStreetList(), modelausgabe, jPlzList, jPlzList, plzPane);
                        panBtn.setBottonLineModus(PanBtn.ModusBottonLine.MODUS_AUSGABE);
                        break;

                    case 10:
                        csvBtnFunc();
                        break;
                }

            }

            @Override
            public void zuruck()
            {
                switch (searchLevel)
                {
                    case 6:
                        panBtn.setBottonLineModus(PanBtn.ModusBottonLine.MODUS_BUND);
                        zuruckBtnFunc(4, 0, 1, modelkreis, 6, jBundList, bundPane);
                        break;

                    case 7:
                        panBtn.setBottonLineModus(PanBtn.ModusBottonLine.MODUS_KREIS);
                        zuruckBtnFunc(6, 1, 2, modelplz, 7, jKreisList, kreisPane);
                        break;

                    case 10:
                        if (deu)
                        {
                            panBtn.setBottonLineModus(PanBtn.ModusBottonLine.MODUS_BUND);
                            mainTabPane.setEnabledAt(1, false);
                            mainTabPane.setEnabledAt(0, true);
                            mainTabPane.setSelectedIndex(0);
                            zuruckBtnFunc(4, 0, 1, modelausgabe, 0, jBundList, bundPane);
                            deu = false;
                        } else
                        {
                            panBtn.setBottonLineModus(PanBtn.ModusBottonLine.MODUS_ORT);
                            mainTabPane.setEnabledAt(1, false);
                            mainTabPane.setEnabledAt(0, true);
                            mainTabPane.setSelectedIndex(0);
                            zuruckBtnFunc(7, 2, 0, modelausgabe, 10, jPlzList, plzPane);
                        }
                        break;
                }
            }

            @Override
            public void auswahl()
            {
                switch (searchLevel)
                {
                    case 4:
                        int bestaetigen = JOptionPane.showConfirmDialog(null, "Alle Straßen Deutschlands auszugeben kann mehr als 10 Stunden in anspruch nehmen."
                                + "\nDie Internetverbindung sollte in dieser Zeit nicht unterbrochen werden."
                                + "\n\nFortfahren? ", "Ausgabe Bestätigen",
                                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        switch (bestaetigen)
                        {
                            case JOptionPane.NO_OPTION:
                                return;
                            case JOptionPane.CLOSED_OPTION:
                                return;
                            default:
                                break;
                        }
                        deu = true;
                        panBtn.setBottonLineModus(PanBtn.ModusBottonLine.MODUS_LOADON);
                        deutschlandBtnFunc();
                        panBtn.setBottonLineModus(PanBtn.ModusBottonLine.MODUS_AUSGABE);
                        break;

                    case 6:
                        selectBtnFunc(jKreisList);
                        break;

                    case 7:
                        selectBtnFunc(jPlzList);
                        break;

                }
            }
        });
    }

    /**
     * Methode Zur Funktion des Weiter Buttons
     *
     * @param lvl
     * @param an
     * @param aus
     * @param liste1
     * @param liste2
     * @param model
     * @param jListe
     */
    private void weiterBtnFunc(int lvl, int an, int aus, List<NameIdObj> liste1, List<NameIdObj> liste2, DefaultListModel model, JList jListe, JList listDisable, JPanel panelDisable)
    {
        searchLevel = lvl;
        jLoading.setVisible(true);
        jLoading.setMaximum(jListe.getSelectedIndices().length);
        listDisable.setEnabled(false);
        panelDisable.setEnabled(false); 
        int[] selected = jListe.getSelectedIndices();
        for (int i = 0; i < selected.length; i++)
        {
            api.connectAuswahl(liste1.get(selected[i]), searchLevel);
            jLoading.setValue(i);
        } 

        if (api.getablageList().isEmpty() == false)
        {
            for (NameIdObj nameIdObj : api.getablageList())
            {
                liste2.add(nameIdObj);
            }
            api.clearAblageListe();
        }

        if (lvl == 7)
        {
            for (NameIdObj nameIdObj : api.getPlzList())
            {
                if (nameIdObj.getSpeicher().contains(",") || nameIdObj.getSpeicher().contains("u.a."))
                {
                    api.connectAuswahl(nameIdObj, 8);
                } else
                {
                    api.setLvl8List(nameIdObj);
                }
            }

            api.getLvl8List().sort(Comparator.comparing(NameIdObj::getName));

            for (NameIdObj nameIdObj : api.getLvl8List())
            {
                model.addElement(nameIdObj);
            }

            auswahlTabPane.setEnabledAt(aus, false);
            auswahlTabPane.setEnabledAt(an, true);
            auswahlTabPane.setSelectedIndex(an);
        } else
        {
            liste2.sort(Comparator.comparing(NameIdObj::getName));

            for (NameIdObj nameIdObj : liste2)
            {
                model.addElement(nameIdObj);
            }

            auswahlTabPane.setEnabledAt(aus, false);
            auswahlTabPane.setEnabledAt(an, true);
            auswahlTabPane.setSelectedIndex(an);
        }
        jLoading.setVisible(false);
    }

    /**
     * Methode zur Funktion des Zurück Buttons
     *
     * @param lvl
     * @param an
     * @param aus
     * @param model
     * @param clear
     */
    private void zuruckBtnFunc(int lvl, int an, int aus, DefaultListModel model, int clear, JList listEnable, JPanel panelEnable)
    {
        searchLevel = lvl;
        listEnable.setEnabled(true);
        panelEnable.setEnabled(true);
        jBundList.setEnabled(true);
        auswahlTabPane.setEnabledAt(aus, false);
        auswahlTabPane.setEnabledAt(an, true);
        auswahlTabPane.setSelectedIndex(an);
        api.clearliste(clear);
        model.clear();
    }

    /**
     * Methode zur Funktion des Ausgabe Buttons
     *
     * @param lvl
     * @param an
     * @param aus
     * @param liste1
     * @param liste2
     * @param model
     * @param jListe
     */
    private void ausgabeBtnFunc(int lvl, int an, int aus, List<NameIdObj> liste1, List<StreetObj> liste2, DefaultListModel model, JList jListe, JList listDisable, JPanel panelDisable)
    {
        jLoading.setVisible(true);
        jLoading.setMaximum(jListe.getSelectedIndices().length);
        searchLevel = lvl;
        listDisable.setEnabled(false);
        panelDisable.setEnabled(false);
        int[] selected = jListe.getSelectedIndices();

        for (int i = 0; i < selected.length; i++)
        {
            api.connectAuswahl(liste1.get(selected[i]), searchLevel);
            jLoading.setValue(i);
        }

        Set<StreetObj> unique = new HashSet<>(liste2);
        liste2.clear();
        liste2.addAll(unique);
        liste2.sort(Comparator.comparing(StreetObj::getOrt));

        for (StreetObj streetObj : liste2)
        {
            model.addElement(streetObj);
        }

        mainTabPane.setEnabledAt(aus, false);
        mainTabPane.setEnabledAt(an, true);
        mainTabPane.setSelectedIndex(an);
        jLoading.setVisible(false);

    }

    /**
     * methode zur Funktion des CSVAusgabe Buttons
     */
    private void csvBtnFunc()
    {
        try
        {
            Tools.writeFile(api.getStreetList());
        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    /**
     * Methode zur Funktion das Deutschland komplett Ausgabe Buttons Alle
     * Zwichen abfrage lvl werden hier automatisch nacheinander abgearbeitet
     */
    private void deutschlandBtnFunc()
    {
        jLoading.setVisible(true);
        jLoading.setMaximum(8832); // schätzwert der gesamt abfragen der sich aus test ergeben hat 
        
        Runnable runnable = new Runnable() 
        {
            @Override
            public void run()
            {
                while (true)
                {
                    jLoading.setValue(api.getAbfragezaehler());
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        bundPane.setEnabled(false);
        jBundList.setEnabled(false);

        for (NameIdObj nameIdObj : api.getLvl4List())
        {
            api.connectAuswahl(nameIdObj, 6);
        }

        if (api.getablageList().isEmpty() == false)
        {
            for (NameIdObj nameIdObj : api.getablageList())
            {
                api.getLvl6List().add(nameIdObj);
            }

            api.clearAblageListe();
        }

        for (NameIdObj nameIdObj : api.getLvl6List())
        {
            api.connectAuswahl(nameIdObj, 7);
        }

        for (NameIdObj nameIdObj : api.getPlzList())
        {
            if (nameIdObj.getSpeicher().contains(",") || nameIdObj.getSpeicher().contains("u.a."))
            {
                api.connectAuswahl(nameIdObj, 8);
            } else
            {
                api.setLvl8List(nameIdObj);
            }
        }

        for (NameIdObj nameIdObj : api.getLvl8List())
        {
            api.connectAuswahl(nameIdObj, 10);
        }

        Set<StreetObj> unique = new HashSet<>(api.getStreetList());
        api.getStreetList().clear();
        api.getStreetList().addAll(unique);
        api.getStreetList().sort(Comparator.comparing(StreetObj::getOrt));

        for (StreetObj streetObj : api.getStreetList())
        {
            modelausgabe.addElement(streetObj);
        }
        mainTabPane.setEnabledAt(0, false);
        mainTabPane.setEnabledAt(1, true);
        mainTabPane.setSelectedIndex(1);
        searchLevel = 10;
    }

    /**
     * Methode Zur Funktion des Alles Auswählen Buttons Wählt alle einträge der
     * aktuell angezeigten liste aus
     *
     * @param jListe
     */
    private void selectBtnFunc(JList jListe)
    {
        int start = 0;
        int end = jListe.getModel().getSize() - 1;

        if (end >= 0)
        {
            jListe.setSelectionInterval(start, end);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> ausgabeListe;
    private javax.swing.JPanel ausgabeTab;
    private javax.swing.JPanel auswahlTab;
    private javax.swing.JTabbedPane auswahlTabPane;
    private javax.swing.JPanel bundPane;
    private javax.swing.JList<String> jBundList;
    private javax.swing.JList<String> jKreisList;
    private javax.swing.JProgressBar jLoading;
    private javax.swing.JList<String> jPlzList;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel kreisPane;
    private javax.swing.JTabbedPane mainTabPane;
    private cz.isgastreet.gui.PanBtn panBtn;
    private javax.swing.JPanel panMain;
    private javax.swing.JPanel plzPane;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables

    //Startbedingungen der Tabs
    private void startSetup()
    {
        jLoading.setVisible(false);
        mainTabPane.setEnabledAt(1, false);
        auswahlTabPane.setSelectedIndex(0);
        auswahlTabPane.setEnabledAt(1, false);
        auswahlTabPane.setEnabledAt(2, false);
    }

    //definition listenverhalten
    private void listen()
    {

        SelectBehave bundBehave = new SelectBehave();
        jBundList.setSelectionModel(bundBehave);
        SelectBehave kreisBehave = new SelectBehave();
        jKreisList.setSelectionModel(kreisBehave);
        SelectBehave ortBehave = new SelectBehave();
        jPlzList.setSelectionModel(ortBehave);

        for (NameIdObj name : api.getLvl4List())
        {
            modelbund.addElement(name);
        }

    }

    //da z.z. nur deutschland -> vordefinierte Bundesländer um Abfragezeit zu Vermeiden
    private void createBundeslander()
    {
        api.setLvl4List("Baden-Württemberg", "62611", "", "");
        api.setLvl4List("Bayern", "2145268", "", "");
        api.setLvl4List("Berlin", "62422", "", "11000000");
        api.setLvl4List("Brandenburg", "62504", "", "");
        api.setLvl4List("Bremen", "62718", "", "");
        api.setLvl4List("Hamburg", "62782", "", "02000000");
        api.setLvl4List("Hessen", "62650", "", "");
        api.setLvl4List("Mecklenburg-Vorpommern", "28322", "", "");
        api.setLvl4List("Niedersachsen", "62771", "", "");
        api.setLvl4List("Nordrhein-Westfalen", "62761", "", "");
        api.setLvl4List("Rheinland-Pfalz", "62341", "", "");
        api.setLvl4List("Saarland", "62372", "", "");
        api.setLvl4List("Sachsen", "62467", "", "");
        api.setLvl4List("Sachsen-Anhalt", "62607", "", "");
        api.setLvl4List("Schleswig-Holstein", "51529", "", "");
        api.setLvl4List("Thüringen", "62366", "", "");

    }
}
