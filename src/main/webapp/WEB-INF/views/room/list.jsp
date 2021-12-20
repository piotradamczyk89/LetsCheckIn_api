<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:if test="${user!=null}">
    <jsp:include page="../Headers_footers/header_inner.jsp"/>
</c:if>
<c:if test="${user==null}">
    <jsp:include page="../Headers_footers/header.jsp"/>
</c:if>
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
        <div class="container">

            <c:forEach items="${rooms}" var="room">

                <div class="">
                    <div class="card mb-3" style="max-width: 900px;">
                            <%--                        "g-0"--%>
                        <div class="row row-cols-3 justify-content-md-start align-content-center">
                            <div class="col-md-4">
                                <c:choose>
                                    <c:when test="${room.photos.size()!=0}">
                                        <img src="<c:url value="../../../img/LetsCheckIn_pictures/${room.photos.get(0).id}"/>"
                                             class="img-fluid rounded-start" alt="...">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="<c:url value="../../../img/LetsCheckIn_pictures/${apartment.photos.get(0).id}"/>"
                                             class="img-fluid rounded-start" alt="...">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-md-2">
                                <strong><h5 class="card-title">${room.roomName.name}</h5></strong>
                                <c:if test="${apartment.rentWay.id==3}">
                                    <p class="card-text"><spring:message
                                            code="DayPrice"/> ${room.roomPrice}</p>
                                </c:if>
                                <p class="card-text">Pokój ${room.persons}-osobowy </p>
                            </div>
                            <div class="col-md-4  ">
                                <div class="col">
                                    <h5 class="card-title ">Dostępne łóżka</h5>
                                    <ul>
                                        <c:if test="${room.singleBed!=null && room.singleBed!=0}">
                                            <li><h6>Łóżka pojedyńcze: ${room.singleBed}</h6></li>
                                        </c:if>
                                        <c:if test="${room.doubleBed!=null && room.doubleBed!=0}">
                                            <li><h6>Łóżka małżeńskie: ${room.singleBed}</h6></li>
                                        </c:if>
                                        <c:if test="${room.coach!=null && room.coach!=0}">
                                            <li><h6>Rozkładana kanapa: ${room.coach}</h6></li>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>

                            <div class="col-md-2 row align-items-center">
                                    <c:if test="${apartment.owner.id==apartment.owner.id}">
                                        <a href="/room/delete/${room.id}" class="btn btn-danger text-center">Usuń</a>
                                        <a href="/room/edit/${room.id}" class="btn btn-danger text-center">Edytuj</a>
                                    </c:if>
                                    <a href="/room/details/${apartment.id}" class="btn btn-danger text-center">Szczegóły</a>
                            </div>


                        </div>
                    </div>
                </div>

                <%--</a>--%>
            </c:forEach>

        </div>
    </section>

</main>
<!-- End #main -->
<jsp:include page="../Headers_footers/footer.jsp"/>
