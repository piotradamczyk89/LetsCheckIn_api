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
    <div class="container room">
      <div class="row gy-3">
        <div class="col">
          <div class="card" style="width: 18rem;">
            <div class="card-body">
              <form:form id="formRoom" modelAttribute="room" enctype="multipart/form-data" method="post">
                <label> <spring:message code="HowMayPersonInRoom"/>
                  <form:input type="number" path="persons" class="form-control"/>
                </label>
                <label> <spring:message code="SingleBed"/>
                  <form:input type="number" path="singleBed" class="form-control"/>
                </label>
                <label> <spring:message code="doubleBed"/>
                  <form:input type="number" path="doubleBed" class="form-control"/>
                </label>
                <label> Ilość rozkładanych kanap
                  <form:input type="number" path="coach" class="form-control"/>
                </label>
                <label> Cana za wynajęcia pokoju
                  <form:input type="number" path="roomPrice" class="form-control"/>
                </label>
                <div class="form-check form-switch">
                  <form:checkbox path="withOwnBathroom" class="form-check-input" role="switch" id="flexSwitchCheckDefault"/>
                  <label class="form-check-label" for="flexSwitchCheckDefault">Czy pokój posiada własną łazienkę?</label>
                </div>
                <div class="form-check form-switch">
                  <form:checkbox path="withKitchenAnex" class="form-check-input" role="switch" id="flexSwitchCheckDefault"/>
                  <label class="form-check-label" for="flexSwitchCheckDefault">Czy salon jest połączony z kuchnią?</label>
                </div>
                <div class="form-check form-switch">
                  <form:checkbox path="interconnecting" class="form-check-input" role="switch" id="flexSwitchCheckDefault"/>
                  <label class="form-check-label" for="flexSwitchCheckDefault">Czy pokój jest przechodni?</label>
                </div>
                              <div class="form-check form-switch">
                  <form:checkbox path="isBalcony" class="form-check-input" role="switch" id="flexSwitchCheckDefault"/>
                  <label class="form-check-label" for="flexSwitchCheckDefault">Czy pokój ma balkon?</label>
                </div><br><br>

                <div class="mb-3">
                  <label for="formFileMultiple" class="form-label"><spring:message
                          code="UpladPicture"/>
                    <input class="form-control" type="file" id="formFileMultiple" multiple
                           name="pictures" accept="image/png, image/jpeg"/>
                    <div id="outputDiv" class="container-sm" style="max-width: 300px;">
                      <img id="output" class="img-fluid img-thumbnail"/>
                    </div>
                  </label>
                </div>
              </form:form>
            </div>
          </div>

        </div>
        <%--                </c:forEach>--%>

        <%--</a>--%>
      </div>
      <button id="formSubmited" type="button" class="btn btn-secondary"><spring:message code="Send"/></button>

    </div>
  </section>

</main>
<!-- End #main -->
<jsp:include page="../Headers_footers/footer.jsp"/>