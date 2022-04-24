package com.capgemini.cappay.repository;

import com.capgemini.cappay.model.Account;
import com.capgemini.cappay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account getAccountByUserId(User userId);

    @Query(value = "SELECT * FROM public.account a " +
                   "WHERE a.account_status ilike 'ACTIVE'" +
                   "AND to_char(a.creation_date, 'MM') = ?1", nativeQuery = true)
    List<Account> getActiveAccountsByMonth(String month);
}
