package app.hb.jokingaround.network;


import java.util.ArrayList;

import app.hb.jokingaround.models.JokeModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("random_ten")
    Call<ArrayList<JokeModel>> getJokes();
}

