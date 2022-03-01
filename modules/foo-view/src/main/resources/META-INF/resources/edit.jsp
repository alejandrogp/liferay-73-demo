<%@ page import="com.liferay.logirail.foo.view.constants.FooViewPortletKeys" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.logirail.foo.manager.service.FooLocalServiceUtil" %>
<%@ page import="com.liferay.logirail.foo.manager.model.Foo" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ include file="init.jsp" %>

<%
    long fooEditId = ParamUtil.getLong(request, FooViewPortletKeys.FOO_EDIT_ID, 0);

    Foo foo = FooLocalServiceUtil.fetchFoo(fooEditId);

    Calendar calendar = Calendar.getInstance();
    if(Validator.isNotNull(foo)){
        calendar.setTime(foo.getField4());
    }else{
        calendar.setTime(new Date());
    }
%>

<liferay-ui:success key="save-success" message="save.success" />
<liferay-ui:error key="save-error" message="save.error" />

<liferay-portlet:actionURL name="/foo/save" var="saveURL" />
<aui:form  name="edit_form" action="<%=saveURL.toString()%>" method="post">

    <aui:input name="<%=FooViewPortletKeys.FOO_EDIT_ID%>" value="<%=fooEditId%>" type="hidden" />

    <h2><liferay-ui:message key="foo.title"/></h2>

    <aui:input name="<%=FooViewPortletKeys.FIELD1%>" type="text" label="field1" value="<%=Validator.isNotNull(foo)?foo.getField1():""%>">
        <aui:validator name="required" errorMessage="validator.required" />
    </aui:input>

    <aui:select name="<%=FooViewPortletKeys.FIELD2%>" label="field2">
        <aui:option value="true" selected="<%=Validator.isNotNull(foo)?foo.getField2():false%>"><liferay-ui:message key="true"/></aui:option>
        <aui:option value="false" selected="<%=Validator.isNotNull(foo)?foo.getField2():false%>"><liferay-ui:message key="false"/></aui:option>
    </aui:select>

    <aui:input name="<%=FooViewPortletKeys.FIELD3%>" type="text" label="field3" value="<%=Validator.isNotNull(foo)?foo.getField3():""%>">
        <aui:validator name="number" errorMessage="validator.numeric" />
    </aui:input>

    <label for="<%=FooViewPortletKeys.FIELD4%>"><liferay-ui:message key="field4"/></label>
    <liferay-ui:input-date name="<%=FooViewPortletKeys.FIELD4%>"
       yearValue="<%=calendar.get(Calendar.YEAR)%>"
       monthValue="<%=calendar.get(Calendar.MONTH)%>"
       dayValue="<%=calendar.get(Calendar.DAY_OF_MONTH)%>" />

    <aui:input name="<%=FooViewPortletKeys.FIELD5%>" type="text" label="field5" value="<%=Validator.isNotNull(foo)?foo.getField5():""%>"></aui:input>

    <aui:button-row>
        <aui:button type="submit" cssClass="btn btn-primary" />

        <portlet:renderURL var="viewURL">
            <portlet:param name="mvcPath" value="/view.jsp"></portlet:param>
        </portlet:renderURL>
        <aui:button type="cancel" onClick="<%= viewURL %>" />
    </aui:button-row>

</aui:form>