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
    <link href="https://fonts.googleapis.com/css?family=Open+Sans&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lobster&display=swap" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 style="font-family: 'Lobster', cursive;">MB SHOP</h1>
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
<div>
    <%--ページナビゲーション--%>
<%--        <a href="/ItemsServlet?page=${p-1}"><< </a>--%>
    <c:forEach items="${pages}" var="page">
        <a href="/ItemsServlet?page=${page}"> ${page} </a>
    </c:forEach>
<%--        <a href="/ItemsServlet?page=${p+1}"> >></a>--%>
</div>
    </div>

    <%--商品一覧部分--%>
    <div>
        <form action="/CartServlet?mode=1&id=${item.id}" method="get">
            <table border="1" cellspacing="10">
                <c:forEach items="${items}" var="item">
            <tr>
                <td>
                    <img src="./img/product_${item.id}.png" alt="dummy"><br>
                    <form action="/CartServlet" method="post">
                        <input type="hidden" name="id" value="${item.id}">
                        <input type="hidden" name="mode" value="1">
                        <input type="submit" value="カートにいれる">
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
