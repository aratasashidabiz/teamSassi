<%--
  Created by IntelliJ IDEA.
  User: yuta.doi
  Date: 2019-05-23
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>ItemDetail</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lobster&display=swap" rel="stylesheet">
</head>
<body>

<h1 style="font-family: 'Lobster', cursive;">MB SHOP</h1>

<table>
    <tr>
        <h2>${item.title}</h2>
    </tr>
    <tr>
        <td>
            <img src="img/product_${item.id}.png" width="400" height="400">
        </td>
        <td>
            <ul style="list-style: none;">
                <li>
                    タイトル
                    <p>${item.title}</p>
                </li>
                <li>
                    価格
                    <p>${item.price}</p>
                </li>
                <li>
                    出演
                    <p>${item.players}</p>
                </li>
                <li>
                    監督
                    <p>${item.directors}</p>
                </li>
                <li>
                    解説
                    <p>${item.description}</p>
                </li>
                <li>
                    <form action="/CartServlet" method="post">
                        <input type="hidden" name="id" value="${item.id}">
                        <input type="hidden" name="mode" value="1">
                        <input type="submit" value="カートに入れる">
                    </form>
                </li>
            </ul>
        </td>

    </tr>
</table>

<a href="/ItemServlet"><< 戻る</a>


</body>
</html>
