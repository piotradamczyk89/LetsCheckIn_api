<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../Headers_footers/header_inner.jsp"/>
<main id="main">

    <!-- ======= Breadcrumbs ======= -->
    <section id="breadcrumbs" class="breadcrumbs">
        <div class="container">

            <ol>
                <li><a href="index.html">Home</a></li>
                <li>Inner Page</li>
            </ol>
            <h2>Inner Page</h2>

        </div>
    </section><!-- End Breadcrumbs -->

    <section class="inner-page">
        <div class="container ">
            <div class="card mb-3 align-items-center" style="max-width: 920px; ">
                <form:form modelAttribute="apartment" method="post" enctype="multipart/form-data">
                    <div class="row g-0 align-items-center">
                        <div class="col-md-4">
                            <div class="card-body">
                                <h5 class="card-title">
                                    <h5 class="card-title">Dane Obiektu</h5>
                                </h5>
                                <p class="card-text">
                                    <div>
                                    <spring:message code="ObjectName"/>
                                    <form:input path="name"/>
                                    </div>
                                <div>
                                    <c:if test="${apartment.rentWay.id==1}">
                                    <spring:message code="DayPrice"/><br>
                                        <form:input path="apartmentPrice"/> <br>
                                    </c:if>
                                    <c:if test="${apartment.rentWay.id==2}">
                                        Cena za miesiąc<br>
                                        <form:input path="apartmentPrice"/> <br>
                                    </c:if>
                                </div>
                                </p>
                            </div>
                        </div>

                    <div class="col-md-4">
                        <div class="card-body">
                            <h5 class="card-title">
                                <h5 class="card-title">Dane Adresowe</h5>
                            </h5>
                            <p class="card-text">
                                Kraj:<br>
                                <form:select path="country" items="${country}" itemLabel="country" itemValue="id"/> <br>
                                Miasto:<br>
                                <form:input type="text" path="city" class="form-control"/>
                            </p>
                        </div>
                    </div>
                        <div class="col-md-4 row align-items-center">
                            <div class="card-body">
                                <p class="card-text">
                                    Adres:<br>
                                    <form:input type="text" path="address" class="form-control"/>
                                </p>
                            </div>
                        </div>


                    </div>

                    <div class="row g-0">
                        <div class="col-md-4">
                            <div class="card-body">
                                <h5 class="card-title">Szczegóły Obiektu</h5>
                                <h5><spring:message code="RoomAmount"/> ${apartment.rooms.size()}</h5>
                                <p class="card-text">
                                    <c:if test="${apartment.rentWay.id!=3}">
                                    <label>Liczba Toalet:
                                        <form:input type="text" path="toilets" class="form-control"/>
                                    </label>
                                    <label>Powierzchnia obiektu
                                        <form:input type="text" path="area" class="form-control"/>
                                    </label>
                                    </c:if>
                                </p>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card-body">
                                <h5 class="card-title">Opis Obiektu</h5>
                                <p class="card-text">
                                    <label>
                                        <form:textarea rows="5" type="text" path="description" class="form-control"/>
                                    </label>
                                </p>
                            </div>
                        </div>
                        <div class="col-md-4 row align-items-center">
                            <a href="/apartment/edit/${apartment.id}" class="btn btn-info">Zapisz zmiany</a>
                            <a href="/room/add/${apartment.id}" class="btn btn-success">Dodaj pokój</a>
                        </div>
                    </div>
                </form:form>
            </div>

        </div>

        </div>
    </section>

</main>
<!-- End #main -->
<jsp:include page="../Headers_footers/footer.jsp"/>