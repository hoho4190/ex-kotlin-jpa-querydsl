# Kotlin + Spring Date JPA + QueryDSL
## SKILLS
- Gradle
- Kotlin
- Spring Frmework
- JPA, Hibername
- Spring Data JPA
- QueryDSL
- MySQL, H2
- JUnit5, Mokito

## QueryDSL
### QueryDSL 이란?
JPQL을 편하게 작성하도록 도와주는 빌더 클래스 모음, 비표준 오픈소스 프레임워크

### 사용 이유
- JpaRepository interface에서 여러 조건을 사용하면 어떤 쿼리를 사용하는지 직관성이 떨어짐
- native Query, JPQL 문제점
  - 문자열이라 Type-check가 불가능, 오타 가능성
  - 컴파일 시점에 오류를 알 수 없음
  - 동적 쿼리에 한계가 있음
- 문자가 아닌 코드로 작성
  - Type-safe함, 오타로 인한 문제점 해결
  - 코드 자동완성(IDE 도움)
  - 도메인 변경 사항을 리팩토링하는데 효과적
- 컴파일 시점에 문법 오류를 발견
- 동적 쿼리

### Repository
- Custom Repository
  - 사용할 때 하나의 Repository만 주입 받으면 되지만
  - 하나의 Entity 당 3개의 Repository 파일을 관리해야 해서 번거로움

- JPAQueryFactory만 주입받아 사용하는 Repository
  - 사용할 때 추가로 Repository를 주입 받아야 함


> 어떤 기능을 구현하기 위해 다양한 엔티티를 Join하여 함께 참조해야 하는 경우,
> 이걸 A엔티티 Repository의 역할로 봐야할지, B엔티티 Repository의 역할로 봐야할지 애매모호한 상황이 발생함.
>
> 이런 경우 특정 엔티티를 메인으로 하지 않는 기능이기 때문에, JPAQueryFactory만 주입받아 사용하는 Repository를 사용하면 좋음.

### JPQL과 영속성
JPQL도 엔티티를 조회하면 해당 엔티티는 영속성 컨텍스트에서 관리된다.   
하지만, 엔티티가 아니라면 영속성 컨텍스트에서 관리되지 않는다.

JPA에서 `find()` 메소드는 1차 캐시에서 먼저 조회하지만   
JPQL은 **1차 캐시를 먼저 조회하지 않고** DB에 질의하여 결과를 가져온다.  
JPQL은 SQL로 변환되어 DB에 바로 날려보기 때문이다.

JPA는 영속성 컨텍스트를 이용하여 쓰기 지연을 하는데 JPQL은 DB에 바로 질의 하기 때문에  
JPQL은 **쿼리 실행전 flush를 호출**하여 DB와 싱크를 맞춘다.

1. JPQL은 DB를 우선 조회
2. 조회한 값을 1차 캐시에 저장 시도
3. 조회한 엔티티가 1차 캐시에 이미 존재하고 있다면, DB에서 조회한 엔티티는 버리고 1차 캐시의 엔티티를 반환
   (비교는 식별자로만 하기 때문에 다른 필드의 값이 달라도 식별자만 같다면 같은 엔티티로 인식)

### Select 성능 개선
- exist 메소드 대체하기
- Cross Join 회피하기
- (Entity가 아닌 Dto를 사용하기)
- Group By 최적화
- 커버링 인덱스

### Insert 성능 개선
- JdbcTemplate으로 Batch Insert 사용하기
- Type safe하게 Batch Insert 사용하기

### Update 성능 개선
- Batch Update 최적화 (Dirty checking 하지 않기)

## Reference
- [QueryDSL이란?](https://velog.io/@tigger/QueryDSL)
- [QueryDSL](https://velog.io/@ljinsk3?tag=Querydsl)
- [Spring Data JPA와 QueryDSL 이해, 실무 경험 공유](https://ict-nroo.tistory.com/117)
- [우아한테크콘서트 2020 Querydsl 강의 정리](https://kkambi.tistory.com/193)
- [QueryDSL을 효과적으로 실무에 적용하기 위한 고민](https://dico.me/java/articles/290/ko)
- [JPA 영속성 컨텍스트 주의 점](https://cheese10yun.github.io/jpa-persistent-context/)
- [영속성 컨텍스트와 플러시 이해하기](https://ict-nroo.tistory.com/130)
- [JPQL과 영속성 컨텍스트](https://jobc.tistory.com/206)
- [querydsl 학습_1](https://haepyung88.tistory.com/226)
