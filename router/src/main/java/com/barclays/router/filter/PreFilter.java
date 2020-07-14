package com.barclays.router.filter;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class PreFilter extends ZuulFilter{
private static Logger log=LoggerFactory.getLogger(PreFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context=RequestContext.getCurrentContext();
		HttpServletRequest request=context.getRequest();
		log.info("PreFilter : "+ String.format(" %s request to %s "+request.getMethod() , request.getRequestURL().toString()));
		return null;
	}

	@Override
	public String filterType() {
		return "Pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
