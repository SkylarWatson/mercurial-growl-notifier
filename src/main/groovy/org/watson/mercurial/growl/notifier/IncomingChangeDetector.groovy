package org.watson.mercurial.growl.notifier

import org.watson.mercurial.growl.notifier.domain.ChangeSetDetail;

class IncomingChangeDetector {
    HgAdapter adapter = new HgAdapter()

    ChangeSetDetail detect() {
        def result = adapter.execute()

        def name = (result =~ /user: (.+)/)
        def summary = (result =~ /description:(|\n)(.+)/)
        def files = (result =~ /files: (.+)/)

        if(name && summary) {
            def commits = result.split("\n").findAll { it.contains("user:") }.size()
            def fileCount = files[files.count-1][1].trim().split(" ").size()
            def totalFiles = files.collect({ it[1].trim().split(" ").size() }).sum();

            new ChangeSetDetail(
                    commits: commits,
                    name: name[name.count-1][1].trim(),
                    summary: summary[summary.count-1][2].trim(),
                    files: fileCount,
                    totalFiles: totalFiles
            )
        } else {
            null
        }
    }
}