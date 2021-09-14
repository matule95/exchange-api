package mz.co.checkmob.api.notification;

public interface Notifiable <N extends Notifiable<N>>{
  /**
   * Sends the given notification
   * @param notification the notification instance
   * @return the notifiable
   */
  @SuppressWarnings("unchecked")
  default  <P extends Notification> N sendNotification(P notification) {
    NotificationsContextHolder.getChannel().send(this, notification);
    return (N) this;
  }

  String getEmail();

  static Notifiable of(String email) {
    return new GenericNotifiable(email);
  }
}
