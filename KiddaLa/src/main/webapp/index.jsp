<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
  http://localhost:8080/KiddaLa/ にアクセスした時、
  ファイル名を指定しなくても自動的にMainMenu.jspへ転送するための入り口ファイル。
--%>
<%
    response.sendRedirect("MainMenu.jsp");
%>
