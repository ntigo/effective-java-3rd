package com.coopnc.effectivejava3rd.item13.exam03;

import com.coopnc.effectivejava3rd.item13.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * 얕은 복사를 주의해야되는 이유...!
 *  - 책의 Stack class는 복잡하게 설명함으로 간단하게...
 */
public class CloneShallowDeepCopy implements Cloneable {
    private List<Person> personList;
    private String region;

    public CloneShallowDeepCopy( final String region, final List<Person> personList ) {
        this.region = region;
        this.personList = personList;
    }

    public List<Person> getPersonList() {
        return personList;
    }
//    public void setPersonList(List<Person> personList) {
//        this.personList = personList;
//    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
//        StringBuilder toStringBuilder = new StringBuilder();
//        toStringBuilder.append()
        return "CloneShallowDeepCopy{" +
                "personList=" + personList +
                ", region='" + region + '\'' +
                '}';
    }

    /**
     * 얕은 복사로 처리됨으로 원본의 personList를 복사된 인스턴스가 공유하게 된다...!
     * @return
     * @throws RuntimeException
     */
//    @Override
//    public CloneShallowDeepCopy clone() throws RuntimeException {
//        try {
//            return (CloneShallowDeepCopy)super.clone();
//        } catch( CloneNotSupportedException e ) {
//            throw new RuntimeException();
//        }
//    }

    /**
     * personList를 깊은 복사 처리하여 방금 확인한 문제를 해결한다...!
     * @return
     * @throws RuntimeException
     */
    @Override
    public CloneShallowDeepCopy clone() throws RuntimeException {
        try {
            CloneShallowDeepCopy cloneInstance = (CloneShallowDeepCopy)super.clone();

            if ( cloneInstance.personList != null ) {
                cloneInstance.personList = new ArrayList<>();

                if ( personList.size() > 0 ) {
                    for( Person person : personList ) {
                        cloneInstance.personList.add( person.clone() );
                    }
                }
            }
            return cloneInstance;
        } catch( CloneNotSupportedException e ) {
            throw new RuntimeException();
        }
    }
}



