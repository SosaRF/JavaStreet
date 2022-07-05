
package cz.isgastreet.core;

/**
 *
 * @author Marco Schulz
 */
public class StreetObj 
{

    private String strasse;
    private String plz;
    private String ort;
    private String knz;

    public StreetObj(String straße, String plz, String ort, String knz)
    {
        this.strasse = straße;
        this.plz = plz;
        this.ort = ort;
        this.knz = knz;
    }

    public String getStraße()
    {
        return strasse;
    }

    public void setStraße(String straße)
    {
        this.strasse = straße;
    }

    public String getPlz()
    {
        return plz;
    }

    public void setPlz(String plz)
    {
        this.plz = plz;
    }

    public String getOrt()
    {
        return ort;
    }

    public void setOrt(String ort)
    {
        this.ort = ort;
    }

    public String getKnz()
    {
        return knz;
    }

    public void setKnz(String knz)
    {
        this.knz = knz;
    }

    @Override
    public String toString()
    {
        return strasse + ";" + plz + ";" + ort + ";" + knz;
    }
    
     @Override
    public int hashCode() {
        return strasse.hashCode() ^ plz.hashCode() ^ ort.hashCode() ^ knz.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj instanceof StreetObj) == false)
            return false;

        StreetObj so = (StreetObj) obj;
        return so.strasse.equals(strasse) && so.plz.equals(plz) && so.ort.equals(ort) && so.knz.equals(knz);
    }
    
    
}
