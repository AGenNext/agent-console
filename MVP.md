# MVP Build

## MVP Goal

Ship a usable internal Agent Console that runs on k3s and gives operators a simple hPanel-style entry point.

## MVP Scope

Included:

- Dashboard UI
- API health endpoint
- Workspace list/create/delete contract
- Agent list/create/delete/restart contract
- Containerfiles for web and API
- Helm chart for k3s install
- Internal-only access by default
- GitHub Actions build workflow

Not included yet:

- Public ingress
- Authentik OIDC
- SurrealDB audit store
- OPA gates
- Real pod log streaming
- Full Kubernetes deployment creation service
- Image signing

## MVP Install

```bash
helm upgrade --install agent-console ./charts/agent-console --namespace agent-console --create-namespace
```

## MVP Access

```bash
kubectl port-forward svc/agent-console-web 3000:3000 -n agent-console
```

Open:

```text
http://127.0.0.1:3000
```

## MVP API

```bash
kubectl port-forward svc/agent-console-api 8080:8080 -n agent-console
curl http://127.0.0.1:8080/api/dashboard
```

## MVP Done Criteria

- API builds with Maven
- Web builds with npm
- Helm templates render
- Chart installs into k3s
- Web loads over port-forward
- API returns dashboard JSON
