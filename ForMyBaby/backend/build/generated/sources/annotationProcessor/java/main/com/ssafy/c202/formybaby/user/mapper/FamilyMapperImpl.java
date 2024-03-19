package com.ssafy.c202.formybaby.user.mapper;

import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateRequest;
import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.global.jpaEnum.Role;
import com.ssafy.c202.formybaby.user.entity.Family;
import com.ssafy.c202.formybaby.user.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-19T16:25:53+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17 (Oracle Corporation)"
)
@Component
public class FamilyMapperImpl implements FamilyMapper {

    @Override
    public Family initFamilyEntity(User user, Baby baby, BabyCreateRequest babyCreateRequest, String familyCode) {
        if ( user == null && baby == null && babyCreateRequest == null && familyCode == null ) {
            return null;
        }

        Family family = new Family();

        if ( babyCreateRequest != null ) {
            if ( babyCreateRequest.role() != null ) {
                family.setRole( Enum.valueOf( Role.class, babyCreateRequest.role() ) );
            }
        }
        family.setUser( user );
        family.setBaby( baby );
        family.setFamilyCode( familyCode );

        return family;
    }

    @Override
    public Family updateFamilyChild(BabyCreateRequest babyCreateRequest, Family family) {
        if ( babyCreateRequest == null ) {
            return family;
        }

        if ( babyCreateRequest.role() != null ) {
            family.setRole( Enum.valueOf( Role.class, babyCreateRequest.role() ) );
        }

        return family;
    }
}
