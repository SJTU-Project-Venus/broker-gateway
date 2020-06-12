//package macoredroid.brokergateway.fix;
//
//import com.bevis.factory.MyMessageFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.io.DefaultResourceLoader;
//import org.springframework.core.io.ResourceLoader;
//import quickfix.*;
//import quickfix.mina.acceptor.DynamicAcceptorSessionProvider;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.InetSocketAddress;
//import java.util.*;
//
//import static quickfix.Acceptor.*;
//

//public class DefaultFixServer implements com.bevis.core.FixServer {
//
//
//    private static ThreadedSocketAcceptor acceptor = null;
//
//
//    private final Map<InetSocketAddress, List<DynamicAcceptorSessionProvider.TemplateMapping>> dynamicSessionMappings = new HashMap<>();
//
//    private Application fixAcceptor;
//
//    private volatile boolean isRunning;
//
//
//    public DefaultFixServer(Application fixAcceptor) {
//        this.fixAcceptor = fixAcceptor;
//    }
//
//
//    @Override
//    public synchronized void start() {
//        if (isRunning) {
//
//            return;
//        }
//
//
//        InputStream inputStream = null;
//        try {
//            ResourceLoader resourceLoader = new DefaultResourceLoader();
//            inputStream = resourceLoader.getResource(CONFIG_FILE).getInputStream();
//            SessionSettings settings = new SessionSettings(inputStream);
//            MessageStoreFactory storeFactory1 = new MemoryStoreFactory();
//            LogFactory logFactory = new FileLogFactory(settings);
//            MessageFactory messageFactory = new MyMessageFactory();
//            acceptor = new ThreadedSocketAcceptor(fixAcceptor, storeFactory1, settings, logFactory, messageFactory);
//            configureDynamicSessions(settings, fixAcceptor, storeFactory1, logFactory, messageFactory);
//            acceptor.start();
//            isRunning = true;
//
//        } catch (Exception e) {
//
//        } finally {
//            closeStream(inputStream);
//        }
//    }
//
//
//    @Override
//    public void stop() {
//        if (isRunning && null != acceptor) {
//            acceptor.stop(true);
//            isRunning = false;
//
//        }
//    }
//
//
//    @Override
//    public void stopWithSlightly() {
//        if (isRunning && null != acceptor) {
//            acceptor.stop();
//            isRunning = false;
//
//        }
//    }
//
//
//    @Override
//    public boolean isRunning() {
//        return isRunning;
//    }
//
//
//    @Override
//    public List<SessionID> getSessionIDs() {
//        if (null != acceptor) {
//            return acceptor.getSessions();
//        }
//
//        return null;
//    }
//
//
//    @Override
//    public List<Session> getSessions() {
//        if (null != acceptor) {
//            return acceptor.getManagedSessions();
//        }
//
//        return null;
//    }
//
//
//    @Override
//    public SessionSettings getSettings() {
//        if (null != acceptor) {
//            acceptor.getSettings();
//        }
//
//        return null;
//    }
//
//
//    private void closeStream(InputStream inputStream) {
//        if (null != inputStream) {
//            try {
//                inputStream.close();
//            } catch (IOException e) {
//
//            }
//        }
//    }
//
//
//    private void configureDynamicSessions(SessionSettings settings, Application application, MessageStoreFactory messageStoreFactory, LogFactory logFactory, MessageFactory messageFactory) throws ConfigError, FieldConvertError {
//        Iterator<SessionID> sectionIterator = settings.sectionIterator();
//        while (sectionIterator.hasNext()) {
//            SessionID sessionID = sectionIterator.next();
//            if (isSessionTemplate(settings, sessionID)) {
//                InetSocketAddress address = getAcceptorSocketAddress(settings, sessionID);
//                getMappings(address).add(new DynamicAcceptorSessionProvider.TemplateMapping(sessionID, sessionID));
//            }
//        }
//
//        for (Map.Entry<InetSocketAddress, List<DynamicAcceptorSessionProvider.TemplateMapping>> entry : dynamicSessionMappings.entrySet()) {
//            acceptor.setSessionProvider(entry.getKey(), new DynamicAcceptorSessionProvider(
//                    settings, entry.getValue(), application, messageStoreFactory, logFactory,
//                    messageFactory));
//        }
//    }
//
//
//    private List<DynamicAcceptorSessionProvider.TemplateMapping> getMappings(InetSocketAddress address) {
//        return dynamicSessionMappings.computeIfAbsent(address, k -> new ArrayList<>());
//    }
//
//
//    private InetSocketAddress getAcceptorSocketAddress(SessionSettings settings, SessionID sessionID) throws ConfigError, FieldConvertError {
//        String acceptorHost = "0.0.0.0";
//        if (settings.isSetting(sessionID, SETTING_SOCKET_ACCEPT_ADDRESS)) {
//            acceptorHost = settings.getString(sessionID, SETTING_SOCKET_ACCEPT_ADDRESS);
//        }
//
//        int acceptorPort = (int) settings.getLong(sessionID, SETTING_SOCKET_ACCEPT_PORT);
//        return new InetSocketAddress(acceptorHost, acceptorPort);
//    }
//
//
//    private boolean isSessionTemplate(SessionSettings settings, SessionID sessionID) throws ConfigError, FieldConvertError {
//        return settings.isSetting(sessionID, SETTING_ACCEPTOR_TEMPLATE) && settings.getBool(sessionID, SETTING_ACCEPTOR_TEMPLATE);
//    }
//}