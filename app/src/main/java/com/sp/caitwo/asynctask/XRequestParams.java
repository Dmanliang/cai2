package com.sp.caitwo.asynctask;

import com.sp.caitwo.App;
import com.sp.caitwo.bean.UserInfoBean;
import com.sp.xwjlibrary.util.ACache;
import com.sp.xwjlibrary.util.BaseUtil;

import org.xutils.http.RequestParams;
import org.xutils.http.app.ParamsBuilder;

public class XRequestParams extends RequestParams {

    public XRequestParams() {
        super();
        addHeaderInfo();
    }

    private void addHeaderInfo() {
        this.addParameter("client_from","android");
        if (!BaseUtil.isEmpty(App.userInfoBean.getData().getAccess_token())) {
            this.addHeader("Authorization", App.userInfoBean.getData().getAccess_token());
        }
    }

    public XRequestParams(String uri) {
        super(uri);
        addHeaderInfo();
    }

    public XRequestParams(String uri, ParamsBuilder builder, String[] signs, String[] cacheKeys) {
        super(uri, builder, signs, cacheKeys);
        addHeaderInfo();
    }
}
