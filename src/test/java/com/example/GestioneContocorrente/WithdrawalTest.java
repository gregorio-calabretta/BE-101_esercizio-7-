package com.example.GestioneContocorrente;

import com.example.GestioneContocorrente.dtos.WithdrawalDtoResponse;
import com.example.GestioneContocorrente.exception.ResourceNotFoundException;
import com.example.GestioneContocorrente.mappers.WithdrawalMapper;
import com.example.GestioneContocorrente.model.BankAccount;
import com.example.GestioneContocorrente.model.Withdrawal;
import com.example.GestioneContocorrente.model.User;
import com.example.GestioneContocorrente.repository.WithdrawalRepo;
import com.example.GestioneContocorrente.service.WithdrawalServiceImpl;
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
public class WithdrawalTest {
    private static final LocalDateTime DATE_TIME = LocalDateTime.of(2020, 2, 1,1,1);

    public static final long AMOUNT = 300;
    @Mock
    private WithdrawalRepo withdrawalRepo;
 
    @Mock
    private WithdrawalMapper withdrawalMapper;

    @InjectMocks
    private WithdrawalServiceImpl withdrawalService;

    User user = User.builder().id(1L).firstname("Giacomo").lastname("Rossi").ssn("yuyuvhjbjh")
            .username("hhh").password("hbhjkb67tg").createdAt(DATE_TIME).build();

    BankAccount bankAccount = BankAccount
            .builder()
            .id(1L)
            .balance(2*AMOUNT)
            .createdAt(DATE_TIME)
            .build();
    Withdrawal withdrawal = Withdrawal
            .builder()
            .id(1L)
            .amount(AMOUNT)
            .user(user)
            .bankAccount(bankAccount)
            .build();
    Withdrawal withdrawal2 = Withdrawal
            .builder()
            .id(2L)
            .amount(2*AMOUNT)
            .user(user)
            .bankAccount(bankAccount)
            .build();
    WithdrawalDtoResponse withdrawalDtoResponse = new WithdrawalDtoResponse(1L,AMOUNT,DATE_TIME);
    WithdrawalDtoResponse withdrawalDtoResponse2 = new WithdrawalDtoResponse(2L,2*AMOUNT,DATE_TIME);



    @Test
    public void testFindById() throws Exception {
        when(withdrawalRepo.findById(1L)).thenReturn(Optional.of(withdrawal));
        when(withdrawalMapper.map(withdrawal)).thenReturn(withdrawalDtoResponse);

        WithdrawalDtoResponse response = withdrawalService.getWithdrawalById(1L);

        assertNotNull(response);
        assertEquals(withdrawalDtoResponse,response);
        Assertions.assertDoesNotThrow(() -> withdrawalService.getWithdrawalById(1L));
    }




    @Test
    public void testFindByIdShouldReturnException() {

        Assertions.assertThrows(ResourceNotFoundException.class,() -> withdrawalService.getWithdrawalById(4L));
    }

    @Test
    public void testGetAllWithdrawals()  {
        List<Withdrawal> withdrawalList = new ArrayList<>();
        withdrawalList.add(withdrawal);
        withdrawalList.add(withdrawal2);

        List<WithdrawalDtoResponse> withdrawalDtoResponseList = new ArrayList<>();
        withdrawalDtoResponseList.add(withdrawalDtoResponse);
        withdrawalDtoResponseList.add(withdrawalDtoResponse2);

        when(withdrawalRepo.findAll()).thenReturn((withdrawalList));
        when(withdrawalMapper.mapAll(withdrawalList))
                .thenReturn(withdrawalDtoResponseList);

        List<WithdrawalDtoResponse> response = withdrawalService.getAllWithdrawals();


        Assertions.assertEquals(withdrawalDtoResponseList.size(), response.size(), "findAll should return 2 withdrawals");
        Assertions.assertEquals(withdrawalDtoResponseList, response);
    }


}
