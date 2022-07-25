package com.jzj.vblog.utils.sign;

import com.jzj.vblog.utils.result.BusinessException;
import com.jzj.vblog.web.pojo.vo.UserInfoVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Jzj
 * @version 1.0
 * @date 2021/7/24 21:57
 */
public class JwtUtils {

    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    public static final String APP_SECRET = "ukc8BDbRzgUDaY6pZFfWus2jZWLPHO";

    public static String getJwtToken(UserInfoVo vo){

        String JwtToken = Jwts.builder()
                //头部信息
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //时间设置
                .setSubject("vblog-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                //主体信息
                .claim("id",vo.getId() )
                .claim("name", vo.getName())
                .claim("introduction",vo.getIntroduction())
                .claim("avatar",vo.getAvatar())
                .claim("roles",vo.getRoles())
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
            e.printStackTrace();
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
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token获取会员id
     * @param request
     * @return
     */
    public static Integer getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if(StringUtils.isEmpty(jwtToken)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (Integer)claims.get("id");
    }

    /**
     * 根据token获取用户信息
     */
    public static UserInfoVo getUserByJwtToken(String token){
        //判断token是否有效
        if(StringUtils.isEmpty(token)) return null;
        boolean flag = checkToken(token);
        if(!flag) throw new BusinessException("token无效,数据异常");
        //获取用户信息
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        UserInfoVo user = new UserInfoVo();
        user.setName((String) claims.get("name"));
        user.setAvatar((String) claims.get("avatar"));
        user.setIntroduction((String) claims.get("introduction"));
        user.setRoles((String) claims.get("roles"));
        return user;
    }
}
