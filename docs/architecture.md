# Agent Console Architecture

## Goal

Build an internal hPanel-style console for AGenNext operations.

The console hides Kubernetes from daily operators while keeping Kubernetes as the source of truth.

## Runtime Shape

```text
Browser
  ↓
Next.js Console
  ↓
Spring Boot API
  ↓
Kubernetes Java Client
  ↓
k3s API Server
  ↓
Deployments / Services / Jobs / Secrets / ConfigMaps
```

## Repository Boundary

This repository owns the console.

`Agent-Platform` owns platform contracts, architecture, and standards.

## Why Not Crossplane First

Crossplane is useful once AGenNext becomes a full platform API across many internal or external teams.

For the first internal loop, use the direct path:

```text
Console API → Kubernetes API
```

Add Crossplane later only for external infrastructure composition such as DNS, object storage, cloud databases, and provider resources.

## Core Objects

### Workspace

Maps to a Kubernetes namespace.

```text
Workspace → Namespace
```

### Agent

Maps to a Kubernetes deployment unit.

```text
Agent → Deployment + Service + ConfigMap + Secret refs
```

### Secret

Maps to a Kubernetes secret.

```text
Secret → Kubernetes Secret
```

### Logs

Read from pod logs.

```text
Logs → Pod logs
```

## API Surface

```http
GET    /api/dashboard
GET    /api/workspaces
POST   /api/workspaces
DELETE /api/workspaces/{name}
GET    /api/agents
POST   /api/agents
DELETE /api/agents/{workspace}/{name}
POST   /api/agents/{workspace}/{name}/restart
GET    /api/logs/{workspace}/{pod}
GET    /api/secrets
POST   /api/secrets
```

## First Deployment Rule

No public ingress by default.

Use port-forward until auth and policy are active.
