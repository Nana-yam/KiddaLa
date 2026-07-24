<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
    private String esc(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
%>
<%
    String[][] tableData = (String[][]) session.getAttribute("tableData");

    String phoneValue = request.getParameter("tel");
    if (phoneValue == null) { phoneValue = ""; }
    String kanaValue = request.getParameter("kana");
    if (kanaValue == null) { kanaValue = ""; }

    String errorCode = (String) request.getAttribute("errorCode");
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html>
<head>
<% if (errorCode != null) { %>
<script>
    alert("<%= esc(errorMessage) %>");
</script>
<% } %>
<meta charset="UTF-8">
<title>&lt;&lt;顧客情報検索&gt;&gt;KIDDA-LA業務システム</title>
<link rel="stylesheet" href="css/common.css">
</head>
<body>
    <div class="page-wrapper">

        <div class="corner-deco top-left"><img src="images/pizza_topleft.png" alt="" onerror="this.style.display='none'"></div>
        <div class="corner-deco top-right"><img src="images/pizza_topright.png" alt="" onerror="this.style.display='none'"></div>
        <div class="corner-deco bottom-left"><img src="images/pizza_bottomleft.png" alt="" onerror="this.style.display='none'"></div>
        <div class="corner-deco bottom-right"><img src="images/pizza_bottomright.png" alt="" onerror="this.style.display='none'"></div>

        <div class="header">
            <p class="system-name">KIDDA-LA 業務システム</p>
            <h1 class="screen-name">《顧客情報検索》</h1>
        </div>

        <div class="search-layout">

            <!-- 検索フォーム -->
            <div class="search-form">
                <form action="KiddaLaController" method="post" id="searchForm">
                    <input type="hidden" name="command" value="CustomerSearch">

                    <div class="field-label">電話番号（ハイフンなし）</div>
                    <input type="text" class="field-input" name="tel"
                           placeholder="例：09012345678"
                           value="<%= esc(phoneValue) %>">

                    <div class="field-label">氏名カナ（全角カタカナ）</div>
                    <input type="text" class="field-input" name="kana"
                           placeholder="例：ヤマダタロウ"
                           value="<%= esc(kanaValue) %>">

                    <div class="search-button-row">
                        <button type="submit" class="btn btn-search btn-block">検索</button>
                    </div>

                    <div class="form-buttons">
                        <button type="button" class="btn btn-secondary" id="clearBtn"
                                onclick="document.getElementById('searchForm').tel.value='';document.getElementById('searchForm').kana.value='';">入力消去</button>
                        <a href="MainMenu.jsp" class="btn btn-secondary" style="text-decoration:none; display:inline-block; text-align:center;">戻る</a>
                        <button type="button" class="btn btn-secondary" onclick="window.close();">閉じる</button>
                    </div>
                </form>
            </div>

            <!-- 検索結果 -->
            <div class="result-panel">
                <h2>検索結果</h2>

                <% if (tableData != null && tableData.length > 0) { %>
                    <table class="result-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>氏名</th>
                                <th>カナ</th>
                                <th>住所</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (int i = 0; i < tableData.length; i++) { %>
                                <tr class="clickable-row"
                                    onclick="document.location.href='KiddaLaController?command=OrderDisplay&id=<%= esc(tableData[i][0]) %>'">
                                    <td><%= esc(tableData[i][0]) %></td>
                                    <td class="name-cell"><%= esc(tableData[i][1]) %></td>
                                    <td><%= esc(tableData[i][2]) %></td>
                                    <td><%= esc(tableData[i][4]) %></td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                <% } else { %>
                    <p class="no-result">電話番号または氏名カナを入力して検索してください。</p>
                <% } %>
            </div>
        </div>

        <p class="footer-credit">&copy;Infotech Serve Inc.</p>
    </div>
</body>
</html>
