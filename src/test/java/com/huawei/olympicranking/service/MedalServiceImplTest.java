package com.huawei.olympicranking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.huawei.olympicranking.data.dto.MedalRequest;
import com.huawei.olympicranking.data.entity.Medal;
import com.huawei.olympicranking.exception.DuplicateMedalException;
import com.huawei.olympicranking.exception.ResourceNotFoundException;
import com.huawei.olympicranking.repository.MedalRepository;

@ExtendWith(MockitoExtension.class)
public class MedalServiceImplTest {

    @Mock
    private MedalRepository medalRepository;
    @InjectMocks
    private MedalServiceImpl medalService;

    @Test
    void shouldThrowException_WhenDuplicateMedal() { 
         when(medalRepository.existsByAthleteNameAndMedalTypeAndSportName(anyString(), anyString(), anyString())).thenReturn(true);
        assertThrows(DuplicateMedalException.class, () -> medalService.insertMedal(new MedalRequest("null", "null", "null", "null")));
     }

    @Test
    void shouldThrowException_WhenNoResource() { 
        when(medalRepository.findAll()).thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> medalService.getOverallRankings());
     }

    @Test
    void shouldSaveMedal_WhenValidInput() {
        MedalRequest req = new MedalRequest(Medal.MEDAL_BRONZE, "TR", "morca", "swimming");
        Medal medal = new Medal(Medal.MEDAL_BRONZE, "swimming", "TR", "morca");

        when(medalRepository.save(any())).thenReturn(medal);
        Medal result = medalService.insertMedal(req);

        assertEquals(result.getAthleteName(), medal.getAthleteName());
        verify(medalRepository).save(any(Medal.class));
    }
}
