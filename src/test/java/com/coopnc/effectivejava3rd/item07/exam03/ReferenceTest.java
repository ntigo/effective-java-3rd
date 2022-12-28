package com.coopnc.effectivejava3rd.item07.exam03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ReferenceTest {

    @Test
    @DisplayName("Soft Reference 테스트")
    void softRefTest() throws InterruptedException {
        String strongRef = "a";

        // 연관 관계를 통한 Strong Reference 를 주입
        SoftReference<String> softReference = new SoftReference<>( strongRef );
        // 명시적인 null 할당으로 해제 대상으로 변경
        strongRef = null;

        System.gc();
        // GC 가 충분히 일어날 수 있도록 강제 지연 처리
        TimeUnit.SECONDS.sleep( 3 );

        // 해당 객체는 해제되지 않음. (GC는 메모리가 긴급한 순간까지 유예한다.)
        assertNull( softReference.get(), () -> "is not null." );
    }

    @Test
    @DisplayName( "Hashmap Leak 발생 테스트" )
    void hashmapTest() throws InterruptedException {
        HashMap<Integer, String> map = new HashMap<>();

        Integer key1 = 1000;
        Integer key2 = 2000;

        map.put(key1, "key1의 객체입니다.");
        map.put(key2, "key2의 객체입니다.");

        key1 = null;

        System.gc();  //강제 Garbage Collection
        // GC 발생 시킨 후 딜레이가 필요함
        TimeUnit.SECONDS.sleep( 3 );

        // keySet 이 없어지는 상태로 get을 하는 부분이 모순이 발생
        /*for (Integer key : map.keySet()) {
            System.out.println(map.get(key));
        }*/

        // map의 숫자로 점검이 필요함
        assertEquals( 1, map.size(), "is not valid size." );
    }

    /**
     * Leak 테스팅의 주요한 관점은 isEmpty 와 같이 개체의 수를 직접 접근하여
     * 파악할 수 있는 수단을 이용하여 테스팅 하는 것이 중요하다.
     * 해당 객체가 null 으로 변경 되었는지 테스팅하는 부분은 무의미하다.
     * @throws InterruptedException
     */
    @Test
    @DisplayName( "WeakHashMap Leak 발생 테스트" )
    void weakHashmapTest() throws InterruptedException {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();

        // 캐시 비대상인 경우 Leak 발생 확인 가능
        Integer key1 = 1000; // 100
        Integer key2 = 2000; // 101

        map.put(key1, "key1의 객체입니다.");
        map.put(key2, "key2의 객체입니다.");

        key1 = null;

        System.gc();  //강제 Garbage Collection
        // GC 발생 시킨 후 딜레이가 필요함
        TimeUnit.SECONDS.sleep( 3 );

        // keySet 이 없어지는 상태로 get을 하는 부분이 모순이 발생
        /*for (Integer key : map.keySet()) {
            System.out.println(map.get(key));
        }*/

        // map의 숫자로 점검이 필요함
        assertEquals( 1, map.size(), "is not valid size." );
    }
}