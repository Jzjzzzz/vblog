package com.jzj.vblog.utils.sign;

import com.jzj.vblog.security.custom.CustomUser;
import com.jzj.vblog.utils.result.BusinessException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * <p>
 * Jwt工具类
 * </p>
 *
 * @author Jzj
 * @since 2021/7/24 21:57
 */
public class JwtUtils {

    public static final long EXPIRE = 1000 * 60 * 60 * 24;

    public static final String APP_SECRET = "ukc8BDbRzgUDaY6pZFfWus2jZWLPHO";

    public static final String USERID = "userId";

    public static final String USERNAME = "username";

    public static String getJwtToken(CustomUser customUser){

        return Jwts.builder()
                //头部信息
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //时间设置
                .setSubject("AUTH-USER")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                //主体信息
                .claim(USERID, customUser.getSysUser().getId())
                .claim(USERNAME, customUser.getSysUser().getUsername())
                //签名哈希
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
    }

    /**
     * 判断token是否存在与有效
     */
    public static boolean checkToken(String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String getSubject(String token,String key){
        try {
            if(!checkToken(token)) throw new BusinessException("token无效");
            if (StringUtils.isEmpty(token)) return "";
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get(key);
        } catch (Exception e) {
            return null;
        }
    }
}
