package ch.deathmar;

import java.io.Serializable;

public class Result implements Serializable {
    int count;
    int total;
    String screenName;

    public Result(String twitter, int count, int total) {
        this.screenName = twitter;
        this.count = count;
        this.total = total;
    }

    public String toString() {
        return ".@" + screenName + "のフォロー・フォロワー" + total + "アカウントのうち" + count + "はユースケです " + (count / total * 100) + "%";
    }
}
