package org.watson.mercurial.growl.notifier;

import org.watson.mercurial.growl.notifier.factory.MessageFactory
import com.google.code.jgntp.*
import org.watson.mercurial.growl.notifier.factory.PriorityFactory
import org.watson.mercurial.growl.notifier.domain.ChangeSetDetail

class IncomingPullNotifier {
    PriorityFactory priorityFactory = new PriorityFactory()
    MessageFactory messageFactory = new MessageFactory()
    ImageLoader imageLoader = new ImageLoader()

    void notifiy(ChangeSetDetail detail) throws Exception {
        def image = imageLoader.loadUsing("logo.png")

        def applicationInfo = Gntp.appInfo("Incoming Pull").icon(image).build();
        def notificationInfo = Gntp.notificationInfo(applicationInfo, "Mercurial Notification").icon(image).build();
        def client = Gntp.client(applicationInfo).forHost("localhost").build();

        client.register();
        client.notify(Gntp.notification(notificationInfo, detail.name)
                .text(messageFactory.createFrom(detail))
                .priority(priorityFactory.findUsing(detail.commits))
                .icon(image)
                .header(Gntp.CUSTOM_HEADER_PREFIX, "?").build());
    }
}