<%@ page import="com.liferay.logirail.foo.manager.service.FooLocalServiceUtil" %>
<%@ page import="com.liferay.logirail.foo.manager.model.Foo" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="java.util.List" %>
<%@ include file="/init.jsp" %>

<%
    System.out.println("view.jsp");

    List<Foo> fooList = FooLocalServiceUtil.getFoos(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

    System.out.println("fooList: " + fooList.size());
%>

<ul>
    <c:forEach items="<%=fooList%>" var="foo">
        <li>Field1: ${foo.field1}, Field4: ${foo.field4}</li>
    </c:forEach>
</ul>