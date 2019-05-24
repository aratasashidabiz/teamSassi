<%--
  Created by IntelliJ IDEA.
  User: arata.sashida
  Date: 2019-05-23
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>商品一覧</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
</head>
<body>
<div class="container">
    <header class="header"><h1 style="font-family: 'Roboto', sans-serif;">MB SHOP</h1></header>
    <h2>商品一覧</h2>
    <div>
        <%--検索ボタンとフォーム--%>
        <form  action="/ItemsServlet" method="get">
            <div class="form-group">
                <label for="">ページ内検索</label>
                <input type="text" name="keyword" class="form-control" id="" placeholder="検索">
            </div>
            <input type="button" class="btn btn-primary" name="searchKeyword" value="検索">
        </form>

<%--        <form action="/ItemsServlet" method="get">--%>
<%--            <input type="text" name="keyword">--%>
<%--            <input type="button" name="searchKeyword" value="検索">--%>
<%--        </form>--%>

    </div>

    <div>
        <%--ページナビゲーション--%>
        <cetner><< 1,2,3,4,5 >></cetner>
    </div>

    <%--商品一覧部分--%>
    <div>
        <form action="/CartServlet?mode=1&id=${item.id}" method="get">
            <table border="1" cellspacing="10">
                <c:forEach items="${items}" var="item">
                    <tr>
                        <td>
                            <img src="./img/dummy.png" alt="dummy"><br>
<%--                            <input type="button" onclick="/CartServlet?mode=1&id=${item.id}" class="btn btn-info" value="カートに入れる">--%>
                            <form action="/CartServlet?mode=1&id=${item.id}" method="post">
                                <input type="submit" value="カートに入れる">
                            </form>
                        </td>
                        <td>
                            <a href="/ItemsServlet?mode=1&id=${item.id}">${item.title}</a><br>
                            価格:<br>
                                ${item.price}
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
</html>
