<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:include page="wrapper.prefix.jsp"/>

<% Exception e = (Exception)request.getAttribute("ex"); %>
<H2>错误: <%= e.getClass().getSimpleName()%></H2>
<hr />
<P>错误描述：</P>
<%= e.getMessage()%>
<P>错误信息：</P>
<% e.printStackTrace(new java.io.PrintWriter(out)); %>
            
<jsp:include page="wrapper.suffix.jsp"/>