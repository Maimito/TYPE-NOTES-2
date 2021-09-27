package com.maimito.type_notes.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotesList{
	@SerializedName("notes")
	@Expose
	private List<NotesListItem> notesList;

	public void setNotesList(List<NotesListItem> notesList){
		this.notesList = notesList;
	}

	public List<NotesListItem> getNotesList(){
		return notesList;
	}
}