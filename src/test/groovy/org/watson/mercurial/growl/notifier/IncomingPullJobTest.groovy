package org.watson.mercurial.growl.notifier;

import org.gmock.GMockTestCase
import org.junit.Before
import org.junit.Test
import org.watson.mercurial.growl.notifier.domain.ChangeSetDetail

class IncomingPullJobTest extends GMockTestCase {
    private IncomingPullJob job

    private IncomingChangeDetector mockDetector
    private IncomingPullNotifier mockNotifier

    @Before
    void setUp() {
        job = new IncomingPullJob()
        
        mockDetector = mock(IncomingChangeDetector)
        mockNotifier = mock(IncomingPullNotifier)
        
        job.notifier = mockNotifier
        job.detector = mockDetector
    }

    @Test
    void testRun() {
        def details = new ChangeSetDetail()

        mockDetector.detect().returns details
        mockNotifier.notifiy(details)

        play {
            job.execute(null)
        }
    }
}