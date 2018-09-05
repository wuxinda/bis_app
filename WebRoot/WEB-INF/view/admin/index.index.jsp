<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="vbox">          
    <header class="header bg-white b-b clearfix">
        <p class="h4">Welcome, ${loggedInUser.userName}</p>
    </header>
    
    <section class="scrollable wrapper w-f">
    
    </section>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
