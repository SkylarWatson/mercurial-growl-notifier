package org.watson.mercurial.growl.notifier;

import org.quartz.Job;
import org.quartz.JobExecutionContext

class IncomingPullJob implements Job {
    IncomingChangeDetector detector = new IncomingChangeDetector()
    IncomingPullNotifier notifier = new IncomingPullNotifier()

    void execute(JobExecutionContext context) {
        notifier.notifiy(detector.detect())
    }
}