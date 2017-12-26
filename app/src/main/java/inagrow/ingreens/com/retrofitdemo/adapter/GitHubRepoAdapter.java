package inagrow.ingreens.com.retrofitdemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import inagrow.ingreens.com.retrofitdemo.R;
import inagrow.ingreens.com.retrofitdemo.models.GitHubRep;

/**
 * Created by root on 26/12/17.
 */

public class GitHubRepoAdapter extends ArrayAdapter<GitHubRep> {

    LayoutInflater inflater;
    Context context;
    List<GitHubRep> repos;
    ImageView ivAvtar;
    TextView tvId, tvName, tvURL;

    public GitHubRepoAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    public GitHubRepoAdapter(Context context, int resource, List<GitHubRep> repos) {
        super(context, resource, repos);
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item, null);
        }

        GitHubRep p = getItem(position);

        if (p != null) {
            ivAvtar=v.findViewById(R.id.ivAvtar);
            new DownloadImageTask(ivAvtar).execute(p.getOwner().getAvatar_url());
            tvId = (TextView) v.findViewById(R.id.tvId);
            tvId.setText(String.valueOf(p.getId()));
            tvName = (TextView) v.findViewById(R.id.tvName);
            tvName.setText(p.getName());
            tvURL = (TextView) v.findViewById(R.id.tvURL);
            tvURL.setText(p.getGit_url());
        }

        return v;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
