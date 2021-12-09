package  mz.co.checkmob.api.core.file.configuration;

import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.core.file.FileSystem;
import mz.co.checkmob.api.core.file.LocalFileSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
public class FileSystemConfiguration {
  private final Environment env;

  @Bean
  FileSystem fileSystem() {
    String storagePath = env.getProperty("app.storage.path", "storage");
    return LocalFileSystem.of(storagePath);
  }
}
