package com.ssafy.c202.formybaby.baby.mapper;

import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateRequest;
import com.ssafy.c202.formybaby.baby.dto.request.BabyUpdateRequest;
import com.ssafy.c202.formybaby.baby.entity.Baby;
import java.time.LocalDate;
import java.time.ZoneOffset;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-20T15:26:33+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class BabyMapperImpl implements BabyMapper {

    @Override
    public Baby toBabyEntity(BabyCreateRequest babyCreateRequest) {
        if ( babyCreateRequest == null ) {
            return null;
        }

        Baby baby = new Baby();

        baby.setBabyName( babyCreateRequest.babyName() );
        if ( babyCreateRequest.birthDate() != null ) {
            baby.setBirthDate( babyCreateRequest.birthDate().toLocalDate() );
        }
        baby.setBabyGender( babyCreateRequest.babyGender() );
        baby.setProfileImg( babyCreateRequest.profileImg() );

        return baby;
    }

    @Override
    public Baby toBabyEntity(BabyUpdateRequest babyUpdateRequest) {
        if ( babyUpdateRequest == null ) {
            return null;
        }

        Baby baby = new Baby();

        if ( babyUpdateRequest.babyId() != null ) {
            baby.setBabyId( Long.parseLong( babyUpdateRequest.babyId() ) );
        }
        baby.setBabyName( babyUpdateRequest.babyName() );
        if ( babyUpdateRequest.birthDate() != null ) {
            baby.setBirthDate( LocalDate.parse( babyUpdateRequest.birthDate() ) );
        }
        baby.setBabyGender( babyUpdateRequest.babyGender() );
        baby.setProfileImg( babyUpdateRequest.profileImg() );

        return baby;
    }

    @Override
    public void updateBabyFromRequest(BabyUpdateRequest babyUpdateRequest, Baby baby) {
        if ( babyUpdateRequest == null ) {
            return;
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
        if ( babyUpdateRequest.babyGender() != null ) {
            baby.setBabyGender( babyUpdateRequest.babyGender() );
        }
        if ( babyUpdateRequest.profileImg() != null ) {
            baby.setProfileImg( babyUpdateRequest.profileImg() );
        }
    }
}
