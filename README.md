# 스프링 부트 블로그 만들기

## 1. 개발 환경

- Springboot
- MyBatis
- MySQL
- JSP
- Spring Security
- Lombok

## 2. 요구사항(기능)

- 회원가입
- 로그인
- 사용자 프로파일(사진) 업로드
- 게시글 등록
- 게시글 수정
- 게시글 삭제
- 게시글 목록
- 게시글 상세보기
- 게시글 페이징
- 게시글 검색
- 게시글 권한 관리
- 댓글 등록
- 댓글 삭제

## 3. 테이블

```sql
create user 'spring'@'%' identified by 'bitc5600';
GRANT ALL PRIVILEGES ON *.* TO 'spring'@'%';
create database spring;
use spring;
```

```sql
CREATE TABLE user(
   id int auto_increment primary key,
    username varchar(100) unique not null,
    password varchar(100) not null,
    email varchar(100),
    profile varchar(200),
    role varchar(20),
    createDate timestamp
) engine=InnoDB default charset=utf8;
```

```sql
CREATE TABLE post(
   id int auto_increment primary key,
    title varchar(100) not null,
    content longtext,
    userId int,
    createDate timestamp,
    foreign key (userId) references user (id) on delete set null
) engine=InnoDB default charset=utf8;
```

```sql
CREATE TABLE comment(
   id int auto_increment primary key,
    userId int,
    postId int,
    content varchar(300) not null,
    createDate timestamp,
    foreign key (userId) references user (id) on delete set null,
    foreign key (postId) references post (id) on delete cascade
) engine=InnoDB default charset=utf8;
```

# 스프링 시큐리티 기본

### SQL 필요 명령어

```sql
create user 'cos'@'%' identified by 'cos1234';
GRANT ALL PRIVILEGES ON *.* TO 'cos'@'%';
create database security;
use security;
```

### SecurityConfig.java 권한 설정

```java
		http.authorizeRequests()
		.antMatchers("/user/**").authenticated()
		//.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
		//.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
```

### 컨트롤러의 함수에 직접 권한 설정

```java
	//어노테이션으로 권한 확인하기
	//@PostAuthorize("hasRole('ROLE_MANAGER')")
	//@PreAuthorize("hasRole('ROLE_MANAGER')")
	@Secured("ROLE_MANAGER")
```

```java
// 둘중에 하나라도 실패하면 rollback 하기
	@Transactional(rollbackFor = Exception.class)
	public void updateAndDelete(Post post) {
		try {
			mRepo.update(post);
			mRepo.delete(post.getId());
			System.out.println("DB 정상");
		} catch (Exception e) {
			System.out.println("DB 오류");
			throw new RuntimeException(e);
		}

	}
​
```
