package org.watson.mercurial.growl.notifier;

import com.google.code.jgntp.GntpNotification
import org.junit.Before
import org.junit.Test;

class PriorityFactoryTest {
    private PriorityFactory factory

    @Before
    void setUp() {
        factory = new PriorityFactory()
    }

    @Test
    void findUsing_normal_priority() {
        assert factory.findUsing(1) == GntpNotification.Priority.NORMAL
        assert factory.findUsing(2) == GntpNotification.Priority.NORMAL
        assert factory.findUsing(3) == GntpNotification.Priority.NORMAL
        assert factory.findUsing(4) == GntpNotification.Priority.NORMAL
    }

    @Test
    void findUsing_high_priority() {
        assert factory.findUsing(5) == GntpNotification.Priority.HIGH
        assert factory.findUsing(6) == GntpNotification.Priority.HIGH
        assert factory.findUsing(7) == GntpNotification.Priority.HIGH
        assert factory.findUsing(8) == GntpNotification.Priority.HIGH
        assert factory.findUsing(9) == GntpNotification.Priority.HIGH
    }

    @Test
    void findUsing_highest_priority() {
        assert factory.findUsing(10) == GntpNotification.Priority.HIGHEST
        assert factory.findUsing(11) == GntpNotification.Priority.HIGHEST
        assert factory.findUsing(12) == GntpNotification.Priority.HIGHEST
        assert factory.findUsing(13) == GntpNotification.Priority.HIGHEST
        assert factory.findUsing(14) == GntpNotification.Priority.HIGHEST
    }
}