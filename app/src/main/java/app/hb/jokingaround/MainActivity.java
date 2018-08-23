package app.hb.jokingaround;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import app.hb.jokingaround.databinding.ActivityMainBinding;
import app.hb.jokingaround.models.JokeModel;
import app.hb.jokingaround.network.ApiService;
import app.hb.jokingaround.network.Client;
import app.hb.jokingaround.utils.UtilsHelper;
import retrofit2.Call;
import retrofit2.Callback;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int i = 0;
    private Context mContext;
    private ActivityMainBinding mBinding;
    private ArrayList<JokeModel> jokeArrayModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mContext = MainActivity.this;
        mBinding.tvNextJoke.setOnClickListener(this);

        getJokes();
    }


    // ws get list of jokes
    private void getJokes() {
        if (UtilsHelper.isInternetExist(mContext)) {
            ApiService mApiService = Client.getInstance(getApplicationContext()).getService();

            mApiService.getJokes().enqueue(new Callback<ArrayList<JokeModel>>() {
                @Override
                public void onResponse(@NonNull Call<ArrayList<JokeModel>> call, @NonNull retrofit2.Response<ArrayList<JokeModel>> response) {
                    if (response.isSuccessful()) {
                        jokeArrayModels = response.body();
                        i += 1;
                        PutJokesOnScreen(i);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<JokeModel>> call, Throwable t) {
                    Timber.e("on failure ws call: \n" + t.getMessage());
                }
            });
        }
    }

    private void PutJokesOnScreen(int i) {
        mBinding.tvSetup.setText(jokeArrayModels.get(i).getSetup());
        mBinding.tvPunchline.setText(jokeArrayModels.get(i).getPunchline());
    }


    @Override
    public void onClick(View v) {
        if (i < 9) {
            i += 1;
            PutJokesOnScreen(i);
        } else {
            i = 0;
            getJokes();
        }
    }


}
