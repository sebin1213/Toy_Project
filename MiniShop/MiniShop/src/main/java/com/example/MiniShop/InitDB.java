package com.example.MiniShop;


//@Component
//@RequiredArgsConstructor
//public class InitDB {
//
//    private final InitService initService;
//
//    // 스프링 라이프사이클이 있어서 트랜젝션 먹이고 이런게 잘 안됨 그래서 별도의 빈으로 등록해야함
//    @PostConstruct//애플리케이션 로딩시점에 이거를 호출해주고싶어서
//    public void init() {
//        initService.dbInit1();
//    }
//    @Component
//    @Transactional
//    @RequiredArgsConstructor
//    static class InitService {
//        private final EntityManager em;
//        public void dbInit1() {
//
////            Item item = new Item();
////            em.persist(item.createItem("셔츠", 15900, 200, "상세정보~!~!~!!~!~!~"));
////            Member member = new Member();
////            em.persist(member);
////            Member member1 = createMember1("user","456","0000000000");
////            em.persist(member1);
//        }
//    }
//}