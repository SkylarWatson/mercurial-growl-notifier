package org.watson.mercurial.growl.notifier.factory

import org.watson.mercurial.growl.notifier.domain.ChangeSetDetail

class MessageFactory {
    String createFrom(ChangeSetDetail detail) {
        def message = "${detail.summary}\nFiles Modified: $detail.files"

        detail.commits == 1 ? message : (message += additionalSummary(detail))
    }

    private additionalSummary(ChangeSetDetail detail) {
        " (out of $detail.totalFiles files across $detail.commits commits)"
    }
}