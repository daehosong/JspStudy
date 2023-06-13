package hello2.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
동시성 문제가 고려 되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

public class MemberRepository {


    private static Map<Long, Member> store = new HashMap<>();
    // sequence는 id가 하나씩 증가하는 시퀀스로 사용함.
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){

    //  싱글톤으로 생성 -> 일단 private으로 생성자를 막아야 함.
    /*
    싱글톤 :
    객체를 생성할 때마다 메모리 영역을 할당받아야 한다. 하지만 한번의 new를 통해 객체를 생성한다면 메모리 낭비를 방지할 수 있다.
    또한 싱글톤으로 구현한 인스턴스는 '전역'이므로, 다른 클래스의 인스턴스들이 데이터를 공유하는 것이 가능한 장점이 있다
     */
        return instance;
    }
    private MemberRepository() {

    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    public Member findById(Long id){
        return store.get(id);
    }
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }
    public void clearStore(){
        store.clear();
    }

}
