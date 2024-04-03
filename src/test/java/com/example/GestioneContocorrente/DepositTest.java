package com.example.GestioneContocorrente;

import com.example.GestioneContocorrente.dtos.DepositDtoResponse;
import com.example.GestioneContocorrente.exception.ResourceNotFoundException;
import com.example.GestioneContocorrente.mappers.DepositMapper;
import com.example.GestioneContocorrente.model.BankAccount;
import com.example.GestioneContocorrente.model.Deposit;
import com.example.GestioneContocorrente.model.User;
import com.example.GestioneContocorrente.repository.DepositRepo;
import com.example.GestioneContocorrente.service.DepositServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DepositTest {
    private static final LocalDateTime DATE_TIME = LocalDateTime.of(2020, 2, 1,1,1);

    public static final long AMOUNT = 300;
    @Mock
    private DepositRepo depositRepo;

    @Mock
    private DepositMapper depositMapper;

    @InjectMocks
    private DepositServiceImpl depositService;

    User user = User.builder().id(1L).firstname("Giacomo").lastname("Rossi").ssn("yuyuvhjbjh")
            .username("hhh").password("hbhjkb67tg").createdAt(DATE_TIME).build();

    BankAccount bankAccount = BankAccount
            .builder()
            .id(1L)
            .balance(2*AMOUNT)
            .createdAt(DATE_TIME)
            .build();
    Deposit deposit = Deposit
            .builder()
            .id(1L)
            .amount(AMOUNT)
            .user(user)
            .bankAccount(bankAccount)
            .build();
    Deposit deposit2 = Deposit
            .builder()
            .id(2L)
            .amount(2*AMOUNT)
            .user(user)
            .bankAccount(bankAccount)
            .build();
    DepositDtoResponse depositDtoResponse = new DepositDtoResponse(1L,AMOUNT,DATE_TIME);
    DepositDtoResponse depositDtoResponse2 = new DepositDtoResponse(2L,2*AMOUNT,DATE_TIME);



    @Test
    public void testFindById() throws Exception {
        when(depositRepo.findById(1L)).thenReturn(Optional.of(deposit));
        when(depositMapper.map(deposit)).thenReturn(depositDtoResponse);

        DepositDtoResponse response = depositService.getDepositById(1L);

        assertNotNull(response);
        assertEquals(depositDtoResponse,response);
        Assertions.assertDoesNotThrow(() -> depositService.getDepositById(1L));
    }




    @Test
    public void testFindByIdShouldReturnException() {

        Assertions.assertThrows(ResourceNotFoundException.class,() -> depositService.getDepositById(4L));
    }

    @Test
    public void testGetAllDeposits()  {
        List<Deposit> depositList = new ArrayList<>();
        depositList.add(deposit);
        depositList.add(deposit2);

        List<DepositDtoResponse> depositDtoResponseList = new ArrayList<>();
        depositDtoResponseList.add(depositDtoResponse);
        depositDtoResponseList.add(depositDtoResponse2);

        when(depositRepo.findAll()).thenReturn((depositList));
        when(depositMapper.mapAll(depositList))
                .thenReturn(depositDtoResponseList);

        List<DepositDtoResponse> response = depositService.getAlldeposits();


        Assertions.assertEquals(depositDtoResponseList.size(), response.size(), "findAll should return 2 deposits");
        Assertions.assertEquals(depositDtoResponseList, response);
    }


}
