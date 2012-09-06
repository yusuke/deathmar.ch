<!DOCTYPE html>
<%@page contentType="text/html;charset=UTF-8" language="java" %><%@taglib prefix="tag" tagdir="/WEB-INF/tags" %><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><tag:skelton title="@${screenName}のフォロー・フォロワーのユースケ">
    @${screenName}のフォロー・フォロワーにユースケは${fn:length(yusukes)}人います

    <tag:loggedin>
        <div id="tweeted" class="alert alert-success fade in" style="display:none">
            <a class="close" data-dismiss="alert">x</a>
            ツイートしました！
        </div>

        <div id="tweetform" style="display:none">
            結果をツイートしよう！
            <form action="./post" method="post" class="form-vertical">
                <textarea style="width:300px" rows="3" name="text" id="tweetText"></textarea><br>
                <input type="button" name="button" value="ツイート" onclick="tweet()" class="btn"/>
            </form>
        </div>
        <script>
            function tweet(){
                $.ajax({
                    type:'post',
                    url:'./post',
                    data:{
                        'text':$("#tweetText").val()
                    },
                    success:function (data) {
                        $("#tweeted").show();
                        $("#tweetform").hide();
                    }
                });

            }
        </script>
        <c:if test="${'/'.concat(progress.screenName) == requestScope['javax.servlet.forward.path_info']}">

            <script language="JavaScript">
                $("#tweetform").show();
                var tweetText = ".@${progress.screenName} のフォロー・フォロワー";
                if (${progress.count} == 0) {
                    tweetText += "にユースケはいません。 http://deathmar.ch/countyusuke/yusuke${requestScope['javax.servlet.forward.path_info']} ";
                } else {
                    tweetText += "のうちユースケは" + ${progress.count} + "人います。  http://deathmar.ch/countyusuke/yusuke${requestScope['javax.servlet.forward.path_info']} ";
                }
                tweetText += "あなたは何人？ #countyusuke";
                $("#tweetText").html(tweetText);
            </script>
        </c:if>
    </tag:loggedin>

    <div class="yusukes">
        <c:forEach var="yusuke" items="${yusukes}">
            @${yusuke}<br>
            <%--<a href="https://twitter.com/${yusuke}" class="twitter-follow-button" data-show-count="true" data-lang="ja">${yusuke}をフォロー</a><br>--%>
        </c:forEach>
        <script src="http://platform.twitter.com/anywhere.js?id=jGwHawqfBPyrfETcjprA&amp;v=1"
                type="text/javascript"></script>
        <script type="text/javascript"> twttr.anywhere(function (T) {
        T.hovercards({expanded:true});
        }); </script>
    </div>
    <script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>

    <hr width="300px">

</tag:skelton>