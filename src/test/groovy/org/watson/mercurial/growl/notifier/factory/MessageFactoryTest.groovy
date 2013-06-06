package org.watson.mercurial.growl.notifier.factory

import org.junit.Before
import org.junit.Test
import org.watson.mercurial.growl.notifier.domain.ChangeSetDetail;

public class MessageFactoryTest {
    private MessageFactory factory;

    @Before
    void setUp() {
        factory = new MessageFactory()
    }

    @Test
    void createFrom_change_set_where_single_file_was_modified() {
        def detail = new ChangeSetDetail(commits: 1, summary: "commit message", files: 2, totalFiles: 2)
        
        assert factory.createFrom(detail) == "commit message\nFiles Modified: 2"
    }

    @Test
    void createFrom_change_set_where_multiple_files_were_modified() {
        def detail = new ChangeSetDetail(commits: 2, summary: "commit message", files: 2, totalFiles: 5)

        assert factory.createFrom(detail) == "commit message\nFiles Modified: 2 (out of 5 files across 2 commits)"
    }
}


/*
        def specialMessage = (detail.commits == 1) ? "" : "(out of $detail.totalFiles files across $detail.commits commits)"
                .text("$detail.summary\nFiles Modified: $detail.files $specialMessage").priority(priorityFactory.findUsing(detail.commits))
*/