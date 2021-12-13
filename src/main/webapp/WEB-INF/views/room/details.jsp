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
            <div class="container">
                <div class="row justify-content-md-center mb-4">
                    <div class="col-md-auto mb-6">
                        <h2 class="text-center"><u>${room.roomName.name} w ${room.apartment.name}</u></h2>
                    </div>
                </div>
                <div class="row justify-content-md-start">
                    <div class="col col-md-4">
                        <h4 class="text-center"><u>${room.roomName.name}
                            <c:if test="${room.persons!=0 && room.persons!=null}"> ${room.persons}-os</c:if> </u>
                        </h4>
                        <c:if test="${room.apartment.rentWay.id==3}">
                            <h6 class="text-center">Cena pokoju za dobe: ${room.roomPrice}</h6>
                        </c:if>
                        <c:if test="${room.singleBed!=null && room.singleBed!=0}">
                            <h6 class="text-center">Liczba łóżek pojedyńczych: ${room.singleBed}</h6>
                        </c:if>
                        <c:if test="${room.doubleBed!=null && room.doubleBed!=0}">
                            <h6 class="text-center">Liczba łóżek podwójnych: ${room.singleBed}</h6>
                        </c:if>
                        <c:if test="${room.coach!=null && room.coach!=0}">
                            <h6 class="text-center">Liczba rozkładanych kanap: ${room.coach}</h6>
                        </c:if>
                    </div>
                    <c:if test="${room.apartment.rentWay.id==3}">
                        <div class="col col-md-4">
                            <h4 class="text-center"><u>Dane adresowe</u></h4>
                            <h6 class="text-center">Kraj: ${room.apartment.country.name}</h6>
                            <h6 class="text-center">Miasto: ${room.apartment.city}</h6>
                            <h6 class="text-center">Kraj: ${room.apartment.address}</h6>
                        </div>
                    </c:if>
                    <div class="col col-md-4">
                        <c:if test="${room.interconnecting==true||room.isBalcony==true||room.withKitchenAnex==true || room.withOwnBathroom== true}">
                            <h4 class="text-center"><u>Dodatkowe informacje</u></h4>
                            <ul class="text-center">
                                <c:if test="${room.interconnecting==true}">
                                    <li class="text-center"><h6><em>Pokój jest przechodni</em></h6></li>
                                </c:if>
                                <c:if test="${room.isBalcony==true}">
                                    <li><h6><em>Pokój posiada balkon</em></h6></li>
                                </c:if>
                                <c:if test="${room.withOwnBathroom==true}">
                                    <li><h6><em>Pokój posiada własną łazienkę</em></h6></li>
                                </c:if>
                                <c:if test="${room.withKitchenAnex==true}">
                                    <li><h6><em>Pokój z anksem kuchennym</em></h6></li>
                                </c:if>
                            </ul>
                        </c:if>
                    </div>
                </div>

<%--                AKcja--%>
                <div class="row justify-content-md-center align-items-center mb-4">
                    <div class="col-md-auto">
                        <h4 class="text-center"><u>Akcja</u></h4>
                    </div>
                </div>
                <c:if test="${room.apartment.owner.id==userId}">
                    <div class="row justify-content-md-center mb-4">
                        <div class="col-md-2">
                            <a href="/room/edit/${room.id}" class="btn btn-danger text-center">Dodaj pokój</a>
                        </div>
                        <div class="col-md-2">
                            <a href="/room/listByApart/${room.id}" class="btn btn-danger text-center">Zarządzaj
                                pokojami</a>
                        </div>
                        <div class="col-md-2">
                            <a href="/room/edit/${room.id}" class="btn btn-danger text-center">Edytuj
                                apartament</a>
                        </div>
                        <div class="col-md-2">
                            <a href="/reservation/addRoom/${room.id}" class="btn btn-danger text-center">Rezerwuj
                                apartament</a>
                        </div>

                    </div>
                </c:if>
                <c:if test="${room.apartment.owner.id!=userId}">
                    <div class="row justify-content-md-center mb-4">
                        <div class="col-md-auto">
                            <a href="/reservation/addRoom/${room.id}" class="btn btn-danger text-center"><p class="text-center">Rezerwuj</p></a>
                        </div>

                    </div>
                </c:if>
                <c:if test="${room.apartment.rentWay.id==3}">
                    <div class="row justify-content-md-center">
                        <div class="col-md-auto">
                            <h4 class="text-center"><u>Zdjęcia obiektu</u></h4>

                            <div class="container ">
                                <div class="card mb-3 align-items-center"
                                     style="max-width: 1500px; max-height: 800px;  ">
                                    <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
                                        <div class="carousel-inner">
                                            <c:forEach items="${room.photos}" var="photo">
                                                <c:if test="${room.photos.get(0)==photo}">
                                                    <div class="carousel-item active">
                                                            <%--                                                        <img src="/home/piotr/CodersLAB/LetsCheckIn_pictures/${photo.id}"--%>
                                                            <%--                                                             class="d-block w-100"--%>
                                                            <%--                                                             alt="...">--%>
                                                        <img src="../../../img/LetsCheckIn_pictures/${photo.id}"
                                                             class="d-block w-100"
                                                             alt="...">
                                                    </div>
                                                </c:if>
                                                <c:if test="${room.photos.get(0)!=photo}">
                                                    <div class="carousel-item ">
                                                        <img src="../../../img/LetsCheckIn_pictures/${photo.id}"
                                                             class="d-block w-100"
                                                             alt="...">
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                        <button class="carousel-control-prev" type="button"
                                                data-bs-target="#carouselExampleControls"
                                                data-bs-slide="prev">
                                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                            <span class="visually-hidden">Previous</span>
                                        </button>
                                        <button class="carousel-control-next" type="button"
                                                data-bs-target="#carouselExampleControls"
                                                data-bs-slide="next">
                                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                            <span class="visually-hidden">Next</span>
                                        </button>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </c:if>
            </div>

    </section>

</main>
<!-- End #main -->
<jsp:include page="../Headers_footers/footer.jsp"/>