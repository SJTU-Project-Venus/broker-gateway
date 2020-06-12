//package macoredroid.brokergateway.fix;
//
//import quickfix.Session;
//import quickfix.SessionID;
//import quickfix.SessionSettings;
//
//import java.util.List;
//
//public interface FixServer {
//
//    String CONFIG_FILE = "classpath:fix-server.cfg";
//
//    void start();
//
//    void stop();
//
//
//
//    void stopWithSlightly();
//
//
//    boolean isRunning();
//
//
//    List<SessionID> getSessionIDs();
//
//
//    List<Session> getSessions();
//
//
//    SessionSettings getSettings();
//}