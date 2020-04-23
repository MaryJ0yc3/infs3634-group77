package com.example.infs3634_group77.Helpers;

import com.example.infs3634_group77.Entities.DefinitionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface DefinitionService {
    // Making API request (pls don't touch)
    @GET("/api/v4/dictionary/{word}")
    Call<DefinitionResponse> getDefinitions(@Path(value = "word") String word, @Header("Authorization") String authHeader);
}
