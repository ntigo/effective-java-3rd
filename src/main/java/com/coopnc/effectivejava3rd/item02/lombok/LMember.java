package com.coopnc.effectivejava3rd.item02.lombok;

import lombok.*;

@Builder(builderMethodName = "Builder")
//@AllArgsConstructor(access = AccessLevel.PRIVATE) // 객체 생성을 임의로 할 수 없도록 공개 생성자를 숨김
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class LMember {
    @NonNull
    private final String name; // Requirement
    @NonNull
    private final String birth; // Requirement

    private final String bloodType;
    private final int tall;
    private final String cellular;
}
