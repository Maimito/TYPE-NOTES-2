package com.maimito.type_notes.api;

import com.maimito.type_notes.handler.ResponseHandler;
import com.maimito.type_notes.model.NotesListItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DBService {
    @GET("user/login.php")
    Call<ResponseHandler> getUserLogin(@Query("username") String username,
                                       @Query("password") String password);

    @POST("user/register.php")
    Call<ResponseHandler> postRegister(@Field("username") String username,
                                       @Field("password") String password,
                                       @Field("fullname") String fullname);

    @GET("notes/get_notes.php")
    Call<List<NotesListItem>> getNotes(@Query("uid") String uid);

    @FormUrlEncoded
    @POST("notes/add_notes.php")
    Call<ResponseHandler> postAddNotes(@Field("note_title") String
                                                        note_title,
                                       @Field("note_content") String
                                                        note_content,
                                       @Field("date_modified") String
                                                        date_modified,
                                       @Field("date_created") String
                                                        date_created);

    @FormUrlEncoded
    @POST("notes/save_notes.php")
    Call<ResponseHandler> postSaveNotes(@Field("note_title") String
                                             note_title,
                                        @Field("note_content") String
                                             note_content,
                                        @Field("date_modified") String
                                             date_modified,
                                        @Field("date_created") String
                                             date_created);

    @FormUrlEncoded
    @POST("notes/edit_notes.php")
    Call<ResponseHandler> postEditNotes(@Field("note_title") String
                                             note_title,
                                        @Field("note_content") String
                                             note_content,
                                        @Field("date_modified") String
                                             date_modified,
                                        @Field("id") String
                                             id);

    @FormUrlEncoded
    @POST("notes/delete_notes.php")
    Call<ResponseHandler> postDeleteNotes(@Field("id") String
                                             id);
}
