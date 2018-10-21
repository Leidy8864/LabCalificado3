package pe.leidy_cs.labcalificado3.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.curioustechizen.ago.RelativeTimeTextView;

import java.util.List;

import pe.leidy_cs.labcalificado3.activities.NoteDetailActivity;
import pe.leidy_cs.labcalificado3.R;
import pe.leidy_cs.labcalificado3.models.Note;
import pe.leidy_cs.labcalificado3.repository.NoteRepository;
import pe.leidy_cs.labcalificado3.repository.UserRepository;

import static pe.leidy_cs.labcalificado3.repository.NoteRepository.updateArchive;
import static pe.leidy_cs.labcalificado3.repository.NoteRepository.updateStar;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private List<Note> notes;

    public NoteAdapter(List<Note> notes){
        this.notes = notes;
    }

    public void setNotes(List<Note> notes){
        this.notes = notes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleText;
        TextView contentText;
        RelativeTimeTextView dateText;
        CheckBox favoriteStar;
        CheckBox archivarClick;
        public ImageButton button;

        ViewHolder(View itemView){
            super(itemView);
            titleText = (TextView) itemView.findViewById(R.id.title_text);
            contentText = (TextView) itemView.findViewById(R.id.content_text);
            dateText = (RelativeTimeTextView) itemView.findViewById(R.id.date_text);
            favoriteStar = (CheckBox) itemView.findViewById(R.id.favorite_star);
            archivarClick = (CheckBox) itemView.findViewById(R.id.archivar_click);
            button = (ImageButton) itemView.findViewById(R.id.delete_button);
        }
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder viewHolder, final int position) {
        final Note note = this.notes.get(position);
        viewHolder.titleText.setText(note.getTitle());
        viewHolder.contentText.setText(note.getContent());
        viewHolder.dateText.setReferenceTime(note.getDate().getTime());
        viewHolder.favoriteStar.setChecked(note.getFavorite());
        viewHolder.archivarClick.setChecked(note.getArchivate());

        viewHolder.favoriteStar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                note.setFavorite(b);
                updateStar(note.getId(), b);
            }
        });

        viewHolder.archivarClick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                note.setArchivate(b);
                updateArchive(note.getId(), b);
            }
        });

        viewHolder.button.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoteRepository.delete(note.getId());
                notes.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());
            }
        });

        // OnClick on CardView
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), NoteDetailActivity.class);
                intent.putExtra("ID", note.getId());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.notes.size();
    }

}
