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
    <div class="container">
      <div class="row justify-content-md-start">
          <div class="col">
            <div class="row justify-content-center">
              <div class="card  mb-4" style="max-width: 560px;">
                <div class="card-header">
                  Rachunek do rezerwavji: ${billEdit.reservation.name}
                </div>
                <div class="card-body">
                  <h5 class="card-title">${billEdit.name}</h5>
                  <p class="card-text">Data wystawinia ${billEdit.createDate}</p>
                  <p class="card-text">Termin opłaty ${billEdit.expireDate}</p>
                  <p class="card-text">Wartość rachunku do zapłaty ${billEdit.cost}</p>
                  <form:form method="post" modelAttribute="billEdit">
                    <form:checkbox path="paid"/>
                    <form:hidden path="id" value="${billEdit.id}"/>
                    <form:hidden path="name" value="${billEdit.name}"/>
                    <form:hidden path="reservation" value="${billEdit.reservation.id}"/>
                    <form:hidden path="createDate" value="${billEdit.createDate}"/>
                    <form:hidden path="expireDate" value="${billEdit.expireDate}"/>
                    <form:hidden path="cost" value="${billEdit.cost}"/>
                    <p class="card-text">Czy rachunek opłacony?</p>
                    <input type="submit" value="Update">
                  </form:form>

                </div>
              </div>
            </div>
          </div>
      </div>

    </div>
  </section>

</main>
<!-- End #main -->
<jsp:include page="../Headers_footers/footer.jsp"/>