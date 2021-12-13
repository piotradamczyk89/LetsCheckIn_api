<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>KnightOne Bootstrap Template - Index</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->

    <link href="<c:url value="/resources/img/favicon.png"/>" rel="icon">
    <link href="<c:url value="/resources/img/apple-touch-icon.png"/>" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/vendor/bootstrap-icons/bootstrap-icons.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/vendor/boxicons/css/boxicons.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/vendor/glightbox/css/glightbox.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/vendor/remixicon/remixicon.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/vendor/swiper/swiper-bundle.min.css"/>" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">

    <!-- =======================================================
    * Template Name: KnightOne - v4.7.0
    * Template URL: https://bootstrapmade.com/knight-simple-one-page-bootstrap-template/
    * Author: BootstrapMade.com
    * License: https://bootstrapmade.com/license/
    ======================================================== -->
</head>

<body>

<!-- ======= Header ======= -->
<header id="header" class="fixed-top header-inner-pages">
    <div class="container-fluid">

        <div class="row justify-content-center">
            <div class="col-xl-9 d-flex align-items-center justify-content-lg-between">
                <h1 class="logo me-auto me-lg-0"><a href="index.html">KnightOne</a></h1>
                <!-- Uncomment below if you prefer to use an image logo -->
                <!-- <a href="index.html" class="logo me-auto me-lg-0"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->

                <nav id="navbar" class="navbar order-last order-lg-0">
                    <ul>
                        <li><a class="nav-link scrollto active" href="/user/app">Szukaj noclegu</a></li>

                        <li class="dropdown"><a href="#"><span>Zarzadzaj swoimi obiektami</span> <i class="bi bi-chevron-down"></i></a>
                            <ul>
                                <li><a href="/apartment/add">Dodaj obiekt</a></li>
                                <li><a href="/apartment/list">Zobcz liste obiektów</a></li>
                            </ul>
                        </li>
                        <li class="dropdown"><a href="#"><span>Zobacz swoje rezerwacje</span> <i class="bi bi-chevron-down"></i></a>
                            <ul>
                                <li><a href="#">Drop Down 1</a></li>
                                <li class="dropdown"><a href="#"><span>Deep Drop Down</span> <i class="bi bi-chevron-right"></i></a>
                                    <ul>
                                        <li><a href="#">Rezerwacje zrealizowane</a></li>
                                        <li><a href="#">Rezerwacje trwajace</a></li>
                                    </ul>
                                </li>
                                <li><a href="#">Drop Down 2</a></li>
                                <li><a href="#">Drop Down 3</a></li>
                                <li><a href="#">Drop Down 4</a></li>
                            </ul>
                        </li>
                        <li><a class="nav-link scrollto active" href="/user/edit/${user.id}">Witaj ${user.userName}</a></li>
                    </ul>
                    <i class="bi bi-list mobile-nav-toggle"></i>
                </nav><!-- .navbar -->

                <sec:authorize access="isAuthenticated()">
                    <form action="<c:url value="/logout"/>" method="post">
                        <input type="submit" value="Wyloguj">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </sec:authorize>

            </div>
        </div>

    </div>
</header><!-- End Header -->
