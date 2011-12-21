<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<head>
	<title>zombietank.com - Home</title>
	<style type="text/css">
		.zombieBackground {
			background-image: url('<c:url value="/resources/img/Zombie-icon.png" />')
			background-position: right;
			background-repeat:no-repeat;
		}
	</style>
</head>

<!-- Main hero unit for a primary marketing message or call to action -->
<div class="hero-unit zombieBackground" style="background-position: right;background-repeat:no-repeat; background-image: url('<c:url value="/resources/img/Zombie-icon.png" />"'>
	<div class="span-two-thirds">
		<h1>Braaaiiinnss!</h1>
		<p>Watch out, zombies are hugry for your brain.</p>
		<p>
			<a class="btn primary large">Learn more &raquo;</a>
		</p>
	</div>
</div>

<!-- Example row of columns -->
<div class="row">
	<div class="span-one-third">
		<h2>Zombie Patrol</h2>
		<p>We offer a variety of zombie protection services, at the industries lowest rates per kill!</p>
		<p>
			<a class="btn" href="#">View details &raquo;</a>
		</p>
	</div>
	<div class="span-one-third">
		<h2>Emergency Response</h2>
		<p>Bitten by a zombie? Need someone to put you down? We offer this exclusive service to our paying 
		members. No credit? No problem! Sign up today and pay only $19.99/month!</p>
		<p>
			<a class="btn" href="#">View details &raquo;</a>
		</p>
	</div>
</div>