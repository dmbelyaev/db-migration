SCRIPT_DIR="$(dirname "$(readlink -f "$0")")"
kind create cluster --config $SCRIPT_DIR/../k8s/kind-config.yaml
