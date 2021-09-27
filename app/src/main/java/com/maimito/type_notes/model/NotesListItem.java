package com.maimito.type_notes.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotesListItem{
	@SerializedName("note_content")
	private String noteContent;
	@SerializedName("note_title")
	private String noteTitle;
	@SerializedName("date_modified")
	private String dateModified;
	@SerializedName("user_id")
	private String userId;
	@SerializedName("date_created")
	private String dateCreated;
	@SerializedName("id")
	private String id;


	public void setNoteContent(String noteContent){
		this.noteContent = noteContent;
	}

	public String getNoteContent(){
		return noteContent;
	}

	public void setNoteTitle(String noteTitle){
		this.noteTitle = noteTitle;
	}

	public String getNoteTitle(){
		return noteTitle;
	}

	public void setDateModified(String dateModified){
		this.dateModified = dateModified;
	}

	public String getDateModified(){
		return dateModified;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setDateCreated(String dateCreated){
		this.dateCreated = dateCreated;
	}

	public String getDateCreated(){
		return dateCreated;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}
}
