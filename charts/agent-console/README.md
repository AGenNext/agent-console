# Agent Console Helm Chart

This chart installs the internal AGenNext Agent Console into a Kubernetes cluster.

Default mode is internal only.

Recommended first access path:

kubectl port-forward svc/agent-console-web 3000:3000 -n agent-console

Public ingress should stay disabled until authentication and policy gates are configured.
