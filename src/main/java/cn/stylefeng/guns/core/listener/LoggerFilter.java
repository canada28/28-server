package cn.stylefeng.guns.core.listener;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author canrom7
 * @create 2017-12-24 1:08
 * @email canrom7@outlook.com
 * 请求日志打印
 **/
@Order(1)
public class LoggerFilter implements Filter {
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static boolean isInit = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        if (servletRequest instanceof HttpServletRequest) {
            request = (HttpServletRequest) servletRequest;
            String ip = getIpAddr(request);
            System.out.println();
            System.out.println("------------------------|" + dateFormat.format(new Date()) + "|-------------------------");
            System.out.println("请求IP：" + ip);
            System.out.println("请求uri：" + request.getMethod() + " " + request.getRequestURI());
            StringBuilder strs = new StringBuilder();
            for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
                for (String str : entry.getValue()) {
                    strs.append("[").append(entry.getKey().toString()).append(" ：").append(str).append("]").append(" ");
                }

            }
            System.out.println("请求params：" + strs);
            System.out.println();
        } else {
            try {
                servletResponse.getWriter().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    /**
     * 获取请求主机的地址
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}