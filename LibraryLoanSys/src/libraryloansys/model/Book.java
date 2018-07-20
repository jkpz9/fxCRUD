package libraryloansys.model;

import java.util.Arrays;
import java.util.List;

public class Book {
    private long id;
    private String name;
    private List<String> authors;
    private int publishedYear;
    private boolean available;
    
    public String getAuthors()
    {
        String res = "";
        for (int i=0; i<authors.size(); i++)
        {
            res += authors.get(i) + "_";
        }
        
        return res.substring(0, res.length()-1);
    }
    
    public void setAuthors(String authors)
    {
        this.authors = Arrays.asList(authors.split("_"));
    }
    
    public int hascode()
    {
        final int prime = 31;
        int res = 1;
        
        res = prime*res + ((authors == null) ? 0 : authors.hashCode());
        res = prime*res + ((name == null) ? 0 : name.hashCode());
        res = prime*res + publishedYear;
        
        return res;
    }
    
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        
        if (obj == null) return false;
        
        if (getClass() != obj.getClass()) return false;
        
        Book that = (Book)obj;
        if (this.authors == null)
        {
            if (that.authors != null) return false;
        }
        
        else if (!this.authors.equals(that.authors)) return false;
        
        if (this.name == null)
        {
            if (that.name != null) return false;
        }
        else if (!this.name.equals(that.name)) return false;
        
        if (this.publishedYear != that.publishedYear) return false;
        
        return true;
        
    }
    
    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name=" + name + ", authors=" + this.getAuthors() + ", publishedYear=" + publishedYear + ", available=" + available + '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    
}
