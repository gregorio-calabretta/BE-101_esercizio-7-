package com.example.GestioneContocorrente;

import com.example.GestioneContocorrente.dtos.BankAccountDtoRequest;
import com.example.GestioneContocorrente.dtos.BankAccountDtoResponse;
import com.example.GestioneContocorrente.dtos.UserDtoResponse;
import com.example.GestioneContocorrente.exception.ResourceNotFoundException;
import com.example.GestioneContocorrente.mappers.BankAccountMapper;
import com.example.GestioneContocorrente.model.BankAccount;
import com.example.GestioneContocorrente.model.User;
import com.example.GestioneContocorrente.repository.BankAccountRepo;
import com.example.GestioneContocorrente.repository.UserRepo;
import com.example.GestioneContocorrente.service.BankAccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
public class BankAccountServiceTest {
    private static final LocalDateTime DATE_TIME = LocalDateTime.of(2020, 2, 1,1,1);

    public static final int BALANCE = 300;
    @Mock
    private BankAccountRepo bankAccountRepo;
    @Mock
    private UserRepo userRepo;
    @Mock
    private BankAccountMapper bankAccountMapper;

    @InjectMocks
    private BankAccountServiceImpl bankAccountService;


        BankAccount bankAccount = BankAccount
                .builder()
                .id(1L)
                .balance(BALANCE)
                .createdAt(DATE_TIME)
                .build();

    BankAccount bankAccount2 = BankAccount
            .builder()
            .id(2L)
            .balance(2*BALANCE)
            .createdAt(DATE_TIME)
            .build();



    User user = User.builder().id(1L).firstname("Giacomo").lastname("Rossi").ssn("yuyuvhjbjh")
            .username("hhh").password("hbhjkb67tg").createdAt(DATE_TIME).build();



        BankAccountDtoResponse bankAccountDtoResponse = new BankAccountDtoResponse(1L,BALANCE,DATE_TIME);
        BankAccountDtoResponse bankAccountDtoResponse2 = new BankAccountDtoResponse(2L,2*BALANCE,DATE_TIME);
        BankAccountDtoRequest bankAccountDtoRequest = new BankAccountDtoRequest(bankAccount.getBalance(),bankAccount.getCreatedAt(),user.getId());
        UserDtoResponse userDtoResponse = new UserDtoResponse(user.getId(), user.getFirstname(),user.getLastname()
                ,user.getSsn(),user.getUsername(),user.getCreatedAt());

    @Test
    public void testFindNameById() throws Exception {
        when(bankAccountRepo.findById(1L)).thenReturn(Optional.of(bankAccount));
        when(bankAccountMapper.map(bankAccount)).thenReturn(bankAccountDtoResponse);
        BankAccountDtoResponse response = bankAccountService.getBankAccountById(1L);
        assertNotNull(response);
        assertEquals(bankAccount,response);
        Assertions.assertDoesNotThrow(() -> bankAccountService.getBankAccountById(1L));
    }




    @Test
    public void testFindNameByIdShouldReturnException() {
        when(bankAccountRepo.findById(2L)).thenReturn(Optional.of(bankAccount));
        when(bankAccountMapper.map(bankAccount)).thenReturn(bankAccountDtoResponse);
        Assertions.assertThrows(ResourceNotFoundException.class,() -> bankAccountService.getBankAccountById(1L));
    }

    @Test
    public void testGetAllBankAccounts(){
        List<BankAccount> accountList = new ArrayList<>();
        accountList.add(bankAccount);
        accountList.add(bankAccount2);

        List<BankAccountDtoResponse> bankAccountDtoResponseList = new ArrayList<>();
        bankAccountDtoResponseList.add(bankAccountDtoResponse);
        bankAccountDtoResponseList.add(bankAccountDtoResponse2);

        when(bankAccountRepo.findAll()).thenReturn((accountList));

        when(bankAccountMapper.mapAll(accountList))
        .thenReturn(bankAccountDtoResponseList);

        List<BankAccountDtoResponse> response = bankAccountService.getAllBankAccounts();


        Assertions.assertEquals(bankAccountDtoResponseList.size(), response.size(), "findAll should return 2 bankAccounts");
        Assertions.assertEquals(bankAccountDtoResponseList, response);
        Mockito.verify(bankAccountRepo).findAll();
    }



    @Test
    public void testCreateBankAccount() throws Exception {
        when(bankAccountRepo.save(bankAccount)).thenReturn(bankAccount);
        when(userRepo.save(user)).thenReturn(user);
        when(userRepo.findById(user.getId())).thenReturn(Optional.of(user));

        BankAccount bankAccountForMapping = BankAccount
                .builder()
                .balance(BALANCE)
                .createdAt(DATE_TIME)
                .user(user)
                .build();

        when(bankAccountMapper.map(bankAccountForMapping)).thenReturn(bankAccountDtoResponse);


    BankAccountDtoResponse response = bankAccountService.createBankAccount(bankAccountDtoRequest);

    Assertions.assertEquals(bankAccountDtoResponse, response);
    }


}
