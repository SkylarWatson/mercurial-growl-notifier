package org.watson.mercurial.growl.notifier;

class HgAdapter {
    String execute() {
        'cmd /c "cd C:\\RAD8WS && hg incoming -v"'.execute().text
    }
}