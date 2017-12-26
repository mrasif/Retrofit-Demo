package inagrow.ingreens.com.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import java.util.List;

import inagrow.ingreens.com.retrofitdemo.adapter.*;
import inagrow.ingreens.com.retrofitdemo.config.AppConfig;
import inagrow.ingreens.com.retrofitdemo.dao.GitHubClient;
import inagrow.ingreens.com.retrofitdemo.models.GitHubRep;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.lvRepos);

        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit= builder.build();
        GitHubClient client=retrofit.create(GitHubClient.class);
        Call<List<GitHubRep>> call=client.reposForUser("mrasif");
        call.enqueue(new Callback<List<GitHubRep>>() {
            @Override
            public void onResponse(Call<List<GitHubRep>> call, Response<List<GitHubRep>> response) {
                List<GitHubRep> repos=response.body();
                listView.setAdapter(new GitHubRepoAdapter(getApplicationContext(), R.layout.list_item, repos));
            }

            @Override
            public void onFailure(Call<List<GitHubRep>> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage() );
            }
        });
    }
}
