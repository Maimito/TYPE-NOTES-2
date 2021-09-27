package com.maimito.type_notes.api;

import com.maimito.type_notes.model.ErrorHandler;
import com.maimito.type_notes.model.NotesListItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DBService {
    
    @GET("notes/get_notes.php")
    Call<List<NotesListItem>> getNotes();

    @FormUrlEncoded
    @POST("notes/add_notes.php")
    Call<ErrorHandler> postAddNotes(@Field("note_title") String
                                                        note_title,
                                                @Field("note_content") String
                                                        note_content,
                                                @Field("date_modified") String
                                                        date_modified,
                                                @Field("date_created") String
                                                        date_created);

    @FormUrlEncoded
    @POST("notes/save_notes.php")
    Call<ErrorHandler> postSaveNotes(@Field("note_title") String
                                             note_title,
                                     @Field("note_content") String
                                             note_content,
                                     @Field("date_modified") String
                                             date_modified,
                                     @Field("date_created") String
                                             date_created);

    @FormUrlEncoded
    @POST("notes/edit_notes.php")
    Call<ErrorHandler> postEditNotes(@Field("note_title") String
                                             note_title,
                                     @Field("note_content") String
                                             note_content,
                                     @Field("date_modified") String
                                             date_modified,
                                     @Field("id") String
                                             id);

    @FormUrlEncoded
    @POST("notes/delete_notes.php")
    Call<ErrorHandler> postDeleteNotes(@Field("id") String
                                             id);
}
