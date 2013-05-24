<%@ page import="java.util.Calendar" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title><decorator:title default="zombietank.com" /></title>
    <meta name="author" content="Tom Hermann">
    <meta name="description" content="Somewhere around the intersection of zombies and tanks.">

	<!-- html5 shim -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.js"></script>

	<!-- styles -->
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">

    <style type="text/css">
      body {
        padding-top: 60px;
      }
    </style>

	<!-- favicons -->
    <link rel="shortcut icon" href="<c:url value="/resources/img/favicon.ico?v02" />">
    <link rel="apple-touch-icon" href="<c:url value="/resources/img/apple-touch-icon.png" />">
    <link rel="apple-touch-icon" sizes="72x72" href="<c:url value="/resources/img/apple-touch-icon-72x72.png" />">
    <link rel="apple-touch-icon" sizes="114x114" href="<c:url value="/resources/img/apple-touch-icon-114x114.png" />">
	<decorator:head />
  </head>

  <body>
    <div class="topbar">
		<div class="fill">
			<div class="container">
				<a class="brand" href="<c:url value="/" />">zombietank</a>
				<ul class="nav">
					<li><a href="<c:url value="/" />">Home</a></li>
				</ul>
			</div>
		</div>
	</div>
    <div class="container">
	  <decorator:body />
      <footer>
        <p>&copy; zombietank.com <%= Calendar.getInstance().get(Calendar.YEAR) %></p>
      </footer>
    </div>
  </body>
</html>
