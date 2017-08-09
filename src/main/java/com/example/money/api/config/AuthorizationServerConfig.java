package com.example.money.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig  extends AuthorizationServerConfigurerAdapter{

	//responsavel por pegar usuario e senha da app
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
			.inMemory()
			.withClient("angular")
			.secret("@angul@r0")
			.scopes("read", "write")
			.authorizedGrantTypes("password","refresh_token") // usado pra nos dar um novo acess token
			.accessTokenValiditySeconds(20) //tempo de vida do acess token
			.refreshTokenValiditySeconds(3600 * 24); // tempo de vida do refresh token
	}
	
	// para a app angular buscar o token e enviar de volta 
	//pra acessar e validá-lo
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
				.tokenStore(tokenStore())
				.accessTokenConverter(accessTokenConverter())
				.reuseRefreshTokens(false) //sempre que pedir um acess token, um novo refresh token 
				//sera enviado, enquando o usuario estiver usando a app, não sera deslogado
				.authenticationManager(authenticationManager); //<< validar credenciais
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey("senh@quevalid@otoken"); // senha que valida o token
		return jwtAccessTokenConverter;
	}

	//onde vou armazenar o token
	@Bean
	public TokenStore tokenStore() {	
		return new JwtTokenStore(accessTokenConverter());
	}	
	
	
	
	
	
	
	
	
	
}
