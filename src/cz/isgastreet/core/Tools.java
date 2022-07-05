package cz.isgastreet.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Tools
{
    /**
     * Methode zum erstellen der CSV datei
     * @param strassen
     * @throws IOException 
     */
    public static void writeFile(List<StreetObj> strassen) throws IOException
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(null);
        File file = fileChooser.getSelectedFile();
        file = new File(file.getParentFile(), file.getName() + ".CSV");
        FileWriter fw = new FileWriter(file);

        try
        {
            fw.write("STR;PLZ;ORT;GKZ");
            fw.write(System.lineSeparator());
            for (StreetObj streetObj : strassen)
            {

                fw.write(streetObj.toString());
                fw.write(System.lineSeparator());

            }
            fw.close();

        } catch (IOException e)
        {

            System.out.println(e);

        }
    }
    /**
     * Funktion zum anzeigen von falschen angeben des benutzers
     * erwartet Fehlertext
     * @param text 
     */
    public static void fehlermeldung(String text)
    {
        JOptionPane optionPane = new JOptionPane(text,JOptionPane.ERROR_MESSAGE);
        JDialog dialog = optionPane.createDialog("Ungültige Anfrage");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
}
