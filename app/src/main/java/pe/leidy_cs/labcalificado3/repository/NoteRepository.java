package pe.leidy_cs.labcalificado3.repository;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.leidy_cs.labcalificado3.models.Note;

public class NoteRepository {
    private static List<Note> notes = new ArrayList<>();
    public static List<Note> list(){
        List<Note> users = SugarRecord.listAll(Note.class);
        return users;
    }


    public static Note read(Long id){
        Note user = SugarRecord.findById(Note.class, id);
        return user;
    }

    public static void create(String title, String content, Date date, Boolean favorite, Boolean archivate){
        Note note = new Note(title, content, date, favorite, archivate);
        SugarRecord.save(note);
    }

    public static void update(Long id, String title, String content, Date date, Boolean favorite, Boolean archivate){
        Note note = SugarRecord.findById(Note.class, id);
        note.setTitle(title);
        note.setContent(content);
        note.setDate(date);
        note.setFavorite(favorite);
        note.setArchivate(archivate);
        SugarRecord.save(note);
    }

    public static void updateStar(Long id, Boolean favorite){
        Note note = SugarRecord.findById(Note.class, id);
        note.setFavorite(favorite);
        SugarRecord.save(note);
    }

    public static void updateArchive(Long id, Boolean archivate){
        Note note = SugarRecord.findById(Note.class, id);
        note.setArchivate(archivate);
        SugarRecord.save(note);
    }

    public static void delete(Long id){
        Note note = SugarRecord.findById(Note.class, id);
        SugarRecord.delete(note);
    }

    public static Note get(Long id){
        for (Note note : notes) {
            if(note.getId().equals(id))
                return note;
        }
        return null;
    }

}
