package com.ssafy.c202.formybaby.user.mapper;

import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateRequest;
import com.ssafy.c202.formybaby.global.jpaEnum.Role;
import com.ssafy.c202.formybaby.user.entity.Family;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-19T15:05:36+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class FamilyMapperImpl implements FamilyMapper {

    @Override
    public Family initFamilyEntity(Long userId, String familyCode) {
        if ( userId == null && familyCode == null ) {
            return null;
        }

        Family family = new Family();

        family.setFamilyCode( familyCode );

        return family;
    }

    @Override
    public Family updateFamilyChild(BabyCreateRequest babyCreateRequest) {
        if ( babyCreateRequest == null ) {
            return null;
        }

        Family family = new Family();

        family.setFamilyCode( babyCreateRequest.familyCode() );
        if ( babyCreateRequest.role() != null ) {
            family.setRole( Enum.valueOf( Role.class, babyCreateRequest.role() ) );
        }

        return family;
    }
}
