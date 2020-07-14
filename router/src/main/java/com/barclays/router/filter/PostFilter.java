package com.barclays.router.filter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class PostFilter extends ZuulFilter{

	private static Logger log=LoggerFactory.getLogger(PostFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		HttpServletResponse httpServletResponse=RequestContext.getCurrentContext().getResponse();
		log.info("PostFilter : "+ String.format("response's content type is "+httpServletResponse.getStatus()));
		return null;
	}

	@Override
	public String filterType() {
		return "Post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
