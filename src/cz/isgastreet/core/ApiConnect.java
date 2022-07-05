package cz.isgastreet.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marco Schulz
 */

public class ApiConnect
{
    private final List<NameIdObj> lvl4Liste = new ArrayList<>();
    private final List<NameIdObj> lvl6Liste = new ArrayList<>();
    private final List<NameIdObj> lvl8Liste = new ArrayList<>();
    private final List<NameIdObj> plzListe = new ArrayList<>();
    private final List<StreetObj> streetList = new ArrayList<>();
    private final List<NameIdObj> ablageListe = new ArrayList<>();

    int abfragezaehler;
    private boolean abstufung;

    public ApiConnect()
    {

    }

    /**
     * Über diese Methode wird die API Abfragefolge mithilfe des Suchlevels gesteuert.
     * 
     * @param obj
     * @param lvl
     */
    
    public void connectAuswahl(NameIdObj obj, int lvl)
    {
        abfragezaehler++;
        abstufung = false;
        URL url;
        

        if (lvl == 10 && obj.getKnz().length() != 8)
        {
            knzKorrektur(obj);
        }
        try
        {
            String[] commands = new String[]
            {

                "http://www.overpass-api.de/api/interpreter?data="
                + "[out:csv(\"name\",::id,\"de:amtlicher_gemeindeschluessel\";false)];area(" + areaCalc(obj.getId()) + ");"
                + "relation[\"boundary\"=\"administrative\"][\"admin_level\"=\"4\"](area);out;",
                
                "http://www.overpass-api.de/api/interpreter?data="
                + "[out:csv(\"name\",::id,\"de:amtlicher_gemeindeschluessel\";false)];area(" + areaCalc(obj.getId()) + ");"
                + "relation[\"boundary\"=\"administrative\"][\"admin_level\"=\"6\"](area);out;",
                
                "http://www.overpass-api.de/api/interpreter?data="
                + "[out:csv(\"note\",\"postal_code\",::id;false)];area(" + areaCalc(obj.getId()) + ");"
                + "relation[\"boundary\"=\"postal_code\"](area);out;",
                
                "http://www.overpass-api.de/api/interpreter?data="
                + "[out:csv(\"name\",::id,\"de:amtlicher_gemeindeschluessel\";false)];area(" + areaCalc(obj.getId()) + ");"
                + "relation[\"boundary\"=\"administrative\"][\"admin_level\"=\"8\"](area);out;",
                
                "http://www.overpass-api.de/api/interpreter?data="
                + "[out:csv(\"addr:postcode\";false)];area(" + areaCalc(obj.getId()) + ");"
                + "node[\"addr:postcode\"](area);out;",
                
                "http://www.overpass-api.de/api/interpreter?data="
                + "[out:csv(\"name\";false)];area(" + areaCalc(obj.getId()) + ");way[\"highway\"~\"residential|tertiary|secondary|primary\"][name](area);out;",

            };

            switch (lvl)
            {

                case 4:
                    url = new URL(commands[0]);
                    System.out.println(commands[0]);
                    break;

                case 6:
                    url = new URL(commands[1]);
                    System.out.println(commands[1]);
                    break;

                case 7:
                    url = new URL(commands[2]);
                    System.out.println(commands[2]);
                    break;

                case 8:
                    url = new URL(commands[3]);
                    System.out.println(commands[3]);
                    break;

                case 9:
                    url = new URL(commands[4]);
                    System.out.println(commands[4]);
                    break;

                case 10:
                    url = new URL(commands[5]);
                    System.out.println(commands[5]);
                    break;

                default:
                    url = new URL("");
                    break;
            }

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/csv");

            switch (conn.getResponseCode())
            {
                case 429:
                    return;
                case 504:
                    return;
                case 400:
                    return;
                default:
                    break;
            }
            
            if (conn.getResponseCode() != 200)
            {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());

            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()), StandardCharsets.UTF_8));

            String output;

            while ((output = br.readLine()) != null)
            {

                switch (lvl)
                {
                    case 4:
                        fall(lvl4Liste, output, obj, lvl);
                        break;

                    case 6:
                        fall(lvl6Liste, output, obj, lvl);
                        break;

                    case 7:
                        fall(plzListe, output, obj, lvl);
                        break;

                    case 8:
                        fall(lvl8Liste, output, obj, lvl);
                        break;

                    case 9:
                        fall(lvl8Liste, output, obj, lvl);
                        break;

                    case 10:
                        fall(streetList, output, obj, lvl);
                        break;
                }
                
                if (lvl == 9)//nach ersten ergebnis aus der schleife brechen da bei lvl 9 eine gefundene plz ausreicht
                {
                    break;
                }

            }

            if (abstufung == false)
            {
                if (lvl == 7) // wenn bei lvl 7 keine abstufung gefunden wurde fehlt dem ort die plz, plz muss so über eine node in dem bereich gefunden werden passiert nur etwa bei 10 orten in ganz deutschland
                {
                    connectAuswahl(obj, 9);
                } else
                {
                    ablageListe.add(obj);
                }

            }

            conn.disconnect();
            abstufung = false;

        } catch (MalformedURLException e)
        {
            System.out.println(e);

        } catch (IOException e)
        {
            System.out.println(e);
        }

    }

    /**
     * methode die aus den abgerufenen daten von OSM das objekt dazu erstellt und dieses in die jeweilige liste einträgt
     * @param liste
     * @param out
     * @param obj
     * @param lvl 
     */
    
    private void fall(List liste, String out, NameIdObj obj, int lvl)
    {

        System.out.println(out);
        String splited[] = out.split("\\t");

        switch (lvl)
        {
            case 4:
                liste.add(new NameIdObj(splited[0], splited[1], "", "", ""));
                break;

            case 6:
                if (out.contains("Condominium Lëtzebuerg-Deutschland") == false) //rausfiltern der OSM einträge da kein Landkreis
                {
                    if (splited.length > 2)
                    {
                        liste.add(new NameIdObj(splited[0].trim(), splited[1].trim(), obj.getName(), splited[2].trim(), splited[1].trim()));
                    } else
                    {
                        liste.add(new NameIdObj(splited[0].trim(), splited[1].trim(), obj.getName(), obj.getKnz(), splited[1].trim()));
                    }
                }
                break;

            case 7:
                liste.add(new NameIdObj(splited[1].trim(), splited[2].trim(),
                        splited[0].replaceAll("[0-9]", "").replaceAll("[\"]", "").trim(), obj.getKnz(), obj.getLkr()));
                break;

            case 8:
                if (splited.length > 2)
                {
                    liste.add(new NameIdObj(obj.getName(), splited[1].trim(), splited[0].trim(), splited[2].trim(), obj.getLkr()));
                } else
                {
                    liste.add(new NameIdObj(obj.getName(), splited[1].trim(), splited[0].trim(), obj.getKnz(), obj.getLkr()));
                }
                break;

            case 9:
                liste.add(new NameIdObj(splited[0].trim(), obj.getId(), obj.getName(), obj.getKnz(), obj.getLkr()));
                break;

            case 10:
                liste.add(new StreetObj(splited[0].trim(), obj.getName(), obj.getSpeicher(), obj.getKnz()));
                break;

        }

        abstufung = true;
    }

     /**
     * Methode zur Korrektur der Gemeindekennzahl, weist den orten, die während
     * des durchlaufs keine komplette Gemeindekennzahl abgegriffen haben, die die ganze gkz zu. 
     * @param obj 
     */
    
    private void knzKorrektur(NameIdObj obj)
    {
        try
        {
            
            String nameSplit[] = obj.getSpeicher().split(" ");
            String splitedNext[] = nameSplit[0].split("/");
            URL url = new URL("http://www.overpass-api.de/api/interpreter?data="
                    + "[out:csv(\"name\",::id,\"de:amtlicher_gemeindeschluessel\";false)];"
                    + "area(" + areaCalc(obj.getLkr()) + ");relation[\"boundary\"=\"administrative\"][\"admin_level\"=\"8\"][~\"^name.*$\"~\"" +splitedNext[0]+ "\"][\"de:amtlicher_gemeindeschluessel\"](area);out;");
            
//            System.out.println("http://www.overpass-api.de/api/interpreter?data="
//                    + "[out:csv(\"name\",::id,\"de:amtlicher_gemeindeschluessel\";false)];"
//                    + "area(" + areaCalc(obj.getLkr()) + ");relation[\"boundary\"=\"administrative\"][\"admin_level\"=\"8\"][~\"^name.*$\"~\"" +splitedNext[0]+ "\"][\"de:amtlicher_gemeindeschluessel\"](area);out;");
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/csv");

           switch (conn.getResponseCode())
            {
                case 429:
                    return;
                case 504:
                    return;
                case 400:
                    return;
                default:
                    break;
            }
           
            if (conn.getResponseCode() != 200)
            {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());

            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()), StandardCharsets.UTF_8));

            String output;

            while ((output = br.readLine()) != null)
            {
                System.out.println(output);
                String splited[] = output.split("\\t");
                if (splited.length > 2)
                {
                    if (splited[2].length() > 7)
                    {
                        obj.setSpeicher(splited[0].trim());
                        obj.setKnz(splited[2].trim());  
                    } 
                }
            }
            conn.disconnect();

        } catch (MalformedURLException e)
        {
            System.out.println(e);

        } catch (IOException e)
        {
            System.out.println(e);
        }
    }
    
    /**
     * Methode zur berechnung der Area werte
     * @param calc
     * @return 
     */
    
    private Long areaCalc(String calc)
    {
       Long area = Long.parseLong(calc);
       area += 3600000000L;
       return area;
    }
    
    public void setLvl4List(String name, String id, String speicher, String knz)
    {
        lvl4Liste.add(new NameIdObj(name, id, speicher, knz, ""));
    }

    public List<NameIdObj> getLvl4List()
    {
        return lvl4Liste;
    }

    public List<NameIdObj> getLvl6List()
    {
        return lvl6Liste;
    }

    public List<NameIdObj> getLvl8List()
    {
        return lvl8Liste;
    }

    public void setLvl8List(NameIdObj obj)
    {
        lvl8Liste.add(obj);
    }

    public List<NameIdObj> getPlzList()
    {
        return plzListe;
    }

    public List<NameIdObj> getablageList()
    {
        return ablageListe;
    }

    public void clearAblageListe()
    {
        ablageListe.clear();
    }

    public List<StreetObj> getStreetList()
    {
        return streetList;
    }

    public boolean getAbstufung()
    {
        return abstufung;
    }

    public int getAbfragezaehler()
    {
        return abfragezaehler;
    }

    public void clearliste(int lvl)
    {

        switch (lvl)
        {

            case 4:
                lvl4Liste.clear();
                break;

            case 6:
                lvl6Liste.clear();
                ablageListe.clear();
                break;

            case 7:
            case 8:
                plzListe.clear();
                lvl8Liste.clear();
                ablageListe.clear();
                break;

            case 10:
                streetList.clear();
                break;

            default:
                lvl6Liste.clear();
                lvl8Liste.clear();
                ablageListe.clear();
                streetList.clear();
                break;
        }
    }
}
