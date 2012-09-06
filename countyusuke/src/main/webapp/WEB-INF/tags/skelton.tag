<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@attribute name="title" required="true" %>
<html lang="ja">
<head>
    <meta charset="utf-8">
    <title><%=title%> | #countyusuke</title>
    <base href="/countyusuke/">
    <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.1.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.1.0/css/bootstrap-responsive.min.css">
    <link href="./css/docs.css"
          rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
    <style>
        .twtr-widget {
            text-align: center;
        }
        .progress {
            text-align: center;
            margin: auto;
        }
        hr {
            text-align: center;
            margin: auto;
        }
        hr {
            text-align: center;
            margin: 20px auto 20px auto;
        }

        .twtr-doc {
            text-align: left;
            margin: auto;
        }
        .yusukes{
            font-size: 120%;
        }
    </style>
</head>
<body style="background-image: url(images/background.png);background-repeat: repeat">

<div style="text-align:center" class="container-fluid">
    <div class="navbar navbar-fix-top">
        <div class="navbar-inner">
            <a class="brand" style="margin-left:0px" href="http://deathmar.ch/countyusuke/">Deathmar.ch</a>
            <ul class="nav pull-left">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        deathmar.ch
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="http://deathmar.ch/ulooooo/">＼( ‘ω’)／ｳｳｵｵｵｱｱｱｱｱｱｱｰｰｰｰｰｰ!!!</a></li>
                        <li><a href="http://deathmar.ch/sekachu/">世界の中心から愛をツイートする</a></li>
                        <li><a href="http://deathmar.ch/michu/">だったらだまってろクソが。</a></li>
                        <li class="active"><a href="http://deathmar.ch/countyusuke/">フォロー/フォロワーにユースケは何人？</a></li>
                    </ul>
                </li>
            </ul>
            <tag:loggedin>
                <ul class="nav pull-left">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            @${fn:escapeXml(twitter.screenName)}
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="./logout">logout</a></li>
                        </ul>
                    </li>
                </ul>
            </tag:loggedin>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <jsp:doBody/>
            <tag:notloggedin>
                あなたのフォロー、フォロワーにゆーすけは何人？<br>
                Twitterアカウントでサインインして数えよう！<br>
                <a href="signin"><img src="./images/signin.png" width="272" height="72"/></a>
            </tag:notloggedin>


            <!-- social buttons -->
            <br/>
            <a href="https://twitter.com/share" class="twitter-share-button" data-via="yusuke"
               data-lang="ja">ツイート</a>
            <script>!function (d, s, id) {
                var js, fjs = d.getElementsByTagName(s)[0];
                if (!d.getElementById(id)) {
                    js = d.createElement(s);
                    js.id = id;
                    js.src = "//platform.twitter.com/widgets.js";
                    fjs.parentNode.insertBefore(js, fjs);
                }
            }(document, "script", "twitter-wjs");</script>
            <a href="http://b.hatena.ne.jp/entry/http://deathmar.ch/countyusuke/" class="hatena-bookmark-button"
               data-hatena-bookmark-title="フォロー/フォロワーにユースケは何人？ | #countyusuke"
               data-hatena-bookmark-layout="standard"
               title="このエントリーをはてなブックマークに追加"><img src="http://b.st-hatena.com/images/entry-button/button-only.gif"
                                                 alt="このエントリーをはてなブックマークに追加" width="20" height="20"
                                                 style="border: none;"/></a>
            <script type="text/javascript" src="http://b.st-hatena.com/js/bookmark_button.js" charset="utf-8"
                    async="async"></script>


            <!-- search widget -->
            <script charset="utf-8" src="http://widgets.twimg.com/j/2/widget.js"></script>
            <script>
                new TWTR.Widget({
                    version:2,
                    type:'search',
                    search:'#countyusuke',
                    interval:30000,
                    title:'#countyusuke',
                    subject:'',
                    width:300,
                    height:300,
                    theme:{
                        shell:{
                            background:'#8ec1da',
                            color:'#ffffff'
                        },
                        tweets:{
                            background:'#ffffff',
                            color:'#444444',
                            links:'#1985b5'
                        }
                    },
                    features:{
                        scrollbar:false,
                        loop:true,
                        live:true,
                        behavior:'default'
                    }
                }).render().start();
            </script>
        </div>
    </div>
</div>
<script type="text/javascript">

    var _gaq = _gaq || [];
    _gaq.push(['_setAccount', 'UA-262260-7']);
    _gaq.push(['_trackPageview']);

    (function () {
        var ga = document.createElement('script');
        ga.type = 'text/javascript';
        ga.async = true;
        ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
        var s = document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(ga, s);
    })();

</script>
<script src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.1.0/js/bootstrap.min.js"></script>
</body>
</html>

