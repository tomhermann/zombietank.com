<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title><decorator:title default="zombietank.com" /></title>
    <meta name="author" content="Tom Hermann">
    <meta name="description" content="Somewhere around the intersection of zombies and tanks.">

    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le styles -->
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
      }
    </style>

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="<c:url value="/resources/img/favicon.ico" />">
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
            <li class="active"><a href="<c:url value="/" />">Home</a></li>
            <li><a href="<c:url value="/contact" />">Contact</a></li>
          </ul>
        </div>
      </div>
    </div>
    <div class="container">
	  <decorator:body />

      <footer>
        <p>&copy; zombietank.com 2011</p>
      </footer>
    </div>
  </body>
</html>
