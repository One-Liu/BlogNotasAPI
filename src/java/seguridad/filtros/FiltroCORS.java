package seguridad.filtros;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.HttpHeaders;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FiltroCORS implements Filter {

    public static final String ORIGIN_HEADER = "Origin";

    public FiltroCORS() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletResponse customresponse = (HttpServletResponse) response;
            String[] origins = {
                "ClienteEscritorio-Code777"
            };
            
            Set<String> allowedOrigins = new HashSet<String>(Arrays.asList(origins));
            
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String originHeader = httpServletRequest.getHeader(ORIGIN_HEADER);
            
            if (allowedOrigins.contains(originHeader)) {
                customresponse.addHeader("Access-Control-Allow-Origin", originHeader);
                customresponse.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
                customresponse.addHeader("Access-Control-Allow-Headers", "*");
                
                if (httpServletRequest.getMethod().equals("OPTIONS")) {
                    customresponse.setStatus(HttpServletResponse.SC_ACCEPTED);

                    return;
                }
                
                chain.doFilter(request, customresponse);
            } else {
                customresponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                customresponse.addHeader(HttpHeaders.CONTENT_TYPE, "text/html; charset=UTF-8");
                customresponse.getWriter().write("<h1>401 NO AUTORIZADO - ORIGEN NO VÁLIDO</h1>");
            }
        }
    }
}
