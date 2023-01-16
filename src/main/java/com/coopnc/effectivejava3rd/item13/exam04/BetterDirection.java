package com.coopnc.effectivejava3rd.item13.exam04;

/**
 * clone보다는 복사 생성자( 변환 생성자 ), 복사 팩토리( 변환 팩터리 )가 더 좋다...!
 *  - Collection이나 Map을 보면 안다...!
 */
public class BetterDirection {
    public String job;
    public int experienceYear;

    public BetterDirection( final String job, final int experienceYear ) {
        this.job = job;
        this.experienceYear = experienceYear;
    }

    /**
     * 복사 생성자 ( 변환 생성자 - conversion constructor )
     * @param betterDirection
     */
    public BetterDirection( final BetterDirection betterDirection ) {
        job = betterDirection.getJob();
        experienceYear = betterDirection.getExperienceYear();
    }

    /**
     * 복사 팩토리 ( 변환 팩토리 - conversion factory )
     * @param betterDirection
     * @return
     */
    public static BetterDirection newInstance( final BetterDirection betterDirection ) {
        return new BetterDirection( betterDirection.getJob(), betterDirection.getExperienceYear() );
    }

    public String getJob() {
        return job;
    }
    public int getExperienceYear() {
        return experienceYear;
    }
}
