package com.ssafy.c202.formybaby.user.mapper;

import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateRequest;
import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.user.entity.Family;
import com.ssafy.c202.formybaby.user.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-04T17:13:59+0900",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240325-1403, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class FamilyMapperImpl implements FamilyMapper {

    @Override
    public Family initFamilyEntity(User user, Baby baby, BabyCreateRequest babyCreateRequest, String familyCode, int familyRank) {
        if ( user == null && baby == null && babyCreateRequest == null && familyCode == null ) {
            return null;
        }

        Family family = new Family();

        if ( babyCreateRequest != null ) {
            family.setRole( babyCreateRequest.role() );
        }
        family.setUser( user );
        family.setBaby( baby );
        family.setFamilyCode( familyCode );
        family.setFamilyRank( familyRank );

        return family;
    }

    @Override
    public Family copyFamily(Family family) {
        if ( family == null ) {
            return null;
        }

        Family family1 = new Family();

        family1.setBaby( family.getBaby() );
        family1.setFamilyCode( family.getFamilyCode() );
        family1.setFamilyId( family.getFamilyId() );
        family1.setFamilyRank( family.getFamilyRank() );
        family1.setRole( family.getRole() );
        family1.setUser( family.getUser() );

        return family1;
    }

    @Override
    public Family updateFamilyChild(BabyCreateRequest babyCreateRequest, Family family) {
        if ( babyCreateRequest == null ) {
            return family;
        }

        if ( babyCreateRequest.role() != null ) {
            family.setRole( babyCreateRequest.role() );
        }

        return family;
    }
}
