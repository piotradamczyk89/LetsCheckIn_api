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
                                <p>Podaj secret_id oraz secret_key</p>
                                <p>Wartości te trzeaba wygenerować po utworzeniu konta <a href="https://ob.nordigen.com/user-secrets/">tutaj</a></p>
                            </div>
                            <div class="card-body">
                                <form method="post">
                                    <label class="card-text"> Ppodaj secret Id
                                        <input name="secretId" type="text"/>
                                    </label>
                                    <label class="card-text"> Podaj secret key
                                        <input name="secretKey" type="text"/>
                                    </label>
                                    <input type="submit" value="Wyslij">
                                </form>

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