package seguridad.filtros;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import seguridad.AutorizacionBasica;

public class FiltroAuthBasica implements javax.servlet.Filter {

    private final String AUTHORIZATION_HEADER = "Authorization";
    private final String PREFIX = "Basic ";

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        boolean validauth = false;
        
        if(request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String authheader = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
            
            if(authheader != null && authheader.startsWith(PREFIX)) {
                String credencialesBase64 = authheader.replaceFirst(PREFIX, "");
                
                if (AutorizacionBasica.autenticar(credencialesBase64)) {
                    validauth = true;
                    chain.doFilter(request, response);
                }
            }

            if(!validauth) {
                if (response instanceof HttpServletResponse) {
                    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    httpServletResponse.addHeader(HttpHeaders.CONTENT_TYPE, "text/html; charset=UTF-8");
                    httpServletResponse.getWriter().write("<h1>401 NO AUTORIZADO</h1>");
                }
            }
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}
