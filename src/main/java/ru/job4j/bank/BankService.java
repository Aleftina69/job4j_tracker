package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает работу простейшей банковской системы.
 * @author TISHAKOVA ALEFTINA
 * @version 1.0
 */
public class BankService {
    /**
     * Коллекция содержит всех пользователей системы с привязанными к ним счетами.
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Mетод добавляет пользователя в систему.
     * Mетод putIfAbsent проверяет есть ли такой ключ в карте и если его нет,
     * то производит вставку данных.
     * @param user пользователь
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод позволяет удалить пользователя из системы.
     * @param passport номер паспорта пользователя
     */
    public void deleteUser(String passport) {
        users.remove(new User(passport, ""));
    }

    /**
     * Метод должен добавить новый счет к пользователю.
     * Метод находит пользователя по паспорту. Для этого нужно использовать метод findByPassport.
     * После этого мы получим список всех счетов пользователя и добавим новый счет к ним.
     * В этом методе должна быть проверка, что такого счета у пользователя еще нет.
     * @param passport номер паспорта пользователя
     * @param account счет пользователя
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * @param passport номер паспорта пользователя
     * @return метод ищет и возвращает пользователя по номеру паспорта. Если ничего не найдено-возвращает null.
     */
    public User findByPassport(String passport) {
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                return user;
            }
        }
        return null;
    }

    /**
     * @param passport номер паспорта пользователя
     * @param requisite реквизиты
     * @return метод ищет и возвращает счет пользователя по реквизитам.
     * Поскольку метод findByPassport может вернуть null, то прежде чем получать список аккаунтов,
     * требуется проверить что метод findByPassport вернул отличное от null значение.
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            for (Account account : accounts) {
                if (account.getRequisite().equals(requisite)) {
                    return account;
                }
            }
        }
        return null;
    }

    /**
     *
     * @param sourcePassport паспорт пользователя, со счета которого будет выполняться перевод
     * @param sourceRequisite реквизиты счета, с которого будет выполняться перевод
     * @param destinationPassport паспорт пользователя, на счета которого будет выполняться перевод
     * @param destinationRequisite реквизиты счета, на который будет выполняться перевод
     * @param amount колличество переводимых денег
     * @return  метод предназначен для перечисления денег с одного счёта на другой счёт
     * Если счёт не найден или не хватает денег на счёте с которого переводят, то метод должен вернуть false.
     */
    public boolean transferMoney(String sourcePassport, String sourceRequisite,
                                 String destinationPassport, String destinationRequisite,
                                 double amount) {
        boolean result = false;
        Account sourceAccount = findByRequisite(sourcePassport, sourceRequisite);
        Account destinationAccount = findByRequisite(destinationPassport, destinationRequisite);
        if (sourceAccount != null && destinationAccount != null && sourceAccount.getBalance() >= amount) {
            sourceAccount.setBalance(sourceAccount.getBalance() - amount);
            destinationAccount.setBalance(destinationAccount.getBalance() + amount);
            result = true;
        }
        return result;
    }

    /**
     * @param user пользователь
     * @return метод возвращает список всех счетов пользователя
     */
    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}
