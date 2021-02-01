package com.newha.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newha.service.JwtService;
import com.newha.service.UserService;
import com.newha.vo.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//http://localhost/swagger-ui.html
@Api("UserController V1")
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

	@Autowired
	public JavaMailSender javaMailSender;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserService service;

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@ApiOperation(value = "유저 리스트 조회", notes = "유저 리스트를 리턴", response = List.class)
	@GetMapping(value = "/user")
	public List<User> selectAll() {
		return service.selectAll();
	}

	@ApiOperation(value = "아이디 중복검사", notes = "아이디 중복검사결과'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@GetMapping(value = "/idcheck/{id}") // 아이디체크
	public ResponseEntity<Map<String, String>> selectid(
			@ApiParam(value = "id", required = true) @PathVariable String id) {
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		int result = service.selectId(id);
		if (result == 0) {
			map.put("message", "success"); // 0이면 없는거 1이면 있는거
			status = HttpStatus.ACCEPTED;
		} else {
			map.put("message", "fail");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, String>>(map, status);
	}

	@ApiOperation(value = "이메일 인증", notes = "입력값으로 id(email) 주면 이메일 발송. 리턴값은 confirm: 인증번호 ", response = Map.class)
	@GetMapping(value = "/emailauth/{id}") // 이메일 인증
	public ResponseEntity<Map<String, Integer>> emailauth(@ApiParam(value = "String", required = true) @PathVariable String id)
			throws MessagingException {
		int confirm = (int) ((Math.random() * (9999 - 1000)) + 1000);
		HttpStatus status = null;
		Map<String, Integer> map = new HashMap<>();
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			message.setSubject("뉴하 이메일 인증입니다.");
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(id));
			message.setText("인증번호: " + confirm);
			message.setSentDate(new Date());
			javaMailSender.send(message);
			map.put("confirm", confirm);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Integer>>(map, status);
	}

	@ApiOperation(value = "내가 구독한 큐레이터", notes = "아이디 입력하면 구독한 큐레이터 thumbnail_path, name 리턴", response = ArrayList.class)
	@GetMapping(value = "/subscribe/{id}") // 내가 구독한 큐레이터
	public ResponseEntity<ArrayList<Map<String, String>>> subscribe(
			@ApiParam(value = "String", required = true) @PathVariable String id) {
		ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
		int userNo = service.userNo(id);
		HttpStatus status = null;
		try {
			List<Integer> l = service.follow(userNo);
			for (int i = 0; i < l.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				User user = service.selectUser(l.get(i));
				map.put("name", user.getName());
				map.put("thumbnail_path", user.getThumbnail_path());
				list.add(map);
			}
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<ArrayList<Map<String, String>>>(list, status);
	}

	@ApiOperation(value = "회원가입", notes = "회원가입 성공 결과'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@PostMapping(value = "/join")
	public ResponseEntity<Map<String, String>> insert(
			@ApiParam(value = "User", required = true) @RequestBody User u /*
																			 * , @ApiParam(value = "tag List", required
																			 * = true)@RequestParam List<String> tag
																			 */ ) {
		Map<String, String> map = new HashMap<>();
		int result = service.insert(u);
		int userNo = service.userNo(u.getId());
		HttpStatus status = null;
		/*
		 * for (int i = 0; i < tag.size(); i++) { service.inserttag(userNo, tag.get(i));
		 * }
		 */
		if (result == 0) {
			map.put("message", "회원가입 실패");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		else {
			map.put("message", "회원가입 성공");
			status = HttpStatus.ACCEPTED;
			}
		return new ResponseEntity<Map<String, String>>(map, status);
	}

	@ApiOperation(value = "회원 탈퇴", notes = "탈퇴 결과'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Map<String, String>> delete(@ApiParam(value = "id", required = true) @PathVariable String id) {
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		try {
			service.delete(id);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, String>>(map, status);
	}

	@ApiOperation(value = "회원 탈퇴", notes = "수정 결과'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@PutMapping(value = "/update")
	public ResponseEntity<Map<String, String>> update(@ApiParam(value = "User", required = true) @RequestBody User u) {
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		try {
			service.update(u);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, String>>(map, status);
	}

	// 토근 유효여부 검사
	@ApiOperation(value = "로그인", notes = "'success' 또는 'fail', httpstatus, userInfo 리턴", response = Map.class)
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<Map<String, Object>> getInfo(@ApiParam(value = "id", required = true) @PathVariable String id,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		System.out.println(id);
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				User user = service.userInfo(id);
				System.out.println(user.getId());
				resultMap.put("userInfo", user);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", "FAIL");
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 로그인
	@ApiOperation(value = "토큰 유효성 검사", notes = "'success' 또는 'fail', token 리턴", response = Map.class)
	@PostMapping(value = "/user/login")
	public ResponseEntity<Map<String, Object>> login(@ApiParam(value = "id", required = true) @RequestBody User user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			User loginUser = service.login(user);
			if (loginUser != null) {
				String token = jwtService.create("id", loginUser.getId(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 사람검색
	@ApiOperation(value = "유저 검색", notes = "유저 List 리턴", response = List.class)
	@GetMapping(value = "/search/people/{keyword}")
	public List<User> searchUser(@ApiParam(value = "keyword", required = true) @PathVariable String keyword) {
		return service.searchUser(keyword + "%");
	}
}