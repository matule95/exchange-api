package mz.co.checkmob.api.notification;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class GenericNotifiable implements Notifiable<GenericNotifiable> {
  @Getter
  private final String email;
}
