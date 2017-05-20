package org.mjt.appleman.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import org.mjt.appleman.Appleman;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
				return new GwtApplicationConfiguration(640, 480);
                //return new GwtApplicationConfiguration(480, 320);
				//return new GwtApplicationConfiguration(1024, 768);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new Appleman();
        }
}