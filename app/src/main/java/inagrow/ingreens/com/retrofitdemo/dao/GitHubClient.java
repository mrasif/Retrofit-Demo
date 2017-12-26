package inagrow.ingreens.com.retrofitdemo.dao;

import java.util.List;

import inagrow.ingreens.com.retrofitdemo.models.GitHubRep;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by root on 26/12/17.
 */

public interface GitHubClient {
    @GET("/users/{user}/repos")
    Call<List<GitHubRep>> reposForUser(@Path("user") String user);

}
