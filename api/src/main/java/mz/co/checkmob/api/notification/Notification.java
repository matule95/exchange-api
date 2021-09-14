package mz.co.checkmob.api.notification;

import mz.co.checkmob.api.notification.message.MailMessage;

public interface Notification {
  default MailMessage toMailMessage(Notifiable notifiable) {
    return null;
  }
}
