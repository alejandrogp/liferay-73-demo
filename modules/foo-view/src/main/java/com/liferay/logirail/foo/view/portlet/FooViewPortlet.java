package com.liferay.logirail.foo.view.portlet;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.logirail.foo.manager.model.Foo;
import com.liferay.logirail.foo.manager.service.FooLocalServiceUtil;
import com.liferay.logirail.foo.view.constants.FooViewPortletKeys;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author L71877487A
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=FooView",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + FooViewPortletKeys.FOOVIEW,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class FooViewPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		Foo foo = FooLocalServiceUtil.createFoo(CounterLocalServiceUtil.increment(Foo.class.getName()));

		foo.setCompanyId(themeDisplay.getCompanyId());
		foo.setField1(" Creado a las " + new Date());
		foo.setField4(new Date());

		FooLocalServiceUtil.updateFoo(foo);


		List<Foo> fooList = FooLocalServiceUtil.getFoos(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		for(Foo f:fooList){
			System.out.println("  " + f.getField1());
		}

		super.doView(renderRequest,renderResponse);
	}

}