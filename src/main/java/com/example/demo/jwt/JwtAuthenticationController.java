package com.example.demo.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	Logger log = LoggerFactory.getLogger(JwtAuthenticationController.class);

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUserDetailService jwtUserDetailService;

	@RequestMapping(value = "/authorize", method = RequestMethod.POST)
	public ResponseEntity<?> authenticate(@RequestBody JwtTokenRequest tokenRequest) throws Exception {
		authenticate(tokenRequest.getUsername(), tokenRequest.getPassword());

		final UserDetails user = jwtUserDetailService.loadUserByUsername(tokenRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(user);

		return ResponseEntity.ok(new JwtResponseToken(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			log.error("User is disabled " + username);
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			log.error("Bad credentials " + username);
			throw new Exception("INVALID_CREDENTIALS", e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
