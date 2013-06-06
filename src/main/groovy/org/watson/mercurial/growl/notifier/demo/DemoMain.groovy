package org.watson.mercurial.growl.notifier.demo

import org.watson.mercurial.growl.notifier.IncomingPullNotifier
import org.watson.mercurial.growl.notifier.domain.ChangeSetDetail

class DemoMain {
    static void main(args) {
        new IncomingPullNotifier().notifiy(new ChangeSetDetail(
                name: "Skylar Watson",
                summary: "my commit message",
                totalFiles: 25,
                commits: 5,
                files: 10))
    }
}