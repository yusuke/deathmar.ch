/*
Copyright (c) 2012, Yusuke Yamamoto
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the Yusuke Yamamoto nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY Yusuke Yamamoto ``AS IS'' AND ANY
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL Yusuke Yamamoto BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package ch.deathmar;

import twitter4j.*;
import twitter4j.internal.async.DispatcherFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountYusuke implements Progress {
    public void count(final Twitter twitter) {
        try {
            User me = twitter.verifyCredentials();
            screenName = twitter.getScreenName();
            final Set<Long> connected = new HashSet<Long>(me.getFollowersCount() + me.getFriendsCount());
            long cursor = -1L;
            IDs friends;
            do {
                friends = twitter.getFriendsIDs(cursor);
                for (long id : friends.getIDs()) {
                    connected.add(id);
                }
                cursor = friends.getNextCursor();
            } while (friends.hasNext());

            cursor = -1L;
            do {
                friends = twitter.getFollowersIDs(cursor);
                for (long id : friends.getIDs()) {
                    connected.add(id);
                }
                cursor = friends.getNextCursor();
            } while (friends.hasNext());
            total = connected.size();
            final Iterator<Long> ite = connected.iterator();
            new DispatcherFactory().getInstance().invokeLater(
                    new Runnable() {
                        @Override
                        public void run() {
                            ResponseList<User> users;
                            try {
                                long[] lookupTarget = null;
                                for (int i = 0; i < connected.size(); i++) {
                                    if (i % 100 == 0) {
                                        if (i != 0) {
                                            users = twitter.lookupUsers(lookupTarget);
                                            count(users);
                                            evaluated += users.size();
                                            if (users.getRateLimitStatus().getRemainingHits() == 0) {
                                                failed = true;
                                                break;
                                            }
                                        }
                                        lookupTarget = new long[Math.min(100, connected.size() - i)];
                                    }
                                    lookupTarget[i % 100] = ite.next();
                                }
                                if (lookupTarget.length != 100) {
                                    users = twitter.lookupUsers(lookupTarget);
                                    count(users);
                                    evaluated += users.size();
                                }
                                Store.put(String.valueOf(twitter.getScreenName()), yusukes);
                            } catch (TwitterException te) {
                                failed = true;
                            }
                        }
                    });

        } catch (TwitterException te) {
            failed = true;
        }
    }

    int total;

    public String getScreenName() {
        return screenName;
    }

    String screenName;

    public List<String> getYusukes() {
        return yusukes;
    }

    List<String> yusukes = new ArrayList<String>();
    boolean failed = false;
    int evaluated = 0;

    private void count(List<User> users) {
        for (User user : users) {
            if (isYusuke(user.getScreenName() + user.getName())) {
                yusukes.add(user.getScreenName());
            }
        }
    }

    private static final Pattern p = Pattern.compile(".*(ゆうすけ|ゆーすけ|ユウスケ|ユースケ|ﾕｳｽｹ|ﾕｰｽｹ|yusuke|佑亮|佑介|佑助|佑輔|優介|優佑|優助|優祐|優輔|勇介|勇佑|勇典|勇助|勇祐|勇輔|友介|友佑|友助|友祐|友輔|右介|右典|夕介|悠介|悠佑|悠助|悠祐|悠輔|攸介|有介|有佑|有資|祐介|祐助|祐輔|裕介|裕典|裕助|裕将|裕祐|裕輔|遊助|雄亮|雄介|雄佑|雄助|雄右|雄祐|雄輔).*", Pattern.CASE_INSENSITIVE);

    public static boolean isYusuke(String name) {
        Matcher m = p.matcher(name);
        return m.matches();
    }

    @Override
    public int getProgress() {
        return (int) ((double) evaluated / (double) total * 90 + 10);
    }

    @Override
    public boolean isFailed() {
        return failed;
    }
    public int getCount(){
        return yusukes.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountYusuke that = (CountYusuke) o;

        if (evaluated != that.evaluated) return false;
        if (failed != that.failed) return false;
        if (total != that.total) return false;
        if (screenName != null ? !screenName.equals(that.screenName) : that.screenName != null) return false;
        if (yusukes != null ? !yusukes.equals(that.yusukes) : that.yusukes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = total;
        result = 31 * result + (screenName != null ? screenName.hashCode() : 0);
        result = 31 * result + (yusukes != null ? yusukes.hashCode() : 0);
        result = 31 * result + (failed ? 1 : 0);
        result = 31 * result + evaluated;
        return result;
    }

    @Override
    public String toString() {
        return "CountYusuke{" +
                "total=" + total +
                ", screenName='" + screenName + '\'' +
                ", yusukes=" + yusukes +
                ", failed=" + failed +
                ", evaluated=" + evaluated +
                '}';
    }
}
