package th.ac.ku.menu.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;

@Configuration //springframwork
@EnableWebSecurity //เป็นweb securityเลยมีwebเพิ่มขึ้นมา
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //เอาWebSecurityConfigurerAdapter มาoveride methodที่มีในนั้นอยู่แล้ว

    @Value("${auth0.audience}")
    private String audience; //ไปอ่านจากauth0.audience


    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer; //ไปอ่านจากspring.security.oauth2.resourceserver.jwt.issuer-uri


    @Override
    protected void configure(HttpSecurity http) throws Exception {//HttpSecurity methodจะreturnค่าเป็นตัวเอง
//        http
//                .csrf().disable()//csrf()ให้เราไปคลิ๊กลิ้งค์หลอกจะเกิดที่frontend แต่apiเป็น stateless ไม่มีcsrfเลยdisable
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated();
//        //เรียก method มาSETค่าในmethodนั้น ปกติต้องเรียก http.setCSRF(false)ซึ่งเป็นการsetแยก ลำบากใช้ set method chainingได้ http..csrf().disable().authorizeRequests()
//        //พอchaining ถ้าmethodเยอะจะยาวเลยมีการทำแบบข้างบนในcodeคือตัดcode
//        //อันนี้จะเหมือนเป็น http.csrf().disable() ,http.authenticated() -> ต้องการควบคุมการเข้าถึงโปรแกรมนี้ โดยเมืื่อ requestใดๆ ต้องมีการ authenticate ก่อน
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()

                // use stateless session, so user's state is not stored ตัวdefaultของมันคือ statefull แต่เราจะใช้ stateless
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //ใช้stateless

                // use oauth as a resource server to do jwt validation ->บอกว่าจะใช้ OAuth ในการทำ jwt validation
                .and()
                .oauth2ResourceServer()
                .jwt()
                .decoder(jwtDecoder());//ใช้jwtDecoder()

    }

    private JwtDecoder jwtDecoder() { //สร้างอ๊อบเจ็ค JwtDecoder ในการถอดรหัสจาก JWT token ที่ได้รับมา
        OAuth2TokenValidator<Jwt> withAudience =
                new AudienceValidator(audience);

        OAuth2TokenValidator<Jwt> withIssuer =
                JwtValidators.createDefaultWithIssuer(issuer);

        OAuth2TokenValidator<Jwt> validator =
                new DelegatingOAuth2TokenValidator<>(withAudience, withIssuer);

        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder)
                JwtDecoders.fromOidcIssuerLocation(issuer);

        jwtDecoder.setJwtValidator(validator);

        return jwtDecoder;
    }


}
