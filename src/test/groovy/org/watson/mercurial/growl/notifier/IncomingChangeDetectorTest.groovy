package org.watson.mercurial.growl.notifier;

import org.gmock.GMockTestCase
import org.junit.Before
import org.junit.Test;

class IncomingChangeDetectorTest extends GMockTestCase {
    private IncomingChangeDetector detector
    private HgAdapter mockAdapter

    @Before
    void setUp() {
        detector = new IncomingChangeDetector()
        mockAdapter = mock(HgAdapter)

        detector.setAdapter(mockAdapter)
    }

    @Test
    void testDetect_no_changes() {
        mockAdapter.execute().returns("""
                comparing with https://server/repo/Project
                searching for changes
                no changes found
        """)

        play {
            assert !detector.detect()
        }
    }

    @Test
    void testDetect_has_changes_files_description_is_on_same_line() {
        mockAdapter.execute().returns("""comparing with https://server/repo/Project
            searching for changes
            changeset:   1111:26b1cae3b260
            tag:         tag
            user:        Skylar Watson
            date:        Tue May 21 10:59:35 2013 -0500
            files:       Project/src/com/watson/foo.java Project/src/com/watson/baz.java Project/src/com/watson/bar.java
            description: worked on ...

        """)

        play {
            def result = detector.detect()
            assert result.name == "Skylar Watson"
            assert result.summary == "worked on ..."
            assert result.commits == 1
            assert result.files == 3
        }
    }

    @Test
    void testDetect_has_changes_files_description_is_on_new_line() {
        mockAdapter.execute().returns("""comparing with https://server/repo/Project
            searching for changes
            changeset:   1111:26b1cae3b260
            tag:         tag
            user:        Skylar Watson
            date:        Tue May 21 10:59:35 2013 -0500
            files:       Project/src/com/watson/foo.java Project/src/com/watson/baz.java Project/src/com/watson/bar.java
            description:
            worked on ...

        """)

        play {
            def result = detector.detect()
            assert result.name == "Skylar Watson"
            assert result.summary == "worked on ..."
            assert result.commits == 1
            assert result.files == 3
        }
    }

    @Test
    void testDetect_has_changes_multiple() {
        mockAdapter.execute().returns("""comparing with https://server/repo/Project
            searching for changes
            changeset:   1111:26b1cae3b260
            user:        Skylar Watson
            date:        Tue May 21 10:59:35 2013 -0500
            files:       Project/src/com/watson/foo.java Project/src/com/watson/baz.java Project/src/com/watson/bar.java
            description:
            worked on ...


            changeset:   1111:26b1cae3b260
            user:        Skylar Watson
            date:        Tue May 21 10:59:35 2013 -0500
            files:       Project/src/com/watson/foo.java Project/src/com/watson/baz.java
            description:
            also ...
        """)

        play {
            def result = detector.detect()
            assert result.name == "Skylar Watson"
            assert result.summary == "also ..."
            assert result.commits == 2
            assert result.totalFiles == 5
            assert result.files == 2
        }
    }
}