package desafio.backend.picpay.domain.client.service;

import desafio.backend.picpay.domain.transfer.Transfer;
import desafio.backend.picpay.infra.client.Notification.NotificationClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationClient notificationClient;

    public NotificationService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    public void sendNotification(Transfer transfer) {
        try{
            logger.info("Sending notification");
            var resp = notificationClient.sendNotification(transfer);

            if(resp.getStatusCode().isError()){
                logger.error("Error while sending notification, status: " + resp.getStatusCode());
            }
        }catch(Exception e){
            logger.error("Error while sending notification", e);
        }
    }
}
