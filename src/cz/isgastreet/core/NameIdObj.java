package cz.isgastreet.core;

/**
 *
 * @author Marco Schulz
 */
public class NameIdObj
{

    private String name;
    private String id;
    private String speicher;
    private String knz;
    private String lkr;

    public NameIdObj(String name, String id, String speicher, String knz, String lkr)
    {
        
        this.name = name;
        this.id = id;
        this.speicher = speicher;
        this.knz = knz;
        this.lkr = lkr;
        
    }

    public String getName()
    {
        
        return name;
        
    }

    public void setName(String name)
    {
        
        this.name = name;
        
    }

    public String getId()
    {
        
        return id;
        
    }

    public void setId(String id)
    {
        
        this.id = id;
        
    }

    public String getSpeicher()
    {
        
        return speicher;
        
    }

    public void setSpeicher(String speicher)
    {
        
        this.speicher = speicher;
        
    }

    public String getKnz()
    {
        return knz;
    }

    public void setKnz(String knz)
    {
        this.knz = knz;
    }

    public String getLkr()
    {
        return lkr;
    }

    public void setLkr(String lkr)
    {
        this.lkr = lkr;
    }

    @Override
    public String toString()
    {
        return name + "  " + speicher;
    }

}
