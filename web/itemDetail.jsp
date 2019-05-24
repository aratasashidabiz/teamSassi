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
</head>
<body>

<h1>{エム・ビーショップ}</h1>

<table>
    <tr>
        <h2>${item.title}</h2>
    </tr>
    <tr>
        <td>
            <img src="img/dummy.png">
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
                    <form action="/CartServlet?mode=1&id=${item.id}" method="post">
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
