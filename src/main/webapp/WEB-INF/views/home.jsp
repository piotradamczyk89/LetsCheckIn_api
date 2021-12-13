<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="Headers_footers/header.jsp"/>

<!-- ======= Hero Section ======= -->
<section id="hero" class="d-flex flex-column justify-content-center">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-xl-8">
                <h1>Let's Check IN - travell, manage, rent apartments </h1>
                <h2>Where you want to sleep? </h2>
                <form:form method="get" action="/apartment/listSearch" modelAttribute="country">
                    <div class="row">
                        <div class="col">
                            <form:select class="form-select" aria-label=".form-select-sm example"
                                         items="${countries}" itemLabel="name" itemValue="name" path="name"/>
                        </div>
                        <div class="col">
                            <input type="text" name="city" class="form-control" placeholder="<spring:message code="City.type"/>"
                                   aria-label="Last name">
                        </div>
                        <div class="col">
                            <input type="number" name="person" class="form-control" placeholder="<spring:message code="HowManyPerson"/>"
                                   aria-label="Last name">
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </div>
                    <div class="row">
                        <div class="col">
                            <input type="date" class="form-select" aria-label=".form-select-sm example"
                                   name="startDate" placeholder="Wybierze poczatek"/>
                        </div>
                        <div class="col">
                            <input type="date" class="form-select" aria-label=".form-select-sm example"
                                   name="endDate" placeholder="Wybierze koniec"/>
                        </div>
                    </div>
                    <input type="submit" value="<spring:message code="Send"/>"/>
                </form:form>

            </div>
        </div>
    </div>
</section>
<!-- End Hero -->


<jsp:include page="Headers_footers/footer.jsp"/>
