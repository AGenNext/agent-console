package io.agennext.console.kubernetes;

import io.fabric8.kubernetes.api.model.NamespaceBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class KubernetesWorkspaceService {
  private final KubernetesClient client;

  public KubernetesWorkspaceService(KubernetesClient client) {
    this.client = client;
  }

  public List<Map<String, Object>> listWorkspaces() {
    return client.namespaces().list().getItems().stream()
        .filter(namespace -> "agent-console".equals(namespace.getMetadata().getLabels().get("agennext.io/managed-by")))
        .map(namespace -> Map.<String, Object>of(
            "name", namespace.getMetadata().getName(),
            "namespace", namespace.getMetadata().getName(),
            "status", "active"))
        .toList();
  }

  public Map<String, Object> createWorkspace(String name) {
    client.namespaces().resource(new NamespaceBuilder()
        .withNewMetadata()
        .withName(name)
        .addToLabels("agennext.io/managed-by", "agent-console")
        .addToLabels("agennext.io/workspace", "true")
        .endMetadata()
        .build()).serverSideApply();

    return Map.of("accepted", true, "operation", "create-workspace", "namespace", name);
  }

  public Map<String, Object> deleteWorkspace(String name) {
    client.namespaces().withName(name).delete();
    return Map.of("accepted", true, "operation", "delete-workspace", "namespace", name);
  }
}
