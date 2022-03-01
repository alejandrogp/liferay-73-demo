package com.liferay.logirail.foo.view.action;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.logirail.foo.manager.model.Foo;
import com.liferay.logirail.foo.manager.service.FooLocalServiceUtil;
import com.liferay.logirail.foo.view.constants.FooViewPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + FooViewPortletKeys.FOOVIEW,
                "mvc.command.name=/foo/save"
        },
        service = MVCActionCommand.class
)
public class SaveFooActionCommand implements MVCActionCommand{

    private static Log _log = LogFactoryUtil.getLog(SaveFooActionCommand.class);

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        long fooEditId 	= ParamUtil.getLong(actionRequest, FooViewPortletKeys.FOO_EDIT_ID, 0);
        String field1 	= ParamUtil.getString(actionRequest, FooViewPortletKeys.FIELD1, "");
        boolean field2 	= ParamUtil.getBoolean(actionRequest, FooViewPortletKeys.FIELD2, false);
        int field3 	    = ParamUtil.getInteger(actionRequest, FooViewPortletKeys.FIELD3, 0);
        String field4 	= ParamUtil.getString(actionRequest, FooViewPortletKeys.FIELD4, "");
        String field5 	= ParamUtil.getString(actionRequest, FooViewPortletKeys.FIELD5, "");

        Foo foo = FooLocalServiceUtil.fetchFoo(fooEditId);

        Date date = new Date();
        try {
            date = df.parse(field4);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(Validator.isNull(foo)){
            //Create new Foo
            foo = FooLocalServiceUtil.createFoo(CounterLocalServiceUtil.increment(Foo.class.getName()));
        }

        foo.setField1(field1);
        foo.setField2(field2);
        foo.setField3(field3);
        foo.setField4(date);
        foo.setField5(field5);

        FooLocalServiceUtil.updateFoo(foo);

        SessionMessages.add(actionRequest, "save-success");

        actionResponse.setRenderParameter(FooViewPortletKeys.FOO_EDIT_ID, String.valueOf(fooEditId));
        actionResponse.setRenderParameter("jspPage", "/edit.jsp");

        return true;
    }
}
