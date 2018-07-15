package es.jjlop.apiserver.filter;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/*
 * https://github.com/spring-cloud/spring-cloud-netflix/issues/1110
 */
@Component
public class Remove4xxErrorCodeSetZuulFilter extends ZuulFilter
{
    private static final String ERROR_STATUS_CODE_KEY = "error.status_code";

    @Override
    public boolean shouldFilter()
    {
        Integer statusCode = (Integer) RequestContext.getCurrentContext().get(ERROR_STATUS_CODE_KEY);
        return statusCode != null && statusCode >= 400 && statusCode < 500;
    }

    @Override
    public Object run()
    {
        RequestContext.getCurrentContext().set(ERROR_STATUS_CODE_KEY, null);
        return null;
    }

    @Override
    public String filterType()
    {
        return "post";
    }

    @Override
    public int filterOrder()
    {
        return Integer.MIN_VALUE;
    }

}
