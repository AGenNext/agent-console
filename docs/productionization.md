# Productionization Plan

## Goal

Make Agent Console safe enough for internal production use on k3s.

## Production Principles

- Internal-only by default
- No public ingress until authentication is active
- Least privilege RBAC
- Namespace-per-workspace
- All writes audited
- Readiness and liveness probes required
- Resource requests and limits required
- Containers run as non-root
- Images pinned for production releases
- Secrets never stored in frontend state

## Immediate Production Baseline

1. Health endpoints from Spring Boot Actuator
2. Kubernetes readiness and liveness probes
3. CPU and memory requests/limits
4. Non-root container security context
5. ServiceAccount per install
6. Ingress disabled by default
7. GitHub Actions build checks
8. Helm chart linting
9. Clear internal install path

## Next Security Layer

1. Authentik OIDC
2. OPA admission policy
3. SurrealDB audit event store
4. Restricted RBAC by managed namespace label
5. Signed images with Cosign
6. SBOM generation
7. Dependabot or Renovate
8. Branch protection on main

## Runtime Boundary

Agent Console can operate only AGenNext-managed namespaces.

Managed namespaces must carry:

```yaml
agennext.io/managed-by: agent-console
agennext.io/workspace: "true"
```

## Deployment Rule

First production install must use port-forward or private network access.

Public exposure is blocked until auth, TLS, and audit are active.
