package com.liferay.logirail.foo.view.action;

import com.liferay.logirail.foo.manager.model.Foo;
import com.liferay.logirail.foo.manager.service.FooLocalServiceUtil;
import com.liferay.logirail.foo.view.constants.FooViewPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + FooViewPortletKeys.FOOVIEW,
                "mvc.command.name=/foo/delete"
        },
        service = MVCActionCommand.class
)
public class DeleteFooActionCommand implements MVCActionCommand{

    private static Log _log = LogFactoryUtil.getLog(DeleteFooActionCommand.class);

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

        long fooEditId 	= ParamUtil.getLong(actionRequest, FooViewPortletKeys.FOO_EDIT_ID, 0);

        Foo foo = FooLocalServiceUtil.fetchFoo(fooEditId);

        if(Validator.isNotNull(foo)){
            //Delete Foo
            FooLocalServiceUtil.deleteFoo(foo);
        }

        SessionMessages.add(actionRequest, "delete-success");

        actionResponse.setRenderParameter("jspPage", "/view.jsp");

        return true;
    }
}
