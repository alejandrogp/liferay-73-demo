<%@ page import="com.liferay.logirail.foo.manager.service.FooLocalServiceUtil" %>
<%@ page import="com.liferay.logirail.foo.manager.model.Foo" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="java.util.List" %>

<%@ include file="/init.jsp" %>

<%
    List<Foo> fooList = FooLocalServiceUtil.getFoos(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
%>

<style>
    table, th, td {
      border: 1px solid black;
      border-collapse: collapse;
    }
    th, td {
      padding: 10px;
    }
</style>

<portlet:renderURL var="addURL">
    <portlet:param name="mvcPath" value="/edit.jsp"></portlet:param>
</portlet:renderURL>
<aui:button-row>
    <aui:button onClick="<%= addURL.toString() %>" value="new"></aui:button>
</aui:button-row>

<liferay-ui:success key="delete-success" message="delete.success" />
<table>
    <tr>
        <th>FooId</th>
        <th>Field1</th>
        <th>Field2</th>
        <th>Field3</th>
        <th>Field4</th>
        <th>Field5</th>
        <th><liferay-ui:message key="delete"/></th>
    </tr>
    <c:forEach items="<%=fooList%>" var="foo">
        <tr>
            <liferay-portlet:renderURL varImpl="editURL">
                <portlet:param name="fooEditId" value="${foo.fooId}" />
                <portlet:param name="mvcPath" value="/edit.jsp" />
            </liferay-portlet:renderURL>
            <td>
                <a href="<%=editURL.toString()%>">
                    <strong>${foo.fooId}</strong>
                </a>
            </td>
            <td>${foo.field1}</td>
            <td>${foo.field2}</td>
            <td>${foo.field3}</td>
            <td>${foo.field4}</td>
            <td>${foo.field5}</td>

            <liferay-portlet:actionURL name="/foo/delete" var="deleteURL" >
                <portlet:param name="fooEditId" value="${foo.fooId}" />
            </liferay-portlet:actionURL>
            <td>
                <liferay-ui:icon-delete message="delete.confirm" showIcon="<%= true %>" url="<%= deleteURL %>" label="delete"/>
            </td>

        </tr>
    </c:forEach>
</table>