package com.puppy.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class JwtUtils {

    private final String HEADER_PREFIX  = "Bearer ";
    private final String JWT_SECRET = "ToS9IQ4IV9w1QZtAeVnJaQ==";
    private final long JWT_TTL = 1000 * 60 * 60 * 12L;  //12小时

    /**
     *  获取前缀
     * */
    public String getAuthorizationHeaderPrefix(){
        return HEADER_PREFIX ;
    }

    public String removePrefix(String token){
        return token.substring(HEADER_PREFIX.length());
    }

    public String addPrefix(String token){
        return HEADER_PREFIX + token;
    }

    /**
     *  生成token
     * */
    public String createToken(Map<String,String> contents)
    {
        JwtBuilder builder = Jwts.builder();
        //设置载荷
        contents.forEach((key, val) -> builder.claim(key,val));

        //添加Token过期时间
        Date exp = new Date(System.currentTimeMillis() + JWT_TTL);
        builder.setExpiration(exp);

        //生成JWT
        return addPrefix(builder.signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact());
    }

    //获取用户权限
    public Map<String,String> getAuthentication(String token){
        Claims claims = Jwts.parser()                           //解析Token的payload
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(removePrefix(token))
                .getBody();
        return null;
    }

    //验证Token是否正确
    public boolean validateToken(String token){
        try {
            if(StringUtils.hasText(token) && token.startsWith(HEADER_PREFIX)) {
                Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(removePrefix(token));   //通过密钥验证Token
                return true;
            }
            else
            {
                log.error("Invalid JWT token : token not exists or prefix error");
            }
        }catch (SignatureException e) {                                     //签名异常
            log.error("Invalid JWT signature: {}", e);
        } catch (MalformedJwtException e) {                                 //JWT格式错误
            log.error("Invalid JWT token trace: {}", e);
        } catch (ExpiredJwtException e) {                                   //JWT过期
            log.error("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {                               //不支持该JWT
            log.error("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {                              //参数错误异常
            log.error("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }

}
