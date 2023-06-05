# Mini Board Project
보드프로젝트
설정 -> 어드민 -> 프론트 순서대로 구현
***

<h1>1Day<h1><br>
***
*스프링부트 기본설정 
*타임리프 설정 
*초기세팅 
- 스프링 부트 초기 설정
 -> 레포지토리 생성, 로컬저장소 만들고 연동하기 (소스트리 클론)
 -> 인텔리제이 파일 생성 (의존라이브러리 함께 설치 )
  - 롬복,스프링부트 웹, 타임리프, 데브툴, JPA , MYSQL Driver, H2, Validation
->추가 메이븐 라이브러리 (My sql connector/j , Thymeleaf layout , ModelMapper) + querydsl디펜더시에는 classifier jakarta 추가 
-> 플러그인 설정 : 백그라운드 프로그램 , 뒤에서 모니터링 하면서 자동생성하는 등의 역할을 함 
-설정 :AutoMaker(프로젝트 자동빌드), fileEncoding:UTF-8
   

*workbench 연결 

*스프링 시큐리티 설정 클래스 생성
*타임리프 기본 레이아웃 -front
Templates 구성 (layouts-adimin/front/mobile ,main-index, outlines-adimin/front/mobile)
  
*front 레이아웃 구성 
 ->버전 정보 입력: 버전이 바뀌거나 했을때 브라우저가 기본적으로 캐싱하는데304, 강제로 갱신하도록 하기위함 -> 개발자는 잘보이지만 사용자는 갱신되지 않으면 안보일수 있기 때문에 필요함 
 ->스프링 시큐리티 csrf요청 위변조 검증 토큰 추가됨 포스트 방식일땐 토큰없으면 오류 발생 ajax 같은경우 발생하기 때문에 meta로  토큰 추가 

*Interceptor 클래스 추가 (요청전 요청후 공통기능, 접근제어 기능)
-> 읽어드릴수 있도록 MvcConfig설정
 ->인터셉터에서 버전관리 (관리자에서 버전바꾸면 자동으로 바뀌도록)
 
 
<h1>2Day<h1><br>
 *타임리프 레이아웃 admin 템플릿 구성
  ->front와 틀 동일
 *css/js 기본틀 추가  
 상단테그에 url에 따라 클래스를 설정해두면 css 설정을 쉽게 할수 있음 
 
 *회원가입: 엔티티, 레포지토리 인터페이스 생성, 서비스 
   *엔티티생성 - 엔티티 베이스 엔티티 (@MappedSuperclass //상위클래스 호환될수 있는 공통 속성, @@EntityListeners(AuditingEntityListener.class)->mvcConfig에 연결(@EnableAuditing))
   *레포지토리 생성 - MemberRepository (JPA, Qeurydsl 상속)
   *dto 생성 (JoinForm)
  -> 시큐리티 설정에 패스워드 해시화 설정  
   *MemberSaveService 클래스 생성 
     -> save 서비스 (비밀번호 해시화 처리후 저장 )
	 
  -save와 saveAll, saveAndFlush 메서드 차이
   ->save()는 commint 수행시 DB저장/ saveAndFlush()는 DB 즉시 반영
   ->saveAll() :n개 저장, List에 entity를 모두 담아서 한 번에 saveAll 하는 게 성능면에서 더 좋음
   
   *join.html 생성 
   *messages 생성(error,validation,commons ) ->Mvc연결
   *Validator 만들기
        /**
         * 1. 아이디 중복여부 :repository exists 메서드로 존재여부 확인 - 프리디케이트 공부하기
         * 2. 비밀번호 형식 체크(알파벳(대문자,소문자), 숫자, 특수문자 포함 )-인터페이스 설계 /경우에 따라 다양하게 사용할수 있도록 나눠서 설계
		     -> 정규식 pattern.matcher사용 
         * 3. 비밀번호 비밇번호 확인 일치 여부
         * 4. 휴대전화번호(선택) - 입력된 경우 형식 체크- 인터페이스 설계 
         * 5. 휴대전화번호가 입력된 경우 숫자만 추출해서 다시 커맨드 객체에 저장
         * 6. 필수 약관 동의 체크
         */
	*JoinController 유효성검사 적용 	 
	*join.html 완성 


<h1>3DAY<h1><br>
*login 구현(컨트롤러 연결 , login.html생성)
*xeicon이용하여 아이콘 추가

*접근제한을 위해 security config설정 추가
-> 성공,실패시 html에 해당사유들을 보여지도록 하기 위해서 Url보다는 Handler를 이용하여 구현 (LoginFailureHandler, LoginSuccessHandler생성) + LoginValidationException이용

*UserDetails,UserDetailsService 인터페이스 구현 클래스(회원관련)
*공통예외 CommonException -> 다시 한번 보기
     +static 블럭의 의미 static{} :초기화 블럭(initialization block)
	 클래스 초기화 블럭 : 클래스 변수의 복잡한 초기화에 사용된다. 클래스가 처음 로딩될 때 한번만 수행된다.
     인스턴스 초기화 블럭 : 인스턴스 변수의 복잡한 초기화에 사용된다. 인스턴스가 생성될때 마다 수행된다. (생성자보다 먼저 수행된다.)
*LoginValidationException 로그인 유효성 검사시 예외 클래스 생성(CommonException상속)
 *LoginFailureHandler
 -> 세션에 저장된 아이디와 비밀번호가 일치하지 않거나 비어있을 경우, 에러메세지출력되도록 html 
 *회원권한
  ->Role constant 추가  /엔티티 ROle추가 /UserDetails Role기본값 추가, saveService기본값 추가 
  ->SecurityConfig 페이지에따른 접근권한 설정
 *에러페이지
 - 템플릿경로:/error/응답코드.html (자동연결됨)
  -timestamp -오류발생시각 / status-Http 상태코드 /error-오류발생원ㅇ / exception-예외객체/ trace -printStackTrace()
   /path-오류의 유입 URL
 
 *로그인시 회원권한에 따른 헤더부분 차별구현 (회원- 로그아웃,사용자정보/관리자-로그아웃,사용자정보, 관리자페이지버튼/그외- 로그인,회원가입 )
  ->spring security사용
   -Spring Data JPA + Spring Security - 수정자(AwareAuditor 인터페이스 구현체 )
       -> MemberUtils(로그인여부, 관리자여부, 멤버정보 가져오기, DB연결)
	   
	   ++ 스프링 시큐리티에서 회원정보 조회 방법
	     -Principal principal - String getName() : 아이디만 조회
 		 -@AuthenticationPrincipal UserDetails 구현 클래스의 객체
   -직접 회원 정보 가져오기  
     -SecurityContextHolder
	    - getContext().getAuthentication()
		 -((Object) getPrincipal():비회원(String): anonymousUser, 회원일때 :UserDetails 구현 객체 
*공통오류페이지 생성
 @ExceptionHandler, @ControllerAdvice @RestControllerAdvice
 
 <h1>4DAY<h1><br>
 *공통오류페이지 처리
 -Common 일반 컨트롤러(@ControllerAdvice)
 - Common REST 컨틀롤러(@RestControllerAdvice)
   -일반 요청 응답과 오류 통일성 있게 처리(JSONData) 
  
  *공통 CSS 적용
   -글꼴(구글 폰트), 기본스타일 제거, 기본스타일커스텀 추가 (라디오, 체크박스)

 *어드민페이지 만들기 (securityConfig에서 관리자권한 풀고 작업)
   html/ css 기본레이아웃 작업
    -사이트 설정 (/admin/config)
	
	   ->ConfigController / classappend로 on클래스 지정 추가
	   -추후에 설정이 많이 추가됨을 고려
	    -CodeValue 엔티티 code(PK), value-JSON :설정만큼의 테이블의 생성하기 보다 키와 값으로 하나의 테이블 이용
		->entity 생성, codeValuerepository 생성
		-saveService :insert,update기능 한번에 생성 (있으면 기존값 사용 없으면 생성)
		-infoService :조회
		 :ObjectMapper JSON pasing작업 /
		  ++ JSON->java 객체로 바꿔주는것 (readValue)/ java객체->JSON으로 바꿔주는것(writeValue)
		 -DeleteService 
		

	
<h1>5DAY<h1><br>
 -사이트 설정 구현 
  - Controller 구현 설정 
   -infoservice 수정: code,class 자바객체인경우 + Map이나 List인경우 typeReference를 이용해서 value값으로 조회하도록 추가 
   - interceptor에서 사이트 설정 request 전달 
   
 -페이지 제목 (pageTitle::siteTitle )의형태로 수정 

 -게시판 설정 (/admin/board)	
    - 게시판 설정 == 게시판
	- 게시판 데이터 
	BoardController 추가  
	Menu조회기능 : MenuDetails, Menu클래스 
	
    -submenu.html  css 설정 

 