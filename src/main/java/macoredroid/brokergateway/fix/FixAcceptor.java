//package macoredroid.brokergateway.fix;
//
//import com.bevis.enums.BizExceptionCodeEnum;
//import com.bevis.messages.MyMessage;
//import com.bevis.model.FixUserBO;
//import com.bevis.model.LogonBO;
//import com.google.common.collect.Maps;
//import exceptions.BizException;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Component;
//import quickfix.*;
//import quickfix.Message;
//import quickfix.MessageCracker;
//import quickfix.field.*;
//import quickfix.fix44.*;
//
//import java.time.LocalDateTime;
//import java.util.Map;
//import java.util.UUID;

//@Component
//@Slf4j
//public class FixAcceptor extends MessageCracker implements Application {
//
//    private static Map<String, FixUserBO> FIX_API_USER_MAP = Maps.newHashMap();
//
//    static {
//        FIX_API_USER_MAP.put("7d8f8655-ce10-428d-b10a-b9dcc25b352d",
//                new FixUserBO("7d8f8655-ce10-428d-b10a-b9dcc25b352d", "d741bc45-d53a-4343-b97b-0f5f179ce8fe", "U001", true));
//    }
//
//
//
//
//
//
//    @Handler
//    public void onNewOrderMessage(NewOrderSingle message, SessionID sessionID) throws FieldNotFound {
//
//        String clOrdId = message.getClOrdID().getValue();
//        String symbol = message.getSymbol().getValue();
//
//        ExecutionReport report = new ExecutionReport();
//        report.set(new ClOrdID(clOrdId));
//        report.set(new OrderID(UUID.randomUUID().toString()));
//        report.set(new ExecType(ExecType.NEW));
//        report.set(new OrdStatus(OrdStatus.NEW));
//        report.set(new TransactTime(LocalDateTime.now()));
//        report.set(new ExecID("dddd"));
//        report.set(new Side(Side.BUY));
//        report.set(new LeavesQty(1));
//        report.set(new CumQty(1));
//        report.set(new AvgPx(1.00D));
//        report.set(new Symbol(symbol));
//        report.set(new Text("jjj"));
//
//        try {
//            boolean result = Session.sendToTarget(report, sessionID);
//
//        } catch (SessionNotFound sessionNotFound) {
//
//        }
//    }
//
//
//
//
//
//    @Override
//    protected void onMessage(Message message, SessionID sessionID) {
//        try {
//            String msgType = message.getHeader().getString(35);
//
//        } catch (FieldNotFound e) {
//
//        }
//    }
//
//
//    @Override
//    public void fromApp(Message message, SessionID sessionId) {
//        String log = String.format(",sessionId=%s, message=%s", sessionId, message);
//        String accessKey = StringUtils.EMPTY;
//        try {
//            //1.校验
//            accessKey = message.getHeader().getString(SenderCompID.FIELD);
//            FixUserBO fixUserBO = FIX_API_USER_MAP.get(accessKey);
//            if (null == fixUserBO) {
//                throw new BizException(BizExceptionCodeEnum.API_FIX_USER_NOT_EXIST);
//            }
//
//            if (!fixUserBO.getIsValid()) {
//                throw new BizException(BizExceptionCodeEnum.API_FIX_USER_NO_PERMISSIONS);
//            }
//
//            //2.设置userNO到header
//            message.getHeader().setField(new SenderSubID(String.valueOf(fixUserBO.getUserNo())));
//            // 3.业务操作
//            crack(message, sessionId);
//        } catch (BizException e) {
//            Session session = Session.lookupSession(sessionId);
//            LOGGER.error(String.format("fix user valid failed:accesskey=%s,msg=%s", accessKey, e.getDesc()), e);
//            session.logout(String.valueOf(e.getDesc()));
//            session.sentLogout();
//        } catch (UnsupportedMessageType unsupportedMessageType) {
//            LOGGER.error("application message error: unsupported message type" + log, unsupportedMessageType);
//        } catch (FieldNotFound fieldNotFound) {
//            LOGGER.error("application message error:" + fieldNotFound.getMessage() + log, fieldNotFound);
//        } catch (IncorrectTagValue incorrectTagValue) {
//            LOGGER.error("application message error:" + incorrectTagValue.getMessage() + log, incorrectTagValue);
//        }
//    }
//
//}