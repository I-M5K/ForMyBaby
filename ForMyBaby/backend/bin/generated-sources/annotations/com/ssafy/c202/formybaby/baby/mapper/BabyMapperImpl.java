package com.ssafy.c202.formybaby.baby.mapper;

import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateRequest;
import com.ssafy.c202.formybaby.baby.dto.request.BabyUpdateRequest;
import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse2;
import com.ssafy.c202.formybaby.baby.entity.Baby;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-04T17:13:59+0900",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240325-1403, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class BabyMapperImpl implements BabyMapper {

    @Override
    public Baby toBabyEntity(BabyCreateRequest babyCreateRequest) {
        if ( babyCreateRequest == null ) {
            return null;
        }

        Baby baby = new Baby();

        baby.setBabyGender( babyCreateRequest.babyGender() );
        baby.setBabyName( babyCreateRequest.babyName() );
        if ( babyCreateRequest.birthDate() != null ) {
            baby.setBirthDate( LocalDate.parse( babyCreateRequest.birthDate() ) );
        }
        baby.setRole( babyCreateRequest.role() );

        return baby;
    }

    @Override
    public Baby toBabyEntity(BabyReadResponse2 babyReadResponse2) {
        if ( babyReadResponse2 == null ) {
            return null;
        }

        Baby baby = new Baby();

        baby.setBabyGender( babyReadResponse2.babyGender() );
        baby.setBabyId( babyReadResponse2.babyId() );
        baby.setBabyName( babyReadResponse2.babyName() );
        baby.setBirthDate( babyReadResponse2.birthDate() );
        baby.setProfileImg( babyReadResponse2.profileImg() );
        baby.setRole( babyReadResponse2.role() );

        return baby;
    }

    @Override
    public Baby toBabyEntity(BabyUpdateRequest babyUpdateRequest) {
        if ( babyUpdateRequest == null ) {
            return null;
        }

        Baby baby = new Baby();

        baby.setBabyGender( babyUpdateRequest.babyGender() );
        if ( babyUpdateRequest.babyId() != null ) {
            baby.setBabyId( Long.parseLong( babyUpdateRequest.babyId() ) );
        }
        baby.setBabyName( babyUpdateRequest.babyName() );
        if ( babyUpdateRequest.birthDate() != null ) {
            baby.setBirthDate( LocalDate.parse( babyUpdateRequest.birthDate() ) );
        }
        baby.setProfileImg( babyUpdateRequest.profileImg() );

        return baby;
    }

    @Override
    public void updateBabyFromRequest(BabyUpdateRequest babyUpdateRequest, Baby baby) {
        if ( babyUpdateRequest == null ) {
            return;
        }

        if ( babyUpdateRequest.babyGender() != null ) {
            baby.setBabyGender( babyUpdateRequest.babyGender() );
        }
        if ( babyUpdateRequest.babyId() != null ) {
            baby.setBabyId( Long.parseLong( babyUpdateRequest.babyId() ) );
        }
        if ( babyUpdateRequest.babyName() != null ) {
            baby.setBabyName( babyUpdateRequest.babyName() );
        }
        if ( babyUpdateRequest.birthDate() != null ) {
            baby.setBirthDate( LocalDate.parse( babyUpdateRequest.birthDate() ) );
        }
        if ( babyUpdateRequest.profileImg() != null ) {
            baby.setProfileImg( babyUpdateRequest.profileImg() );
        }
    }
}
