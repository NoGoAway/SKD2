package se.skanskan.mittskanskan;

public class Rssitem 
{
    private String title = null;
    private String description = null;
    private String link = null;
    
   

    public Rssitem()
    {
    	
    }
    
    public Rssitem(String title, String description, String link)
    {
        super();
        this.title = title;
        this.description = description;
        this.link = link;
    }


    public void setTitle(String title) 
    {
        this.title = title;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }
    
    public void setLink(String link)
    {
    	this.link = link;
    }

    public String getTitle() 
    {
        return title;
    }

    public String getLink() 
    {
        return link;
    }
    
    public String getDesc()
    {
    	return description;
    }
    }

