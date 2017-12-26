package inagrow.ingreens.com.retrofitdemo.models;

/**
 * Created by root on 26/12/17.
 */

public class GitHubRep {

    private int id;
    private String name;
    private String git_url;
    private Owner owner;

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getGit_url() {
        return git_url;
    }
    public Owner getOwner() {
        return owner;
    }
}
