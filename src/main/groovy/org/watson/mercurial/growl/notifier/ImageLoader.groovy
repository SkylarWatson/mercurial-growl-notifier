package org.watson.mercurial.growl.notifier

import java.awt.image.RenderedImage
import javax.imageio.ImageIO

class ImageLoader {
    RenderedImage loadUsing(String name) {
        ImageIO.read(getClass().getClassLoader().getResourceAsStream(name));
    }
}
