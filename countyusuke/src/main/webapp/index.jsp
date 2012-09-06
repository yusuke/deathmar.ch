<!DOCTYPE html><%@page contentType="text/html;charset=UTF-8" language="java" %><%@taglib prefix="tag" tagdir="/WEB-INF/tags" %><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<tag:skelton title="カウントユースケ - フォロー/フォロワーにユースケは何人？">
    <tag:loggedin>
        <div class="progress progress-info progress-striped active" style="width:300px">
            <div class="bar" id="progress" style="width:0">カウント中。しばらくお待ちください</div>
        </div>
        <input type="button" class="btn" id="retry" onclick="javascript:location.href='./callback'" value="再度解析する"
               style="display: none">
        <script language="JavaScript">
            setTimeout(c, 500);
            function c() {
                $.getJSON("progress.json?callback=?",
                        function (data) {
                            if (data.failed) {
                                $(".progress").addClass("progress-warning");
                                $(".bar").css("width", "100%");
                                $(".bar").html("エラーが発生しました");
                                $("#retry").show();
                            } else {
                                $(".bar").css("width", data.progress + "%");
                                if (data.progress == 100) {
                                    location.href = "/countyusuke/yusuke/" + data.screenName;
                                } else {
                                    setTimeout(c, 500);
                                }
                            }
                        });
            }
        </script>
    </tag:loggedin>
</tag:skelton>