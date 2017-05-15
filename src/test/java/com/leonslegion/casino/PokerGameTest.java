package com.leonslegion.casino;

import com.leonslegion.casino.Abstracts.CasinoGame;
import com.leonslegion.casino.Abstracts.Player;
import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.CardGamePackage.PokerGame;
import com.leonslegion.casino.CardGamePackage.PokerPlayer;
import org.junit.*;
import org.mockito.Mockito;

/**
 * Created by danzygmund-felt on 5/13/17.
 */
public class PokerGameTest {

    Account account1 = Mockito.mock(Account.class);
    Account account2 = Mockito.mock(Account.class);
    Account account3 = Mockito.mock(Account.class);

    PokerPlayer player1 = Mockito.mock(PokerPlayer.class);
    PokerPlayer player2 = Mockito.mock(PokerPlayer.class);
    PokerPlayer player3 = Mockito.mock(PokerPlayer.class);

    CasinoGame game = new PokerGame();

    @Test
    public void initialDealTest() {

    }

}
