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
                        <h2 class="text-center"><u>${apartment.name}</u></h2>
                    </div>
                </div>
                <div class="row justify-content-md-center">
                    <div class="col ">
                        <h4 class="text-center"><u>Dane Adresowe</u></h4>

                    </div>
                    <c:if test="${apartment.rentWay.id!=3}">
                        <div class="col">
                            <h4 class="text-center"><u>Cena</u></h4>
                        </div>
                    </c:if>
                    <div class="col">
                        <h4 class="text-center"><u>Szczegóły obiektu</u></h4>
                    </div>
                </div>
                <div class="row justify-content-md-start mb-4">
                    <div class="col">
                        <h6 class="text-center"> Kraj: <strong>${apartment.country.name}</strong></h6>
                        <h6 class="text-center"> Miasto: <strong>${apartment.city}</strong></h6>
                        <h6 class="text-center"> Adres: <strong>${apartment.address}</strong></h6>
                    </div>
                    <c:if test="${apartment.rentWay.id!=3}">
                        <div class="col">
                            <c:if test="${apartment.rentWay.id==1}">
                            <h6 class="text-center">
                                    <spring:message code="DayPrice"/>
                                </c:if>
                                <c:if test="${apartment.rentWay.id==2}">
                                <h6 class="text-center">Cena za miesiąc:
                                    </c:if>
                                    <strong> ${apartment.apartmentPrice} </strong></h6>
                        </div>
                    </c:if>
                    <div class="col">
                        <h6 class="text-center"><spring:message code="RoomAmount"/>
                            <strong>${apartment.rooms.size()}</strong></h6>
                        <c:if test="${apartment.rentWay.id!=3}">
                            <h6 class="text-center"> Liczba łazienek: <strong>${apartment.toilets}</strong></h6>
                            <h6 class="text-center">Powierzchnia apartamentu: <strong>${apartment.area}</strong></h6>
                        </c:if>
                    </div>
                </div>

                <%-- ----------Action for Users -----------------%>
                <div class="row justify-content-md-center align-items-center mb-4">
                    <div class="col-md-auto">
                        <h4 class="text-center"><u>Akcja</u></h4>
                    </div>
                </div>
                <c:if test="${apartment.owner.id==user.id}">
                    <div class="row justify-content-md-center mb-4">
                        <div class="col-md-2">
                            <a href="/room/add/${apartment.id}" class="btn btn-danger text-center">Dodaj pokój</a>
                        </div>
                        <div class="col-md-2">
                            <a href="/room/listByApart/${apartment.id}" class="btn btn-danger text-center">Zarządzaj
                                pokojami</a>
                        </div>
                        <div class="col-md-2">
                            <a href="/apartment/edit/${apartment.id}" class="btn btn-danger text-center">Edytuj
                                apartament</a>
                        </div>
                        <div class="col-md-2">
                            <a href="/apartment/delete/${apartment.id}" class="btn btn-danger text-center">Usuń
                                apartament</a>
                        </div>

                    </div>
                </c:if>
                <c:if test="${apartment.owner.id!=user.id}">
                    <div class="row justify-content-md-center mb-4">
                        <div class="col-md-2">
                            <a href="/reservation/addApart/${apartment.id}" class="btn btn-danger text-center">Rezerwuj</a>
                        </div>

                    </div>
                </c:if>
                <c:if test="${apartment.rentWay.id!=3}">
                    <div class="row justify-content-md-center mb-4 ">
                        <div class="col-md-auto">
                            <h4><u>Szczegóły pokojów</u></h4>
                        </div>
                    </div>

                    <div class="row row-cols-3 justify-content-md-start">
                        <c:forEach items="${apartment.rooms}" var="room">
                            <div class="col">
                                <h4><u>${room.roomName.name}
                                    <c:if test="${room.persons!=0 && room.persons!=null}"> ${room.persons}-os</c:if> </u>
                                </h4>
                                <c:if test="${apartment.rentWay.id==3}">
                                    <h6>Cena pokoju za dobe: ${room.roomPrice}</h6>
                                </c:if>
                                <c:if test="${room.singleBed!=null && room.singleBed!=0}">
                                    <h6>Liczba łóżek pojedyńczych: ${room.singleBed}</h6>
                                </c:if>
                                <c:if test="${room.doubleBed!=null && room.doubleBed!=0}">
                                    <h6>Liczba łóżek podwójnych: ${room.doubleBed}</h6>
                                </c:if>
                                <c:if test="${room.coach!=null && room.coach!=0}">
                                    <h6>Liczba rozkładanych kanap: ${room.coach}</h6>
                                </c:if>

                                <c:if test="${room.interconnecting==true||room.isBalcony==true||room.withKitchenAnex==true || room.withOwnBathroom== true}">
                                    <h5>Dodatkowe informacje</h5>
                                    <ul>
                                        <c:if test="${room.interconnecting==true}">
                                            <li><h6><em>Pokój jest przechodni</em></h6></li>
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
                        </c:forEach>
                    </div>
                </c:if>
                <c:if test="${apartment.rentWay.id!=3}">
                    <div class="row justify-content-md-center">
                        <div class="col-md-auto">
                            <h4 class="text-center"><u>Zdjęcia obiektu</u></h4>

                            <div class="container ">
                                <div class="card mb-3 align-items-center"
                                     style="max-width: 1500px; max-height: 800px;  ">
                                    <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
                                        <div class="carousel-inner">
                                            <c:forEach items="${apartment.photos}" var="photo">
                                                <c:if test="${apartment.photos.get(0)==photo}">
                                                    <div class="carousel-item active">
                                                            <%--                                                        <img src="home/piotr/CodersLAB/LetsCheckIn_pictures/${photo.id}.jpg"--%>
                                                            <%--                                                             class="d-block w-100"--%>
                                                            <%--                                                             alt="...">--%>
                                                        <img src="../../../img/LetsCheckIn_pictures/${photo.id}"
                                                             class="d-block w-100"
                                                             alt="...">
                                                    </div>
                                                </c:if>
                                                <c:if test="${apartment.photos.get(0)!=photo}">
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
        </div>

    </section>

</main>
<!-- End #main -->
<jsp:include page="../Headers_footers/footer.jsp"/>