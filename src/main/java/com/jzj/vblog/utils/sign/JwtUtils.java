package com.jzj.vblog.utils.sign;

import com.jzj.vblog.security.custom.CustomUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Jzj
 * @version 1.0
 * @date 2021/7/24 21:57
 */
public class JwtUtils {

    private final static Logger log = LoggerFactory.getLogger(JwtUtils.class);

    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    public static final String APP_SECRET = "ukc8BDbRzgUDaY6pZFfWus2jZWLPHO";

    public static String getJwtToken(CustomUser customUser){

        String JwtToken = Jwts.builder()
                //头部信息
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //时间设置
                .setSubject("AUTH-USER")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                //主体信息
                .claim("userId",customUser.getSysUser().getId() )
                .claim("username", customUser.getSysUser().getUsername())
                //签名哈希
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();

        return JwtToken;
    }

    /**
     * 判断token是否存在与有效
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            log.error("checkToken(String jwtToken)方法数据异常:{}",e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     * @param request
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if(StringUtils.isEmpty(jwtToken)) return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            log.error("checkToken(HttpServletRequest request)数据异常:{}",e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 根据token获取会员id
     * @param request
     * @return
     */
    public static String getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if(StringUtils.isEmpty(jwtToken)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("userId");
    }

    /**
     * 根据token获取会员姓名
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        try {
            if (StringUtils.isEmpty(token)) return "";
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("username");
        } catch (Exception e) {
            log.error("getUsername(String token)方法数据异常:{}",e.getMessage());
            return null;
        }
    }
}
