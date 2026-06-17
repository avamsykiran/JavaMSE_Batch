package in.bta.txns.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import in.bta.txns.entities.AccountHolder;
import in.bta.txns.entities.Txn;
import in.bta.txns.entities.TxnType;
import in.bta.txns.exceptions.TxnException;
import in.bta.txns.repos.AccountHolderRepo;
import in.bta.txns.repos.TxnRepo;

@ExtendWith(MockitoExtension.class)
class TxnServiceImplTest {

    @Mock
    private ProfilesClient profiles;

    @Mock
    private AccountHolderRepo ahRepo;

    @Mock
    private TxnRepo txnRepo;

    @InjectMocks
    private TxnServiceImpl service;

    @Test
    void shouldReturnPeriodicTxnsByAhId() throws Exception {
        LocalDate start = LocalDate.of(2025, 1, 1);
        LocalDate end = LocalDate.of(2025, 12, 31);
        Txn txn = createTxn(100L, TxnType.CREDIT, 100.0, createAh(1L, 0.0));

        when(txnRepo.getPeriodicTxnByAhId(1L, start, end)).thenReturn(Arrays.asList(txn));

        assertEquals(1, service.getPeriodicTxnsByAhId(1L, start, end).size());
        verify(txnRepo).getPeriodicTxnByAhId(1L, start, end);
    }

    @Test
    void shouldReturnTxnByIdWhenFound() {
        Txn txn = createTxn(10L, TxnType.DEBIT, 25.0, createAh(1L, 50.0));
        when(txnRepo.findById(10L)).thenReturn(Optional.of(txn));

        Txn result = service.getById(10L);

        assertNotNull(result);
        assertEquals(10L, result.getTxnId());
    }

    @Test
    void shouldReturnNullWhenTxnByIdNotFound() {
        when(txnRepo.findById(20L)).thenReturn(Optional.empty());

        assertNull(service.getById(20L));
    }

    @Test
    void shouldThrowTxnExceptionIfHolderMissingOnAdd() {
        Txn txn = createTxn(null, TxnType.CREDIT, 100.0, null);

        assertThrows(TxnException.class, () -> service.add(txn));
    }

    @Test
    void shouldAddTxnAndUpdateExistingBalanceWhenHolderExists() throws Exception {
        AccountHolder ah = createAh(5L, 50.0);
        Txn txn = createTxn(null, TxnType.CREDIT, 30.0, ah);

        when(ahRepo.findById(5L)).thenReturn(Optional.of(ah));
        when(txnRepo.save(txn)).thenReturn(txn);

        Txn result = service.add(txn);

        assertEquals(80.0, result.getHolder().getCurrentBalance());
        verify(ahRepo).save(ah);
        verify(txnRepo).save(txn);
    }

    @Test
    void shouldCreateAccountHolderWhenMissingAndProfileExists() throws Exception {
        AccountHolder holderRef = createAh(9L, 0.0);
        Txn txn = createTxn(null, TxnType.CREDIT, 40.0, holderRef);

        when(ahRepo.findById(9L)).thenReturn(Optional.empty());
        when(profiles.checkAccountHolderExists(9L)).thenReturn(Boolean.TRUE);
        when(txnRepo.save(txn)).thenReturn(txn);

        Txn result = service.add(txn);

        assertEquals(40.0, result.getHolder().getCurrentBalance());
        assertEquals(9L, result.getHolder().getAhId());
        verify(ahRepo).save(any(AccountHolder.class));
        verify(txnRepo).save(txn);
    }

    @Test
    void shouldThrowTxnExceptionWhenAddForMissingHolderNotFoundInProfiles() {
        AccountHolder holderRef = createAh(11L, 0.0);
        Txn txn = createTxn(null, TxnType.DEBIT, 15.0, holderRef);

        when(ahRepo.findById(11L)).thenReturn(Optional.empty());
        when(profiles.checkAccountHolderExists(11L)).thenReturn(Boolean.FALSE);

        assertThrows(TxnException.class, () -> service.add(txn));
    }

    @Test
    void shouldThrowTxnExceptionWhenUpdateMissingTxn() {
        Txn txn = createTxn(12L, TxnType.CREDIT, 20.0, createAh(2L, 10.0));
        when(txnRepo.existsById(12L)).thenReturn(false);

        assertThrows(TxnException.class, () -> service.update(txn));
    }

    @Test
    void shouldUpdateTxnAndAdjustBalance() throws Exception {
        AccountHolder ah = createAh(3L, 120.0);
        Txn oldTxn = createTxn(13L, TxnType.CREDIT, 20.0, ah);
        Txn updatedTxn = createTxn(13L, TxnType.DEBIT, 10.0, ah);

        when(txnRepo.existsById(13L)).thenReturn(true);
        when(txnRepo.findById(13L)).thenReturn(Optional.of(oldTxn));
        when(txnRepo.save(updatedTxn)).thenReturn(updatedTxn);

        Txn result = service.update(updatedTxn);

        assertEquals(90.0, ah.getCurrentBalance());
        assertEquals(updatedTxn, result);
        verify(ahRepo).save(ah);
        verify(txnRepo).save(updatedTxn);
    }

    @Test
    void shouldThrowTxnExceptionWhenDeleteMissingTxn() {
        when(txnRepo.existsById(14L)).thenReturn(false);

        assertThrows(TxnException.class, () -> service.deleteById(14L));
    }

    @Test
    void shouldDeleteTxnAndAdjustBalance() throws Exception {
        AccountHolder ah = createAh(4L, 200.0);
        Txn oldTxn = createTxn(15L, TxnType.CREDIT, 50.0, ah);

        when(txnRepo.existsById(15L)).thenReturn(true);
        when(txnRepo.findById(15L)).thenReturn(Optional.of(oldTxn));
        doNothing().when(txnRepo).deleteById(15L);

        service.deleteById(15L);

        assertEquals(150.0, ah.getCurrentBalance());
        verify(ahRepo).save(ah);
        verify(txnRepo).deleteById(15L);
    }

    @Test
    void shouldReturnZeroBalanceWhenAccountHolderNotFound() throws Exception {
        when(ahRepo.findById(7L)).thenReturn(Optional.empty());

        assertEquals(0.0, service.getBalance(7L));
    }

    @Test
    void shouldReturnCurrentBalanceWhenAccountHolderFound() throws Exception {
        AccountHolder ah = createAh(8L, 300.0);
        when(ahRepo.findById(8L)).thenReturn(Optional.of(ah));

        assertEquals(300.0, service.getBalance(8L));
    }

    private Txn createTxn(Long id, TxnType type, double amount, AccountHolder holder) {
        Txn txn = new Txn();
        txn.setTxnId(id);
        txn.setHeader("header");
        txn.setTxnDate(LocalDate.now());
        txn.setType(type);
        txn.setAmount(amount);
        txn.setHolder(holder);
        return txn;
    }

    private AccountHolder createAh(Long ahId, double currentBalance) {
        return new AccountHolder(ahId, currentBalance, new TreeSet<>());
    }
}
