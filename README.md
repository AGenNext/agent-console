# AGenNext Agent Console

Internal hPanel-style operations console for AGenNext.

This repository owns the console experience only:

- Next.js internal UI
- Spring Boot console API
- Kubernetes client integration
- Helm chart
- Console-specific CI/CD
- Console releases

`Agent-Platform` remains the architecture and platform-contract repository.

## Control Plane

```text
Browser
  ↓
Agent Console Web
  ↓
Agent Console API
  ↓
Kubernetes API
  ↓
k3s
  ↓
Agent workloads
```

## Initial Scope

Build only the internal operator loop first:

- Dashboard
- Workspaces
- Agents
- Deployments
- Logs
- Secrets
- Settings

Skip for now:

- Crossplane
- Billing
- Marketplace
- Public reseller hosting features
- Email hosting

## Stack

```text
Frontend  : Next.js + Tailwind
Backend   : Spring Boot
Runtime   : k3s / Kubernetes API
Deploy    : Helm
Database  : optional SurrealDB later
Auth      : local admin first, Authentik later
```

## Layout

```text
apps/
├── web/
└── api/
charts/
└── agent-console/
docs/
examples/
```

## Deploy

```bash
helm upgrade --install agent-console ./charts/agent-console \
  --namespace agent-console \
  --create-namespace
```

Then access internally:

```bash
kubectl port-forward svc/agent-console-web 3000:3000 -n agent-console
```

## Security Defaults

- Internal network only
- No public ingress by default
- Namespace-per-workspace
- Least-privilege service account
- Secrets mounted from Kubernetes Secrets
- Audit every create/update/delete action
