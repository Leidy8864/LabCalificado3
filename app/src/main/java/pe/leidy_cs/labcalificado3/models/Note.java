package pe.leidy_cs.labcalificado3.models;

import com.orm.dsl.Table;

import java.util.Date;

@Table

public class Note {

    private Long id;

    private String title;

    private String content;

    private Date date;

    private Boolean favorite;

    private Boolean archivate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Boolean getArchivate() {
        return archivate;
    }

    public void setArchivate(Boolean archivate) {
        this.archivate = archivate;
    }

    public Note() {
    }

    public Note(String title, String content, Date date, Boolean favorite, Boolean archivate) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.favorite = favorite;
        this.archivate = archivate;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", favorite=" + favorite +
                ", archivate=" + archivate +
                '}';
    }
}
