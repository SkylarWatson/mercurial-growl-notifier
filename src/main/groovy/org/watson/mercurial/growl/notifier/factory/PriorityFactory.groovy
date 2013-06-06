package org.watson.mercurial.growl.notifier.factory;

import com.google.code.jgntp.GntpNotification

class PriorityFactory {
    GntpNotification.Priority findUsing(int numberOfCommits) {
        if(numberOfCommits <= 4) {
            GntpNotification.Priority.NORMAL
        } else if(numberOfCommits <= 9) {
            GntpNotification.Priority.HIGH
        } else if (numberOfCommits >= 10) {
            GntpNotification.Priority.HIGHEST
        }
    }
}