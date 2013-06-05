package org.watson.mercurial.growl.notifier;

class HgAdapter {
    public static final String REPOSITORY_LOCATION

    String execute() {
        'cmd /c "cd ' + REPOSITORY_LOCATION + ' && hg incoming -v"'.execute().text
    }
}