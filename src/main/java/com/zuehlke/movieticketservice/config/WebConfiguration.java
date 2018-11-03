package com.zuehlke.movieticketservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;


/**
 * Configures how resources should be handled.
 * Server calls directed to other routes than our API base path should redirect to the frontend SPA.
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Value("${api.base.path}")
    private String apiBasePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**/*.css", "/**/*.html", "/**/*.js", "/**/*.jsx", "/**/*.png", "/**/*.svg","/**/*.ttf", "/**/*.woff", "/**/*.woff2")
                .setCachePeriod(0)
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("/", "/**")
                .setCachePeriod(0)
                .addResourceLocations("classpath:/static/index.html")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) {
                        if (resourcePath.startsWith(apiBasePath) || resourcePath.startsWith(apiBasePath.substring(1))) {
                            return null;
                        }
                        return location.exists() && location.isReadable()
                                ? location
                                : null;
                    }
                });
    }

}
