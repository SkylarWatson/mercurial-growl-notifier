package org.watson.mercurial.growl.notifier.factory;

import com.google.code.jgntp.GntpNotification

class PriorityFactory {
    GntpNotification.Priority findUsing(int numberOfCommits) {
        [(0..4)    :    GntpNotification.Priority.NORMAL,
         (5..9)    :    GntpNotification.Priority.HIGH,
         (10..999) :    GntpNotification.Priority.HIGHEST,
        ].find {k, v ->  k.contains(numberOfCommits)}.value
    }
}