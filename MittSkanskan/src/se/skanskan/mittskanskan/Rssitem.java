package se.skanskan.mittskanskan;

public class Rssitem 
{
    private String title = null;
    private String description = null;
    private String link = null;
    private String date = null;
    
   

    public Rssitem()
    {
    	
    }
    
    public Rssitem(String title, String description, String link, String date)
    {
        super();
        this.title = title;
        this.description = description;
        this.link = link;
        this.date = date;
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
    
    public String getDate()
    {
    	return date;
    }
    }

